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
    <link href="[@spring.url '/css/footer-basic.css' /]" rel="stylesheet">
    <link href="[@spring.url '/css/simple-slider.css' /]" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/3.3.1/css/swiper.min.css" rel="stylesheet">
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
            <div class="container"><a class="navbar-brand" href="/"> <img src="[@spring.url '/images/Logo1.png' /]"
                                                                          height="80 px" width="180px"/></a>
                [#if user??]
                    <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                                class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse"
                         id="navcol-1">
                        <ul class="nav navbar-nav mr-auto">

                            <li class="nav-item" role="presentation"><a class="nav-link" href="/">Home</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/user">Users</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                                                        href="/ingredient">Ingredients</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/fridge">Fridge</a></li>

                        </ul>
                        <span class="navbar-text actions"> <a class="btn btn-light action-button" role="button"
                                                              href="/logout">Logout</a></span>

                    </div>
                [#else]
                    <span class="navbar-text actions"> <a class="btn btn-light action-button" role="button"
                                                          href="/login">Login</a></span>
                [/#if]
            </div>
        </nav>
        <div class="article-list">
            <div class="card">
                <div class="intro">
                    <h2 class="text-center" style="color: #ffffff;">Latest Articles</h2>
                    <p class="text-center" style="color: #ffffff;">Nunc luctus in metus eget fringilla. Aliquam sed justo ligula. Vestibulum nibh erat, pellentesque ut laoreet vitae.</p>
                </div>
                <div class="row articles">
                    <div class="col-sm-6 col-md-4 item"><img class="img-fluid" src="[@spring.url '/images/index2.jpg' /]" /></a>
                        <h3 class="name" style="color: #ffffff;">Article Title</h3>
                        <p class="description" style="color: #ffffff;">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent aliquam in tellus eu gravida. Aliquam varius finibus est, interdum justo suscipit id.</p><a href="#" class="action"><i class="fa fa-arrow-circle-right"></i></a></div>
                    <div
                            class="col-sm-6 col-md-4 item"><img class="img-fluid"src="[@spring.url '/images/index1.jpg' /]" /></a>
                        <h3 class="name" style="color: #ffffff;">Article Title</h3>
                        <p class="description" style="color: #ffffff;">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent aliquam in tellus eu gravida. Aliquam varius finibus est, interdum justo suscipit id.</p><a href="#" class="action"><i class="fa fa-arrow-circle-right"></i></a></div>
                    <div class="col-sm-6 col-md-4 item"><img class="img-fluid" src="[@spring.url '/images/index3.jpg' /]" /></a>
                        <h3 class="name" style="color: #ffffff;">Article Title</h3>
                        <p class="description" style="color: #ffffff;">Aenean tortor est, vulputate quis leo in, vehicula rhoncus lacus. Praesent aliquam in tellus eu gravida. Aliquam varius finibus est, interdum justo suscipit id.</p><a href="#" class="action"><i class="fa fa-arrow-circle-right"></i></a></div>
                </div>
            </div>
        </div>
        <div class="footer-basic">
            <footer>
                <ul class="list-inline">
                    <li class="list-inline-item"><a href="/">Home</a></li>
                    <li class="list-inline-item"><a href="#">Services</a></li>
                    <li class="list-inline-item"><a href="#">About</a></li>
                    <li class="list-inline-item"><a href="#">Terms</a></li>
                    <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
                </ul>
                <p class="copyright">Yumm! 2019</p>
            </footer>
        </div>
    </div>
    </body>
[/#escape]

