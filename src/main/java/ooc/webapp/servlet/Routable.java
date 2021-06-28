package ooc.webapp.servlet;

import ooc.webapp.service.SecurityService;

public interface Routable {

    String getMapping();

    void setSecurityService(SecurityService securityService);
}
