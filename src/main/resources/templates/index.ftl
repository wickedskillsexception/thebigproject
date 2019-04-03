[#ftl]
[#import "/spring.ftl" as spring /]
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet">
    <link href="[@spring.url '/css/page_style.css' /]" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="[@spring.url '/js/jquery.min.js' /] "></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="[@spring.url '/js/bootstrap.min.js' /] "></script>
</head>
[#--https://codepen.io/Roemerdt/pen/NxPjVB/--]
[#escape x as x?html]
    <body style="background-image:url('/images/Background.jpg');">

    <div class="container">
        <a href="/"> <img src="[@spring.url '/images/logo.png' /]" width="100"/></a>

        <ol class="breadcrumb">
            <li class="active"><a href="/index">Home</a></li>

            [#if user??]
                <li><a href="/user">User</a></li>
                <li><a href="/ingredient">Ingredients</a></li>
                <li><a href="/fridge">Fridges</a></li>
                <li><a href="/logout">Logout</a>
                </li>
            [#else]
                <li><a href="/login">Login</a></li>
            [/#if]
        </ol>
    </div>
    </div>

    </body>



[/#escape]