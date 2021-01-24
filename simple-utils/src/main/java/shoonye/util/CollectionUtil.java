package shoonye.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class CollectionUtil {
    
    /**
     * @param iter
     * @return an empty immutable collection if the collection is null
     */
    public static <T> Collection<T> emptyIfNull(Collection<T> iter) {
        return iter == null ? Collections.<T>emptyList() : iter;
    }
   
    public static <T> boolean hasItems(Collection<T> coll) {
        return coll != null && !coll.isEmpty(); 
    }
    
    public static boolean isNotBlank(Collection<? extends Object> list) {
        if(list == null || list.size() <= 0) {
            return false;
        }
        boolean hasContent = false;
        for (Object obj : list) {
            if(obj instanceof String && StringUtil.hasText((String) obj))  {
                hasContent = true;
                break;
            }
            if(obj!=null) {
                hasContent = true;
                break;
            }
            
        }
        return hasContent;
    }
    
    public static boolean isBlank(Collection<? extends Object> list){
    	if(list == null || list.size() <= 0) {
            return true;
        }
        boolean noContent = true;
        for (Object obj : list) {
            if(obj instanceof String && StringUtil.hasText((String) obj))  {
            	noContent = false;
                break;
            }
            if(obj!=null) {
            	noContent = false;
                break;
            }
            
        }
        return noContent;
    }
    
    public static <T> List<T> subList(List<T> list, int offset, int max){
        if(offset==0 && max==list.size()) return list; 
        if(list==null || list.isEmpty()) return list;
        if(offset<=0) offset =0;
        
        if(offset >=list.size()) return Collections.emptyList(); 
        
        int lastIndex = offset + max;
        lastIndex = lastIndex>list.size()?list.size():lastIndex;
        return list.subList(offset, lastIndex);
    }
    
    public static void main(String args[]) {
        List<String> list = new ArrayList<String>();
        list.add(" ");
        boolean x = isNotBlank(list);
        System.out.println(x);
        
        List<String> strings  = new ArrayList<String>(30);
        for(int i = 0;i<30;i++){
            strings.add("test"+(i));
        }
        System.out.println(subList(strings, 0, 10));
        System.out.println(strings.size());
        System.out.println(subList(strings, 30, 10));
        System.out.println(subList(strings, 29, 10));
        System.out.println(subList(strings, 25, 10));
        System.out.println(subList(strings, 31, 10));
    }
    
    public static String csv(Collection<?> collection) {
        StringBuilder result = new StringBuilder();
        for(Object object : collection) {
            result.append("'"+object+"'");
            result.append(",");
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1): "";
    }

    public static <T> SortedSet<T> sort(Iterable<T> iter) {
        TreeSet<T> set = new TreeSet<T>();
        for (T item : iter) {
            set.add(item);
        }
        return set;
    }
    
}
