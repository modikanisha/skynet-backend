package com.skynet.commons.models;

import com.skynet.commons.constants.EntityConstants;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.skynet.commons.constants.EntityConstants.STATUS;


@Data
@Entity
@Table(name = Network.ENTITY_NAME)
public class Network implements Serializable {

    public static final String NETWORK_SEQ_ID = "network_seq_id";
    public static final String ENTITY_NAME = "network";
    public static final String NETWORK_NAME = "network_name";
    public static final String EMAIL_ID = "email_id";
    public static final String CONTACT_NUMBER = "contact_number";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = NETWORK_SEQ_ID)
    private Integer networkSeqId;

    @Column(name = NETWORK_NAME)
    private String networkName;

    @Column(name = STATUS)
    private String status;

    @Column(name = CONTACT_NUMBER)
    private String contactNumber;

    @Column(name = EMAIL_ID)
    private String emailId;

    @OneToOne
    @JoinColumn(name=Address.COLUMN_ADDRESS_ID)
    private Address address;

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
