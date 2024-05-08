package com.lms.Controller;


import com.lms.Dto.UserDTO;
import com.lms.Entity.User;
import com.lms.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO
            , @PathVariable(name = "id") long id) {

        UserDTO userResponse = userService.updateUser(userDTO, id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable(name = "id") int id) {
        userService.deleteUserById(id);
        return new ResponseEntity("user successfully deleted!!", HttpStatus.OK);
    }


}