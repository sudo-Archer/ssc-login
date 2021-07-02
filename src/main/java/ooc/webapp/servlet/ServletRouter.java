/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooc.webapp.servlet;

import ooc.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gigadot
 */
public class ServletRouter {

    private static final List<Class<? extends AbstractRoutableHttpServlet>> routables = new ArrayList<>();

    static {
        routables.add(HomeServlet.class);
        routables.add(LoginServlet.class);
        routables.add(AddUserServlet.class);
        routables.add(ConfirmationPageServlet.class);
        routables.add(LogoutServlet.class);
        routables.add(EditUserServlet.class);
    }

    private SecurityService securityService;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void init(Context ctx) {
        for (Class<? extends AbstractRoutableHttpServlet> routableClass : routables) {
            try {
                AbstractRoutableHttpServlet routable = routableClass.newInstance();
                routable.setSecurityService(securityService);
                String name = routable.getClass().getSimpleName();
                Tomcat.addServlet(ctx, name, (HttpServlet) routable);
                ctx.addServletMapping(routable.getMapping(), name);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
