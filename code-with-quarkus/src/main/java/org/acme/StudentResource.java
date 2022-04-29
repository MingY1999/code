package org.acme;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/student")
@Transactional
public class StudentResource {

    @GET
    @Path("/bySorce")
    public List<Student> listBySorce(){
        return Student.list("order by sorce");
    }
    
    @GET
    @Path("/byName")
    public List<Student> listByName(){
        return Student.list("order by name");
    }

    @POST
    public void addStudent(Student student){
        student.id = null;
        student.persist();
    }

   
}
