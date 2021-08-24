package proj.db.persistence.views;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import proj.db.persistence.CRUDAbstractOperation;
import proj.db.persistence.models.EmployeesDbViewData;

public class EmployeesDBview extends CRUDAbstractOperation<EmployeesDbViewData> {

	private static final Logger logger = LoggerFactory.getLogger(EmployeesDBview.class);
	private EmployeesDbViewData employeeDbViewdata = new EmployeesDbViewData();
	private ResultSet resultSet = null;

	public EmployeesDBview() {
		try {
			getConnection();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public EmployeesDbViewData selectData(EmployeesDbViewData searchCriteria) {
		logger.debug("Entering selectData()");
		String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
		try {
			prepareStatement(sql);
			setInt(1, searchCriteria.getEmployeeID());
			resultSet = executeQuery();
			while (resultSet.next()) {
				employeeDbViewdata.setEmployeeID(resultSet.getInt("EMPLOYEE_ID"));
				employeeDbViewdata.setFirstName(resultSet.getString("FIRST_NAME"));
				employeeDbViewdata.setLastName(resultSet.getString("LAST_NAME"));
				employeeDbViewdata.setEmail(resultSet.getString("EMAIL"));
				employeeDbViewdata.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
				employeeDbViewdata.setHireDate(resultSet.getDate("HIRE_DATE"));
				employeeDbViewdata.setJobID(resultSet.getString("JOB_ID"));
				employeeDbViewdata.setSalary(resultSet.getInt("SALARY"));
				employeeDbViewdata.setCommissionPct(resultSet.getInt("COMMISSION_PCT"));
				employeeDbViewdata.setManagerID(resultSet.getInt("MANAGER_ID"));
				employeeDbViewdata.setManagerID(resultSet.getInt("DEPARTMENT_ID"));
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				resultSet.close();
				closeResources();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		logger.debug("Exiting selectData()");
		return employeeDbViewdata;
	}

	@Override
	public EmployeesDbViewData[] selectBulk(EmployeesDbViewData searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateData(EmployeesDbViewData data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteData(EmployeesDbViewData data) {
		// TODO Auto-generated method stub
		return 0;
	}

}
