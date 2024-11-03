<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh Sách Video Yêu Thích</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
    </style>
</head>
<body>

<h2>Danh Sách Video Yêu Thích của ${user.fullname}</h2>

<c:if test="${not empty favorites}">
    <table>
        <thead>
        <tr>
            <th>STT</th>
            <th>Tiêu Đề Video</th>
            <th>Ngày Yêu Thích</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="favorite" items="${favorites}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${favorite.video.title}</td>
                <td><fmt:formatDate value="${favorite.likeDate}" pattern="dd-MM-yyyy" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty favorites}">
    <p>Người dùng này chưa có video yêu thích nào.</p>
</c:if>

</body>
</html>
