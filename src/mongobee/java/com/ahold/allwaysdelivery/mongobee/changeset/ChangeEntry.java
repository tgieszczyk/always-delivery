package com.ahold.allwaysdelivery.mongobee.changeset;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;

/**
 * Entry in the changes collection log
 * {@link com.ahold.allwaysdelivery.mongobee.Mongobee#DEFAULT_CHANGE_LOG_LOCK_POLL_RATE} Type:
 * entity class.
 *
 * @author lstolowski
 * @since 27/07/2014
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = ChangeEntry.DEFAULT_CHANGELOG_COLLECTION_NAME)
public class ChangeEntry {

    public static final String DEFAULT_CHANGELOG_COLLECTION_NAME = "dbchangelog";
    public static final String KEY_CHANGEID = "changeId";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_ID = "id";

    @Id
    private ObjectId id;
    @Field
    @Indexed
    private String changeId;
    @Field
    @Indexed
    private String author;
    @Field
    private LocalDateTime timestamp;
    @Field
    private String changeLogClass;
    @Field
    private String changeSetMethodName;

    public ChangeEntry(String changeId, String author, LocalDateTime timestamp, String changeLogClass,
                       String changeSetMethodName) {
        this.changeId = changeId;
        this.author = author;
        this.timestamp = timestamp;
        this.changeLogClass = changeLogClass;
        this.changeSetMethodName = changeSetMethodName;
    }

    public Query buildSearchQuery() {
        return new Query().addCriteria(Criteria.where(KEY_CHANGEID).is(this.changeId).and(KEY_AUTHOR).is(this.author));
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "ChangeEntry{" +
                "id=" + id +
                ", changeId='" + changeId + '\'' +
                ", author='" + author + '\'' +
                ", timestamp=" + timestamp +
                ", changeLogClass='" + changeLogClass + '\'' +
                ", changeSetMethodName='" + changeSetMethodName + '\'' +
                '}';
    }

    public String getChangeId() {
        return this.changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getChangeLogClass() {
        return this.changeLogClass;
    }

    public void setChangeLogClass(String changeLogClass) {
        this.changeLogClass = changeLogClass;
    }

    public String getChangeSetMethodName() {
        return this.changeSetMethodName;
    }

    public void setChangeSetMethodName(String changeSetMethodName) {
        this.changeSetMethodName = changeSetMethodName;
    }

}
