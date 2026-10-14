package com.scc.dao;

import com.scc.model.User;

import java.sql.*;

public class UserDao {

    public UserDao() {

        try (Connection conn = DatabaseConnecation.getConnection();
             Statement stmt = conn.createStatement()) {
            defaultAdminGenerator(conn);
        } catch (SQLException e) {
            System.err.println("Error seeding default admin: " + e.getMessage());
        }
    }


    public void defaultAdminGenerator(Connection conn) {
        String checkSql = "SELECT COUNT(*) FROM users WHERE role ='ADMIN'";
        String insertSql = "INSERT INTO users(full_name, username, password, role, active) VALUES('Admin', 'admin@gmail.com', 'admin123', 'ACTIVE')";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkSql)) {
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.executeUpdate(insertSql);
                System.out.println("-> Default Admin created");
            }
        } catch (SQLException e) {
            System.err.println("Error seeding defualt admin: " + e.getMessage());
        }

    }

    public boolean registerUser( String full_name, String username,String password, String role){

        String sql ="INSERT INTO users(full_name, username, password, role, active) VALUES ( ?, ?, ?, ?,'PENDING')";

        try(Connection conn = DatabaseConnecation.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,full_name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, role.toUpperCase());
            pstmt.executeUpdate();
            System.out.println("\n-> Successful Registration, Waiting for Approval");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed" + e.getMessage());
            return false;
        }
    }

    public boolean usernameExist(String username){
        String sql = "SELECT 2 FROM users WHERE username = ?";

        try(Connection conn = DatabaseConnecation.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            try(ResultSet rs = pstmt.executeQuery()){
                return rs.next();
            }
        }catch (SQLException e){
            System.out.println("Database error: " + e.getMessage());
            return true;
        }
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnecation.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setString(1, username);
             pstmt.setString(2, password);
             try(ResultSet rs = pstmt.executeQuery()){
                 if(rs.next()){
                     String isActive = rs.getString("active");
                     if("PENDING".equalsIgnoreCase(isActive)){
                         System.out.println("-> Account is pending, Waiting for Approval");
                         return null;
                     }
                     return new User(
                             rs.getInt("user_id"),
                             rs.getString("full_name"),
                             rs.getString("username"),
                             rs.getString("password"),
                             rs.getString("role"),
                             rs.getString("active"),
                             rs.getString("created_at")
                     );
                 }else {
                     System.out.println(" -> Invalid username or password");
                 }
             }
        } catch (SQLException e) {
            System.out.println("Database error:" + e.getMessage());
        }
        return null;
    }
}