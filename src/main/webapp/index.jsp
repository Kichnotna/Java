<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<div align="center">
    <h1>Welcome to Homepage</h1>

    <form action="/fileList" method="get">
        <label>
            <input type="text" name="word"/>
        </label>
        <label>
            <button>request</button>
        </label>
    </form>
    <c:if test="${requestScope.fileList.size() > 0}">
        <table id="files" border="1" cellpadding="10">
            <tr>
                <td align="center">Path</td>
                <td align="center">Count</td>
            </tr>
            <c:forEach var="item" items="${requestScope.fileList}">
                <tr>
                    <td align="center"><a href="/download/${item.key}"><c:out value="${item.key}"/></a></td>
                    <td align="center"><c:out value="${item.value}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br />
    </c:if>
</div>
</body>
</html>