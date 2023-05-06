package com.group.customer;

import com.group.customer.customer.Customer;
import com.group.customer.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Customer.class);

            List<Customer> customers = customerRepository.saveAll(
                    List.of(
                            Customer.builder().name("Madiou BAH").email("mmadioubah@gmail.com").build(),
                            Customer.builder().name("Saikou BAH").email("Saikou@gmail.com").build(),
                            Customer.builder().name("Dian BAH").email("Dian@gmail.com").build(),
                            Customer.builder().name("Yaya BAH").email("Yaya@gmail.com").build(),
                            Customer.builder().name("Oury BAH").email("Oury@gmail.com").build(),
                            Customer.builder().name("Talibé BAH").email("Talibé@gmail.com").build(),
                            Customer.builder().name("Habibatou BAH").email("Habibatou@gmail.com").build()
                    )
            );

            customers.forEach(System.out::println);
        };
    }
}
