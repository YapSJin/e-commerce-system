<%-- /admin/admin_products.jsp --%>
<%-- Set attributes for the layout template --%>
<%
    request.setAttribute("activeAdminPage", "products");
    request.setAttribute("pageTitle", "Product Management");
    // Specify the path to the content fragment for this page
    request.setAttribute("mainContentPage", "/admin/content/products_content.jsp");

    // --- TODO: Add backend logic here to fetch productList and set it as a request attribute ---
    // Example: List<Products> productList = productDAO.getAllProducts();
    // request.setAttribute("productList", productList);
%>

<%-- Include the main layout template --%>
<jsp:include page="/admin/admin_layout.jsp" />
