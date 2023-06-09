package com.techie.webflux.dao;

import com.techie.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("processing count: "+i))
                .mapToObj(i -> new Customer(i, "Customer" + i))
                .collect(Collectors.toList());
    }

    private static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Flux<Customer> getCustomersStreams() {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count: "+i))
                .map(i -> new Customer(i, "Customer" + i));
    }

    public Flux<Customer> getCustomerList()  {
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new Customer(i, "customer" + i));
    }
}
