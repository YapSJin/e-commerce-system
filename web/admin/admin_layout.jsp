<%-- /WEB-INF/views/admin/admin_layout.jsp --%>
<%@ page import="java.util.List, Model.Products, Model.Categories"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page import="Model.Staff" %> --%> <%-- Needed if checking roles directly in layout --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%-- Dynamically set title based on the specific page --%>
    <title>Admin Panel - ${param.pageTitle != null ? param.pageTitle : 'Dashboard'}</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin_dashboard_styles.css"> <%-- Adjust path as needed --%>

    <%--
    <%
        // --- Security Check Placeholder ---
        // Staff loggedInStaff = (Staff) session.getAttribute("loggedInStaff");
        String adminUsername = "Admin User"; // Placeholder
        // boolean isManager = false; // Placeholder
        // if (loggedInStaff == null) {
        //     response.sendRedirect(request.getContextPath() + "/login.jsp");
        //     return;
        // } else {
        //     adminUsername = loggedInStaff.getUsername();
        //     // isManager = loggedInStaff.getIsManager();
        // }
    %>
    --%>
</head>
<body>
    <%-- Consider including navbar as a separate component if used elsewhere --%>
    <%-- <jsp:include page="/WEB-INF/views/components/navbar.jsp" /> --%>
    <nav class="navbar navbar-expand-lg bg-body-tertiary mb-4 shadow-sm">
      <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/admin_dashboard.jsp"> <%-- Link back to dashboard --%>
            <i class="fas fa-shield-alt me-2"></i>Admin Panel
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#adminNavbar" aria-controls="adminNavbar" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="adminNavbar">
          <ul class="navbar-nav ms-auto mb-2 mb-lg-0 align-items-center">
            <li class="nav-item me-3">
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" role="switch" id="darkModeSwitch">
                    <label class="form-check-label" for="darkModeSwitch"><i id="darkModeIcon" class="fas fa-moon"></i></label>
                </div>
            </li>
            <li class="nav-item">
              <span class="navbar-text me-3">
                <%-- Welcome, <%= adminUsername %>! --%>
                Welcome, Admin User! </span>
            </li>
            <li class="nav-item">
              <%-- <a class="btn btn-outline-secondary btn-sm" href="${pageContext.request.contextPath}/logout">Logout</a> --%>
              <a class="btn btn-outline-secondary btn-sm" href="#">Logout</a> </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2 col-md-3">
                <%-- Include the sidebar component --%>
                <jsp:include page="/admin/admin_sidebar.jsp" />
            </div>
            <div class="col-lg-10 col-md-9">
                <%-- Main content area where specific page content will be loaded --%>
                <div class="bg-white p-4 rounded shadow-sm mb-4">
                    <%-- Include the actual page content passed via parameter --%>
                    <%-- Ensure the including page sets the 'mainContentPage' attribute --%>
                    <jsp:include page="${mainContentPage}" />
                </div>
            </div>
            </div> </div> <div class="toast-container position-fixed top-0 end-0 p-3">
      </div>

    <div class="modal fade" id="addUserModal" tabindex="-1" aria-labelledby="addUserModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="addUserModalLabel"><i class="fas fa-user-plus me-2"></i>Add New User</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form id="addUserForm">
              <div class="mb-3"> <label for="addUserName" class="form-label">Username</label> <input type="text" class="form-control" id="addUserName" required> </div>
              <div class="mb-3"> <label for="addUserEmail" class="form-label">Email address</label> <input type="email" class="form-control" id="addUserEmail" required> </div>
              <div class="mb-3"> <label for="addUserPassword" class="form-label">Password</label> <input type="password" class="form-control" id="addUserPassword" required> </div>
              <div class="mb-3"> <label for="addUserRole" class="form-label">Role</label> <select class="form-select" id="addUserRole" required> <option value="Customer" selected>Customer</option> </select> </div>
            </form>
          </div>
          <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> <button type="submit" class="btn btn-primary" form="addUserForm">Save User</button> </div>
        </div>
      </div>
    </div>

    <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header"> <h1 class="modal-title fs-5" id="addProductModalLabel"><i class="fas fa-plus-circle me-2"></i>Add New Product</h1> <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> </div>
          <div class="modal-body">
            <form id="addProductForm">
              <div class="mb-3"> <label for="addProductName" class="form-label">Product Name</label> <input type="text" class="form-control" id="addProductName" required> </div>
              <div class="mb-3"> <label for="addProductCategory" class="form-label">Category</label> <input type="text" class="form-control" id="addProductCategory"> </div>
              <div class="mb-3"> <label for="addProductPrice" class="form-label">Price (RM)</label> <input type="number" step="0.01" min="0" class="form-control" id="addProductPrice" required> </div>
              <div class="mb-3"> <label for="addProductStock" class="form-label">Stock Quantity</label> <input type="number" min="0" class="form-control" id="addProductStock" required> </div>
              <div class="mb-3"> <label for="addProductDescription" class="form-label">Description</label> <textarea class="form-control" id="addProductDescription" rows="3"></textarea> </div>
              <div class="mb-3"> <label for="addProductPicture" class="form-label">Product Picture</label> <input class="form-control" type="file" id="addProductPicture" accept="image/png, image/jpeg, image/webp"> <div id="pictureHelp" class="form-text">Upload PNG, JPG, or WEBP images.</div> </div>
            </form>
          </div>
          <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> <button type="submit" class="btn btn-primary" form="addProductForm">Save Product</button> </div>
        </div>
      </div>
    </div>

    <div class="modal fade" id="addPromotionModal" tabindex="-1" aria-labelledby="addPromotionModalLabel" aria-hidden="true">
       <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header"> <h1 class="modal-title fs-5" id="addPromotionModalLabel"><i class="fas fa-tag me-2"></i>Add New Promotion</h1> <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> </div>
          <div class="modal-body">
            <form id="addPromotionForm">
              <div class="mb-3"> <label for="addPromoCode" class="form-label">Promo Code</label> <input type="text" class="form-control" id="addPromoCode" required> </div>
              <div class="mb-3"> <label for="addPromoDiscount" class="form-label">Discount Percentage (%)</label> <input type="number" step="0.1" min="0" max="100" class="form-control" id="addPromoDiscount" required> </div>
              <div class="mb-3"> <label for="addPromoValidFrom" class="form-label">Valid From</label> <input type="date" class="form-control" id="addPromoValidFrom" required> </div>
              <div class="mb-3"> <label for="addPromoValidTo" class="form-label">Valid To</label> <input type="date" class="form-control" id="addPromoValidTo" required> </div>
            </form>
          </div>
          <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> <button type="submit" class="btn btn-primary" form="addPromotionForm">Save Promotion</button> </div>
        </div>
      </div>
    </div>

    <div class="modal fade" id="addStaffModal" tabindex="-1" aria-labelledby="addStaffModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header"> <h1 class="modal-title fs-5" id="addStaffModalLabel"><i class="fas fa-user-plus me-2"></i>Add New Staff Member</h1> <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> </div>
          <div class="modal-body">
            <form id="addStaffForm">
              <div class="mb-3"> <label for="addStaffName" class="form-label">Username</label> <input type="text" class="form-control" id="addStaffName" required> </div>
              <div class="mb-3"> <label for="addStaffEmail" class="form-label">Email address</label> <input type="email" class="form-control" id="addStaffEmail" required> </div>
              <div class="mb-3"> <label for="addStaffPassword" class="form-label">Password</label> <input type="password" class="form-control" id="addStaffPassword" required> </div>
              <div class="mb-3"> <label class="form-label">Role</label> <div class="form-check"> <input class="form-check-input" type="radio" name="staffRole" id="roleStaff" value="staff" checked> <label class="form-check-label" for="roleStaff"> Staff </label> </div> <div class="form-check"> <input class="form-check-input" type="radio" name="staffRole" id="roleManager" value="manager"> <label class="form-check-label" for="roleManager"> Manager </label> </div> </div>
            </form>
          </div>
          <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> <button type="submit" class="btn btn-primary" form="addStaffForm">Save Staff</button> </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <script src="${pageContext.request.contextPath}/js/admin/dashboard_data.js"></script> <%-- Adjust path as needed --%>

</body>
</html>
