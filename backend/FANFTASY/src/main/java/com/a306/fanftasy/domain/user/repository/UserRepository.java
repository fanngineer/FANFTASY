package com.a306.fanftasy.domain.user.repository;

import com.a306.fanftasy.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByAddress(String address);
    User findByUserId(Long userId);
    User findFirstByRole(String role);

}
