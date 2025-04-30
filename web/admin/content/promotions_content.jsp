<%-- /admin/content/promotions_content.jsp --%>
<%-- This file contains only the content specific to the Promotion Management page --%>

<h2 class="mb-3 border-bottom pb-2 text-body"><i class="fas fa-tags"></i> Promotion Management</h2>

<button type="button" class="btn btn-primary mb-3 btn-add" data-bs-toggle="modal" data-bs-target="#addPromotionModal">
   <i class="fas fa-plus"></i> Add Promotion
</button>

<div class="table-responsive">
   <table class="table table-striped table-hover table-bordered align-middle">
       <thead class="table-light">
           <tr>
               <th>ID</th>
               <th>Promo Code</th>
               <th>Discount (%)</th>
               <th>Valid From</th>
               <th>Valid To</th>
               <th>Actions</th>
           </tr>
       </thead>
       <tbody>
            <tr>
                <td>1</td>
                <td>SUMMER20</td>
                <td>20%</td>
                <td>2025-06-01</td>
                <td>2025-08-31</td>
                <td>
                    <a href="#" class="btn btn-sm btn-info action-btn" title="Edit"><i class="fas fa-edit"></i></a>
                    <a href="#" class="btn btn-sm btn-danger action-btn" title="Delete" onclick="if(confirm('Are you sure?')){ showToast('Promotion deleted successfully.', 'success'); } return false;"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>FREESHIP</td>
                <td>0% (+ Free Shipping)</td>
                <td>2025-05-01</td>
                <td>2025-05-31</td>
                <td>
                    <a href="#" class="btn btn-sm btn-info action-btn" title="Edit"><i class="fas fa-edit"></i></a>
                    <a href="#" class="btn btn-sm btn-danger action-btn" title="Delete" onclick="if(confirm('Are you sure?')){ showToast('Promotion deleted successfully.', 'success'); } return false;"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
             </tbody>
   </table>
</div>
