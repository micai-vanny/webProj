package fileBoard;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/modifyFileServlet")
public class ModifyFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModifyFileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");

		ServletContext sc = this.getServletContext();
		String path = sc.getRealPath("upload"); // 서버 상의 경로(물리 경로)
									 				// ┌> 요청정보 ┌>저장위치 ┌>file의 크기(byte 단위)
		MultipartRequest multi = new MultipartRequest(request, path, 8 * 1024 * 1024, "UTF-8",
				new DefaultFileRenamePolicy());
		
		Enumeration en = multi.getFileNames();
		
		String author = multi.getParameter("author");
		String title = multi.getParameter("title");
		String num = multi.getParameter("num");
				
		String fileN = null;

		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String fileName = multi.getFilesystemName(name);
			fileN = fileName;
			System.out.println("name : " + name + " , fileName : " + fileName);
		}
		
		// 입력 후 저장된 값들을 가져옴.
		FileDAO dao = new FileDAO();
		FileVO vo = new FileVO();
		
		vo.setAuthor(author);
		vo.setFileName(fileN);
		vo.setTitle(title);
		vo.setNum(Integer.parseInt(num));
		
		// 가져온 값을 Json형식으로 생성. {"returnCode":"Fail"}
		JSONObject obj = new JSONObject();
		
		if (dao.updateFile(vo)) {
			obj.put("retCode", "Success");
		} else {
			obj.put("retCode", "Fail");
		};
		response.getWriter().print(obj);
	}

}
