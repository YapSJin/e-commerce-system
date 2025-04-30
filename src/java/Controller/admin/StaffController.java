package Controller.admin;

import Model.Users;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.UserTransaction;
import java.util.List;

/**
 *
 * @author jeremyxuanlim
 */

public class StaffController extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Users> staffList = em.createQuery(
                "SELECT u FROM Users u WHERE u.role = :role AND u.isArchived = :isArchived", Users.class)
                .setParameter("role", "staff")
                .setParameter("isArchived", false)
                .getResultList();

            request.setAttribute("staffList", staffList);
            request.getRequestDispatcher("/admin/admin_staff.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/admin_dashboard.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    String action = request.getParameter("action");
    HttpSession session = request.getSession();

    try {
        if ("add".equals(action)) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");

            // Validate input
            if (username == null || username.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                role == null || role.trim().isEmpty()) {

                session.setAttribute("error", "All fields are required");
                response.sendRedirect(request.getContextPath() + "/admin/staff");
                return;
            }

            // Create new user
            Users newStaff = new Users();
            newStaff.setUsername(username.trim());
            newStaff.setEmail(email.trim());
            newStaff.setPassword(hashPassword(password));
            newStaff.setRole(role.trim());
            newStaff.setIsArchived(false);

            utx.begin();
            em.persist(newStaff);
            utx.commit();

            session.setAttribute("success", "Staff member added successfully!");
        }
        else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username").trim();
            String email = request.getParameter("email").trim();
            String role = request.getParameter("role").trim();

            Users staff = em.find(Users.class, id);
            if (staff != null) {
                utx.begin();
                staff.setUsername(username);
                staff.setEmail(email);
                staff.setRole(role);
                em.merge(staff);
                utx.commit();

                session.setAttribute("success", "Staff member updated successfully!");
            } else {
                session.setAttribute("error", "Staff member not found!");
            }
        }
        else if ("archive".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Users staff = em.find(Users.class, id);
            if (staff != null) {
                utx.begin();
                staff.setIsArchived(true);
                em.merge(staff);
                utx.commit();

                session.setAttribute("success", "Staff member archived successfully!");
            } else {
                session.setAttribute("error", "Staff member not found!");
            }
        }
    } catch (Exception e) {
        try {
            if (utx != null) {
                utx.rollback();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        session.setAttribute("error", "An error occurred: " + e.getMessage());
        e.printStackTrace();
    }

    response.sendRedirect(request.getContextPath() + "/admin/staff");
}

    private String hashPassword(String password) throws ServletException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ServletException("Error hashing password", e);
        }
    }
}