package shoonye.util.bean;

import java.util.LinkedHashSet;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 * @param <T>
 */
public class AutoCompleteResult<T> extends LinkedHashSet<Duple<String, T>>{
    private static final long serialVersionUID = 1L;
    public void add(String key , T value){
        add(new Duple<String, T>(key, value));
    }
}
