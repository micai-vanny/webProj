package plugin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.EmpDAO;
import common.scheduleVO;

@WebServlet("/scheduleservlet")
public class scheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public scheduleServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// [{"title":"Meeting1", "startDay":"2021-05-01"..},{},{}...{}]
		EmpDAO dao = new EmpDAO();
		List<scheduleVO> list = dao.getScheduleList();
		//Json.simple.jar라는 파일을 다운 받아서 라이브러리에 넣어주면  JSONArray와 JSONObject를 사용할 수 있음.
		JSONArray ary = new JSONArray();	// 배열 형식으로 JSON Data를 만들어 줄 수 있음.
		for(scheduleVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("title", vo.getTitle());
			obj.put("start", vo.getStartDay());
			obj.put("end", vo.getEndDay());
			ary.add(obj);
		}
		
		response.getWriter().print(ary);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 스케줄 입력받기 title, start, end 
		EmpDAO dao = new EmpDAO();
		
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		scheduleVO vo = new scheduleVO();
		vo.setTitle(title);
		vo.setStartDay(start);
		vo.setEndDay(end);
		
		dao.insertSchedule(vo);
	}

}
