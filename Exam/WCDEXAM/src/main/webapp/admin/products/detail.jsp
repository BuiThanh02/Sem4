
<%@ page import="com.example.wcdexam.entity.Phone" %>
<%
    Phone product = (Phone) request.getAttribute("product");
%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../inlclude/head.jsp">
    <jsp:param name="title" value="Product detail"/>
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
                    <h3 class="page-title"> Product Management </h3>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Product management</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Product detail</li>
                        </ol>
                    </nav>
                </div>
                <div class="row">
                    <div class="col-md-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h3 class="card-title">Customer Detail</h3>
                                <p class="text-success fs-6">Phone Id: <%=product.getId()%></p>
                                <p class="text-success fs-6">Phone Name: <%=product.getName()%></p>
                                <p class="text-success fs-6">Brand Id: <%=product.getBrandId()%></p>
                                <p class="text-success fs-6">Price: <%=product.getPrice()%></p>
                                <p class="text-success fs-6">Description: <%=product.getDescription()%></p>
                                <p class="text-success fs-6">Status: <%=product.getStatus()%></p>

                                <a href="/admin/products/list" class="btn btn-light mt-4">Back to list</a>
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