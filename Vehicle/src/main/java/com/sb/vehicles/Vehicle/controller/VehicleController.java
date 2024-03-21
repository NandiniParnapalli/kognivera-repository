package com.sb.vehicles.Vehicle.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sb.vehicles.Vehicle.beans.Vehicle;
import com.sb.vehicles.Vehicle.repository.VehicleRepository;
import com.sb.vehicles.Vehicle.service.VehicleService;
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
	@Autowired
	VehicleService rep;
	@PostMapping()
	public Vehicle insert(@RequestBody Vehicle v)
	{
		return rep.saveVehicle(v);
	}
	@GetMapping()
	public List<Vehicle> findAll()
	{
		return rep.getAllVehicles();
	}
	@GetMapping("/{id}")
	public Vehicle getById(@PathVariable int id)
	{
		return rep.getVehicleById(id);
	}
	@GetMapping("/cost")
	public Vehicle getByCost(@RequestParam int cost) {
		// TODO Auto-generated method stub
		return rep.getVehicleByCost(cost);
	}
	@DeleteMapping("/delete")
	public String delete(@RequestBody Vehicle v)
	{
		return rep.deleteVehicle(v);
	}
	@PutMapping("/update/{id}")
	public String update(@PathVariable int id,@RequestBody Vehicle v)
	{
		return rep.updateVehicle(id,v);
	}
}
