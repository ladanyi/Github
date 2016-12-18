package github;

import com.google.gson.Gson;
import json.Item;
import json.Page;
import url.UrlBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by user on 2016. 12. 16..
 */
public class GitHubRequester {
    private UrlBuilder urlBuilder;
    private Gson jsonGetter;
    private static final Logger LOGGER = Logger.getLogger(GitHubRequester.class.getName());
    
    public GitHubRequester() {
        this.urlBuilder = new UrlBuilder();
        this.jsonGetter = new Gson();
    }
    
    public void setFollowerLimit(int limit) {
        urlBuilder.addParam("q", "followers:>" + limit);
    }
    
    public void setUserLimit(int limit) {
        urlBuilder.addParam("per_page", String.valueOf(limit));
    }
    
    public ArrayList<Item> getUsers() {
        ArrayList<Item> users = new ArrayList<Item>();
        
        URL url = urlBuilder.buildUserRequesterURL();
        
        if (url != null) {
            String jsonText = readUrl(url);
            Page page = jsonGetter.fromJson(jsonText, Page.class);
            
//            for (Item item : page.getItems()) {
//                users.add(item);
//            }
            page.getItems().stream().forEach(e -> users.add(e));
        }
        
        return getUsersWithFollowerNumber(users);
        //return users;
    }
    
    private String readUrl(URL url) {
        BufferedReader reader;
        StringBuffer buffer = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        
        return buffer.length() > 0 ? buffer.toString() : null;
    }
    
    private ArrayList<Item> getUsersWithFollowerNumber(ArrayList<Item> users) {
        ArrayList<Item> completeUsers = new ArrayList<Item>();
//
////        for (Item user : users) {
////            URL followerUrl = user.getUrl();
////            String jsonText = readUrl(followerUrl);
////            Item completeUser = jsonGetter.fromJson(jsonText, Item.class);
////            completeUsers.add(completeUser);
////        }
      
        users.stream().forEach(e -> completeUsers.add(jsonGetter.fromJson(readUrl(e.getUrl()),Item.class)));
        
        return completeUsers;
    }
}
