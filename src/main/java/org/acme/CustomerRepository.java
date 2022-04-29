package org.acme;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    
    public Customer findById(int id){
        return  find("id", id).firstResult();
    }

    
    
    
}