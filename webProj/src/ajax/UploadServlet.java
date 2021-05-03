package ajax;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jquery/uploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	System.out.println("init call()");
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("Service call()");
    	Enumeration<String> enumer = req.getHeaderNames();
    	while(enumer.hasMoreElements()) {
    		String key = enumer.nextElement();
    		String val = req.getHeader(key);
    		System.out.println(key + " : " + val);
    	}
    }

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doGet call()");
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//request.getHeaderNames();
//		System.out.println("doPost call()");
//	}

}
