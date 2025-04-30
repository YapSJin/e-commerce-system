<%@ page import="java.util.*, Model.Reports, java.text.SimpleDateFormat" %>
<%
    request.setAttribute("activeAdminPage", "reports");
    request.setAttribute("pageTitle", "Reports");
    request.setAttribute("mainContentPage", "/admin/content/reports_content.jsp");
%>
<jsp:include page="/admin/admin_layout.jsp" />