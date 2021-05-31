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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import static com.skynet.commons.enums.Status.ACTIVE;

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
            if(permissionList != null && !permissionList.isEmpty()){
               //TODO
            }
            role.setPermission(permissionList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        roleRepository.save(role);
        return role;
    }

    public static String getJsonString(String path) throws JsonParseException, JsonMappingException, IOException {

        InputStream in = TypeReference.class.getResourceAsStream(path);
        JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
        return mapper.writeValueAsString(jsonNode);
    }
}
