package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import proj.db.persistence.models.EmployeesDbViewData;
import proj.db.persistence.views.EmployeesDBview;

public class Entry {

    private static final Logger logger = LoggerFactory.getLogger(Entry.class);

    public static void main(String[] args) {
        logger.trace("Starting application..");
        EmployeesDBview hrdBview = new EmployeesDBview();
        EmployeesDbViewData hrdBviewData = new EmployeesDbViewData();
        hrdBviewData.setEmployeeID(100);
        hrdBviewData = hrdBview.selectData(hrdBviewData);

        logger.debug("**************************RESULT**************************");
        logger.debug("First name: {}", hrdBviewData.getFirstName());
        logger.debug("Last name: {}", hrdBviewData.getLastName());
        logger.debug("Email: {}", hrdBviewData.getEmail());
        logger.debug("**********************************************************");

        logger.trace("Main finished");
    }

}
