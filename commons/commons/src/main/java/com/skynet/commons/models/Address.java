package com.skynet.commons.models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = Address.ENTITY_NAME)
public class Address {

    public static final String ENTITY_NAME = "address";
    public static final String COLUMN_ADDRESS_ID = "address_seq_id";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_COUNTRY_ID = "country_id";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE_ID = "state_id";
    public static final String COLUMN_COUNTY_ID = "county_id";
    public static final String COLUMN_ZIP_CODE = "zipcode";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ADDRESS_ID)
    private Integer addressSeqId;

    @Column(name = COLUMN_ADDRESS,length = 1000)
    private String address;

    @Column(name = COLUMN_COUNTRY_ID)
    private String countryId;

    @Column(name = COLUMN_CITY)
    private String city;

    @Column(name = COLUMN_STATE_ID)
    private String stateId;

    @Column(name = COLUMN_COUNTY_ID)
    private String countyId;

    @Column(name = COLUMN_ZIP_CODE)
    private String zipcode;
}
