package com.batch.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class PersonMixin {
    @JsonCreator
    public PersonMixin(@JsonProperty("id") Long id,
                       @JsonProperty("name") String name,
                       @JsonProperty("last_name") String lastname,
                       @JsonProperty("age") int age,
                       @JsonProperty("insertion_date") String insertionDate) {}
}
