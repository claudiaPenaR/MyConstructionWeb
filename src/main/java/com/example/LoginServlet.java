package com.example; // Asegúrate de que este sea el paquete correcto


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isAuthenticated = false;

        try {
            // Configura la conexión a la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "12345678");

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                isAuthenticated = true;
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isAuthenticated) {
            // Redirigir a welcome.jsp
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        } else {
            // Redirigir a login.jsp si las credenciales son incorrectas
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=true");
        }

    }
}
