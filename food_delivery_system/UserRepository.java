public class UserRepository {
    private Map<String, User> userStore = new HashMap<>();
    
    public boolean isContactUnique(String contact){
        for (User user : userStore.values()) {
            if (user.getContact().equals(contact)) {
                return false;
            }
        }
        return true;
    }

    public void save(User user){
        userStore.put(user.getId(),user);
    }

    public User getUser(String userID){
        return userStore.get(userID);
    }

    public User getUserByContact(String contact){
        for (User user : userStore.values()) {
            if (user.getContact().equals(contact)) {
                return user;
            }
        }
        return null;
    }

    public void delete(String userId) {
        userStore.remove(userId);
    }
}