package com.adaavanade.shoppingcart.repository;

import com.adaavanade.shoppingcart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
