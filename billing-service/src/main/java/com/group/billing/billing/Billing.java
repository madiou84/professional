package com.group.billing.billing;

import com.group.billing.billing.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "billings")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String invoicePath;

    @Column
    private Long customerId;

    @Transient
    private Customer customer;

    @OneToMany(mappedBy = "billing")
    private List<InventoryItem> inventoryItems;

    private Date createdAt;
}
