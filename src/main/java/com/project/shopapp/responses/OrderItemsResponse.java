package com.project.shopapp.responses;

import lombok.*;

@Data //toString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemsResponse {
    private String foodName;
    private String foodPrice;
    private String foodQuantity;
}
