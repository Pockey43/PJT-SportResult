package fr.formation.inti.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.inti.dao.IRoleDao;
import fr.formation.inti.entity.Role;

@Service
@Transactional
public class RoleService implements IRoleService {

	private final Log log = LogFactory.getLog(RoleService.class);
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	public Integer saveRole(Role role) {
		
		Integer id = roleDao.save(role).getIdRole();
		return id;
	}

	@Override
	public void update(Role role) {
		
		roleDao.save(role);
		
	}

	@Override
	public void delete(Integer id) {
		
		roleDao.deleteById(id);
		
	}

	@Override
	public Optional<Role> findById(Integer id) {
		
		return roleDao.findById(id);
	}

	@Override
	public List<Role> findAll() {
		
		List<Role> list = roleDao.findAll();
		return list;
	}


}