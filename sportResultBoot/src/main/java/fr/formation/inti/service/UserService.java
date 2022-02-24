package fr.formation.inti.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.inti.dao.IUserDao;

import fr.formation.inti.entity.User;

@Service
@Transactional
public class UserService implements IUserService{
	
	private final Log log = LogFactory.getLog(UserService.class);
	
	@Autowired
	private IUserDao userDao;

	@Override
	public User findByEmailAndPassword(String email, String password) {
		
		User user = userDao.findByEmailAndPassword(email, password);

		return user;
	
	}

	@Override
	public Integer saveUser(User user) {
		
		Integer id = userDao.save(user).getIdUser();
		return id;
	}


	@Override
	public Optional<User> findById(Integer id) {
		
		return userDao.findById(id);
	}



	@Override
	public List<User> findAll() {
		
		List<User> list = userDao.findAll();
		return list;
	}

	@Override
	public void delete(Integer id) {
		userDao.deleteById(id);
		
	}

	
	


}