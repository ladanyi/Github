package url;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by user on 2016. 12. 16..
 */
public class UrlBuilder {
    private static final Logger LOGGER = Logger.getLogger(UrlBuilder.class.getName());
    private static final String BASE_URL = "https://api.github.com/";
    private static final String SEARCH_PREFIX = "search/";
    private static final String USERS_PREFIX = "users";
    private static final String QUESTIONMARK = "?";
    private static final String AND = "&";
    private static final String EQUATION = "=";
    private Map<String, String> params;
    
    public UrlBuilder() {
        this.params = new HashMap<String, String>();
    }
    
    public void addParam(String key, String value) {
        this.params.put(key, value);
    }
    
    public URL buildUserRequesterURL() {
        URL url = null;
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL).append(SEARCH_PREFIX).append(USERS_PREFIX);
        
        int counter = 0;
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            
            if (counter == 0) {
                builder.append(QUESTIONMARK);
            } else {
                builder.append(AND);
            }
            
            builder.append(pair.getKey()).append(EQUATION).append(pair.getValue());
            
            it.remove();
            counter++;
        }
        
        
        try {
            url = new URL(builder.toString());
        } catch (Exception e) {
            
            LOGGER.log(Level.SEVERE, e.toString());
        }
        
        return url;
    }
    
}
