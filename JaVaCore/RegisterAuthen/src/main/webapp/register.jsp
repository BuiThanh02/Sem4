<%@ page import="com.example.registerauthen.Entity.Account" %>
<%@ page import="java.util.HashMap" %>
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
    Account account = (Account) request.getAttribute("account");
    if(account == null){
        account = new Account();
    }
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
    if(errors == null){
        errors = new HashMap<>();
    }
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
                        <h3>Create An Account</h3>
                        <form action="register" method="post">
                            <div class="form-group form-box">
                                <input name="username" type="text" class="form-control" value="<%=account.getUsername()%>" aria-label="Full Name">
                                <i class="flaticon-user"></i>
                                <%if(errors.containsKey("username")){%>
                                <span class="text-danger">* <%=errors.get("username")%></span>
                                <%}%>
                            </div>
                            <div class="form-group form-box">
                                <input name="email" type="email" class="form-control" value="<%=account.getEmail()%>" aria-label="Email Address">
                                <i class="flaticon-mail-2"></i>
                                <%if(errors.containsKey("email")){%>
                                <span class="text-danger">* <%=errors.get("email")%></span>
                                <%}%>
                            </div>
                            <div class="form-group form-box clearfix">
                                <input name="password" type="password" class="form-control" autocomplete="off" placeholder="Password" aria-label="Password">
                                <i class="flaticon-password"></i>
                                <%if(errors.containsKey("password")){%>
                                <span class="text-danger">* <%=errors.get("password")%></span>
                                <%}%>
                            </div>
                            <div class="form-group form-box clearfix">
                                <input name="confirmPassword" type="password" class="form-control" autocomplete="off" placeholder="Password" aria-label="Password">
                                <i class="flaticon-password"></i>
                                <%if(errors.containsKey("confirmPassword")){%>
                                <span class="text-danger">* <%=errors.get("confirmPassword")%></span>
                                <%}%>
                            </div>
                            <div class="form-group form-box">
                                <input name="phone" type="email" class="form-control" value="<%=account.getPhone()%>" >
                                <i class="flaticon-mail-2"></i>
                                <%if(errors.containsKey("phone")){%>
                                <span class="text-danger">* <%=errors.get("phone")%></span>
                                <%}%>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-lg btn-theme"><span>Register</span></button>
                            </div>
                            <div class="extra-login form-group clearfix">
                                <span>Or Login</span>
                            </div>
                            <div class="form-group">
                                <button href="/login" type="submit" class="btn btn-primary btn-lg btn-theme"><span>Login</span></button>
                            </div>

                        </form>
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