package fr.formation.inti.service;

import java.util.List;
import java.util.Optional;

import fr.formation.inti.entity.User;

public interface IUserService {

 User findByEmailAndPassword(String email, String password) ;
	
 Integer saveUser(User user);
 
 void delete(Integer id);
 
 Optional<User> findById(Integer id);
 
 
 List<User> findAll(); 

}