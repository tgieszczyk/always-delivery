package com.ahold.alwaysdeliver.location.payload;

import com.ahold.alwaysdeliver.api.json.JsonObjectIdDeserializer;
import com.ahold.alwaysdeliver.api.json.JsonObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Location {
    @Id
    @JsonSerialize(using = JsonObjectIdSerializer.class)
    @JsonDeserialize(using = JsonObjectIdDeserializer.class)
    private ObjectId id;
    @Field
    private String zone;
    @Field
    private String address;
    @Field
    private String place;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
