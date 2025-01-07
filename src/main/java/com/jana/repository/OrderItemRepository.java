package com.jana.repository;

import com.jana.model.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Orderitem, Long> {

}
