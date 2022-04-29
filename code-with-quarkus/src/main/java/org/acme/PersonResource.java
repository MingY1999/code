package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/person")
public class PersonResource {
    @Inject
    PersonRepository personRepository;

    @GET
    public long count(){
    return personRepository.count();
 
}
}
