package com.hotelapp.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {

    private UUID id;
    private String name;
    private int category;

    public Hotel() {}

    public Hotel(Builder builder) {
        this.name = builder.name;
        this.category = builder.category;
        this.id = builder.id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Hotel hotel = (Hotel) obj;
        return this.category == hotel.category && this.id.equals(hotel.id) && name.equals(hotel.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id.hashCode();
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.category;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Hotel ID=%s, NAME=%s (%d stars)", this.id, this.name, this.category);
    }

    public static class Builder {
        private UUID id;
        private final String name;
        private int category;

        public Builder(String name) {
            this.name = name;
        }

        public Builder category(int category) {
            this.category = category;
            return this;
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Hotel build() {
            return new Hotel(this);
        }

    }
}
