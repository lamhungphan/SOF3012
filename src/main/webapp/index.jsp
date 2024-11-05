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
    <br>
    <label>Chọn role:</label>
    <input type="radio" name="role" value="Admin" ${param.role == 'Admin' ? 'checked' : ''}>Admin
    <input type="radio" name="role" value="User" ${param.role == 'User' ? 'checked' : ''}> User
    <input type="radio" name="role" value="All" ${param.role == 'All' ? 'checked' : ''}> All
    <br>
    <label>Tìm tên</label>
    <input type="text" name="filterName" placeholder="Tên muốn tìm" value="${param.filterName}">
    <button type="submit">Lọc</button>
    <br>
    <h3>
        <c:if test="${not empty error}">
            <p style="color:red">${error}</p>
        </c:if>
    </h3>
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
        <th>Favorites</th>
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

<hr>

<h2>Video Yêu Thích của ${item.fullname}</h2>
<table border="1" style="width: 100%">
    <thead>
    <tr>
        <th>Video Title</th>
        <th>Poster</th>
        <th>Description</th>
        <th>Like Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="favorite" items="${favorites}">
        <tr>
            <td>${favorite.video.title}</td>
            <td><img src="${favorite.video.poster}" alt="Poster" width="100"></td>
            <td>${favorite.video.description}</td>
            <td>${favorite.likeDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>