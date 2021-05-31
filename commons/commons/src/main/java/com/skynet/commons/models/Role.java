package com.skynet.commons.models;

import com.skynet.commons.constants.EntityConstants;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.skynet.commons.constants.EntityConstants.ID;
import static com.skynet.commons.constants.EntityConstants.STATUS;


@Data
@Entity
@Table(name = Role.ENTITY_NAME)
public class Role implements Serializable{

    public static final String ENTITY_NAME = "role";
    public static final String ROLE_NAME = "role_name";
    public static final String ROLE_KEY = "role_key";
    public static final String DESCRIPTION = "description";
    public static final String PERMISSION = "permission";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Integer id;

    @Column(name = ROLE_NAME)
    private String roleName;

    @Column(name = ROLE_KEY)
    private String roleKey;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = PERMISSION)
    private String permission;

    //TODO
    @Column(name = STATUS)
    private String status;

    @Column(name = EntityConstants.COLUMN_CREATED_AT)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = EntityConstants.COLUMN_CREATED_BY)
    private String createdBy;

    @Column(name = EntityConstants.COLUMN_UPDATED_AT)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = EntityConstants.COLUMN_UPDATED_BY)
    private String updatedBy;
}
