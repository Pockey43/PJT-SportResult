package fr.formation.inti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.User;

public interface IUserDao extends JpaRepository<User, Integer> {
	
	 User findByEmailAndPassword(String email, String password) ;

	

	
	
	

}