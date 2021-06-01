package com.skynet.employeemgmt.service;

import com.skynet.commons.models.Role;
import com.skynet.employeemgmt.dto.response.SettingCommonListResponse;

public interface RoleService {

    Role createRole(Role role);
    SettingCommonListResponse list();

}
