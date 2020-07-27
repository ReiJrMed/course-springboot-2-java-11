package com.empresa.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.empresa.course.entities.User;
import com.empresa.course.repositories.UserRepository;

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
		return optionalUser.get(); //.get() resgata o objeto instanciado no optional que é o User
	}
	
	public User insert(User user) {
		return userRepository.save(user); //O save por padrão já retorna o objeto salvo
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	public User update(Long id, User user) {
		User entity = userRepository.getOne(id);
		//entity é entidade monitorada, o getOne(id) instancia o objeto, mas não vai ao database ainda
		//sinaliza ao JPA que esse é um objeto monitorado para em seguida fazer operações de banco de dados com ele
		
		updateData(entity, user);
		
		return userRepository.save(entity); 
	}

	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}

}
