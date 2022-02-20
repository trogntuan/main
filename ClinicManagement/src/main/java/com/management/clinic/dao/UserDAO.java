package com.management.clinic.dao;

import com.management.clinic.entity.UserApp;

public interface UserDAO {

    UserApp findByUsernameAndPassword(String username, String password);

    Long save(UserApp userApp);

    void update(UserApp userApp);

    UserApp findById(Long id);
}