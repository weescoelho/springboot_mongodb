package com.weslleycoelho.workshopmongo.resouces;

import com.weslleycoelho.workshopmongo.domain.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    User test1 = new User("1", "Test 1", "test@test.com");
    User test2 = new User("2", "Test 2", "test2@test.com");
    User test3 = new User("3", "Test 3", "test3@test.com");

    List<User> list = new ArrayList<>();

    list.addAll(Arrays.asList(test1, test2, test3));
    return ResponseEntity.ok().body(list);

  }
}
