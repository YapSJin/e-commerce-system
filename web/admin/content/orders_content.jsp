<%-- /WEB-INF/views/admin/content/orders_content.jsp --%>
<%-- This file contains only the content specific to the Order Management page --%>
<%@ page import="java.util.List, Model.Orders" %>
<h2 class="mb-3 border-bottom pb-2 text-body"><i class="fas fa-receipt"></i> Order Management</h2>

<div class="table-responsive">
	<table class="table table-striped table-hover table-bordered align-middle">
		<thead class="table-light">
			<tr>
				<th>ID</th>
				<th>Customer</th>
				<th>Order Date</th>
				<th>Promo Code</th>
				<th>Dicount</th>
				<th>Total Price</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<Orders> orders = (List<Orders>) request.getAttribute("orders");
				if (orders != null) {
					for (Orders order : orders) {
			%>
			<tr>
				<td><%= order.getId() %></td>
				<td><%= order.getUserId().getUsername() %></td>
				<td><%= order.getOrderDate() %></td>
				<td><%= order.getPromoId() != null ? order.getPromoId().getPromoCode() : "N/A" %></td>
				<td><%= order.getPromoId() != null ? "RM " + order.getDiscount() : "N/A" %></td>
				<td>RM <%= order.getTotalPrice() %></td>
				<td>
					<a href="#" class="btn btn-sm btn-success action-btn" title="View Details"><i class="fas fa-eye"></i></a>
				</td>
			</tr>
			<%
					}
				} else {
			%>
			<tr>
				<td colspan="7" class="text-center">No orders found.</td>
			</tr>
			<% } %>
		</tbody>
	</table>
</div>