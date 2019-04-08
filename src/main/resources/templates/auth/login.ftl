[#ftl]
[#import "/spring.ftl" as spring /]
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet">
    <link href="[@spring.url '/css/login_page_style.css' /]" rel="stylesheet">
    <link href="[@spring.url '/css/styles.css' /]" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="[@spring.url '/js/jquery.min.js' /]" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="[@spring.url '/js/bootstrap.min.js' /] "></script>

</head>

[#escape x as x?html]
    <body style="background-image:url('/images/Background.jpg');">
    <a href="/"><img src="[@spring.url '/images/logo.png' /]" height="100" /></a>
    <div class="container">
        <div class="d-flex justify-content-center h-100">
            <div class="card">
                <div class="card-header" style="height: 75px">
                    <h3>Sign In</h3>
                    [#if RequestParameters.error??]
                        <div>
                            <ul>
                                <b style="color:red">
                                    [@spring.message 'invalid.username'/]
                                </b>

                            </ul>
                        </div>
                    [/#if]
                </div>
                <div class="card-body">

                    <form action="/login" method="POST">
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                            </div>
                            <label for="firstName"></label>
                            <input type="text"
                                   class="form-control" id="username" name="username"
                                   placeHolder="E-mail address" "/>

                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-key"></i></span>
                            </div>
                            <label for="lastName"></label>
                            <input type="password"
                                   class="form-control" id="password" name="password"
                                   placeHolder="Password"/>
                        </div>
                        <div class="row align-items-center remember">
                            <input type="checkbox">Remember Me
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Login" class="btn float-right login_btn">
                        </div>
                        <br/> <input type="hidden" class="form-control" id="id" value="0"/>
                    </form>
                </div>
                <div class="card-footer">
                    <div class="d-flex justify-content-center links">
                        Back to<a href="/">Home</a>
                    </div>
                    <div class="d-flex justify-content-center">
                        [#--<a href="#">Forgot your password?</a>--]
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
[/#escape]