package com.skynet.initdata.changelog.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.opencsv.CSVReader;
import com.skynet.commons.models.Employee;
import com.skynet.initdata.changelog.AbstractChangeLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@JsonIgnoreProperties
@ChangeLog
@Slf4j
public class ClientChangeLogs extends AbstractChangeLog {

    private static ObjectMapper mapper = new ObjectMapper();


//    @ChangeSet(order = "01", author = "Kanisha.Modi", id = "employee-01")
//    public void employee(MongoTemplate template) throws IOException {
//        String resourceListString = getJsonString("/content/employee.json");
//        template.findAllAndRemove(new Query(), Employee.class); //clear old roles
//        List<Employee> list = mapper.readValue(resourceListString, new TypeReference<>() {});
//        for(Employee temp : list){
//            temp.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//        }
//        template.insertAll(list);
//    }
}