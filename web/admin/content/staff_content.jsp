<%@ page import="java.util.List, Model.Users" %>

<% if (session.getAttribute("error") != null) { %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <%= session.getAttribute("error") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <% session.removeAttribute("error"); %>
<% } %>

<% if (session.getAttribute("success") != null) { %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <%= session.getAttribute("success") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <% session.removeAttribute("success"); %>
<% } %>

<h2 class="mb-3 border-bottom pb-2 text-body"><i class="fas fa-user-shield"></i> Staff Management</h2>

<button type="button" class="btn btn-primary mb-3 btn-add" data-bs-toggle="modal" data-bs-target="#addStaffModal">
   <i class="fas fa-plus"></i> Add Staff
</button>

<div class="table-responsive">
    <table class="table table-striped table-hover table-bordered align-middle">
        <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Users> staffList = (List<Users>) request.getAttribute("staffList");
                if (staffList != null && !staffList.isEmpty()) {
                    for (Users staff : staffList) {
            %>
            <tr>
                <td><%= staff.getId() %></td>
                <td><%= staff.getUsername() %></td>
                <td><%= staff.getEmail() %></td>
                <td><span class="badge <%= staff.getRole().equals("manager") ? "bg-primary" : "bg-secondary" %>">
                    <%= staff.getRole() %>
                </span></td>
                <td>
                    <button class="btn btn-sm btn-info action-btn"
                            onclick="editStaff('<%= staff.getId() %>', '<%= staff.getUsername() %>', '<%= staff.getEmail() %>', '<%= staff.getRole() %>')"
                            data-bs-toggle="modal" data-bs-target="#editStaffModal">
                        <i class="fas fa-edit"></i>
                    </button>
                    <button class="btn btn-sm btn-danger action-btn"
                            onclick="archiveStaff(<%= staff.getId() %>)">
                        <i class="fas fa-archive"></i>
                    </button>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" class="text-center">No staff members found</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
<!-- Add Staff Modal -->
<div class="modal fade" id="addStaffModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><i class="fas fa-user-plus me-2"></i>Add New Staff</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form id="addStaffForm" action="${pageContext.request.contextPath}/admin/staff" method="POST">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="role" value="staff">

                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>

                    <div class="modal-footer px-0 pb-0">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add Staff</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Edit Staff Modal -->
<div class="modal fade" id="editStaffModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><i class="fas fa-user-edit me-2"></i>Edit Staff Member</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form id="editStaffForm" action="${pageContext.request.contextPath}/admin/staff" method="POST">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" id="editStaffId" name="id">
                    <input type="hidden" name="role" value="staff">

                    <div class="mb-3">
                        <label for="editUsername" class="form-label">Username</label>
                        <input type="text" class="form-control" id="editUsername" name="username" required>
                    </div>

                    <div class="mb-3">
                        <label for="editEmail" class="form-label">Email</label>
                        <input type="email" class="form-control" id="editEmail" name="email" required>
                    </div>

                    <div class="modal-footer px-0 pb-0">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Update Staff</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function editStaff(id, username, email, role) {
        document.getElementById('editStaffId').value = id;
        document.getElementById('editUsername').value = username;
        document.getElementById('editEmail').value = email;
        document.getElementById(role === 'manager' ? 'editRoleManager' : 'editRoleStaff').checked = true;
    }

    function archiveStaff(id) {
        if (confirm('Are you sure you want to archive this staff member? This action can be reversed by an administrator.')) {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = '${pageContext.request.contextPath}/admin/staff';

            const actionInput = document.createElement('input');
            actionInput.type = 'hidden';
            actionInput.name = 'action';
            actionInput.value = 'archive';

            const idInput = document.createElement('input');
            idInput.type = 'hidden';
            idInput.name = 'id';
            idInput.value = id;

            form.appendChild(actionInput);
            form.appendChild(idInput);
            document.body.appendChild(form);
            form.submit();
        }
    }
</script>
