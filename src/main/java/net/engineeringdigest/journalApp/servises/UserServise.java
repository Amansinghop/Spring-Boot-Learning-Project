package net.engineeringdigest.journalApp.servises;

 import net.engineeringdigest.journalApp.entity.User;
 import net.engineeringdigest.journalApp.repository.UserRepository;
 import org.bson.types.ObjectId;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServise {

    @Autowired
    public UserRepository userRepository;


    public void saveUser (User userEntry){
        userRepository.save(userEntry);
    }
    public List<User> getUser (){
        return userRepository.findAll();
    }
    public Optional<User> findUserById (ObjectId myId){
        return userRepository.findById(myId);
    }
    public void deleteUserById(ObjectId myId){
        userRepository.deleteById(myId);
    }
    public User findUserByUserName(String username){
        return userRepository.findByUserName(username);
    }


}
