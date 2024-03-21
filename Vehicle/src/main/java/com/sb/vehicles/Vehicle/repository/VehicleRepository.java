package com.sb.vehicles.Vehicle.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sb.vehicles.Vehicle.beans.Vehicle;
@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, Integer>{
@Query("{'cost':?0}")
	Vehicle findByCost(int cost);

}
