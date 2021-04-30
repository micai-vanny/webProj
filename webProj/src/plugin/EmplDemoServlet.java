package plugin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.EmpDAO;
import common.Employee;

@WebServlet("/EmplDemoServlet")
public class EmplDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmplDemoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmpDAO dao =new EmpDAO();
		List <Employee> list = dao.getEmployeeList();
		int totalCnt = list.size();
		int cnt = 0;
		// 사원번호, 이름, 이메일, 입사일자, 급여
		// { "draw":1, "recordsTotal":57, "recordsFiltered": 57,"data": [[...],[...]] }
		String json = "{\"draw\": 1, "
				+ "\"recordsTotal\": "+ totalCnt + ", "
				+ "\"recordsFiltered\": "+ totalCnt +","
				+ "\"data\": [";
		for(Employee emp : list) {
			json += "[\""+emp.getEmployeeId()+"\","
					+"\""+emp.getFirstName()+"\","
					+"\""+emp.getEmail()+"\","
					+"\""+emp.getPhoneNumber()+"\","
					+"\""+emp.getHireDate()+"\","
					+ "\""+emp.getSalary()+" \""
					+ "]";
			if(++cnt != totalCnt) {
				json+=",";
			}
		}	
		json += "]}";
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
