package spring.service;

import spring.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private static ArrayList<User> userList;

    public UserService() {
        this.userList = new ArrayList<User>();
    }

    public static void addUser(User user) {
        userList.add(user);
    }

    public static void deleteUser(long id) {
        userList.remove(userGetById(id));
    }
 public static void editUser(User user) {
           
             userList.set(indexGetById(user.getId()),user);
		
	}

    public static ArrayList<User> getUsers() {
        return userList;
    }

    public static User userGetById(long id) {
        User searchuser = new User();

        for (User r : userList) {
            if (r.getId() == id) {
                searchuser = r;
            }
        }

        return searchuser;
    }

    public static int indexGetById(long id) {
        int index = -1;

        for (User u : userList) {
            if (u.getId() == id) {
                index = userList.indexOf(u);
            }
        }

        return index;
    }

}
