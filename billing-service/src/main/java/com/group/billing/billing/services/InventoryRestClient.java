package com.group.billing.billing.services;

import com.group.billing.billing.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryRestClient {
    @GetMapping("/inventories/{id}")
    Inventory findInventoryById(@PathVariable Long id);

    @GetMapping("/inventories")
    PagedModel<Inventory> allInventories();
}
