<html>
<body>
<h2>Name and Surname form:</h2>
    <form method="POST" action="<%=request.getContextPath()%>/hello">
        <p><label for="name">Name: </label><input type="text" name="name"/></p>
        <p><label for="surname">Surname: </label><input type="text" name="surname"/></p>
        <p><input type="submit" name="send"/></p>
    </form>
</body>
</html>