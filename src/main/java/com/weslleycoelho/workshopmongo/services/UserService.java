package com.weslleycoelho.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weslleycoelho.workshopmongo.domain.User;
import com.weslleycoelho.workshopmongo.dto.UserDTO;
import com.weslleycoelho.workshopmongo.repositories.UserRepository;
import com.weslleycoelho.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(String id) {
    Optional<User> user = repository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
  }

  public User insert(User obj) {
    return repository.insert(obj);
  }

  public void delete(String id) {
    findById(id);
    repository.deleteById(id);
  }

  public User update(User obj) {
    User newObj = findById(obj.getId());
    updateData(newObj, obj);
    return repository.insert(newObj);
  }

  private void updateData(User newObj, User obj) {
    newObj.setName(obj.getName());
    newObj.setEmail(obj.getEmail());
  }

  public User fromDTO(UserDTO obj) {
    return new User(obj.getId(), obj.getName(), obj.getEmail());
  }
}
