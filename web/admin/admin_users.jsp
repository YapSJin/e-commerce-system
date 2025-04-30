<%-- /admin/admin_users.jsp --%>
<%-- Set attributes for the layout template --%>
<%
    request.setAttribute("activeAdminPage", "users");
    request.setAttribute("pageTitle", "User Management");
    // Specify the path to the content fragment for this page
    request.setAttribute("mainContentPage", "/admin/content/users_content.jsp");

    // --- TODO: Add backend logic here to fetch userList and set it as a request attribute ---
    // Example: List<Customers> userList = userDAO.getAllCustomers();
    // request.setAttribute("userList", userList);
%>

<%-- Include the main layout template --%>
<jsp:include page="/admin/admin_layout.jsp" />
