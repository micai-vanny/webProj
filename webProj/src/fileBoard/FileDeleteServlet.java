package fileBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/fileDeleteServlet")
public class FileDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");	// String type으로 num을 받아왔으니까
		int delNum = Integer.parseInt(num);			// Integer.parseInt로 String을 int로 변경
		
		FileDAO dao = new FileDAO();
		FileVO vo = dao.delFile(delNum);	// delNum을 넘겨줍세다.
		//이제 이걸 upload의 delrow function에서 받아서 처리를 할 것이에여.
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
