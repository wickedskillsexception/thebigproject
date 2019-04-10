[#ftl]
[#import "/spring.ftl" as spring /]
<head>
    <title>User</title>
    [#include '../bootstrap_header.ftl']
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
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/fridge?user_email=${user}">MyFridge</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/recipe">Recipes</a></li>
                        </ul>
                        [#if user??]
                            <div style="float: right; color: grey"><b>Hello, ${user}!</b></div>
                        [/#if]
                        <span class="navbar-text actions"> <a class="btn btn-light action-button" role="button"
                                                              href="/logout">Logout</a></span>

                    </div>
                [#else]
                    <span class="navbar-text actions"> <a class="btn btn-light action-button" role="button"
                                                          href="/login">Login</a></span>
                [/#if]
            </div>
        </nav>
        <div class="card" style="height:auto; min-height: 1000px">
            <div class="card-header">
            </div>
            <div class="card-body">

                [#if errors??]
                    [#list errors as error]
                        <span style="color:red"> ${error}</span>
                        <br>
                    [/#list]
                [/#if]

                <div class="table-responsive">
                    <table class="table table-striped table-hover table-borderless">
                        <tr align="center">
                            <th align="center" scope="col"></th>
                            <th align="center" scope="col"></th>
                        </tr>


                        <tbody style="align-content: center">

                        <input name="id" type="hidden" value="${recipe}"/>
                        <tr>
                            <td scope="row"><img src="${recipe.image}" alt="Avatar"
                                                 style="border-radius: 50%; width: 50px; height: 50px"/></td>
                            <td align="center" scope="row">${recipe.name}</td>

                        </tr>

                        <tr>
                            <td align="center" scope="col">Preparation time</td>
                            <td align="center" scope="row">${recipe.preparationTime}</td>
                        </tr>

                        <tr>
                            <td align="center" scope="col">Ingredients</td>
                            <td align="center" scope="row">${recipe.ingredients}</td>
                        </tr>

                        <tr>
                            <td align="center" scope="col">Preparation</td>
                            <td align="center" scope="row">${recipe.preparation}</td>
                        </tr>


                        <tr>
                            <td align="center" scope="col">Recipe Type</td>
                            <td align="center" scope="row">${recipe.recipeTypes}</td>
                        </tr>

                        <tr>
                            <td align="center" scope="col">Smart points</td>
                            <td align="center" scope="row">${recipe.smartPoints}</td>
                        </tr>

                        </tbody>
                    </table>
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
    </div>
    [#include '../bootstrap_footer.ftl']
    </body>
[/#escape]