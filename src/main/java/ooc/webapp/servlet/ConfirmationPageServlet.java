package ooc.webapp.servlet;

import ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmationPageServlet extends AbstractRoutableHttpServlet {

    String goingToRemove;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            goingToRemove = request.getParameter("remove");
            request.setAttribute("removeUser", goingToRemove);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/confirmationPage.jsp");
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String button = request.getParameter("action");
        if(button.equals("Yes")){
            securityService.removeUser(goingToRemove);
        }
        response.sendRedirect("/index.jsp");
    }
    @Override
    public String getMapping() {
        return "/confirmationPage";
    }

}
