package org.acme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;




@Path("/film")
public class FilmResource {
    @Inject
    FilmRepository filmRepository;
    @Inject
    CustomerRepository customerRepository;
 
    //查询所有电影的信息  
    @GET
    @Path("/getAllFilm")
    public List<Film> getAllFilm(){
        return filmRepository.listAll();
    }
    
    //根据电影名查询该电影所有信息
   @GET
   @Path("/getByName")
   public List<Film> getByName(@QueryParam("name") String name){
       return filmRepository.findByName(name);
   }

   
   @GET
   @Path("/getByName/{name}")
   public List<Film> getByName2(@PathParam("name") String name){
       return filmRepository.findByName(name);
   }

   //新增
    @POST
    @Transactional
    @Path("/addFilm")
    public void add(Film film) {
        filmRepository.persist(film);
    }

    @DELETE
    @Transactional
    @Path("/deleteFilm")
    public void delete(int id){
        filmRepository.delete("id = ? ", id);
    }

    //修改电影信息
    @PUT
    @Transactional
    @Path("/modify")
    public Film modify(Film film){
        Film entity = filmRepository.findById(film.getId());
        if(entity == null) {
            throw new NotFoundException();
        }

        entity.setName(film.getName());
        entity.setPrice(film.getPrice());
        entity.setSit(film.getSit());
        entity.setTime(film.getTime());
        return entity;
    }

 
    @Channel("quote-requests")
    Emitter<String> quoteRequestEmitter; 
    
    @GET
    @Path("/soldTicket")
    @Transactional
    public boolean soldTicket(@QueryParam("fId")  int fId ,@QueryParam("cId") int cId){
        //发送信息
        String message = "";
        Customer customer = customerRepository.findById(cId);
        ArrayList<Film> record = customer.record;
        Iterator<Film> ite = record.iterator();
        while(ite.hasNext()){
            message+= ite.next().getName();
        }
        quoteRequestEmitter.send(message);
        
        //买票
        Film film = filmRepository.findById(fId);
        int fileSit = film.getSit();
        if(fileSit == 0)
            return false;
        else{
            film.setSit(fileSit-1);
            customer.record.add(film);
            return true;
        }    
    }

    
}
