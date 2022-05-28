<%@ page import="java.util.List" %>
<%@ page import="com.example.wcdexam.entity.Phone" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.wcdexam.entity.Brand" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/12/2022
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Phone> list = (List<Phone>)request.getAttribute("list");
    if (list==null){
        list = new ArrayList<>();
    }
    List<Brand> listB = (List<Brand>)request.getAttribute("listB");
    if (listB==null){
        listB = new ArrayList<>();
    }
%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../inlclude/head.jsp">
    <jsp:param name="title" value="List phone"/>
</jsp:include>
<body>
<div class="container-scroller">
    <!-- partial:../../partials/_navbar.html -->
    <jsp:include page="../inlclude/navbar.jsp"></jsp:include>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
        <!-- partial:../../partials/_sidebar.html -->
        <jsp:include page="../inlclude/sidebar.jsp"></jsp:include>
        <!-- partial -->
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="page-header">
                    <h3 class="page-title"> Phone Management </h3>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Phone management</a></li>
                            <li class="breadcrumb-item active" aria-current="page">List phones</li>
                        </ol>
                    </nav>
                </div>
                <div class="row">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">List Phone</h4>
                                <a href="/admin/products/create" class="btn btn-success">Create phone</a>

                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Phone Name</th>
                                        <th>BrandName</th>
                                        <th>Price</th>
                                        <th>Description</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <%
                                        for (Phone pr: list) {
                                    %>
                                    <tr>
                                        <th><%=pr.getId()%></th>
                                        <td><%=pr.getName()%></td>
                                        <td><%=listB.get(2).getName()%></td>
                                        <td><%=pr.getPrice()%></td>
                                        <td >
                                        <%=pr.getDescription()%>
                                        </td>
                                        <td>
                                            <a href="/admin/products/detail?id=<%=pr.getId()%>"><i class="mdi mdi-glasses size-big"></i></a>
                                            <a href="/admin/products/delete?id=<%=pr.getId()%>" onclick="return confirm('Are you sure?')"><i class="mdi mdi-delete size-big"></i></a>

                                        </td>
                                    </tr>
                                    <% }%>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <jsp:include page="../inlclude/footer.jsp"></jsp:include>
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
</div>
<!-- container-scroller -->
<jsp:include page="../inlclude/scripts.jsp"></jsp:include>
</body>
</html>