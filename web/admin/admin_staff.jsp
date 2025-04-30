<%@ page import="Model.Users" %>

<%
    request.setAttribute("activeAdminPage", "staff");
    request.setAttribute("pageTitle", "Staff Management");
    request.setAttribute("mainContentPage", "/admin/content/staff_content.jsp");

    // Check if the logged-in user is a manager
    Users user = (Users) session.getAttribute("user");
    if (user == null || !"manager".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/admin/admin_dashboard.jsp");
        return;
    }
%>

<jsp:include page="/admin/admin_layout.jsp" />