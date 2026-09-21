package com.codegym.dao;

import com.codegym.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123"; // Điền mật khẩu MySQL của bạn

    public UserDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 1. Luyện tập JDBC Transaction (commit & rollback)
    public void addUserTransaction() {
        String insertUserSQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
        String insertPermitSQL = "INSERT INTO user_permision (user_id, permision_id) VALUES (?, ?);";

        Connection conn = null;
        try {
            conn = getConnection();
            // Tắt Auto Commit để bắt đầu Transaction
            conn.setAutoCommit(false);

            // Thêm User thứ nhất
            PreparedStatement pstmt1 = conn.prepareStatement(insertUserSQL);
            pstmt1.setString(1, "Tran Transaction");
            pstmt1.setString(2, "tran@codegym.vn");
            pstmt1.setString(3, "Viet Nam");
            pstmt1.executeUpdate();

            // Cố tình gây lỗi SQL để thử nghiệm Rollback
            PreparedStatement pstmt2 = conn.prepareStatement(insertPermitSQL);
            pstmt2.setInt(1, 99999); // ID không tồn tại
            pstmt2.setInt(2, 1);
            pstmt2.executeUpdate();

            // Commit nếu không có lỗi
            conn.commit();
            System.out.println("Transaction thành công!");

        } catch (SQLException e) {
            // Rollback nếu có lỗi xảy ra
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction đã Rollback do gặp lỗi: " + e.getMessage());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 2. Lấy danh sách users bằng Stored Procedure
    @Override
    public List<User> selectAllUsersSP() {
        List<User> users = new ArrayList<>();
        String query = "{CALL get_all_users()}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 3. Sửa user bằng Stored Procedure
    @Override
    public boolean updateUserSP(User user) {
        boolean rowUpdated = false;
        String query = "{CALL edit_user(?, ?, ?, ?)}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            
            callableStatement.setInt(1, user.getId());
            callableStatement.setString(2, user.getName());
            callableStatement.setString(3, user.getEmail());
            callableStatement.setString(4, user.getCountry());

            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // 4. Xóa user bằng Stored Procedure
    @Override
    public boolean deleteUserSP(int id) {
        boolean rowDeleted = false;
        String query = "{CALL delete_user(?)}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            
            callableStatement.setInt(1, id);
            rowDeleted = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}package com.codegym.dao;

import com.codegym.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123"; // Điền mật khẩu MySQL của bạn

    public UserDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 1. Luyện tập JDBC Transaction (commit & rollback)
    public void addUserTransaction() {
        String insertUserSQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
        String insertPermitSQL = "INSERT INTO user_permision (user_id, permision_id) VALUES (?, ?);";

        Connection conn = null;
        try {
            conn = getConnection();
            // Tắt Auto Commit để bắt đầu Transaction
            conn.setAutoCommit(false);

            // Thêm User thứ nhất
            PreparedStatement pstmt1 = conn.prepareStatement(insertUserSQL);
            pstmt1.setString(1, "Tran Transaction");
            pstmt1.setString(2, "tran@codegym.vn");
            pstmt1.setString(3, "Viet Nam");
            pstmt1.executeUpdate();

            // Cố tình gây lỗi SQL để thử nghiệm Rollback
            PreparedStatement pstmt2 = conn.prepareStatement(insertPermitSQL);
            pstmt2.setInt(1, 99999); // ID không tồn tại
            pstmt2.setInt(2, 1);
            pstmt2.executeUpdate();

            // Commit nếu không có lỗi
            conn.commit();
            System.out.println("Transaction thành công!");

        } catch (SQLException e) {
            // Rollback nếu có lỗi xảy ra
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction đã Rollback do gặp lỗi: " + e.getMessage());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 2. Lấy danh sách users bằng Stored Procedure
    @Override
    public List<User> selectAllUsersSP() {
        List<User> users = new ArrayList<>();
        String query = "{CALL get_all_users()}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 3. Sửa user bằng Stored Procedure
    @Override
    public boolean updateUserSP(User user) {
        boolean rowUpdated = false;
        String query = "{CALL edit_user(?, ?, ?, ?)}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            
            callableStatement.setInt(1, user.getId());
            callableStatement.setString(2, user.getName());
            callableStatement.setString(3, user.getEmail());
            callableStatement.setString(4, user.getCountry());

            rowUpdated = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // 4. Xóa user bằng Stored Procedure
    @Override
    public boolean deleteUserSP(int id) {
        boolean rowDeleted = false;
        String query = "{CALL delete_user(?)}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            
            callableStatement.setInt(1, id);
            rowDeleted = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}