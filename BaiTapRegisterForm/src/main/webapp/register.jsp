<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
<div class="form_wrapper">
    <div class="form_container">
        <div class="title_container">
            <h2>Account</h2>
        </div>
        <div class="row clearfix">
            <div class="">
                <form action="register" method="post">
                    <div class="input_field"> <span><i aria-hidden="true" class="fa fa-envelope"></i></span>
                        <input type="email" name="email" placeholder="Email" required />
                    </div>
                    <div class="input_field"> <span><i aria-hidden="true" class="fa fa-lock"></i></span>
                        <input type="password" name="password" placeholder="Password" required />
                    </div>
                    <div class="input_field"> <span><i aria-hidden="true" class="fa fa-user"></i></span>
                        <input type="text" name="username" placeholder="User name" required />
                    </div>
                    <div class="input_field radio_option">
                        <input type="radio" name="gender" id="rd1" value="male">
                        <label for="rd1">Male</label>
                        <input type="radio" name="gender" id="rd2" value="female">
                        <label for="rd2">Female</label>
                    </div>
                    <div class="input_field select_option">
                        <select name="city">
                            <option value="Ha Noi">Ha Noi</option>
                            <option value="HCM" selected>HCM </option>
                        </select>
                        <div class="select_arrow"></div>
                    </div>
                    <div class="input_field checkbox_option">
                        <input type="checkbox" id="cb1">
                        <label for="cb1">I agree with terms and conditions</label>
                    </div>
                    <div class="input_field checkbox_option">
                        <input type="checkbox" id="cb2">
                        <label for="cb2">I want to receive the newsletter</label>
                    </div>
                    <input class="button" type="submit"/>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>