USE demo;

-- 1. Stored Procedure lấy danh sách người dùng
DELIMITER //
CREATE PROCEDURE get_all_users()
BEGIN
    SELECT * FROM users;
END //
DELIMITER ;

-- 2. Stored Procedure chỉnh sửa người dùng
DELIMITER //
CREATE PROCEDURE edit_user(
    IN user_id INT,
    IN user_name VARCHAR(50),
    IN user_email VARCHAR(50),
    IN user_country VARCHAR(50)
)
BEGIN
    UPDATE users 
    SET name = user_name, email = user_email, country = user_country 
    WHERE id = user_id;
END //
DELIMITER ;

-- 3. Stored Procedure xóa người dùng
DELIMITER //
CREATE PROCEDURE delete_user(
    IN user_id INT
)
BEGIN
    DELETE FROM users WHERE id = user_id;
END //
DELIMITER ;