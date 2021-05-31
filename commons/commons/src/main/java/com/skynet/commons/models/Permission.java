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
@Table(name = Permission.ENTITY_NAME)
public class Permission implements Serializable{

    public static final String ENTITY_NAME = "permission";
    public static final String MODULE_KEY = "module_key";
    public static final String MODULE_NAME = "module_name";
    public static final String READ = "read";
    public static final String CREATE = "create";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Integer id;

    @Column(name = MODULE_KEY)
    private String moduleKey;

    @Column(name = MODULE_NAME)
    private String moduleName;

    @Column(name = CREATE)
    private boolean create;

    @Column(name = UPDATE)
    private boolean update;

    @Column(name = READ)
    private boolean read;

    @Column(name = DELETE)
    private boolean delete;


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