package org.acme;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;



@Path("/customer")
public class CustomerResource {
    @Inject
    CustomerRepository customerRepository;

    
      
    //观众注册
    @POST
    @Path("/addCustomer")
    @Transactional
    public void addCustomer(Customer customer ){

        int id = customer.getId();
        var _customer = customerRepository.findById(id);
        if(_customer==null){
            customerRepository.persist(customer);
        }
        else {
            _customer.record = customer.record;
        }

    }
    //查询所有观众       
    @GET
    @Path("/getAllCustomer")
    public List<Customer> getAllCustomer(){
        return customerRepository.listAll();
    }
}
