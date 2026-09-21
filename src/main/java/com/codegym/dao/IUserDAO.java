package com.codegym.dao;

import com.codegym.model.User;
import java.util.List;

public interface IUserDAO {
    // Các phương thức cũ...

    List<User> selectAllUsersSP();
    boolean updateUserSP(User user);
    boolean deleteUserSP(int id);
}