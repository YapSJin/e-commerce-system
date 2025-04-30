<%-- /admin/content/products_content.jsp --%>
<%-- This file contains only the content specific to the Product Management page --%>

<h2 class="mb-3 border-bottom pb-2 text-body"><i class="fas fa-box-open"></i> Product Management</h2>

<button type="button" class="btn btn-primary mb-3 btn-add" data-bs-toggle="modal" data-bs-target="#addProductModal">
    <i class="fas fa-plus"></i> Add Product
</button>

<div class="table-responsive">
    <table class="table table-striped table-hover table-bordered align-middle">
        <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>IEM</td>
                <td>Audio</td>
                <td>RM 1,200.50</td>
                <td>50</td>
                <td>
                    <a href="#" class="btn btn-sm btn-info action-btn" title="Edit"><i class="fas fa-edit"></i></a>
                    <a href="#" class="btn btn-sm btn-danger action-btn" title="Delete" onclick="if(confirm('Are you sure?')){ showToast('Product deleted successfully.', 'success'); } return false;"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
             <tr>
                <td>2</td>
                <td>Mouse</td>
                <td>Computer Accessories</td>
                 <td>RM 25.99</td>
                <td>100</td>
                <td>
                    <a href="#" class="btn btn-sm btn-info action-btn" title="Edit"><i class="fas fa-edit"></i></a>
                    <a href="#" class="btn btn-sm btn-danger action-btn" title="Delete" onclick="if(confirm('Are you sure?')){ showToast('Product deleted successfully.', 'success'); } return false;"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
             </tbody>
    </table>
</div>
