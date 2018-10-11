package com.ahold.allwaysdelivery.ride.payload;

import com.ahold.allwaysdelivery.api.json.*;
import com.ahold.allwaysdelivery.location.payload.Location;
import com.ahold.allwaysdelivery.user.payload.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalTime;

@Document
public class Ride {
    @Id
    @JsonSerialize(using = JsonObjectIdSerializer.class)
    @JsonDeserialize(using = JsonObjectIdDeserializer.class)
    private ObjectId id;
//    @Field
//    @JsonSerialize(using = JsonObjectIdSerializer.class)
//    @JsonDeserialize(using = JsonObjectIdDeserializer.class)
//    private ObjectId driverId;
//    @Field
//    @JsonSerialize(using = JsonObjectIdSerializer.class)
//    @JsonDeserialize(using = JsonObjectIdDeserializer.class)
//    private ObjectId locationId;

    @Field
    private String title;

    @Field
    @JsonSerialize(using = JsonLocalDateSerializer.class)
    @JsonDeserialize(using = JsonLocalDateDeserializer.class)
    private LocalDate date;
    @Field
    @JsonSerialize(using = JsonLocalTimeSerializer.class)
    @JsonDeserialize(using = JsonLocalTimeDeserializer.class)
    private LocalTime from;
    @Field
    @JsonSerialize(using = JsonLocalTimeSerializer.class)
    @JsonDeserialize(using = JsonLocalTimeDeserializer.class)
    private LocalTime till;
    @Field
    private boolean bonus;
    @Field
    private Integer fee;
    @DBRef
    private Location location;
    @DBRef
    private User driver;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

//    public ObjectId getDriverId() {
//        return driverId;
//    }
//
//    public void setDriverId(ObjectId driverId) {
//        this.driverId = driverId;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTill() {
        return till;
    }

    public void setTill(LocalTime till) {
        this.till = till;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}
