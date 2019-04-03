[#ftl]
[#import "/spring.ftl" as spring /]
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet">
    <link href="[@spring.url '/css/pages.css' /]" rel="stylesheet">
    <link href="[@spring.url '/css/navigation-with-button.css' /]" rel="stylesheet">
    <link href="[@spring.url '/css/styles.css' /]" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="[@spring.url '/js/jquery.min.js' /]" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="[@spring.url '/js/bootstrap.min.js' /] "></script>
</head>

[#escape x as x?html]
    <body style="background-image:url('/images/Background.jpg');">
    <div class="container">
        <nav class="navbar navbar-light navbar-expand-md navigation-clean-button">
            <div class="container"><a class="navbar-brand" href="/"> <img src="[@spring.url '/images/Logo1.png' /]" height="80 px" width="170px"/></a>
                [#if user??]
                    <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                                class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse"
                         id="navcol-1">
                        <ul class="nav navbar-nav mr-auto">

                            <li class="nav-item" role="presentation"><a class="nav-link" href="/user">Users</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/ingredient">Ingredients</a></li>

                        </ul>
                        <span class="navbar-text actions"> <a class="btn btn-light action-button" role="button" href="/logout">Logout</a></span>

                    </div>
                [#else]
                    <span class="navbar-text actions"> <a class="btn btn-light action-button" role="button" href="/login">Login</a></span>
                [/#if]
            </div>
        </nav>
    </div>
    <div></div>
    [#--<div class="container"></div>--]
    </body>
[/#escape]

