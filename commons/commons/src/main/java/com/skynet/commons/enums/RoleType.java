package com.skynet.commons.enums;

import java.util.Arrays;

public enum RoleType {
    CARRIER_ANALYST("Carrier Analyst"),
    CARRIER_SUPERVISOR("Carrier Supervisor"),
    POLICY_ANALYST("Policy Analyst"),
    POLICY_SUPERVISOR("Policy Supervisor"),
    BILLING_ANALYST("Billing Analyst"),
    BILLING_SUPERVISOR("Billing Supervisor"),
    RECOVERY_CASE_ANALYST("Recovery Case Analyst"),
    RECOVERY_CASE_SUPERVISOR("Recovery Case Supervisor"),
    MASS_CHANGE_REQUEST_ANALYST("Mass Change Request Analyst"),
    MASS_CHANGE_REQUEST_SUPERVISOR("Mass Change Request Supervisor"),
    HIPP_INFORMATION_ANALYST("HIPP Information Analyst"),
    HIPP_INFORMATION_SUPERVISOR("HIPP Information Supervisor"),
    MSQ_ANALYST("MSQ Analyst"),
    MSQ_SUPERVISOR("MSQ Supervisor"),
    INTERNAL_USER("Internal User"),
    CLAIM_ANALYST("Claim Analyst"),
    SUPER_ADMIN("Super Admin");

    private String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public RoleType getByLabel(String name) {
        return Arrays.stream(values())
                .filter(value -> value.getName().equalsIgnoreCase(name))
                .findAny().orElse(null);
    }
}
