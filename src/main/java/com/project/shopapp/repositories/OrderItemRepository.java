package com.project.shopapp.repositories;

import com.github.javafaker.Food;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderItem;
import com.project.shopapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteByOrder(Order order);
    List<OrderItem> findByFood(Product product);
    void deleteByFood(Product food);
    @Query("SELECT oi FROM OrderItem oi JOIN oi.order o WHERE o.orderTime BETWEEN :startDate AND :endDate")
    List<OrderItem> findItemsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
