package Controller.admin;

import Model.Users;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.UserTransaction;
import java.util.List;

/**
 *
 * @author IceCube
 */
@WebServlet(name = "UserController", urlPatterns = {"/admin/users"})
public class UserController extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Users> userList = em.createQuery(
                "SELECT u FROM Users u WHERE u.role = :role AND u.isArchived = :isArchived", Users.class)
                .setParameter("role", "customer")
                .setParameter("isArchived", false)
                .getResultList();

            request.setAttribute("userList", userList);
            request.getRequestDispatcher("/admin/admin_users.jsp").forward(request, response);
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
                String role = "customer";
                String name = request.getParameter("name");
                String contact = request.getParameter("contact");

                // Validate input
                if (username == null || username.trim().isEmpty() ||
                    email == null || email.trim().isEmpty() ||
                    password == null || password.trim().isEmpty()||
                    name == null || name.trim().isEmpty() ||
                    contact == null || contact.trim().isEmpty()) {

                    session.setAttribute("error", "All fields are required");
                    response.sendRedirect(request.getContextPath() + "/admin/users");
                    return;
                }

                // Create new user
                Users newUser = new Users();
                newUser.setUsername(username.trim());
                newUser.setEmail(email.trim());
                newUser.setPassword(hashPassword(password));
                newUser.setRole(role);
                newUser.setName(name.trim());
                newUser.setContact(contact.trim());
                newUser.setIsArchived(false);

                utx.begin();
                em.persist(newUser);
                utx.commit();

                session.setAttribute("success", "User added successfully!");
            }
            else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String username = request.getParameter("username").trim();
                String email = request.getParameter("email").trim();
                String name = request.getParameter("name").trim();
                String contact = request.getParameter("contact").trim();

                Users user = em.find(Users.class, id);
                if (user != null) {
                    utx.begin();
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setName(name);
                    user.setContact(contact);
                    em.merge(user);
                    utx.commit();

                    session.setAttribute("success", "User updated successfully!");
                } else {
                    session.setAttribute("error", "User not found!");
                }
            }
            else if ("archive".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Users user = em.find(Users.class, id);
                if (user != null) {
                    utx.begin();
                    user.setIsArchived(true);
                    em.merge(user);
                    utx.commit();

                    session.setAttribute("success", "User archived successfully!");
                } else {
                    session.setAttribute("error", "User not found!");
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

        response.sendRedirect(request.getContextPath() + "/admin/users");
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
