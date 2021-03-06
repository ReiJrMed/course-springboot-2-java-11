package com.empresa.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.empresa.course.entities.User;
import com.empresa.course.repositories.UserRepository;
import com.empresa.course.services.exceptions.DatabaseException;
import com.empresa.course.services.exceptions.ResourceNotFoundException;

//@Component //permite essa classe ser instanciada automaticamente com @Autowired se ela for um componente
@Service //para ser mais específico indicando que é um serviço e ela ser instanciada automaticamente com @Autowired, prioriza-se ser específico
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id){
		Optional<User> optionalUser = userRepository.findById(id); //esse método retorna um valor tipo optional
		return optionalUser.orElseThrow(() -> new ResourceNotFoundException(id));
		//orElseThrow(() -> exc) tenta instanciar e salvar o objeto, se houver problema lança exceção
		//optionalUser.get(); //.get() resgata o objeto instanciado no optional que é o User
	}
	
	public User insert(User user) {
		return userRepository.save(user); //O save por padrão já retorna o objeto salvo
	}
	
	public void delete(Long id) {
		try {
		  userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User user) {
		try {
			User entity = userRepository.getOne(id);
			//entity é entidade monitorada, o getOne(id) instancia o objeto, mas não vai ao database ainda
			//sinaliza ao JPA que esse é um objeto monitorado para em seguida fazer operações de banco de dados com ele
			
			updateData(entity, user);
			
			return userRepository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}

}
