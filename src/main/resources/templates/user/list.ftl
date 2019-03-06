[#ftl]
<b>User List:</b>

<br>
User size: ${users?size}
<div style="float:right"><a href="user/add">Add</a></div>
<table>
    <tr>
        <th>username</th>
        <th>password</th>
        <th>email</th>
        <th>desire calories</th>
        <th>desired recipe type</th>
        <th>fridge</th>
        <th></th>

    </tr>

    [#list users as user]
    <tr>
        <td>${user.username}</td>
        <td>${user.password}</td>
        <td>${user.email}</td>
        <td>${user.desiredCalories}</td>
        <td>${user.desiredRecipeType}</td>
        <td>${user.fridge}</td>
        <td><a href="/user/edit?id=${user.id?c}">Edit</a>
            <a href="/user/delete?id=${user.id?c}">Delete</a>
        </td>
    </tr>
    [/#list]
</table>
