package com.skynet.initdata.changelog.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cloudyrock.mongock.ChangeLog;
import com.skynet.initdata.changelog.AbstractChangeLog;
import lombok.extern.slf4j.Slf4j;

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