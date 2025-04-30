<%-- /WEB-INF/views/admin/content/orders_content.jsp --%>
<%-- This file contains only the content specific to the Order Management page --%>
<%@ page import="java.util.List, Model.Reviews" %>
<h2 class="mb-3 border-bottom pb-2 text-body"><i class="fas fa-receipt"></i> Reviews Management</h2>

<div class="table-responsive">
	<table class="table table-striped table-hover table-bordered align-middle">
		<thead class="table-light">
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th>Product</th>
				<th>Rating</th>
				<th>Review</th>
				<th>Review Date</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<Reviews> reviews = (List<Reviews>) request.getAttribute("reviews");
				if (reviews != null) {
					for (Reviews review : reviews) {
			%>
			<tr>
				<td><%= review.getId() %></td>
				<td><%= review.getUserId().getUsername() %></td>
				<td><%= review.getProductId().getName() %></td>
				<td><%= review.getRating() %></td>
				<td><%= review.getReview() %></td>
				<td><%= review.getReviewDate() %></td>
				<td>
					<a href="${pageContext.request.contextPath}/product?id=<%= review.getProductId().getId() %>&tab=reviews" class="btn btn-sm btn-success action-btn" title="View Details"><i class="fas fa-eye"></i></a>
					<a href="#" class="btn btn-sm btn-info action-btn" title="Reply"><i class="fas fa-edit"></i></a>
				</td>
			</tr>
			<%
					}
				} else {
			%>
			<tr>
				<td colspan="7" class="text-center">No reviews found.</td>
			</tr>
			<% } %>
		</tbody>
	</table>
</div>