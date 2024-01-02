package dev.springrunner;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/hello"})
//  서블릿에 연결할 URL을 작성한다. 서블릿과 URL을 연결하는 방법은 두가지다.
//  첫번째는 web.xml이라는 배포 서술자(Deployment Descriptor)을 작성하는거고,
//  두번째는 애노테이션(Annotation)을 서블릿 클래스에 선언하는 것이다.
public class HelloServlet extends HttpServlet {
//  모든 서블릿은 HttpServlet 클래스를 상속 받아 작성한다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  서블릿 컨테이너로부터 request와 response 객체를 받아 프로그램을 수행하는 service 메소드를 재정의한다.
        //  HttpServlet에는 다양한 수행 메소드가 제공된다. (service(..), doOptions(..), doGet(..), doHead(..), doPost(..), doPut(..), doDelete(..), doTrace(..)

        request.setCharacterEncoding("UTF-8");      //  한국어을 보여주려면 `UTF-8` 인코딩 필수!
        response.setCharacterEncoding("UTF-8");     //  기본값은 `ISO-8859-1`으로 영.문.권.에서는 문제가 없다.

        //  1. 요청 데이터로부터 이름정보를 꺼내는 것
        String requestName = request.getParameter("name");  //  request 객체를 통해 웹 브라우저가 전송한 데이터를 다룰 수 있다.
        //  2. 실행하는 서블릿에 이름정보를 찾는 것
        String servletName = getServletConfig().getServletName();

        //  3. 두 이름정보를 바탕으로 html 을 작성하는 것
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append(" <head>");
        html.append(" <title>초 간단 서블릿 개발하기</title>");
        html.append(" </head>");
        html.append(" <body>");
        html.append(" <h1>" + requestName + "님 안녕하세요.</h1>");
        html.append(" <h1>저는 " + servletName + "입니다.</h1>");
        html.append(" </body>");
        html.append("</html>");

        //  4. HTTP 응답을 작성하는 것 (상태코드, 헤더, 응답 콘텐트)
        //  response 객체를 통해 HTTP 상태코드(200, 정상)와 HTTP 헤더를 설정한다.
        response.setStatus(200);
        response.setContentType("text/html; charset=UTF-8");
        //  브라우저에게 응답시 인코딩 방식(UTF-8)을 알려주기 위해 Content-Type에 전달했다. 생략해도 되지만 직접 작성한 이유이기도 하다. 다시한번 말하지만, 한국어를 올바르게 다루기 위해서는 UTF-8은 기본이다.ㄴ

        PrintWriter writer = response.getWriter();  //  response 객체를 통해 응답을 출력한다.
        writer.write(html.toString());              //  여기서는 텍스트 데이터(HTML)을 출력하기 위해 PrintWriter를 사용하고 있다.
                                                    //  바이너리 데이터(이미지 등)를 출력해야한다면 ServletOutputStream를 사용한다.
    }
}