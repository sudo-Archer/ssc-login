/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooc.webapp.service;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    
    private DatabaseConnector userCredentials = new DatabaseConnector();

    private Hashing hashing = new Hashing();

    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
       return (username != null && userCredentials.getUsernameSet().contains(username));
    }
    
    public boolean authenticate(String username, String password, HttpServletRequest request) {
        String passwordInDB = userCredentials.getPassword(username);
        if (passwordInDB == null){
            return false;
        }
        boolean isMatched = hashing.verifyPassword(password, passwordInDB);
        if (isMatched) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }
    public boolean addUser(String username, String password, String name, String info){
      return userCredentials.insert(username, hashing.getHash(password), name, info);
    }
    public void removeUser(String username){
        userCredentials.remove(username);
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public String getUserTable(String username){
        return userCredentials.getUserTable(username);
    }

    public void EditInfo(HttpServletRequest request, String info) {
        userCredentials.EditInfo((String) request.getSession().getAttribute("username"), info);
    }

    public String getUserInfo(HttpServletRequest request) {
        return userCredentials.getUserInfo((String) request.getSession().getAttribute("username"));
    }

    public void EditName(HttpServletRequest request, String name) {
        userCredentials.EditName((String) request.getSession().getAttribute("username"), name);
    }

    public String getName(HttpServletRequest request) {
        return userCredentials.getName((String) request.getSession().getAttribute("username"));
    }

    public boolean EditUsername(HttpServletRequest request, String newUsername) {
        if(userCredentials.EditUsername((String) request.getSession().getAttribute("username"), newUsername)){
            request.getSession().setAttribute("username", newUsername);
            return true;
        }
        return false;
    }
}
