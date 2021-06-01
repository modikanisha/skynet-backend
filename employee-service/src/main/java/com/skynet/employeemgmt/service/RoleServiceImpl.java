package com.skynet.employeemgmt.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skynet.commons.models.Employee;
import com.skynet.commons.models.Permission;
import com.skynet.commons.models.Role;
import com.skynet.commons.repository.RoleRepository;
import com.skynet.employeemgmt.dto.response.SettingCommonListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import static com.skynet.commons.enums.Status.ACTIVE;
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
        return role;
    }
    @Override
    public SettingCommonListResponse list() {
       List<Role> roles = roleRepository.findAllByStatus(ACTIVE.getName());
        SettingCommonListResponse settingCommonListResponse = new SettingCommonListResponse();

        SettingCommonListResponse.MainTableConfigs  mainTableConfigs = new SettingCommonListResponse.MainTableConfigs();

        mainTableConfigs.setSortableColumns(new String[]{"roleName", "status"});
        mainTableConfigs.setFilterableColumns(new String[]{"roleName","description", "status"});
        mainTableConfigs.setDateColumns(new String[]{"createdAt","updatedAt"});
        mainTableConfigs.setBooleanColumns(new String[]{});

        SettingCommonListResponse.Title mainTableTitle = new SettingCommonListResponse.Title();
        mainTableTitle.setRoleName("Role Name");
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
            }
        }
        settingCommonListResponse.setRecords(roles);
        return settingCommonListResponse;
    }
}
