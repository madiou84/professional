package com.group.inventory;

import com.group.inventory.inventory.Inventory;
import com.group.inventory.inventory.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(InventoryRepository inventoryRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Inventory.class);

            List<Inventory> customers = inventoryRepository.saveAll(
                    List.of(
                            Inventory.builder().name("Printer").price(10).quantity(10).build(),
                            Inventory.builder().name("Samsung").price(10).quantity(20).build(),
                            Inventory.builder().name("Iphone").price(10).quantity(20).build()
                    )
            );

            customers.forEach(System.out::println);
        };
    }
}
