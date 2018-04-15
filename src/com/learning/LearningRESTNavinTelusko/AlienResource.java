package com.learning.LearningRESTNavinTelusko;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/aliens")
public class AlienResource {
	AlienRepository repo=new AlienRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Alien> getAliens(){
		System.out.println("getAliens called...");
		return repo.getAliens();
	}
	
	@GET
	@Path("alien/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Alien getAlien(@PathParam("id") int id){
		System.out.println("getAlien called...");
		return repo.getAlien(id);
	}
	
	@POST
	@Path("/alien")
	@Consumes(MediaType.APPLICATION_XML)
	public Alien createAlien(Alien a1){
		repo.create(a1);
		System.out.println("createAlien called...");
		return a1;
	}
	
	@PUT
	@Path("/alien")
	@Consumes(MediaType.APPLICATION_XML)
	public Alien updateAlien(Alien a1){
		if(repo.getAlien(a1.getId()).getId()==0){
			repo.create(a1);
			System.out.println("Id doesn't exist");
		}
		else{
			repo.update(a1);
			System.out.println("Id exist");
		}
		System.out.println("updateAlien called...");
		return a1;
	}
	
	@DELETE
	@Path("/alien/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public String killAlien(@PathParam("id") int id){
		if(repo.getAlien(id).getId()==0){
			System.out.println("Can't kill as it doesn't exist");
			return "Can't kill as it doesn't exist";
		}
		else{
			repo.kill(id);
			System.out.println("Id exist");
		}
		System.out.println("killAlien called...");
		return "Killed";
	}
}
