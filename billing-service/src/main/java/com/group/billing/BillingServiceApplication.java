package com.group.billing;

import com.group.billing.billing.Billing;
import com.group.billing.billing.BillingRepository;
import com.group.billing.billing.InventoryItem;
import com.group.billing.billing.InventoryItemRepository;
import com.group.billing.billing.model.Customer;
import com.group.billing.billing.model.Inventory;
import com.group.billing.billing.services.CustomerRestClient;
import com.group.billing.billing.services.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@EnableFeignClients
@SpringBootApplication
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(
            BillingRepository billingRepository,
            InventoryItemRepository inventoryItemRepository,
            CustomerRestClient customerRestClient,
            InventoryRestClient inventoryRestClient) {
        return args -> {
            // Find Customer
            Long customerId = 1L;
            Customer customer = customerRestClient.findCustomerById(customerId);
            if (customer == null) throw new RuntimeException("Customer with id=[%s] not found".formatted(customerId));

            // Save billing
            Billing billing = Billing.builder()
                    .createdAt(new Date())
                    .customerId(customerId)
                    .invoicePath("/cloud/s3/file1.mp4")
                    .build();
            billingRepository.save(billing);

            // Find inventories
            Collection<Inventory> inventories = inventoryRestClient.allInventories().getContent();
            inventories.forEach(inventory -> {
                // Save inventoryItem
                InventoryItem inventoryItem = InventoryItem.builder()
                        .billing(billing)
                        .quantity(1 + new Random().nextInt(10))
                        .price(inventory.getPrice())
                        .discount(Math.random())
                        .inventoryId(inventory.getId())
                        .inventory(inventory)
                        .build();
                inventoryItemRepository.save(inventoryItem);
            });
        };
    }

}
