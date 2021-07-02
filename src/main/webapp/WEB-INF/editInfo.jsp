<!DOCTYPE html>
<html>
<body>

<h1>The input value attribute</h1>

<form action="/editInfoPage", method="post">

  <label for="info">Change name to:</label>
  <input type="text" name="name" value="${username}"><br><br>
  <label for="info">Change info to:</label>
  <input type="text" name="info" value="${userinfo}"><br><br>

  <input type="submit" value="Submit">
</form>

</body>
</html>