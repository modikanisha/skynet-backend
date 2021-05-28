package com.skynet.initdata.relational;

import com.fasterxml.jackson.core.type.TypeReference;
import com.skynet.commons.models.Employee;
import com.skynet.initdata.changelog.AbstractChangeLog;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class CarrierChangeLogsRelational extends AbstractChangeLog {

    @PersistenceContext
    @Autowired
    private final EntityManager entityManager;

    @RelationalChangeSet(author = "Kanisha.Modi", id = "cs-01")
    public void addEmployeeList() throws IOException {
        String memberString;
        try {
            memberString = AbstractChangeLog.getJsonString("/content/employee.json");
            List<Employee> employeeList = AbstractChangeLog.mapper.readValue(memberString, new TypeReference<>() {
            });
            bulkUpdate(employeeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Transactional
    public <T> Collection<T> bulkUpdate(Collection<T> entities) {
        int batchSize = 50;
        final List<T> savedEntities = new ArrayList<T>(entities.size());
        int i = 0;
        for (T t : entities) {
            entityManager.merge(t);
            i++;
            if (i % batchSize == 0) {
                // Flush a batch of inserts and release memory.
                entityManager.flush();
                entityManager.clear();
            }
        }
        return savedEntities;
    }
}
