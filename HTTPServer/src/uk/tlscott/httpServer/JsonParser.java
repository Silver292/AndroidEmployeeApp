package uk.tlscott.httpServer;

import java.util.ArrayList;

import com.google.gson.Gson;

import uk.tlscott.employeeDatabase.Employee;
import uk.tlscott.employeeDatabase.IEmployeeDAO;

public class JsonParser {

	private IEmployeeDAO dao;

	public JsonParser(IEmployeeDAO dao) {
		this.dao = dao;
	}

	public String getAllEmployees() {
		Gson gson =  new Gson();
		ArrayList<Employee> allEmployees = dao.selectAllEmployees();

		return gson.toJson(allEmployees);
	}
}
