package com.skynet.commons.models;

import com.skynet.commons.constants.EntityConstants;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.skynet.commons.constants.EntityConstants.STATUS;

@Getter
@Setter
@Entity
@Table(name = Permission.ENTITY_NAME)
public class Permission implements Serializable {

    public static final String PERMISSION_SEQ_ID = "permission_seq_id";
    public static final String ENTITY_NAME = "permission";
    public static final String MODULE_KEY = "module_key";
    public static final String MODULE_NAME = "module_name";
    public static final String READ_FG = "read_fg";
    public static final String CREATE_FG = "create_fg";
    public static final String UPDATE_FG = "update_fg";
    public static final String DELETE_FG = "delete_fg";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PERMISSION_SEQ_ID)
    private Integer permissionSeqId;

    @Column(name = MODULE_KEY)
    private String moduleKey;

    @Column(name = MODULE_NAME)
    private String moduleName;

    @Column(name = CREATE_FG)
    private boolean create;

    @Column(name = UPDATE_FG)
    private boolean update;

    @Column(name = READ_FG)
    private boolean read;

    @Column(name = DELETE_FG)
    private boolean delete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Role.ROLE_SEQ_ID)
    private Role role;
}