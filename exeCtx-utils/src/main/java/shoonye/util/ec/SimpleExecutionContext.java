package shoonye.util.ec;

import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class SimpleExecutionContext implements ExecutionContext {	
	private final HashMap<String, Object> map = new HashMap<String, Object>();

	public SimpleExecutionContext(){}
	
	public SimpleExecutionContext(ExecutionContext ec){
	    this();
	    set(ec);
	}
	
	@Override
	public void set(ExecutionContext ctx) {
	    map.put(SESSIONID, ctx.getSessionId());
	    map.put(REQUESTID, ctx.getRequestId());
	    map.put(APP_CODE, ctx.getAppCode());
	    map.put(API_KEY, ctx.getApiKey());
	    map.put(USER_KEY, ctx.getUserKey());
	    map.put(LOG_LEVEL, ctx.getLogLevel());
	    map.put(TIMEZONE, ctx.getTimeZone());
	    map.put(LOCALE, ctx.getLocale());
	    map.put(APP_DOMAIN, ctx.getAppDomain());
	    map.put(USER_ROLE_ID, ctx.getUserRoleId());
	}

	@Override
	public void set(String key, String value) {
		map.put(key,value);	
	}

	@Override
	public String get(String key) {
		return (String)map.get(key);
	}

	@Override
	public void setApiKey(String apiKey) {
		map.put(API_KEY, apiKey);
	}

	@Override
	public String getApiKey() {
		return (String)map.get(API_KEY);
	}

	@Override
	public String getUserKey() {
		return (String)map.get(USER_KEY);
	}

	@Override
	public void setUserKey(String userKey) {
		map.put(USER_KEY, userKey);
	}

	@Override
	public String getAppCode() {
		return (String)map.get(APP_CODE);
	}

	@Override
	public void setAppCode(String appCode) {
		map.put(APP_CODE, appCode);
	}

	@Override
	public String getRequestId() {
		return (String)map.get(REQUESTID);
	}

	@Override
	public void setRequestId(String requestId) {
		map.put(REQUESTID, requestId);
	}
	

	@Override
	public String getSessionId() {
		return (String)map.get(SESSIONID);
	}

	@Override
	public void setSessionId(String sessionId) {
		map.put(SESSIONID, sessionId);
	}
	

	@Override
	public String getLogLevel() {
		return (String)map.get(LOG_LEVEL);
	}

	@Override
	public void setLogLevel(String logLevel) {
		map.put(LOG_LEVEL, logLevel);
	}

	@Override
	public TimeZone getTimeZone() {
		return (TimeZone)map.get(TIMEZONE);
	}

	@Override
	public void setTimeZone(TimeZone timeZone) {
		map.put(TIMEZONE, timeZone);
	}

	@Override
	public Locale getLocale() {
		return (Locale)map.get(LOCALE);
	}

	@Override
	public void setLocale(Locale locale) {
		map.put(LOCALE, locale);
	}

	public HashMap<String, Object> getMap() {
		return map;
	}

    @Override
    public void setAppDomain(String appDomain) {
        map.put(APP_DOMAIN, appDomain);
    }

    @Override
    public String getAppDomain() {
        return (String)map.get(APP_DOMAIN);
    }

    @Override
    public void flush() {
        map.clear();
    }

	@Override
	public void setUserRoleId(Integer roleId) {
		map.put(USER_ROLE_ID, roleId);
		
	}

	@Override
	public Integer getUserRoleId() {
		if(map.get(USER_ROLE_ID) != null)
			return (Integer) map.get(USER_ROLE_ID);
		else
			return null;
	}

}
