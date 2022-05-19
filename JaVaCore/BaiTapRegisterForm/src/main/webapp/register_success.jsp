<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <title>Document</title>
</head>
<body>
<div style="width: 400px; margin-left: auto; margin-right: auto; border: 2px solid black; margin-top: 100px">

    <div class="w3-center">
        <h3>Account Information</h3>
        <img src="download%20(1).jpg" alt="Avatar" style="width:200px; height: 250px">



    </div>
    <div style="margin-left: 50px">
        <p>Name: <%=request.getAttribute("username")%></p>
        <p>Email: <%=request.getAttribute("email")%></p>
        <p>Password: <%=request.getAttribute("password")%></p>
        <p>Gender: <%=request.getAttribute("gender")%></p>
        <p>City: <%=request.getAttribute("city")%></p>
    </div>


</div>
</body>
</html>