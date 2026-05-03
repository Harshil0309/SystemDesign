import java.util.UUID;

public class UserService{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean isContactUnique(String contact){
        return this.userRepository.isContactUnique(contact);
    }

    private boolean isInputValid(String name,String address,String contact){
        if(name.length() ==0 || address.length()==0 || contact.length()==0){
            return false;
        }
        return true;
    }

    public String createUser(String name,String address,String contact){

        if(!isInputValid(name,address,contact)){
            throw new IllegalArgumentException("Input details are missing");
        }
        if(!isContactUnique(contact)){
            throw new IllegalArgumentException("Contact already exist");
        }

        String userID = UUID.randomUUID().toString();
        User user = new User(userID,name,address,contact);
        this.userRepository.save(user);
        return "User Created Successfully";
        
    }

    public String updateDetails(String userID,String name,String address,String contact){
        if(!isInputValid(name,address,contact)){
            throw new IllegalArgumentException("Input details are missing");
        }
        User existingUser=this.userRepository.getUserByContact(contact);
        if(existingUser!=null && !existingUser.getId().equals(userID)){
            throw new IllegalArgumentException("Contact already exist");
        }

        User user=this.userRepository.getUser(userID);
        if(user==null) {
            throw new IllegalArgumentException("User doesnt exist");
        }
        user.updateName(name);
        user.updateAddress(address);
        user.updateContact(contact);
        this.userRepository.save(user);
        return "User details updated successfully";

    }
     
    public String deleteUser(String id){
        if(id.length()==0){
            throw new IllegalArgumentException("User details empty");
        }
        User user= this.userRepository.getUser(id);
        if(user==null){
            throw new IllegalArgumentException("User doesnt exist");
        }
        this.userRepository.deleteUser(id);
        return "User deleted successfully";
    }


}



