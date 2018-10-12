package com.ahold.alwaysdeliver.user.payload;

import com.ahold.alwaysdeliver.api.json.JsonObjectIdDeserializer;
import com.ahold.alwaysdeliver.api.json.JsonObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Objects;

@Document
public class User {
    @Id
    @JsonSerialize(using = JsonObjectIdSerializer.class)
    @JsonDeserialize(using = JsonObjectIdDeserializer.class)
    private ObjectId id;
    @Field
    private String name;
    @Field
    private String surname;
    @Field
    private LocalDate birthDate;
    @Field
    private boolean approved;
    @Field
    private String phone;
    @Field
    private String email;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User student = (User) o;
        return approved == student.approved &&
                Objects.equals(getId(), student.getId()) &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(birthDate, student.birthDate) &&
                Objects.equals(phone, student.phone) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, surname, birthDate, approved, phone, email);
    }
}
