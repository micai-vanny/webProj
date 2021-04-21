package common;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		
		for(Employee emp : list) {
			System.out.println(emp.getEmployeeId() + ", " + emp.getFirstName());
		}
	}
}
