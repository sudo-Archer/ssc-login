package ooc.webapp.servlet;

import ooc.webapp.service.SecurityService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

public class LoginServlet extends AbstractRoutableHttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do login post logic
        // extract username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            if (securityService.authenticate(username, password, request)) {
                response.sendRedirect("/");
            } else {
                String error = "Wrong username or password.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
                rd.include(request, response);
            }
        } else {
            String error = "Username or password is missing.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(request, response);
        }

        // check username and password against database
        // if valid then set username attribute to session via securityService
        // else put error message to render error on the login form

    }

    @Override
    public String getMapping() {
        return "/login";
    }

}
