import java.util.*;
public class UserService{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User createUser(String name,String contact){
        if(this.userRepository.isContactUnique(contact)){
            String userId=UUID.randomUUID().toString();
            User user=new User(userId,name,contact);
            this.userRepository.save(user);
            return user;
        }
        return null;
    }

    public User getUserById(String id){
        return this.userRepository.getUserById(id); 
    }
}