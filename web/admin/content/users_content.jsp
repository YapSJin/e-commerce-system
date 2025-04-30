<%-- /admin/content/users_content.jsp --%>
<%-- This file contains only the content specific to the User Management page --%>

<h2 class="mb-3 border-bottom pb-2 text-body"><i class="fas fa-users-cog"></i> User Management</h2>

<button type="button" class="btn btn-primary mb-3 btn-add" data-bs-toggle="modal" data-bs-target="#addUserModal">
   <i class="fas fa-plus"></i> Add User
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
             <tr>
                <td>1</td>
                <td>customerREX</td>
                <td>customer1@example.com</td>
                <td>Customer</td>
                <td>
                    <a href="#" class="btn btn-sm btn-info action-btn" title="Edit"><i class="fas fa-edit"></i></a>
                    <a href="#" class="btn btn-sm btn-danger action-btn" title="Delete" onclick="if(confirm('Are you sure?')){ showToast('User deleted successfully.', 'success'); } return false;"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>customer2</td>
                <td>customer2@example.com</td>
                <td>Customer</td>
                <td>
                    <a href="#" class="btn btn-sm btn-info action-btn" title="Edit"><i class="fas fa-edit"></i></a>
                    <a href="#" class="btn btn-sm btn-danger action-btn" title="Delete" onclick="if(confirm('Are you sure?')){ showToast('User deleted successfully.', 'success'); } return false;"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
            </tbody>
    </table>
</div>
