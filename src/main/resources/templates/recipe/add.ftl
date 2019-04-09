[#ftl]
[#import "/spring.ftl" as spring /]
<head>
    <title>User</title>
    [#include '/bootstrap_header.ftl']
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
        <div class="card">
            <div class="card-header">
            </div>
            <div class="card-body">

                [#if errors??]
                    [#list errors as error]
                        <span style="color:red"> ${error}</span>
                        <br>
                    [/#list]
                [/#if]

                <div class="panel-body" style="width: 500px;">
                    <form method="post" action="/recipe/save">
                        <h2 class="text-center" style=" color: #fdfffb;">Add recipe</h2>
                        <div class="form-group"><input class="form-control" type="text" name="name"
                                                       placeholder="Name" value="${recipe.name!''}"/></div>
                        <div class="form-group"><input class="form-control" type="text" name="preparation"
                                                       placeholder="Preparation" value="${recipe.preparation!''}"/></div>
                        <div class="form-group"><input class="form-control" type="text" name="preparationTime"
                                                       placeholder="Preparation time" value="${recipe.preparationTime!''}"/></div>
                        <div class=" form-group"><input class="form-control" type="text" name="recipeType"
                                                        placeholder="Recipe Type" value="${recipe.recipeTypes!''}"/></div>
                        <div class="form-group"><input class="form-control is-invalid" type="text" name="image"
                                                       placeholder="Image" value="${recipe.image!''}"/></div>
                        <div class="form-group"><input class="form-control is-invalid" type="text" name="smartPoints"
                                                       placeholder="Smart points" value="${recipe.smartPoints!''}"/></div>

                        <div class="form-group">
                            [#if recipe.id??]
                                <input name="id" type="hidden" value="${recipe.id?c}"/>
                            [/#if]
                            <button class="btn btn-primary" type="submit">save</button>
                        </div>
                    </form>
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
    [#include '/bootstrap_footer.ftl']
    </body>
[/#escape]