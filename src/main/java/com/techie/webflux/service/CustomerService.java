package com.techie.webflux.service;

import com.techie.webflux.dao.CustomerDao;
import com.techie.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> loadAllCustomers() {
        long startTime = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time : " +(endTime-startTime));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStreams() {
        long startTime = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersStreams();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time : " +(endTime-startTime));
        return customers;
    }
}
