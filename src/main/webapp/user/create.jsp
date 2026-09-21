<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm mới User</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"] { width: 300px; padding: 8px; }
        .btn { padding: 8px 15px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
        .message { color: green; font-weight: bold; margin-bottom: 15px; }
    </style>
</head>
<body>
    <h2>Thêm mới Người dùng</h2>
    
    <c:if test="${message != null}">
        <p class="message">${message}</p>
    </c:if>

    <form action="users?action=create" method="post">
        <div class="form-group">
            <label>Tên người dùng:</label>
            <input type="text" name="name" required />
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" required />
        </div>
        <div class="form-group">
            <label>Quốc gia:</label>
            <input type="text" name="country" required />
        </div>
        
        <!-- Nhóm Checkbox chọn quyền hạn (Permissions) cho Transaction -->
        <div class="form-group">
            <label>Quyền hạn (Permissions):</label>
            <div style="display: flex; gap: 15px; margin-top: 5px;">
                <label><input type="checkbox" name="permissions" value="1"> Thêm (Add)</label>
                <label><input type="checkbox" name="permissions" value="2"> Sửa (Edit)</label>
                <label><input type="checkbox" name="permissions" value="3"> Xoá (Delete)</label>
                <label><input type="checkbox" name="permissions" value="4"> Xem (View)</label>
            </div>
        </div>

        <button type="submit" class="btn">Lưu thông tin</button>
        <a href="users">Quay lại danh sách</a>
    </form>
</body>
</html>