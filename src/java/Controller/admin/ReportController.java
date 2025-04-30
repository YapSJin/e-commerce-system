package Controller.admin;

import Model.*;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.transaction.UserTransaction;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "ReportController", urlPatterns = {"/admin/reports"})
public class ReportController extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    
    @Resource
    UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            HttpSession session = req.getSession();
            Users user = (Users) session.getAttribute("user");

            // Check if user is logged in and has appropriate role
            if (user == null || (!user.getRole().equalsIgnoreCase("manager") && 
                !user.getRole().equalsIgnoreCase("staff"))) {
                res.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }

            if (action == null) {
                loadReports(req, res);
                return;
            }

            switch (action) {
                case "generate":
                    generateReport(req, res, user);
                    break;
                default:
                    loadReports(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if ("delete".equals(action)) {
                deleteReport(req, res);
            } else {
                loadReports(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void loadReports(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            List<Reports> reports = em.createQuery(
                "SELECT r FROM Reports r ORDER BY r.generatedDate DESC", Reports.class)
                .getResultList();
            req.setAttribute("reportList", reports);
            req.getRequestDispatcher("/admin/admin_reports.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void generateReport(HttpServletRequest req, HttpServletResponse res, Users user)
            throws ServletException, IOException {
        try {
            utx.begin();

            // Get and validate dates
            String startDateStr = req.getParameter("startDate");
            String endDateStr = req.getParameter("endDate");
            
            if (startDateStr == null || endDateStr == null || 
                startDateStr.isEmpty() || endDateStr.isEmpty()) {
                req.getSession().setAttribute("error", "Start and end dates are required");
                res.sendRedirect(req.getContextPath() + "/admin/reports");
                return;
            }

            Date startDate = new Date(new java.text.SimpleDateFormat("yyyy-MM-dd")
                .parse(startDateStr).getTime());
            Date endDate = new Date(new java.text.SimpleDateFormat("yyyy-MM-dd")
                .parse(endDateStr).getTime());

            // Query orders
            List<Orders> orders = em.createQuery(
                "SELECT o FROM Orders o " +
                "LEFT JOIN FETCH o.orderdetailsList od " +
                "LEFT JOIN FETCH od.productId " +
                "WHERE o.orderDate BETWEEN :startDate AND :endDate",
                Orders.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();

            // Calculate metrics
            BigDecimal totalRevenue = BigDecimal.ZERO;
            BigDecimal totalDiscounts = BigDecimal.ZERO;
            Set<Integer> uniqueCustomers = new HashSet<>();
            int totalProducts = 0;

            for (Orders order : orders) {
                totalRevenue = totalRevenue.add(order.getTotalPrice());
                if (order.getDiscount() != null) {
                    totalDiscounts = totalDiscounts.add(order.getDiscount());
                }
                uniqueCustomers.add(order.getUserId().getId());
                
                for (Orderdetails detail : order.getOrderdetailsList()) {
                    totalProducts += detail.getQuantity();
                }
            }

            BigDecimal averageOrderValue = orders.isEmpty() ? BigDecimal.ZERO :
                totalRevenue.divide(new BigDecimal(orders.size()), 2, RoundingMode.HALF_UP);

            // Create and save report
            Reports report = new Reports();
            report.setReportType("Sales Report");
            report.setGeneratedDate(new Date());
            report.setGeneratedById(user);
            report.setDetails(formatReportDetails(
                startDate, endDate, orders.size(),
                totalRevenue, averageOrderValue,
                totalDiscounts, totalProducts,
                uniqueCustomers.size()
            ));

            em.persist(report);
            utx.commit();
            
            req.getSession().setAttribute("success", "Report generated successfully!");
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            req.getSession().setAttribute("error", "Failed to generate report: " + e.getMessage());
        }
        res.sendRedirect(req.getContextPath() + "/admin/reports");
    }

    private void deleteReport(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            utx.begin();
            int reportId = Integer.parseInt(req.getParameter("id"));
            Reports report = em.find(Reports.class, reportId);
            
            if (report != null) {
                em.remove(report);
                em.flush(); // Add this line to ensure immediate deletion
                utx.commit();
                req.getSession().setAttribute("success", "Report deleted successfully!");
            } else {
                req.getSession().setAttribute("error", "Report not found!");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            req.getSession().setAttribute("error", "Failed to delete report: " + e.getMessage());
        }
        res.sendRedirect(req.getContextPath() + "/admin/reports");
    }

    private String formatReportDetails(Date startDate, Date endDate, int totalOrders,
            BigDecimal totalRevenue, BigDecimal averageOrderValue,
            BigDecimal totalDiscounts, int totalProducts, int uniqueCustomers) {
        return String.format(
            "Period: %s to %s\n" +
            "Total Orders: %d\n" +
            "Total Revenue: RM %.2f\n" +
            "Average Order Value: RM %.2f\n" +
            "Total Discounts: RM %.2f\n" +
            "Total Products Sold: %d\n" +
            "Unique Customers: %d",
            new java.text.SimpleDateFormat("yyyy-MM-dd").format(startDate),
            new java.text.SimpleDateFormat("yyyy-MM-dd").format(endDate),
            totalOrders, totalRevenue, averageOrderValue,
            totalDiscounts, totalProducts, uniqueCustomers
        );
    }
}