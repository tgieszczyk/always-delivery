package com.ahold.allwaysdelivery.mongobee.dao;

import com.ahold.allwaysdelivery.locker.service.LockerService;
import com.ahold.allwaysdelivery.mongobee.changeset.ChangeEntry;
import com.ahold.allwaysdelivery.mongobee.exception.MongobeeConnectionException;
import com.ahold.allwaysdelivery.mongobee.exception.MongobeeLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author lstolowski
 * @since 27/07/2014
 */
public class ChangeEntryDao {
	private static final String SYSTEM_LOCKER_KEY = "databasechangelog-key";
	private static final Logger logger = LoggerFactory.getLogger("Mongobee dao");

	private boolean waitForLock;
	private long changeLogLockWaitTime;
	private long changeLogLockPollRate;
	private boolean throwExceptionIfCannotObtainLock;

	private LockerService lockerService;
	private MongoTemplate mongoTemplate;
	private ChangeEntryRepository changeEntryRepository;

	public ChangeEntryDao( boolean waitForLock, long changeLogLockWaitTime,
			long changeLogLockPollRate, boolean throwExceptionIfCannotObtainLock) {
		this.waitForLock = waitForLock;
		this.changeLogLockWaitTime = changeLogLockWaitTime;
		this.changeLogLockPollRate = changeLogLockPollRate;
		this.throwExceptionIfCannotObtainLock = throwExceptionIfCannotObtainLock;
	}

	public void setLockerService(LockerService lockerService) {
		this.lockerService = lockerService;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void setChangeEntryRepository(ChangeEntryRepository changeEntryRepository) {
		this.changeEntryRepository = changeEntryRepository;
	}

	/**
	 * Try to acquire process lock
	 *
	 * @return true if successfully acquired, false otherwise
	 * @throws MongobeeConnectionException
	 *             exception
	 * @throws com.ahold.allwaysdelivery.mongobee.exception.MongobeeLockException
	 *             exception
	 */
	public boolean acquireProcessLock() throws MongobeeConnectionException, MongobeeLockException {
		if (!lockerService.isLocked(SYSTEM_LOCKER_KEY)) {
			lockerService.lock(SYSTEM_LOCKER_KEY);
			return lockerService.isActiveKey(SYSTEM_LOCKER_KEY);
		}

		return false;
	}

	public void releaseProcessLock() throws MongobeeConnectionException {
		lockerService.releaseLock(SYSTEM_LOCKER_KEY);
	}

	public boolean isNewChange(ChangeEntry changeEntry) throws MongobeeConnectionException {
		return mongoTemplate.find(changeEntry.buildSearchQuery(), ChangeEntry.class).size() == 0;
	}

	public void save(ChangeEntry changeEntry) throws MongobeeConnectionException {
		changeEntryRepository.save(changeEntry);
	}

	public boolean isWaitForLock() {
		return waitForLock;
	}

	public void setWaitForLock(boolean waitForLock) {
		this.waitForLock = waitForLock;
	}

	public long getChangeLogLockWaitTime() {
		return changeLogLockWaitTime;
	}

	public void setChangeLogLockWaitTime(long changeLogLockWaitTime) {
		this.changeLogLockWaitTime = changeLogLockWaitTime;
	}

	public long getChangeLogLockPollRate() {
		return changeLogLockPollRate;
	}

	public void setChangeLogLockPollRate(long changeLogLockPollRate) {
		this.changeLogLockPollRate = changeLogLockPollRate;
	}

	public boolean isThrowExceptionIfCannotObtainLock() {
		return throwExceptionIfCannotObtainLock;
	}

	public void setThrowExceptionIfCannotObtainLock(boolean throwExceptionIfCannotObtainLock) {
		this.throwExceptionIfCannotObtainLock = throwExceptionIfCannotObtainLock;
	}

}
