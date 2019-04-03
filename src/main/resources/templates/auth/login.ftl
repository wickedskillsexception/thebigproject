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
    <script src="[@spring.url '/js/jquery.min.js' /] "></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="[@spring.url '/js/bootstrap.min.js' /] "></script>

</head>

[#escape x as x?html]
    <body style="background-image:url('/images/Background.jpg');">
    <div class="container">
        <a href="/"> <img src="[@spring.url '/images/logo.png' /]" width="100"/>
            <div class="d-flex justify-content-center h-100">
                <ol class="breadcrumb">
                    <li><a href="/">Home</a></li>
                    <li class="active">Login</li>
                </ol>

                <div class="card" style="margin-left:auto; margin-right:auto; width:400px">
                    <div class="card-header">
                        <h3>Sign In</h3>
                    </div>


                    <div class="panel-body">

                        [#if RequestParameters.error??]
                            <div>
                                <ul>
                                    <b style="color:red">
                                        [@spring.message 'invalid.username'/]
                                    </b>

                                </ul>
                            </div>
                        [/#if]


                        <form action="/login" method="POST">
                            <div class="form-group">
                                <label for="firstName"></label>
                                <input type="text"
                                       class="form-control" id="username" name="username"
                                       placeHolder="Username" "/>
                            </div>
                            <div class="form-group">
                                <label for="lastName"></label>
                                <input type="password"
                                       class="form-control" id="password" name="password"
                                       placeHolder="Password"/>
                            </div>


                            <div class="container-fluid">
                                <div class="collapse navbar-collapse">
                                    <ul class="nav navbar-nav navbar-right">

                                        <li>
                                            <button type="submit" class="btn float-right login_btn">LOGIN</button>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <br/> <input type="hidden" class="form-control" id="id" value="0"/>
                        </form>
                    </div>
                </div>
            </div>

    </div>
    </body>
[/#escape]