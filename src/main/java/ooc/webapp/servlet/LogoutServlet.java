package ooc.webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends AbstractRoutableHttpServlet{

    @Override
    public String getMapping() {
        return "/logout";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        securityService.logout(request);
        response.sendRedirect("/login");
    }
}
