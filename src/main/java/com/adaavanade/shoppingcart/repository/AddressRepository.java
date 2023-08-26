package com.adaavanade.shoppingcart.repository;

import com.adaavanade.shoppingcart.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
