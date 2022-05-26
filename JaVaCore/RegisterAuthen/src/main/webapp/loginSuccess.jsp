<%@ page import="com.example.registerauthen.Entity.Account" %>
<!DOCTYPE html>
<html lang="zxx"><head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>ODDO - Login and Register Form HTML5 Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <!-- External CSS libraries -->
    <link type="text/css" rel="stylesheet" href="Style/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="Style/font-awesome.css">
    <link type="text/css" rel="stylesheet" href="Style/flaticon.css">

    <!-- Favicon icon -->
    <link rel="shortcut icon" href="https://storage.googleapis.com/theme-vessel-items/checking-sites/oddo-2-html/HTML/main/assets/img/favicon.ico" type="image/x-icon">

    <!-- Google fonts -->
    <link href="Style/css2.css" rel="stylesheet">

    <!-- Custom Stylesheet -->
    <link type="text/css" rel="stylesheet" href="Style/style.css">

</head>
<body id="top">
<div class="page_loader"></div>
<%
    Account currentLogin = (Account) session.getAttribute("currentLogin");
%>
<!-- Login 5 start -->
<div class="login-5">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="form-section">
                    <div class="form-inner">
                        <a href="https://storage.googleapis.com/theme-vessel-items/checking-sites/oddo-2-html/HTML/main/login-5.html" class="logo">
                            <img src="Style/logo-2.png" alt="logo">
                        </a>
                        <h3>Hello <%=currentLogin.getUsername()%></h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Login 5 end -->

<!-- External JS libraries -->
<script src="Style/jquery-3.js"></script>
<script src="Style/bootstrap.js"></script>
<script src="Style/jquery.js"></script>
<script src="Style/app.js"></script>
<!-- Custom JS Script -->



</body></html>