<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="主页 - 梦想e拍" />
    </c:import>
<body>
  <div class="wrapper">
      <c:import url="/WEB-INF/layouts/nav.jsp" />
      <section>
          <div class="answer-wrapper">
              欢迎使用达膜贴膜运营管理系统
          </div>
      </section>
      <c:import url="/WEB-INF/layouts/content_footer.jsp" />
  </div>
<c:import url="/WEB-INF/layouts/footer.jsp" />
</body>
</html>