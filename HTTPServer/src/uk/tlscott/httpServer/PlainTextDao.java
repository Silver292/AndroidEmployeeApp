package uk.tlscott.httpServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import uk.tlscott.employeeDatabase.Employee;
import uk.tlscott.employeeDatabase.IEmployeeDAO;
import uk.tlscott.exceptions.*;

public class PlainTextDao implements IEmployeeDAO {

	private Scanner scanner;

	public PlainTextDao(String fileName) {
		File file = new File(fileName);
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insertEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Employee> selectAllEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] attributes = line.split(",");
			Employee emp = new Employee();
			try {
				fillEmployee(emp, attributes);
			} catch (InvalidNationalInsuranceException | UnderMinimumAgeException e) {
				e.printStackTrace();
			}
			employees.add(emp);
		}
		return employees;
	}

	public void fillEmployee(Employee emp, String[] attributes) throws InvalidNationalInsuranceException, UnderMinimumAgeException {
		emp.setId(attributes[0]);
		emp.setName(attributes[1]);
		emp.setGender(attributes[2].charAt(0));
		emp.setDob(attributes[3]);
		emp.setAddress(attributes[4]);
		emp.setPostcode(attributes[5]);
		emp.setNatInscNo(attributes[6]);
		emp.setTitle(attributes[7]);
		emp.setStartDate(attributes[8]);
		emp.setSalary(attributes[9]);
		emp.setEmail(attributes[10]);
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteEmployeeById(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee getNextEmployee(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getPreviousEmployee(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Employee> searchByID(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> searchByName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> searchByNameAndID(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee selectEmployeeById(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee selectEmployeeByName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee selectFirstEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEmployee(Employee arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
