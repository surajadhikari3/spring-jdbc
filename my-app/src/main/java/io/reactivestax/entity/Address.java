package io.reactivestax.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private int id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
}