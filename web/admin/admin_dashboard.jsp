<%-- /admin/admin_dashboard.jsp --%>
<%-- Set attributes for the layout template --%>
<%
    request.setAttribute("activeAdminPage", "dashboard");
    request.setAttribute("pageTitle", "Dashboard");
    // Specify the path to the content fragment for this page
    request.setAttribute("mainContentPage", "/admin/content/dashboard_content.jsp");
%>

<%-- Include the main layout template --%>
<jsp:include page="/admin/admin_layout.jsp" />
