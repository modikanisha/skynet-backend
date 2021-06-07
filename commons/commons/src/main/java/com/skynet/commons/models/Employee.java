package com.skynet.commons.models;

import com.skynet.commons.constants.EntityConstants;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.skynet.commons.constants.EntityConstants.STATUS;


@Data
@Entity
@Table(name = Employee.ENTITY_NAME)
public class Employee implements Serializable {

    public static final String EMPLOYEE_SEQ_ID = "id";
    public static final String ENTITY_NAME = "employee";
    public static final String EMPLOYEE_ID = "employee_id";
    public static final String NAME = "name";
    public static final String USER_NAME = "user_name";
    public static final String PASS_CODE = "pass_code";
    public static final String ROLE = "role";
    public static final String CONTACT_NUMBER = "contact_number";
    public static final String EMAIL_ID = "email_id";
    public static final String ADDRESS = "address";
    public static final String DESCRIPTION = "description";
    public static final String LAST_LOGGED_ID = "last_logged_id";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EMPLOYEE_SEQ_ID)
    private Integer employeeSeqId;

    @Column(name = EMPLOYEE_ID, length = 100)
    private String employeeId;

    @Column(name = NAME)
    private String name;

    @Column(name = USER_NAME)
    private String userName;

    @Column(name = PASS_CODE)
    private String passCode;

    @Column(name = STATUS)
    private String status;

    @Column(name = CONTACT_NUMBER)
    private String contactNumber;

    @Column(name = EMAIL_ID)
    private String emailId;

    @Column(name = ADDRESS, length = 1000)
    private String address;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = LAST_LOGGED_ID)
    @CreationTimestamp
    private Timestamp lastLoggedIn;

    @OneToOne
    @JoinColumn(name = ROLE)
    private Role role;

    @Column(name = EntityConstants.COLUMN_CREATED_AT)
    @CreationTimestamp
    private Timestamp createdAtDate;

    @Column(name = EntityConstants.COLUMN_CREATED_BY)
    private String createdBy;

    @Column(name = EntityConstants.COLUMN_UPDATED_AT)
    @UpdateTimestamp
    private Timestamp updatedAtDate;

    @Column(name = EntityConstants.COLUMN_UPDATED_BY)
    private String updatedBy;
    @Transient
    private Long createdAt;
    @Transient
    private Long updatedAt;
}
