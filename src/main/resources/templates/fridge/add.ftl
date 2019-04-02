[#ftl]
[#if errors??]
    [#list errors as error]
       <span style="color:red"> ${error}</span>
    <br>
    [/#list]
[/#if]
<form method="post" action="/ingredient/save">

    <th></th>
    Ingredient List: <input name="ingredientList" type="input" value="${fridge.ingredientList!''}">
    <br>
    User ID: <input name="userId" type="input" value="${fridge.userId!''}">
    <br>



    [#if fridge.id??]
        <input name="id" type="hidden" value="$fridge.id?c}"/>
    [/#if]
    <input value="save" type="submit"/>
</form>

