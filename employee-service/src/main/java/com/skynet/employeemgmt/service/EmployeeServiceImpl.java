package com.skynet.employeemgmt.service;

import com.skynet.commons.constants.ErrorConstants;
import com.skynet.commons.exceptionHandlers.SkyNetException;
import com.skynet.commons.models.Employee;
import com.skynet.commons.models.Role;
import com.skynet.commons.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import static com.skynet.commons.enums.Status.*;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setDescription("Kanisha static");
        employee.setStatus(ACTIVE.getName());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Page<Employee> list(Pageable pageable) {
     return  employeeRepository.findAll(pageable);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if(employee == null){
            throw new SkyNetException(ErrorConstants.ErrorCode.EMPLOYEE_ERROR,ErrorConstants.SubErrorCode.EMPLOYEE_NOT_FOUND,ErrorConstants.ErrorMessage.REQ_EMPLOYEE_NOT_FOUND);

        }
        Employee dbEmployee = employeeRepository.findByEmployeeSeqId(employee.getEmployeeSeqId());
        if(dbEmployee == null)
            throw new SkyNetException(ErrorConstants.ErrorCode.EMPLOYEE_ERROR,ErrorConstants.SubErrorCode.EMPLOYEE_NOT_FOUND,ErrorConstants.ErrorMessage.EMPLOYEE_NOT_FOUND);

        BeanUtils.copyProperties(employee, dbEmployee);

        dbEmployee.setStatus(getStatus(employee.getStatus()) != null ? getStatus(employee.getStatus()).getName() : INACTIVE.getName());
        employee.setUpdatedAtDate(new Timestamp(System.currentTimeMillis()));

        employeeRepository.save(dbEmployee);
        dbEmployee.setCreatedAt(dbEmployee.getCreatedAtDate().getTime());
        dbEmployee.setUpdatedAt(dbEmployee.getUpdatedAtDate().getTime());

        return dbEmployee;
    }
}
