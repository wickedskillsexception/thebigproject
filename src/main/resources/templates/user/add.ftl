[#ftl]
[#if errors??]
    [#list errors as error]
       <span style="color:red"> ${error}</span>
    <br>
    [/#list]
[/#if]
<form method="post" action="/user/save">

    Username: <input name="username" type="input" value="${user.username!''}">
    <br>
    Password: <input name="password" type="input"  value="${user.password!''}">
    <br>
    E-mail: <input name="email" type="input" value="${user.email!''}">
    <br>


    [#if user.id??]
        <input name="id" type="hidden" value="$user.id?c}"/>
    [/#if]
    <input value="save" type="submit"/>
</form>

