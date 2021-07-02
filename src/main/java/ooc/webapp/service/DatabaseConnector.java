package ooc.webapp.service;

import java.sql.*;
import java.util.*;

public class DatabaseConnector {

    Set<String> usernames;

    public DatabaseConnector(){
        usernames = new HashSet<>();
        Set<User> users = getUsers();
        for (User user: users){
            usernames.add(user.getUsername());
        }
    }


    private Connection connect() throws SQLException, ClassNotFoundException {
        Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssc-demo","ssc","a1234f");
            return con;
    }

    public boolean insert(String username, String password, String name, String info) {
        try {
            Connection con = connect();
            StringBuilder insert = new StringBuilder();
            Statement statement = con.createStatement();
            insert.append("INSERT INTO users(username, password, name, info) VALUES(");
            insert.append("'"+username+"',");
            insert.append("'"+password+"',");
            insert.append("'"+name+"',");
            insert.append("'"+info+"')");
            statement.executeUpdate(insert.toString());
            con.close();
            usernames.add(username);
            return true;
        } catch (SQLException throwables) {
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPassword(String username){
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            String password = null;
            StringBuilder select = new StringBuilder();
            select.append("SELECT password FROM users WHERE username=");
            select.append("'"+username+"'");
            ResultSet rs = statement.executeQuery(select.toString());
            if(rs.next()){
               password = rs.getString(1);
            }
            con.close();
            return password;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Set<User> getUsers(){
        Set<User> users = null;
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            String select = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(select);
            users = new HashSet<>();
            String username;
            String name;
            String info;
            while(rs.next()){
                username = rs.getString(1);
                name = rs.getString(3);
                info = rs.getString(4);
                users.add(new User(username, name, info));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void remove(String username) {
        try {
            Connection con = connect();
            StringBuilder remove= new StringBuilder();
            Statement statement = con.createStatement();
            remove.append("DELETE FROM users WHERE username=");
            remove.append("'"+username+"'");
            statement.executeUpdate(remove.toString());
            con.close();
            usernames.remove(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getUserTable(String username){
        Set<User> users = getUsers();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table style=\"width:40%\">");
        stringBuilder.append("<tr><td>Username:</td><td>Name</td><td>Userinfo</td><td>Buttons</td></tr>");
        String button;
        for (User entry : users){
            if(username.equals(entry.getUsername())){
                button = editButton(username);
            }
            else{
                button = Removable(entry.getUsername());
            }
            stringBuilder.append("<tr><th>" + entry.getUsername() + "</th>" +
                    "<th>"+entry.getName()+"</th>" +
                    "<th>"+entry.getInfo()+"</th>" +
                    "<th>"+button+ "</th></tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }
    private String Removable(String username){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<form action=\"/confirmationPage\" method=\"get\">\n" +
                "            <button name=\"remove\" value=\""+username+"\">Remove</button>");
        stringBuilder.append("</form>");
        return stringBuilder.toString();
    }
    private String editButton(String username){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<form action=\"/editInfoPage\" method=\"get\">\n" +
                "            <button name=\"editInfo\" value=\""+username+"\">Edit info</button>");
        stringBuilder.append("</form>");
        return stringBuilder.toString();
    }

    public Set<String> getUsernameSet() {
        return usernames;
    }

    public void EditInfo(String username, String info) {
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            StringBuilder update = new StringBuilder();
            update.append("UPDATE users SEt info=");
            update.append("'"+info+"' WHERE username=");
            update.append("'"+username+"'");
            statement.executeUpdate(update.toString());
            System.out.println(update);
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getUserInfo(String username) {
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            String info = null;
            StringBuilder select = new StringBuilder();
            select.append("SELECT info FROM users WHERE username=");
            select.append("'"+username+"'");
            ResultSet rs = statement.executeQuery(select.toString());
            if(rs.next()){
                info = rs.getString(1);
            }
            return info;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
