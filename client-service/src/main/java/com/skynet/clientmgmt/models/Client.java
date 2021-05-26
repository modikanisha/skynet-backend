package com.skynet.clientmgmt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = Client.ENTITY_NAME)
public class Client implements Serializable{

    public static final String ENTITY_NAME = "client";
    public static final String EMPLOYEE_ID = "employee_id";
    public static final String NAME = "name";
    public static final String USER_NAME = "user_name";
    public static final String PASS_CODE = "pass_code";
    public static final String ROLE_ID = "role_id";
    public static final String STATUS_ID = "status_id";
    public static final String CONTACT_NO = "contact_no";
    public static final String EMAIL_ID = "email_id";
    public static final String ADDRESS = "address";
    public static final String DESCRIPTION = "description";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = EMPLOYEE_ID,length = 100)
    private String employeeId;

    @Column(name = NAME)
    private String name;

    @Column(name = USER_NAME)
    private String userName;

    @Column(name = PASS_CODE)
    private String passCode;

    //TODO
    @Column(name = ROLE_ID)
    private String roleId;

    //TODO
    @Column(name = STATUS_ID)
    private String statusId;

    @Column(name = CONTACT_NO)
    private String contactNo;

    @Column(name = EMAIL_ID)
    private String emailId;

    @Column(name = ADDRESS, length = 1000)
    private String address;

    @Column(name = DESCRIPTION)
    private String description;

//    @Column(name = EntityConstants.COLUMN_CREATED_AT)
//    @CreationTimestamp
//    private Timestamp createdAt;
//
//    @Column(name = EntityConstants.COLUMN_CREATED_BY)
//    private String createdBy;
//
//    @Column(name = EntityConstants.COLUMN_UPDATED_AT)
//    @UpdateTimestamp
//    private Timestamp updatedAt;
//
//    @Column(name = EntityConstants.COLUMN_UPDATED_BY)
//    private String updatedBy;
}
