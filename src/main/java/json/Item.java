package json;

import java.net.URL;

/**
 * Created by user on 2016. 12. 16..
 */
public class Item {
    private String login;
    private int id;
    private URL avatar_url;
    private String gravatar_id;
    private URL url;
    private URL html_url;
    private URL followers_url;
    private URL following_url;
    private URL gists_url;
    private URL starred_url;
    private URL subscription_url;
    private URL organization_url;
    private URL repos_url;
    private URL events_url;
    private URL received_events_url;
    private String type;
    private boolean site_admin;
    private int score;
    private int followers;
    
    public String getLogin() {
        return login;
    }
    
    public int getFollowers() {
        return followers;
    }
    
    public URL getUrl() {
        return url;
    }
    
    @Override
    public String toString() {
        return "{" +
                "login='" + login + '\'' +
                '}';
    }
}