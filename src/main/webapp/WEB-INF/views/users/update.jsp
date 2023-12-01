<%--
  Created by IntelliJ IDEA.
  User: ad
  Date: 29/11/2023
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
  <c:import url="../layout/navbar.jsp"/>
  <div class="col-12 col-md-12">
    <div class="card">
      <div class="card-header">
        Update user
      </div>
      <div class="card-body">
        <form action="${pageContext.request.contextPath}/users?action=update&id=<c:out value="${user.getId()}"/>" method="post">
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Name</label>
            <input type="text" name="name" value="<c:out value="${user.getName()}"/>" class="form-control" id="" aria-describedby="emailHelp">
          </div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" name="email" class="form-control" disabled value="<c:out value="${user.getEmail()}"/>" id="exampleInputEmail1" aria-describedby="emailHelp">
          </div>
          <div class="mb-3">
            <label for="" class="form-label">Phone</label>
            <input type="text" name="phone" value="<c:out value="${user.getPhone()}"/>" class="form-control" id="phone">
          </div>
          <div class="mb-3">
            <label for="" class="form-label">Address</label>
            <input type="text" name="address" value="<c:out value="${user.getAddress()}"/>" class="form-control" id="address">
          </div>
          <div class="mb-3">
            <label for="" class="form-label">Role</label>
            <select name="role" id="role">
              <option <c:if test="${user.getRole() == 'admin'}"> selected </c:if> value="admin">Admin</option>
              <option <c:if test="${user.getRole() == 'user'}"> selected </c:if>  value="user">User</option>
            </select>
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
          <a href="/users" class="btn btn-info">Cancel</a>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
