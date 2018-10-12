package com.ahold.allwaysdelivery.mongobee;

import com.ahold.allwaysdelivery.locker.service.LockerService;
import com.ahold.allwaysdelivery.mongobee.changeset.ChangeEntry;
import com.ahold.allwaysdelivery.mongobee.dao.ChangeEntryDao;
import com.ahold.allwaysdelivery.mongobee.dao.ChangeEntryRepository;
import com.ahold.allwaysdelivery.mongobee.exception.MongobeeChangeSetException;
import com.ahold.allwaysdelivery.mongobee.exception.MongobeeConnectionException;
import com.ahold.allwaysdelivery.mongobee.exception.MongobeeException;
import com.ahold.allwaysdelivery.mongobee.utils.ChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Mongobee runner
 *
 * @author lstolowski
 * @since 26/07/2014
 */
@Component
public class Mongobee {
	private static final Logger logger = LoggerFactory.getLogger(Mongobee.class);
	private static final boolean DEFAULT_WAIT_FOR_LOCK = false;
	private static final long DEFAULT_CHANGE_LOG_LOCK_WAIT_TIME = 5L;
	private static final long DEFAULT_CHANGE_LOG_LOCK_POLL_RATE = 10L;
	private static final boolean DEFAULT_THROW_EXCEPTION_IF_CANNOT_OBTAIN_LOCK = false;

	private ChangeEntryDao dao;

	private boolean enabled = true;
	@Value("${mongobee.scanpackage}")
	private String changeLogsScanPackage;
	@Autowired
	private Environment springEnvironment;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private LockerService lockerService;
	@Autowired
	private ChangeEntryRepository changeEntryRepository;

	/**
	 * For Spring users: executing mongobee after bean is created in the Spring
	 * context
	 *
	 * @throws Exception
	 *             exception
	 */
	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		this.dao = new ChangeEntryDao(DEFAULT_WAIT_FOR_LOCK, DEFAULT_CHANGE_LOG_LOCK_WAIT_TIME,
				DEFAULT_CHANGE_LOG_LOCK_POLL_RATE, DEFAULT_THROW_EXCEPTION_IF_CANNOT_OBTAIN_LOCK);
		dao.setLockerService(lockerService);
		dao.setMongoTemplate(mongoTemplate);
		dao.setChangeEntryRepository(changeEntryRepository);
	}

	/**
	 * Executing migration
	 *
	 * @throws MongobeeException
	 *             exception
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void execute() throws MongobeeException {
		if (!isEnabled()) {
			logger.info("Mongobee is disabled. Exiting.");
			return;
		}

		if (!dao.acquireProcessLock()) {
			System.out.println("acquireProcessLock -> false");
			logger.info("Mongobee did not acquire process lock. Exiting.");
			return;
		}
		System.out.println("acquireProcessLock -> true");

		logger.info("Mongobee acquired process lock, starting the data migration sequence..");

		try {
			executeMigration();
		} finally {
			logger.info("Mongobee is releasing process lock.");
			dao.releaseProcessLock();
		}

		logger.info("Mongobee has finished his job.");
	}

	private void executeMigration() throws MongobeeConnectionException, MongobeeException {

		ChangeService service = new ChangeService(changeLogsScanPackage, springEnvironment);

		for (Class<?> changelogClass : service.fetchChangeLogs()) {

			Object changelogInstance = null;
			try {
				changelogInstance = changelogClass.getConstructor().newInstance();
				List<Method> changesetMethods = service.fetchChangeSets(changelogInstance.getClass());

				for (Method changesetMethod : changesetMethods) {
					ChangeEntry changeEntry = service.createChangeEntry(changesetMethod);

					try {
						if (dao.isNewChange(changeEntry)) {
							executeChangeSetMethod(changesetMethod, changelogInstance);
							dao.save(changeEntry);
							logger.info(changeEntry + " applied");
						} else if (service.isRunAlwaysChangeSet(changesetMethod)) {
							executeChangeSetMethod(changesetMethod, changelogInstance);
							logger.info(changeEntry + " reapplied");
						} else {
							logger.info(changeEntry + " passed over");
						}
					} catch (MongobeeChangeSetException e) {
						logger.error(e.getMessage());
					}
				}
			} catch (NoSuchMethodException e) {
				throw new MongobeeException(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new MongobeeException(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				Throwable targetException = e.getTargetException();
				throw new MongobeeException(targetException.getMessage(), e);
			} catch (InstantiationException e) {
				throw new MongobeeException(e.getMessage(), e);
			}

		}
	}

	private Object executeChangeSetMethod(Method changeSetMethod, Object changeLogInstance)
			throws IllegalAccessException, InvocationTargetException, MongobeeChangeSetException {
		if (changeSetMethod.getParameterTypes().length == 1
				&& changeSetMethod.getParameterTypes()[0].equals(MongoTemplate.class)) {
			logger.debug("method with MongoTemplate argument");

			return changeSetMethod.invoke(changeLogInstance, mongoTemplate);
		} else if (changeSetMethod.getParameterTypes().length == 2
				&& changeSetMethod.getParameterTypes()[0].equals(MongoTemplate.class)
				&& changeSetMethod.getParameterTypes()[1].equals(Environment.class)) {
			logger.debug("method with MongoTemplate and environment arguments");

			return changeSetMethod.invoke(changeLogInstance, mongoTemplate, springEnvironment);
		} else if (changeSetMethod.getParameterTypes().length == 0) {
			logger.debug("method with no params");

			return changeSetMethod.invoke(changeLogInstance);
		} else {
			throw new MongobeeChangeSetException("ChangeSet method " + changeSetMethod.getName()
					+ " has wrong arguments list. Please see docs for more info!");
		}
	}

	/**
	 * Package name where @ChangeLog-annotated classes are kept.
	 *
	 * @param changeLogsScanPackage
	 *            package where your changelogs are
	 * @return Mongobee object for fluent interface
	 */
	public Mongobee setChangeLogsScanPackage(String changeLogsScanPackage) {
		this.changeLogsScanPackage = changeLogsScanPackage;
		return this;
	}

	/**
	 * @return true if Mongobee runner is enabled and able to run, otherwise false
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Feature which enables/disables Mongobee runner execution
	 *
	 * @param enabled
	 *            MOngobee will run only if this option is set to true
	 * @return Mongobee object for fluent interface
	 */
	public Mongobee setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	/**
	 * Feature which enables/disables waiting for lock if it's already obtained
	 *
	 * @param waitForLock
	 *            Mongobee will be waiting for lock if it's already obtained if this
	 *            option is set to true
	 * @return Mongobee object for fluent interface
	 */
	public Mongobee setWaitForLock(boolean waitForLock) {
		this.dao.setWaitForLock(waitForLock);
		return this;
	}

	/**
	 * Waiting time for acquiring lock if waitForLock is true
	 *
	 * @param changeLogLockWaitTime
	 *            Waiting time in minutes for acquiring lock
	 * @return Mongobee object for fluent interface
	 */
	public Mongobee setChangeLogLockWaitTime(long changeLogLockWaitTime) {
		this.dao.setChangeLogLockWaitTime(changeLogLockWaitTime);
		return this;
	}

	/**
	 * Poll rate for acquiring lock if waitForLock is true
	 *
	 * @param changeLogLockPollRate
	 *            Poll rate in seconds for acquiring lock
	 * @return Mongobee object for fluent interface
	 */
	public Mongobee setChangeLogLockPollRate(long changeLogLockPollRate) {
		this.dao.setChangeLogLockPollRate(changeLogLockPollRate);
		return this;
	}

	/**
	 * Feature which enables/disables throwing MongobeeLockException if Mongobee can
	 * not obtain lock
	 *
	 * @param throwExceptionIfCannotObtainLock
	 *            Mongobee will throw MongobeeLockException if lock can not be
	 *            obtained
	 * @return Mongobee object for fluent interface
	 */
	public Mongobee setThrowExceptionIfCannotObtainLock(boolean throwExceptionIfCannotObtainLock) {
		this.dao.setThrowExceptionIfCannotObtainLock(throwExceptionIfCannotObtainLock);
		return this;
	}

	/**
	 * Set Environment object for Spring Profiles (@Profile) integration
	 *
	 * @param environment
	 *            org.springframework.core.env.Environment object to inject
	 * @return Mongobee object for fluent interface
	 */
	public Mongobee setSpringEnvironment(Environment environment) {
		this.springEnvironment = environment;
		return this;
	}

	/**
	 * Sets pre-configured {@link MongoTemplate} instance to use by the Mongobee
	 *
	 * @param mongoTemplate
	 *            instance of the {@link MongoTemplate}
	 * @return Mongobee object for fluent interface
	 */
	public Mongobee setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		return this;
	}
}
