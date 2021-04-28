package ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax.html")
public class AjaxServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* GET :  서버로부터 정보를 조회하기 위해 설계된 메소드
		 * 요청을 전송할 때 필요한 데이터를 Body에 담지 않고, 쿼리 스트링을 통해 전송.
		 * (쿼리스트링 : url의 끝에 ?와 함께 이름과 값으로 쌍을 이루는 요청 파라미터.
		 * 			  요청 파라미터가 여러개일 경우 &로 연결.
		 * 			  쿼리스트링을 사용하게 될 경우 url에 조회 조건을 표시하기 때문에
		 * 			  특정 페이지를 링크하거나 북마크할 수 있음.)
		 * get은 불필요한 요청을 제한하기 위해 요청이 캐시될 수 있음.
		 * js, css, 이미지 같은 정적 컨텐츠는 데이터 양이 크고 변경될 일이 적기 때문에
		 * 동일한 요청이 발생할 때 서버로 요청을 보내지 않고 캐시데이터를 사용하게 됨.
		 * 이런 문제 때문에 컨텐츠가 변경되어도 기존에 저장 된 캐시를 사용해 내용이 바뀌지 않는
		 * 경우가 종종 발생한다. -> 브라우저의 캐시를 지워주면 다시 서버로 요청을 해서 받아옴.
		 * get 방식의 url 예 : http://localhost/webProj/memberCreate.do?m_id=1&m_name=dongdong&m_age=4
		 */
		String p1 = req.getParameter("p1");
		String p2 = req.getParameter("p2");
		resp.getWriter().print("<h1>Ajax Get Page</h1>");
		resp.getWriter().print("<h3>" + p1 + "," + p2 + "</h3>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* Post : 리소스를 생성/변경하기 위해 설계됨. get과 달리 전송해야 될 데이터를
		 * HTTP 메시지의 body에 담아서 전송한다.
		 * http 메시지의 body는 길이의 제한 없이 데이터를 전송할 수 있다 -> 대용량 데이터 전송 가능
		 * 데이터가 body로 전송되고 내용이 눈에 보이지 않기 때문에 get보다 보안적인 부분에서 
		 * 안전하다고 여겨지지만, post 요청도 크롭 개발자 도구나 Fiddler와 같은 툴로 요청 내용을 
		 * 확인할 수 있기 때문에 민감한 데이터의 경우, 반드시 암호화해 전송해야 한다.
		 * 
		 * Post로 요청을 보낼 때는 요청 헤더의 Content-Type에 요청 데이터 타입을 표시해야 한다.
		 * 데이터 타입을 표시하지 않을 경우 서버는 내용이나 url에 포함된 리소스의 확장자명 등으로
		 * 데이터 타입을 유추한다. 만약 알 수 없을 경우 application/octet-stream으로 요청을 처리한다.
		 * Post 방식의 url 예 : http://localhost/webProj/memberCreate.do
		 * */
		resp.getWriter().print("<h1>Ajax Post Page</h1>");
	}
	
	/* GET과 POST의 차이
	 * 
	 * GET은 서버에게 동일한 요청을 여러 번 전송하더라도 동일한 응답이 돌아와야 한다.
	 * 이에 따라 get은 설계원칙에 의해 서버의 데이터나 상태를 변경시키지 않아야 멱등(수학적개념)되기
	 * 때문에 주로 조회를 하는 일에 많이 사용한다.
	 * 예> 브라우저에서 웹 페이지를 열어보거나 게시글을 읽는 등 단순 조회 목적
	 * 
	 * POST는 서버에게 동일한 요청을 여러 번 전송해도 응답을 항상 다를 수 있다.
	 * 이에 따라 POST는 서버의 상태나 데이터를 변경시킬 때 사용된다.
	 * 게시글을 쓰면 서버에 게시글이 저장되고, 게시글을 삭제하면 해당 데이터가 없어지는 등의 일을
	 * POST로 요청하게 되면 서버의 뭔가가 변경되도록 사용된다.
	 * POST는 생성, 수정, 삭제에 사용할 수 있지만,
	 * 생성에는 POST, 수정은 PUT 또는 PATCH, 삭제는 DELETE가 더 용도에 맞는 메소드이다.*/
}
