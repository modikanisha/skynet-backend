package com.skynet.clientmgmt.dto.request;

import lombok.Data;

@Data
public class NetworkAddRequest {

    private Integer networkSeqId;

    private String networkName;

    private String status;

    private String contactNumber;

    private String emailId;

    private String address;

    private String createdBy;

    private String updatedBy;

    private Long createdAt;

    private Long updatedAt;
}
