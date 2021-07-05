package ooc.webapp.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends AbstractRoutableHttpServlet{
    private String errorMessage = null;
    private String currentInfo;
    private String currentName;
    private String currentUsername;

    @Override
    public String getMapping() {
        return "/editInfoPage";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {

            currentUsername = (String) request.getSession().getAttribute("username");
            currentInfo = securityService.getUserInfo(request);
            currentName = securityService.getName(request);
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("userinfo", currentInfo);
            request.setAttribute("name", currentName);
            request.setAttribute("username", currentUsername);
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
        String newUsername = request.getParameter("newUsername");

        securityService.EditInfo(request, info);
        securityService.EditName(request, name);
        if(!securityService.EditUsername(request, newUsername)){
            errorMessage = "Username has been taken";
            response.sendRedirect("/editInfoPage");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/editInfo.jsp");
            try {
                rd.include(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("/index.jsp");
    }
}
