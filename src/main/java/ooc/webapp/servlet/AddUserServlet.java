package ooc.webapp.servlet;

import ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserServlet extends AbstractRoutableHttpServlet {

    String userMessage= "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            // do MVC in here
            String username = (String) request.getSession().getAttribute("username");
            request.setAttribute("username", username);
            request.setAttribute("userMessage", userMessage);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/addUser.jsp");
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String username= request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String info = request.getParameter("info");

        if(name==null){
            userMessage = "Name can not be null";
        }
        else if(securityService.addUser(username, password, name, info)){
            userMessage = "User has been added";
        }
        else {
            userMessage = "Username has been taken";
        }
        response.sendRedirect("/addUser");
    }

    @Override
    public String getMapping() {
        return "/addUser";
    }

}
