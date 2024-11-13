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
<c:url var="path" value="/video"/>
<form method="get" action="${path}/search">
    <input type="text" name="search-video" value="${keyword}" placeholder=" Video muốn tìm kiếm">
    <button type="submit">Tim kiem</button>
</form>
<h2>Bai 3</h2>
<table border="1" width="100%">
    <thead>
    <tr>
        <th>No.</th>
        <th>Title</th>
        <th>Views</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="video" items="${list}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${video.title}</td>
            <td>${video.views}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>
<h2>Bai 4</h2>
<table border="1" style="width: 100%">
    <thead>
    <tr>
        <th>Title</th>
        <th>Share</th>
        <th>First Share Date</th>
        <th>Last Share Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="favorite" items="${favoriteDetails}" varStatus="vs">
        <tr>
            <td>${favorite[0]}</td> <!-- Title -->
            <td>${favorite[1]}</td> <!-- Likes Count -->
            <td><fmt:formatDate value="${favorite[2]}" pattern="yyyy-MM-dd"/></td> <!-- First Like Date -->
            <td><fmt:formatDate value="${favorite[3]}" pattern="yyyy-MM-dd"/></td> <!-- Last Like Date -->
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>