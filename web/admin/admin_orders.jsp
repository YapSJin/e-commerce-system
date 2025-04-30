<%-- /admin/admin_orders.jsp --%>
<%-- Set attributes for the layout template --%>
<%
    request.setAttribute("activeAdminPage", "orders");
    request.setAttribute("pageTitle", "Order Management");
    // Specify the path to the content fragment for this page
    request.setAttribute("mainContentPage", "/admin/content/orders_content.jsp");

    // --- TODO: Add backend logic here to fetch orderList and set it as a request attribute ---
    // Example: List<Orders> orderList = orderDAO.getAllOrders();
    // request.setAttribute("orderList", orderList);
%>

<%-- Include the main layout template --%>
<jsp:include page="/admin/admin_layout.jsp" />
