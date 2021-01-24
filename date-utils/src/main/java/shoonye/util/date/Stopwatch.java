package shoonye.util.date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class Stopwatch {
    public static final Logger logger = LoggerFactory.getLogger(Stopwatch.class);
    
    private DateTime startTime;
    private DateTime endTime;
    
    public void start(){
        startTime = new DateTime();
        endTime = null;
    }
    
    public void stop(){
        endTime = new DateTime();
    }
    
    public long elapsedMillis(){
        endTimeNullCheck();
        return (endTime.getMillis()-startTime.getMillis());
    }

    public double elapsedSeconds(){
        endTimeNullCheck();
        return ((endTime.getMillis()-startTime.getMillis())/1000d);
    }
    
    protected void endTimeNullCheck() {
        if(endTime==null){
            logger.warn("Elapsed Time called before stoping the stopwatch.");
            stop();
        }
    }
    
   
}
