package com.siit.thebigproject;

import com.siit.thebigproject.dao.EmployeeDAO;
import com.siit.thebigproject.dao.sql.IMEmployeeDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.siit.thebigproject.service.EmployeeService;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public EmployeeService employeeService() {
		EmployeeService ems = new EmployeeService();
		
		ems.setDao(employeeDAO());
		return ems;
	}
	
	@Bean
	public EmployeeDAO employeeDAO() {
		return new IMEmployeeDAO();
	}
}
