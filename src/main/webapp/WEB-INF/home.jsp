<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<html>
<body>
<h2>Welcome, ${username}</h2>
<style>

table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  font-family: sans-serif;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

table thead tr {
    background-color: #009879;
    color: #ffffff;
    text-align: left;
}
table th,
table td {
    padding: 12px 15px;
}
table tbody tr {
    border-bottom: 1px solid #dddddd;
}

table tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
}

table tbody tr:last-of-type {
    border-bottom: 2px solid #009879;
}

</style>

<h3>${userList}</h3>


     <br><br>
    <form action="/logout" method="post">
            <button name="action" value="Logout">Logout</button>
    </form>
    <form action="/addUser" method="get">
            <button name="action" value="addUser">Add user</button>
    </form>

</body>
</html>
