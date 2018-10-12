package com.ahold.allwaysdelivery.locker.payload;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
@Document(collection = Locker.COLLECTION_NAME)
public class Locker {
	public static final String COLLECTION_NAME = "dblocker";

	public static final String _KEY = "key";
	public static final String _NODE_ID = "nodeId";
	public static final String _CREATED_AT = "createdAt";

	@Id
	private ObjectId id;
	@Field
	private LocalDateTime createdAt;
	@Field
	private String key;
	@Field
	private String nodeId;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

}
