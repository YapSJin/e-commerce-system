<%-- /admin/admin_promotions.jsp --%>
<%-- Set attributes for the layout template --%>
<%
    request.setAttribute("activeAdminPage", "promotions");
    request.setAttribute("pageTitle", "Promotion Management");
    // Specify the path to the content fragment for this page
    request.setAttribute("mainContentPage", "/admin/content/promotions_content.jsp");

    // --- TODO: Add backend logic here to fetch promotionList and set it as a request attribute ---
    // Example: List<Promotions> promotionList = promotionDAO.getAllPromotions();
    // request.setAttribute("promotionList", promotionList);
%>

<%-- Include the main layout template --%>
<jsp:include page="/admin/admin_layout.jsp" />
