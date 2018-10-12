package com.ahold.allwaysdelivery.locker.service;

import com.ahold.allwaysdelivery.locker.payload.Locker;
import com.ahold.allwaysdelivery.locker.repository.LockerRepository;
import com.ahold.allwaysdelivery.utils.GenericBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
@Service
public class LockerService {
	private static final Pageable PAGEABLE = new PageRequest(0, 1, Direction.DESC, Locker._CREATED_AT);
	private String nodeId = UUID.randomUUID().toString();

	@Value("${takto.locker.maxLiveInSeconds ?: 300}")
	private int maxLiveInSeconds;
	@Autowired
	private LockerRepository lockerRepository;

	/**
	 * Lock given object
	 * 
	 * @param key
	 * @return
	 */
	public boolean isLocked(String key) {
		LocalDateTime expirationTimestamp = LocalDateTime.now().minusSeconds(maxLiveInSeconds);
		return lockerRepository.isKeyExists(key, expirationTimestamp);
	}

	public void lock(String key) {
		Locker locker = GenericBuilder.of(Locker::new).with(Locker::setCreatedAt, LocalDateTime.now())
				.with(Locker::setKey, key).with(Locker::setNodeId, nodeId).build();

		lockerRepository.save(locker);
	}

	public void releaseLock(String key) {
		LocalDateTime expirationTimestamp = LocalDateTime.now().minusSeconds(maxLiveInSeconds);
		lockerRepository.releaseLocker(key, nodeId, expirationTimestamp);
	}

	public boolean isActiveKey(String key) {
		Locker locker = lockerRepository.findByKeyOrderByCreatedAtDesc(key);

		if (locker != null ) {
			return locker.getNodeId().equals(nodeId);
		}
		return true;
	}

	void setMaxLiveInSeconds(int maxLiveInSeconds) {
		this.maxLiveInSeconds = maxLiveInSeconds;
	}
}
