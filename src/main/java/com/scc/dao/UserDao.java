package com.scc.dao;

import com.scc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

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
                     return new User(
                             rs.getInt("user_id"),
                             rs.getString("full_name"),
                             rs.getString("username"),
                             rs.getString("password"),
                             rs.getString("role"),
                             rs.getString("active"),
                             rs.getString("created_at")
                     );
                 }
             }
        } catch (SQLException e) {
            System.out.println("Database error:" + e.getMessage());
        }
        return null;
    }
}