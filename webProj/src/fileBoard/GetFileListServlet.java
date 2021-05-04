package fileBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/getFileListServlet")
public class GetFileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetFileListServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// json data -> DAO: 전체 리스트를 가지고 오는 메소드.
		// [{},{},{}]
		// JSONObject, JSONArray		
		
		FileDAO dao = new FileDAO();
		List<FileVO> list = dao.getFileList();
		
		JSONArray ary = new JSONArray();
		for(FileVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("num", vo.getNum());
			obj.put("author", vo.getAuthor());
			obj.put("title", vo.getTitle());
			obj.put("fileName", vo.getFileName());
			obj.put("day", vo.getDay());
			ary.add(obj);
		}
		response.getWriter().print(ary);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
