package com.harry.zyneticbookstore.dao;

import com.harry.zyneticbookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
