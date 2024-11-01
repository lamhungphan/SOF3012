<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Department Management</title>
</head>
<body>
<c:url var="path" value="/user"/>
<!-- FORM -->
<form method="post" action="${path}/index">
    <br>
    <label>Id</label>
    <input name="id" value="${item.id}">
    <br>
    <label>Fullname</label>
    <input name="fullname" value="${item.fullname}">
    <br>
    <label>Password</label>
    <input name="password" value="${item.password}">
    <br>
    <label>Email</label>
    <input name="email" value="${item.email}">
    <br>
    <input type="checkbox" name="admin" ${item.admin ? 'checked' : ''} value="true">
    <label>Admin:</label><br>
    <button formaction="${path}/create">Create</button>
    <button formaction="${path}/update">Update</button>
    <button formaction="${path}/delete">Delete</button>
    <button formaction="${path}/reset">Reset</button>
    <label>Chon role:</label>
    <input type="radio" name="role" value="Admin" ${item.admin == 'Admin' ? 'checked' : ''}>Admin
    <input type="radio" name="role" value="User" ${item.admin == 'User' ? 'checked' : ''}> User
    <input type="radio" name="role" value="All"> All
    <button type="submit">Lọc</button>
    <br>
</form>
<hr>
<!-- TABLE -->
<table border="1" style="width: 100%">
    <thead>
    <tr>
        <th>No.</th>
        <th>Id</th>
        <th>Password</th>
        <th>Fullname</th>
        <th>Email</th>
        <th>Role</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${list}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${d.id}</td>
            <td>${d.password}</td>
            <td>${d.fullname}</td>
            <td>${d.email}</td>
            <td>${d.admin ? 'Admin' : 'User'}</td>
            <td><a href="${path}/edit/${d.id}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>