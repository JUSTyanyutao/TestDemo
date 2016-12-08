<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(200);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<question>404 - 页面不存在</question>
</head>

<body>
	<h2>404 - 页面不存在.</h2>
	<p><a href="<c:link value="/"/>">返回首页</a></p>
</body>
</html>