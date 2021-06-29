package ooc.webapp.service;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;

public class DatabaseConnector {

    private Set<String> usernames;

    public DatabaseConnector(){
        usernames = getUsernames();
    }

    private Connection connect() throws SQLException, ClassNotFoundException {
        Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssc-demo","ssc","a1234f");
            return con;
    }

    public boolean put(String username, String password) {
        try {
            Connection con = connect();
            StringBuilder insert = new StringBuilder();
            Statement statement = con.createStatement();
            insert.append("INSERT INTO users(username, password) VALUES(");
            insert.append("'"+username+"',");
            insert.append("'"+password+"')");
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

    public String get(String username){
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

    public Set<String> getUsernameSet(){
        return usernames;
    }
    private Set<String> getUsernames(){
        Set<String> usernames = null;
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            String select = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(select);
            usernames = new HashSet<>();
            while(rs.next()){
                usernames.add(rs.getString(1));
            }
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usernames;
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

    public String getUserTable(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table style=\"width:40%\">");
        stringBuilder.append("<tr><th>Username:</th><th>buttons</th></tr>");
        for (String entry : usernames){
            stringBuilder.append("<tr><th>" + entry + "</th><th>"+button(entry)+"</th></tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private String button(String username){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<form action=\"/confirmationPage\" method=\"get\">\n" +
                "            <button name=\"remove\" value=\""+username+"\">Remove</button>");
        stringBuilder.append("</form>");
        return stringBuilder.toString();
    }
}
