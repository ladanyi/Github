import github.GitHubRequester;
import json.Item;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by user on 2016. 12. 16..
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    public static void main(String... args) {
        GitHubRequester requester = new GitHubRequester();
        requester.setFollowerLimit(0); // to sort the result by followers
        requester.setUserLimit(50);
        ArrayList<Item> users = requester.getUsers();
        
        LOGGER.log(Level.INFO, "Request result with " + users.size() + "users.");
        
        for (Item user : users) {
            System.out.println(user.getLogin() + " - " + user.getFollowers());
        }
    }
    
}
