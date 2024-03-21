package com.sb.vehicles.Vehicle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.vehicles.Vehicle.beans.Vehicle;
import com.sb.vehicles.Vehicle.repository.VehicleRepository;

@Service
public class VehicleService {
@Autowired
VehicleRepository rep;
public Vehicle saveVehicle(Vehicle v)
{
	return rep.save(v);
}
public List<Vehicle> getAllVehicles()
{
	return rep.findAll();
}
public Vehicle getVehicleById(int id)
{
	
	return rep.findById(id).orElse(null);
}
public String deleteVehicle(Vehicle v)
{
	rep.delete(v);
	return "vehicle deleted";
}
public String updateVehicle(int id,Vehicle v)
{
	Vehicle existingVehicle=rep.findById(id).orElse(null);
	if(existingVehicle!=null)
	{
	 rep.save(v);
	return "updated";
	}
	else
	{
		return "vehicle not found";
	}
}
public Vehicle getVehicleByCost(int cost) {
	// TODO Auto-generated method stub
	return rep.findByCost(cost);
}
}
