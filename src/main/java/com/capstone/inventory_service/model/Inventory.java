package com.capstone.inventory_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "t_inventory")
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
