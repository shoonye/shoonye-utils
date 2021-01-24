package shoonye.util;

import java.io.File;
import java.util.Comparator;
/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class LastModifiedComparator implements Comparator {   
    public static final int OLDEST_FIRST = 1;  
    public static final int NEWEST_FIRST = 2; 
    private int order;

    public LastModifiedComparator() {
        order = OLDEST_FIRST;
    }

    public LastModifiedComparator(int order) {
        this.order = order;
    }

	public int compare(Object o1, Object o2) {
        if ((o1 instanceof File) && (o2 instanceof File)) {
            long lm1 = ((File) o1).lastModified();
            long lm2 = ((File) o2).lastModified();

            if (lm1 < lm2) {
                return (order == OLDEST_FIRST) ? -1 : 1;
            } else if (lm1 > lm2) {
                return (order == OLDEST_FIRST) ? 1 : -1;
            } else {
                return 0;
            }
        } else if ((o1 instanceof Comparable) && (o2 instanceof Comparable)) {
            return ((Comparable) o1).compareTo(((Comparable) o2));
        } else {
            return -1;
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof LastModifiedComparator);   
    }
    
    public int hashCode(){
        return "LastModifiedComparator".hashCode()*31;
    }
}



