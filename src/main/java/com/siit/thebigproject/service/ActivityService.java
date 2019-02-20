package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.ActivityDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.siit.thebigproject.domain.Activity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ActivityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private ActivityDAO acDao;

    public ActivityService() {
    }

    public Collection<Activity> listAll() {
        return acDao.getAll();
    }

    public long calculateHours(Activity activity) {
        LOGGER.debug("Calculating hours between " + activity.getStart() + " and " + activity.getEnd() + ".");
        return acDao.calculateHours(activity);
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleting activity dates for id: " + id);
        Activity activity = acDao.findById(id);
        if (activity != null) {
            acDao.delete(activity);
            return true;
        }

        return false;
    }

    public Activity get(Long id) {
        LOGGER.debug("Getting activity dates for id: " + id);
        return acDao.findById(id);

    }

    public void save(Activity activity) throws ValidationException {
        LOGGER.debug("Saving: " + activity);
        validate(activity);

        acDao.update(activity);
    }

    private void validate(Activity activity) throws ValidationException {
        LocalDate currentDate = LocalDate.now();
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(activity.getStart())) {
            errors.add("Start date is Empty");
        } else{
            if (activity.getStart().isAfter(currentDate)){
                errors.add("Start date is after current date.");
            }
            if (activity.getStart().isAfter(activity.getEnd())){
                errors.add("Start date is before end date.");
            }
            if(activity.getStart().isEqual(activity.getEnd())){
                errors.add("Start date is the same with end date.");
            }
        }

        if (StringUtils.isEmpty(activity.getEnd())) {
            errors.add("End date is Empty");
        } else{
            if (activity.getEnd().isAfter(currentDate)){
                errors.add("End date is after current date");
            }
        }

        if (activity.getType() == null) {
            errors.add("Type is Empty");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[] {}));
        }
    }

    public ActivityDAO getAcDao() {
        return acDao;
    }

    public void setAcDao(ActivityDAO dao) {
        this.acDao = dao;
    }
}
