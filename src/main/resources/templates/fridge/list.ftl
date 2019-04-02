[#ftl]
<b>Fridge List:</b>

<br>
Fridge size: ${fridges?size}
<div style="float:right"><a href="ingredient/add">Add</a></div>
<table>
    <tr>
        <th>ingredientList</th>
        <th>userId</th>
        <th></th>

    </tr>

    [#list fridges as fridge]
    <tr>
        <td>${fridge.userId}</td>
        <td><a href="/ingredient/edit?id=${fridge.id?c}">Edit</a>
            <a href="/ingredient/delete?id=${fridge.id?c}">Delete</a>
        </td>
    </tr>
    [/#list]
</table>
