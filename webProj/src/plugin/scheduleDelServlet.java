package plugin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.EmpDAO;
import common.scheduleVO;

@WebServlet("/scheduleDelServlet")
public class scheduleDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public scheduleDelServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDAO dao = new EmpDAO();
		
		String title = request.getParameter("title");
		
		scheduleVO vo = new scheduleVO();
		vo.setTitle(title);
		
		dao.deleteSchedule(vo);
	}

}
