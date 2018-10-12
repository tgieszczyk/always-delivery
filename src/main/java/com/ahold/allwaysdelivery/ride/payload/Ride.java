package com.ahold.allwaysdelivery.ride.payload;

import com.ahold.allwaysdelivery.api.json.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Document
public class Ride {
    @Id
    @JsonSerialize(using = JsonObjectIdSerializer.class)
    @JsonDeserialize(using = JsonObjectIdDeserializer.class)
    private ObjectId id;
    @Field
    @JsonSerialize(using = JsonObjectIdSerializer.class)
    @JsonDeserialize(using = JsonObjectIdDeserializer.class)
    private ObjectId locationId;
    @Field
    @JsonSerialize(using = JsonObjectIdSerializer.class)
    @JsonDeserialize(using = JsonObjectIdDeserializer.class)
    private ObjectId driverId;

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
    @Field
    private RideReservationStatus status = RideReservationStatus.PENDING;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

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

    public ObjectId getLocationId() {
        return locationId;
    }

    public void setLocationId(ObjectId locationId) {
        this.locationId = locationId;
    }

    public ObjectId getDriverId() {
        return driverId;
    }

    public void setDriverId(ObjectId driverId) {
        this.driverId = driverId;
    }

    public RideReservationStatus getStatus() {
        return status;
    }

    public void setStatus(RideReservationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ride)) return false;
        Ride ride = (Ride) o;
        return isBonus() == ride.isBonus() &&
                Objects.equals(getId(), ride.getId()) &&
                Objects.equals(getLocationId(), ride.getLocationId()) &&
                Objects.equals(getDriverId(), ride.getDriverId()) &&
                Objects.equals(getTitle(), ride.getTitle()) &&
                Objects.equals(getDate(), ride.getDate()) &&
                Objects.equals(getFrom(), ride.getFrom()) &&
                Objects.equals(getTill(), ride.getTill()) &&
                Objects.equals(getFee(), ride.getFee()) &&
                getStatus() == ride.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLocationId(), getDriverId(), getTitle(), getDate(), getFrom(), getTill(), isBonus(), getFee(), getStatus());
    }
}
