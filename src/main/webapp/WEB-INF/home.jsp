<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<html>
<body>
<h2>Welcome, ${username}</h2>
<style>

table, th, td {
  border: 1px solid black;
}
</style>

<h3> List of usernames:${userList}</h3>


     <br><br>
    <form action="/logout" method="post">
            <button name="action" value="Logout">Logout</button>
    </form>
    <form action="/addUser" method="get">
            <button name="action" value="addUser">Add user</button>
    </form>

</body>
</html>
