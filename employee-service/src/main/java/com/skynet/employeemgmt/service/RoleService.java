package com.skynet.employeemgmt.service;

import com.skynet.commons.dto.response.SettingCommonListResponse;
import com.skynet.commons.models.Role;

public interface RoleService {

    Role createRole(Role role);
    Role updateRole(Role role);
    SettingCommonListResponse list();

}
