package com.weslleycoelho.workshopmongo.resouces;

import com.weslleycoelho.workshopmongo.domain.Post;
import com.weslleycoelho.workshopmongo.domain.User;
import com.weslleycoelho.workshopmongo.dto.UserDTO;
import com.weslleycoelho.workshopmongo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<User> list = service.findAll();
    List<UserDTO> listDto = list.stream().map(user -> new UserDTO(user)).toList();
    return ResponseEntity.ok().body(listDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    User user = service.findById(id);
    UserDTO userDto = new UserDTO(user);
    return ResponseEntity.ok().body(userDto);
  }

  @GetMapping("/{id}/posts")
  public ResponseEntity<List<Post>> findByPosts(@PathVariable String id) {
    User user = service.findById(id);
    return ResponseEntity.ok().body(user.getPosts());
  }

  @PostMapping()
  public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
    User obj = service.fromDTO(dto);
    obj = service.insert(obj);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO dto) {
    User obj = service.fromDTO(dto);
    obj.setId(id);
    obj = service.update(obj);
    return ResponseEntity.noContent().build();
  }

}
