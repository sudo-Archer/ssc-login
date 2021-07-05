<!DOCTYPE html>
<html>
<body>

<h1>The input value attribute</h1>

<form action="/editInfoPage", method="post">

  <label for="newUsername">Change Username to:</label>
  <input type="text" name="newUsername" value="${username}"><br><br>
  <label for="info">Change name to:</label>
  <input type="text" name="name" value="${name}"><br><br>
  <label for="name">Change Userinfo to:</label>
  <input type="text" name="info" value="${userinfo}"><br><br>
  <input type="submit" value="Submit">
</form>

<form action="/index.jsp" method="get">
    <button name="action" value="homePage">Cancel</button>
</form>

</body>
</html>