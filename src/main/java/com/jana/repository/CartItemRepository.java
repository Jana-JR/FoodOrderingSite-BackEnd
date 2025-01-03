package com.jana.repository;

import com.jana.model.CartItem;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

@Data
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
