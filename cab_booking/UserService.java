import java.util.UUID;
public class UserService{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User createUser(String name,String contact){
        if(this.userRepository.isContactUnique(contact)){
            String userId=UUID.randomUUID().toString();
            User user = new User(userId,name,contact);
            this.userRepository.save(user);
            return user;
        }
        return null;
    }

    public User getUserById(String id){
        return this.userRepository.getUser(id); 
    }

    public void updateDetails(String id,String name,String contact){
        User existingUser=this.userRepository.getUserByContact(contact);
        if(existingUser!=null){
            if(!existingUser.getId().equals(id)){
                return;
            }
            User user=this.userRepository.getUser(id);
            user.setContact(contact);
            user.setName(name);
            this.userRepository.save(user);
        }
        return;
    }
}