package shoonye.util.ec;

import java.util.Locale;
import java.util.TimeZone;


public class ThreadLocalExecutionContext implements ExecutionContext{

	private static ThreadLocal<ExecutionContext> delegate = new   ThreadLocal<ExecutionContext>() {
		@Override
		protected ExecutionContext initialValue() {
			return new SimpleExecutionContext();
		}
	};
	
	public void set(ExecutionContext ctx){
		delegate.set(ctx);
	}

	public void set(String key, String value) {
		delegate.get().set(key, value);
	}

	public String get(String key) {
		return delegate.get().get(key);
	}

	public void setApiKey(String appKey) {
		delegate.get().setApiKey(appKey);
	}

	public String getApiKey() {
		return delegate.get().getApiKey();
	}

	public String getUserKey() {
		return delegate.get().getUserKey();
	}

	public void setUserKey(String userKey) {
		delegate.get().setUserKey(userKey);
	}

	public String getAppCode() {
		return delegate.get().getAppCode();
	}

	public void setAppCode(String appCode) {
		delegate.get().setAppCode(appCode);
	}

	public String getRequestId() {
		return delegate.get().getRequestId();
	}

	public void setRequestId(String requestId) {
		delegate.get().setRequestId(requestId);
	}

	public void setLocale(Locale locale) {
		delegate.get().setLocale(locale);
	}

	public Locale getLocale() {
		return delegate.get().getLocale();
	}

	public void setTimeZone(TimeZone timeZone) {
		delegate.get().setTimeZone(timeZone);
	}

	public TimeZone getTimeZone() {
		return delegate.get().getTimeZone();
	}


	public void setLogLevel(String logLevel) {
		delegate.get().setLogLevel(logLevel);
	}

	public String getLogLevel() {
		return delegate.get().getLogLevel();
	}

	public void setSessionId(String sessionId) {
		delegate.get().setSessionId(sessionId);
	}

	public String getSessionId() {
		return delegate.get().getSessionId();
	}

    @Override
    public void setAppDomain(String appDomain) {
        delegate.get().setAppDomain(appDomain);
    }

    @Override
    public String getAppDomain() {
        return delegate.get().getAppDomain();        
    }

	@Override
	public void setUserRoleId(Integer roleId) {
		delegate.get().setUserRoleId(roleId);;
	}

	@Override
	public Integer getUserRoleId() {
		return delegate.get().getUserRoleId();
	}
	
    @Override
    public void flush() {
        delegate.get().flush();
    }

    @Override
    public String toString() {
        return "ThreadLocalExecutionContext [getRequestId()=" + getRequestId() 
                + ", getLogLevel()=" + getLogLevel() 
                + ", getSessionId()=" + getSessionId() + "]";
    }

}
