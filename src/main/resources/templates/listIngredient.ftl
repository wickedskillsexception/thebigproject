[#ftl]
<b>Ingredient List:</b>

<br>
Ingredient size: ${ingredients?size}
<div style="float:right"><a href="ingredient/add">Add</a></div>
<table>
    <tr>
        <th>name</th>
        <th>unit</th>
        <th>quantity</th>
        <th></th>

    </tr>

    [#list ingredients as ingredient]
    <tr>
        <td>${ingredient.name}</td>
        <td>${ingredient.unit}</td>
        <td>${ingredient.quantity}</td>
        <td><a href="/ingredient/edit?id=${ingredient.id?c}">Edit</a>
            <a href="/ingredient/delete?id=${ingredient.id?c}">Delete</a>
        </td>
    </tr>
    [/#list]
</table>
