package org.acme;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
@ApplicationScoped
public class FilmRepository implements PanacheRepository<Film> {
    public Film findById(int id){
        return find("id", id).firstResult();
    }
    public List<Film> findByName(String name){
        return find("name", name).list();
    }

   
}
