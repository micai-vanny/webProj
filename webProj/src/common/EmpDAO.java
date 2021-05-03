package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;
	
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Employee insertEmpBySeq(Employee emp) {
		conn = DBCon.getConnect();

		Employee empl = new Employee();

		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "insert into emp_temp(employee_id, first_name, last_name, email, salary, hire_date, job_id, department_id)"
				+ "values(?, ?, ?, ?, ?, ?, ?, 50)";
		try {
			int empId = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				empId = rs.getInt(1);
			}

			psmt = conn.prepareStatement(sql2); // insert sql 전달
			psmt.setInt(1, empId);
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getEmail());
			psmt.setInt(5, emp.getSalary());
			psmt.setString(6, emp.getHireDate());
			psmt.setString(7, emp.getJobId());

			int up = psmt.executeUpdate();
			System.out.println(up + "건 입력됨.");

			// ↓입력한 값을 반환해주기 위함.
			empl.setEmployeeId(empId);
			empl.setFirstName(emp.getFirstName());
			empl.setLastName(emp.getLastName());
			empl.setEmail(emp.getEmail());
			empl.setSalary(emp.getSalary());
			empl.setHireDate(emp.getHireDate());
			empl.setJobId(emp.getJobId());

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return empl;
	}

	public void insertEmp(Employee emp) {
		String sql = "insert into emp_temp(employee_id,first_name, last_name, email, salary, hire_date, job_id, department_id)"
				+ "values ((select max(employee_id)+1 from emp_temp), ?, ?, ?, ?, ?, ?, 50)";
		conn = DBCon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJobId());
			psmt.setInt(6, emp.getSalary());

			int up = psmt.executeUpdate();
			System.out.println(up + "건 입력됨.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public List<Employee> getEmpByDept(String dept) {
		// 사원 정보를 가지고 오는 처리
		String sql = "select * from emp_temp where department_id = " + dept //
				+ " order by employee_id";
		conn = DBCon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));

				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return employees;
	}

	public List<Employee> getEmpList() {
		// 사원 정보를 가지고 오는 처리
		String sql = "select * from emp_temp order by employee_id";
		conn = DBCon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));

				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return employees;
	}

	// (4/30 추가) empl_demo
	public List<Employee> getEmployeeList() {
		// 사원 정보를 가지고 오는 처리
		String sql = "select * from empl_demo order by employee_id";
		conn = DBCon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setPhoneNumber(rs.getString("phone_number"));

				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return employees;
	} // getEmployeeList()

	public Map<String, Integer> getEmployeeByDept() {
		// 부서명, 사원수
		Map<String, Integer> map = new HashMap<>();
//		map.put("부서", 20);

		String sql = "select d.department_name, COUNT(1)\r\n"//
				+ "from empl_demo e, departments d\r\n"//
				+ "where e.department_id = d.DEPARTMENT_ID\r\n"//
				+ "group by d.department_name";
		conn = DBCon.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}

	// Schedule data를 가지고 오는 메소드 (5/3추가)
	public List<scheduleVO> getScheduleList() {
		conn = DBCon.getConnect();
		String sql = "select * from schedule";
		List<scheduleVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				scheduleVO vo = new scheduleVO();
				vo.setTitle(rs.getString("title"));
				vo.setStartDay(rs.getString("start_day"));
				vo.setEndDay(rs.getString("end_day"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}	// end of getScheduleList
	
	// Schedule data를 받아오는 메소드 : 스케줄 추가 (5/3추가)
	public void insertSchedule(scheduleVO vo) {
		conn = DBCon.getConnect();
		String sql = "insert into schedule(title, start_day, end_day) values(?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getStartDay());
			psmt.setString(3, vo.getEndDay());

			int in = psmt.executeUpdate();
			System.out.println("스케줄 " + in + " 건 등록됨.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	} // end of insertSchedule
	
	// Schedule data를 DB에서 삭제하는 메소드 : 스케줄 삭제 (5/3 추가)
	public void deleteSchedule(scheduleVO vo) {
		conn = DBCon.getConnect();
		String sql = "delete from schedule where title = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			
			int del = psmt.executeUpdate();
			System.out.println("스케줄 " +  del +" 건 삭제됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
