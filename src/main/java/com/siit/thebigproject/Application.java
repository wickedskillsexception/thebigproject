package com.siit.thebigproject;

import com.siit.thebigproject.domain.Employee;
import com.siit.thebigproject.domain.Gender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.siit.thebigproject.service.EmployeeService;
import com.siit.thebigproject.service.ValidationException;

import java.util.Date;

public class Application {

    public static void main(String[] args) throws ValidationException {


        /*xml configuration example */
        ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");

        EmployeeService employeeService = appContext.getBean("employeeService", EmployeeService.class);
        System.out.println(employeeService);

        EmployeeService employeeService1 = appContext.getBean("employeeService", EmployeeService.class);
        System.out.println(employeeService1);


        Employee em = new Employee();
        em.setBirthDate(new Date(70, 1, 1));
        em.setEmploymentDate(new Date());
        em.setGender(Gender.FEMALE);
        em.setJobTitle("Ana");
        em.setFirstName("Babanan");
        em.setLastName("Gogu");
        employeeService.save(em);

        System.out.println(employeeService.listAll().iterator().next());

/*        ActivityService activityService = appContext.getBean("activityService", ActivityService.class);

        Activity activity = new Activity();
        activity.setStart(LocalDate.parse("2019-01-23"));
        activity.setEnd(LocalDate.parse("2019-01-25"));
        activity.setType(WORK);

        activityService.save(activity);

        System.out.println(activityService.listAll().iterator().next());

        System.out.println(activityService.calculateHours(activity));

        ApplicationContext apContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);*/
    }
}
