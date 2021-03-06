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
                <h5 class="mb-0">You have :${recipes?size} recipes to choose from!
                    <a href="/recipe/add" class="btn btn-primary" style="background-color: rgb(86, 198, 198);" type="button">Add</a>
                </h5>
            </div>
            <div class="card-body">

                [#if errors??]
                    [#list errors as error]
                        <span style="color:red"> ${error}</span>
                        <br>
                    [/#list]
                [/#if]

                </table>
                <div class="table-responsive">
                    <table id="example" class="table table-striped table-hover table-borderless ">
                        <thead>
                        <tr align="center">
                            <th align="center" scope="col">Image</th>
                            <th align="center" scope="col">Name</th>
                            <th align="center" scope="col"></th>
                        </tr>
                        </thead>
                        <tbody id= "myPager" style="align-content: center">
                        [#list recipes as recipe]
                        <tr>
                            <td scope="row"><img src="${recipe.image}" alt="Avatar"
                                                 style="border-radius: 50%; width: 50px; height: 50px"/></td>
                            <td align="center" scope="row">${recipe.name}</td>

                            <td align="center" scope="row">

                                <a class="btn btn-primary" style="background-color: rgb(86, 198, 198);" role="button"
                                   href="/recipe/view?id=${recipe.id?c}">View</a>
                                <a class="btn btn-primary" style="background-color: rgb(86, 198, 198);" role="button"
                                   href="/recipe/delete?id=${recipe.id?c}">Delete</a>
                                <a class="btn btn-primary" style="background-color: rgb(86, 198, 198);" role="button"
                                   href="/recipe/edit?id=${recipe.id?c}">Edit</a>
                            </td>
                        </tr>
                        [/#list]
                        </tbody>
                        <tfoot>

                        </tfoot>
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


    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });
    </script>

[/#escape]