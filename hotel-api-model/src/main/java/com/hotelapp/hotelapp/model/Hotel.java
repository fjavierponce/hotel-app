package com.hotelapp.hotelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {

    private UUID id;

    @NotNull(message = "Hotel name must not be null")
    private String name;

    @Min(value = 1, message = "Category must be a number between 1 and 5")
    @Max(value = 5, message = "Category must be a number between 1 and 5")
    private int category;

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
}
