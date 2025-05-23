package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.servises.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    public UserServise userServise;

    @GetMapping
    public ResponseEntity<List<User>> getUser(){
        List<User> userList=userServise.getUser();
        return  new ResponseEntity< List<User>>(userList,HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<User> createUser(@RequestBody User user){
        userServise.saveUser(user);
        return  new ResponseEntity<User>(user,HttpStatus.OK);
    }
    @PutMapping("/{userName}")
    public  ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName){
        User userInDb= userServise.findUserByUserName(userName);
        if(user != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userServise.saveUser(userInDb);
        }

        return  new ResponseEntity< >( HttpStatus.NO_CONTENT);
    }




}
