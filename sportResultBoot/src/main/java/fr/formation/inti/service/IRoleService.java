package fr.formation.inti.service;

import java.util.List;
import java.util.Optional;

import fr.formation.inti.entity.Role;

public interface IRoleService {

	Integer saveRole(Role role);
	 
	 void update(Role role);
	 
	 void delete(Integer id);
	 
	 Optional<Role> findById(Integer id);
	 
	 List <Role> findAll();
	
}