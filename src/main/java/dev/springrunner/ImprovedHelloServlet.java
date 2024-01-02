package dev.springrunner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/improved-hello"})
public class ImprovedHelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestName = request.getParameter("name");
        String servletName = getServletConfig().getServletName();

        request.setAttribute("requestName", requestName);   //  request 속성에 이름과 값을 주입한다.
        request.setAttribute("servletName", servletName);   //  이렇게 주입된 속성은 요청이 종료되기 전에는 언제든지 꺼내 사용할 수 있다.

        request.getRequestDispatcher("/improved-hello.jsp")
                .forward(request, response);    //  HTML을 출력하던 코드를 제거하고 JSP(improved-hello.jsp)에게 요청(request)과 응답(response)를 전달한다.
    }
}