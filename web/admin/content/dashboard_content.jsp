<%-- /admin/content/dashboard_content.jsp --%>
<%-- This file contains only the content specific to the Dashboard page --%>

<h2 class="mb-3 border-bottom pb-2 text-body"><i class="fas fa-tachometer-alt"></i> Admin Dashboard</h2>
<p class="text-body-secondary">Overview of the system.</p>

<div class="row g-3 mb-4">
    <div class="col-xl-3 col-md-6">
         <div class="card bg-body border-start border-primary border-4 h-100">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="card-title text-body-secondary fw-normal mb-1">Total Users</h5>
                        <p class="card-text fs-4 fw-bold mb-0 text-body" id="total-users">Loading...</p>
                    </div>
                    <i class="fas fa-users card-icon text-primary"></i>
                </div>
            </div>
        </div>
    </div>
     <div class="col-xl-3 col-md-6">
        <div class="card bg-body border-start border-success border-4 h-100">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="card-title text-body-secondary fw-normal mb-1">Total Products</h5>
                        <p class="card-text fs-4 fw-bold mb-0 text-body" id="total-products">Loading...</p>
                    </div>
                    <i class="fas fa-box-open card-icon text-success"></i>
                </div>
            </div>
        </div>
    </div>
     <div class="col-xl-3 col-md-6">
        <div class="card bg-body border-start border-info border-4 h-100">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="card-title text-body-secondary fw-normal mb-1">Total Orders</h5>
                        <p class="card-text fs-4 fw-bold mb-0 text-body" id="total-orders">Loading...</p>
                    </div>
                    <i class="fas fa-receipt card-icon text-info"></i>
                </div>
            </div>
        </div>
    </div>
     <div class="col-xl-3 col-md-6">
        <div class="card bg-body border-start border-warning border-4 h-100">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="card-title text-body-secondary fw-normal mb-1">Sales (Month)</h5>
                        <p class="card-text fs-4 fw-bold mb-0 text-body" id="total-sales">Loading...</p>
                    </div>
                    <i class="fas fa-dollar-sign card-icon text-warning"></i>
                </div>
            </div>
        </div>
    </div>
</div><h3 class="mt-4 mb-3 text-body">Sales Statistics (Last 7 Days)</h3>
 <div class="chart-container bg-body-secondary p-3 rounded">
    <canvas id="salesChart"></canvas>
</div>

<%-- Note: The corresponding JavaScript (fetchSalesChartData, fetchSummaryData)
     needs to be included in the main layout (admin_layout.jsp)
     or specifically on pages that require it.
     Ensure the JS checks if the required elements (e.g., #salesChart) exist before running.
--%>
