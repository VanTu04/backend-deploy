package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.shopapp.enums.PAYMENT_STATUS;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderItem;
import com.project.shopapp.models.TableReservation;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data //toString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;

    private LocalDateTime orderTime;

    private PAYMENT_STATUS paymentStatus;

    private BigDecimal totalPrice;

    private String customerName;

    private String reservationCode;

    private List<OrderItemsResponse> orderItems;
}
