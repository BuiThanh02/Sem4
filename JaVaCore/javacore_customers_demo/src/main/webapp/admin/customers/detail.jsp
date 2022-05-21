<%@ page import="com.example.javacore_customers_demo.entity.Customer" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 5/12/22
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Customer customer = (Customer) request.getAttribute("customer");
    int action = (int) request.getAttribute("action");
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
    String url = "/admin/customers/create";
    if(action == 2){
        url = "/admin/customers/edit";
    }
    if(errors == null){
        errors = new HashMap<>();
    }
%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../includes/head.jsp"></jsp:include>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../includes/navbar.jsp"></jsp:include>

    <!-- Main Sidebar Container -->
    <jsp:include page="../includes/sidebar.jsp"></jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Student management</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="/admin/students/list">Student Management</a></li>
                            <li class="breadcrumb-item active">Create new</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div style="width: 400px; margin-left: auto; margin-right: auto; border: 2px solid black; margin-top: 100px">

                            <div class="w3-center">
                                <h3>Account Information</h3>
                                <img src="download%20(1).jpg" alt="Avatar" style="width:200px; height: 250px">



                            </div>
                            <div style="margin-left: 50px">
                                <p>ID: <%=request.getAttribute("id")%></p>
                                <p>Name: <%=request.getAttribute("name")%></p>
                                <p>Email: <%=request.getAttribute("phone")%></p>
                                <p>Password: <%=request.getAttribute("image")%></p>
                                <p>Gender: <%=request.getAttribute("birthday")%></p>
                            </div>


                        </div>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../includes/footer.jsp"></jsp:include>
    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<jsp:include page="../includes/script.jsp"></jsp:include>
<script>
    document.addEventListener('DOMContentLoaded', function (){
        $('#reservationdate').datetimepicker({
            format: 'YYYY-MM-DD'
        });
    })
</script>
</body>
</html>