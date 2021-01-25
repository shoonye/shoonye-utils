package shoonye.util.ec;

import java.util.Locale;
import java.util.TimeZone;


public interface ExecutionContext {
		static final String SESSIONID = "SESSIONID";
		static final String REQUESTID = "REQUESTID";
		static final String APP_CODE = "APP_CODE";
		static final String API_KEY = "API_KEY";
		static final String USER_KEY = "USER_KEY";
		static final String LOG_LEVEL = "LOG";
		static final String DEVICE_TYPE = "DT";
		static final String TIMEZONE = "TZ";
		static final String LOCALE = "LOCALE";
		static final String APP_DOMAIN = "APP_DOMAIN";
		static final String USER_ROLE_ID = "USER_ROLE_ID";

		
		public void set(ExecutionContext ctx);
		
		public abstract void set(String key, String value);
		public abstract String get(String key);	
		public abstract void flush();

		
		public abstract void setApiKey(String apiKey);
		public abstract String getApiKey();
		
		public abstract void setAppDomain(String appDomain);
		public abstract String getAppDomain();
		
		public String getAppCode();
		public void setAppCode(String appCode);

		public abstract void setLogLevel(String logLevel);
		public abstract String getLogLevel();
	
		public String getRequestId();
		public void setRequestId(String requestId);
		
		public abstract void setSessionId(String sessionId);
		public abstract String getSessionId();

		public String getUserKey();
		public void setUserKey(String userKey);
		
		public abstract void setUserRoleId(Integer roleId);
		public abstract Integer getUserRoleId();
		
		public abstract void setLocale(Locale locale);
		public abstract Locale getLocale();

		public abstract void setTimeZone(TimeZone timeZone);
		public abstract TimeZone getTimeZone();

}
