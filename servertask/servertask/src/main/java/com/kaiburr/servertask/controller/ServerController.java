package com.kaiburr.servertask.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaiburr.servertask.model.Server;
import com.kaiburr.servertask.repository.ServerRepository;
import com.kaiburr.servertask.utilities.ResponseMessage;

@RestController
public class ServerController {
	 
	@Autowired 
	private ServerRepository serverRepository; 
	
	
	@GetMapping("/findAllServers")
	public List<Server> getAllServers(){
		
		return this.serverRepository.findAll();
	}
	@GetMapping("/findServer/{id}")
	public Optional<Server> getServers(@PathVariable int id){
		return this.serverRepository.findById(id);
	}
	
	@GetMapping("/findServerByName/{name}")
	public List<Server> getServersByName(@PathVariable String name){
		return this.serverRepository.getServersByName(name);
		
	}
	@PutMapping("/findAllServers1")
	public List<Server> getAllServers1(){
		return this.serverRepository.findAll();
	}
	
	@PostMapping("/addServer")
	public ResponseEntity<?> saveServer(@RequestBody Server server) {
	    Integer serverId = server.getId();
	    Optional<Server> existingServer = serverRepository.findById(serverId);
	    if (existingServer.isPresent()) {
	        // Server with the same ID already exists
	        ResponseMessage response = new ResponseMessage();
	        response.setStatuscode(400); // Bad Request
	        response.setMessage("Server with ID " + serverId + " already exists");
	        return ResponseEntity.badRequest().body(response);
	    }
	    
	    serverRepository.save(server);
	    
	    // Server saved successfully
	    ResponseMessage response = new ResponseMessage();
	    response.setStatuscode(200); // OK
	    response.setMessage("Server saved successfully");
	    response.setData(server);
	    return ResponseEntity.ok(response);
	}



	@DeleteMapping("/deleteServer/{id}") 
	public String deleteServer(@PathVariable int id) {
		serverRepository.deleteById(id);
		return "Server with id " + id + " is deleted";
	}
	
}