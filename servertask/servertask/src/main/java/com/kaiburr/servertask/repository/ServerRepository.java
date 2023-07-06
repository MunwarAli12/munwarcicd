package com.kaiburr.servertask.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.kaiburr.servertask.model.Server;

@Repository
public interface ServerRepository extends MongoRepository<Server, Integer>{
 
	@Query(value="{name : ?0}")
	List<Server> getServersByName(String name);
}
