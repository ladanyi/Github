package json;

import java.util.List;

/**
 * Created by user on 2016. 12. 16..
 */
public class Page {
    private int total_count;
    private boolean incomplete_result;
    private String description;
    private List<Item> items;
    
    public int getTotal_count() {
        return total_count;
    }
    
    public boolean isIncomplete_result() {
        return incomplete_result;
    }
    
    public String getDescription() {
        return description;
    }
    
    public List<Item> getItems() {
        return items;
    }
}