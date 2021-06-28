/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooc.webapp.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    
    private DatabaseConnector userCredentials = new DatabaseConnector();

    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
       return (username != null && userCredentials.getUsernameSet().contains(username));
    }
    
    public boolean authenticate(String username, String password, HttpServletRequest request) {
        String passwordInDB = userCredentials.get(username);
        boolean isMatched = StringUtils.equals(password, passwordInDB);
        if (isMatched) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }

    public boolean addUser(String username, String password){
      return userCredentials.put(username, password);
    }
    public void removeUser(HttpServletRequest request){
        String username = (String) request.getSession()
                .getAttribute("username");
        userCredentials.remove(username);
        request.setAttribute("username", null);
    }

    public Iterable<String> getListOfUser(){
        return userCredentials.getUsernameSet();
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    
}
