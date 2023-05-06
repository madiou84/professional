package com.group.billing;

import com.group.billing.billing.Billing;
import com.group.billing.billing.BillingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(BillingRepository billingRepository) {
        return args -> {
            billingRepository.saveAll(
                    List.of(
                            Billing.builder().invoicePath("/cloud/s3/file1.mp4").build(),
                            Billing.builder().invoicePath("/cloud/s3/file2.mp4").build(),
                            Billing.builder().invoicePath("/cloud/s3/file3.mp4").build(),
                            Billing.builder().invoicePath("/cloud/s3/file4.mp4").build(),
                            Billing.builder().invoicePath("/cloud/s3/file5.mp4").build(),
                            Billing.builder().invoicePath("/cloud/s3/file6.mp4").build()
                    )
            );
        };
    }

}
