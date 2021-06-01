package com.skynet.employeemgmt.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skynet.commons.constants.ErrorConstants;
import com.skynet.commons.dto.response.SettingCommonListResponse;
import com.skynet.commons.exceptionHandlers.SkyNetException;
import com.skynet.commons.models.Permission;
import com.skynet.commons.models.Role;
import com.skynet.commons.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import static com.skynet.commons.enums.Status.*;
import static com.skynet.commons.utils.jsonParseUtil.getJsonString;


@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public static ObjectMapper mapper = new ObjectMapper();


    @Override
    public Role createRole(Role role) {
        role.setStatus(ACTIVE.getName());
        String memberString;
        try {
            memberString = getJsonString("/content/permissions.json");
            Set<Permission> permissionList = mapper.readValue(memberString, new TypeReference<>() {
            });
            role.setPermission(permissionList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        roleRepository.save(role);
        role.setCreatedAt(role.getCreatedAtDate().getTime());
        role.setUpdatedAt(role.getUpdatedAtDate().getTime());
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        Role dbRole = roleRepository.findByRoleSeqId(role.getRoleSeqId());
        if(dbRole == null)
        throw new SkyNetException(ErrorConstants.ErrorCode.ROLE_ERROR,ErrorConstants.SubErrorCode.ROLE_ID_NOT_FOUND,ErrorConstants.ErrorMessage.ROLE_ID_NOT_FOUND);

        BeanUtils.copyProperties(role, dbRole);

        dbRole.setStatus(getStatus(role.getStatus()) != null ? getStatus(role.getStatus()).getName() : INACTIVE.getName());
        dbRole.setUpdatedAtDate(new Timestamp(System.currentTimeMillis()));

        roleRepository.save(dbRole);
        dbRole.setCreatedAt(role.getCreatedAtDate().getTime());
        dbRole.setUpdatedAt(role.getUpdatedAtDate().getTime());
        dbRole.getPermission().stream();
        return dbRole;
    }

    @Override
    public SettingCommonListResponse list() {
       List<Role> roles = roleRepository.findAllByOrderByUpdatedAtDateDesc();
        SettingCommonListResponse settingCommonListResponse = new SettingCommonListResponse();

        SettingCommonListResponse.MainTableConfigs  mainTableConfigs = new SettingCommonListResponse.MainTableConfigs();

        mainTableConfigs.setSortableColumns(new String[]{"roleName", "status"});
        mainTableConfigs.setFilterableColumns(new String[]{"roleName","description", "status"});
        mainTableConfigs.setDateColumns(new String[]{"createdAt","updatedAt"});
        mainTableConfigs.setBooleanColumns(new String[]{});

        SettingCommonListResponse.Title mainTableTitle = new SettingCommonListResponse.Title();
        mainTableTitle.setName("Role Name");
        mainTableTitle.setDescription("Description");
        mainTableTitle.setStatus("Status");
        mainTableTitle.setCreatedAt("Created At");
        mainTableTitle.setCreatedBy("Created By");
        mainTableTitle.setUpdatedAt("Updated At");
        mainTableTitle.setUpdatedBy("Updated By");
        mainTableConfigs.setTitles(mainTableTitle);
        settingCommonListResponse.setMainTableConfigs(mainTableConfigs);

        SettingCommonListResponse.SubTableConfigs  subTableConfigs = new SettingCommonListResponse.SubTableConfigs();

        subTableConfigs.setSortableColumns(new String[]{"moduleName"});
        subTableConfigs.setFilterableColumns(new String[]{"moduleName"});
        subTableConfigs.setBooleanColumns(new String[]{"read","create","update","delete"});
        subTableConfigs.setDateColumns(new String[]{});

        SettingCommonListResponse.SubTitle subTitle = new SettingCommonListResponse.SubTitle();
        subTitle.setModuleName("Module Name");
        subTitle.setRead("Read");
        subTitle.setCreate("Create");
        subTitle.setUpdate("Update");
        subTitle.setDelete("Delete");
        subTableConfigs.setTitles(subTitle);
        settingCommonListResponse.setSubTableConfigs(subTableConfigs);
        if(roles != null && !roles.isEmpty()){
            for(Role role:roles){
                if(role.getPermission() != null && !role.getPermission().isEmpty()){
                    for (Permission permission: role.getPermission()){
                        permission.setRole(null);
                    }
                }
                role.setCreatedAt(role.getCreatedAtDate().getTime());
                role.setUpdatedAt(role.getUpdatedAtDate().getTime());
                role.getPermission().stream();
            }
        }
        settingCommonListResponse.setRecords(roles);
        return settingCommonListResponse;
    }
}
