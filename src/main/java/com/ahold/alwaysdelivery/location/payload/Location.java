package com.ahold.alwaysdelivery.location.payload;

import com.ahold.alwaysdelivery.api.json.JsonObjectIdDeserializer;
import com.ahold.alwaysdelivery.api.json.JsonObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Location {
    @Id
    @JsonSerialize(using = JsonObjectIdSerializer.class)
    @JsonDeserialize(using = JsonObjectIdDeserializer.class)
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

}
