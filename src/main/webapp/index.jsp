<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form with Bootstrap</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">

    <div class="card mt-4">
        <table class="table table-bordered">
            <thead class="table-primary">
            <tr>
                <th>Id</th>
                <th>Fullname</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.fullname}</td>
                    <td>${item.email}</td>
                    <td>${item.password}</td>
                    <td>${item.admin}</td>
                    <td><a href="${pageContext.request.contextPath}/index?findId=${item.id}" class="text-decoration-none">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagination mt-4 d-flex justify-content-center">
            <c:if test="${totalPages > 1}">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="${pageContext.request.contextPath}/index?page=${i}"
                       class="btn btn-outline-primary mx-1 ${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
