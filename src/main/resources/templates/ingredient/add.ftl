[#ftl]
[#if errors??]
    [#list errors as error]
       <span style="color:red"> ${error}</span>
    <br>
    [/#list]
[/#if]
<form method="post" action="/ingredient/save">

    <th></th>
    Name: <input name="name" type="input" value="${ingredient.name!''}">
    <br>
    Unit: <input name="unit" type="input" value="${ingredient.unit!''}">
    <br>



    [#if ingredient.id??]
        <input name="id" type="hidden" value="$ingredient.id?c}"/>
    [/#if]
    <input value="save" type="submit"/>
</form>

