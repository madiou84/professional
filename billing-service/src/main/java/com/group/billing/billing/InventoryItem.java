package com.group.billing.billing;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.group.billing.billing.model.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long inventoryId;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Billing billing;
    private int quantity;
    private double price;
    private double discount;
    @Transient
    private Inventory inventory;
}