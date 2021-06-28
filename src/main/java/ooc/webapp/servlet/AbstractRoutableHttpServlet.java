package ooc.webapp.servlet;

import ooc.webapp.service.SecurityService;

import javax.servlet.http.HttpServlet;

public abstract class AbstractRoutableHttpServlet extends HttpServlet implements Routable {
    protected SecurityService securityService;
    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
