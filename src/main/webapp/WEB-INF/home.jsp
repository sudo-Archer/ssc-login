<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<html>
<body>
<h2>Welcome, ${username}</h2>
<h3> List of usernames:${userList}</h3>


     <br><br>
    <form action="/logout" method="post">
            <button name="action" value="Logout">Logout</button>
    </form>
    <form action="/addUser" method="get">
            <button name="action" value="addUser">Add user</button>
    </form>

    <form action="/confirmationPage" method="get">
        <button name="action" value="removeUser">Remove User</button>
    </form>

</body>
</html>
