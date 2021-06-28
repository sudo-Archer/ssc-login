<html>
    <body>
        <h2>Add User</h2>
        <p>${userMessage}</p>

        <form action="/addUser" method="post">
            Username:<br/>
            <input type="text" name="username"/>
            <br/>
            Password:<br/>
            <input type="password" name="password">
            <br><br>
            <input type="submit" value="Submit">
        </form>

        <form action="/index.jsp" method="get">
                    <br><br>
                    <button name="action" value="homePage">Home</button>
                </form>
    </body>
</html>