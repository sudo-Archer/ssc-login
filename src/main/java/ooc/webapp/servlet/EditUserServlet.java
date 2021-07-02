package ooc.webapp.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends AbstractRoutableHttpServlet{
    @Override
    public String getMapping() {
        return "/editInfoPage";
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            request.setAttribute("userinfo", securityService.getUserInfo(request));
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/editInfo.jsp");
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String info = request.getParameter("info");
        String name = request.getParameter("name");
        securityService.EditInfo(request, info);
        response.sendRedirect("/index.jsp");
    }
}
