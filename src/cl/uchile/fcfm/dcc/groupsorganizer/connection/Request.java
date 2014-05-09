package cl.uchile.fcfm.dcc.groupsorganizer.connection;

import java.util.HashMap;
import java.util.Map;

public class Request {
	private Map<String, Object> mPackage;
	private String mUri;
	private String mMethodName;

	public Request() {
		this("");
	}
	
	public Request(String uri) {
		mPackage = new HashMap<String, Object>();
		this.mUri = uri;
	}
	
	public void add(String name, Object value) {
		mPackage.put(name, value);
	}
	
	public void setURI(String uri) {
		this.mUri = uri;
	}
	
	public String getUri() {
		return this.mUri+(getMethodName()==null?"":getMethodName());
	}
	
	public Map<String, Object> getPackage() {
		return this.mPackage;
	}

	public void setMethodName(String methodName) {
		mMethodName = methodName;
	}
	
	public String getMethodName() {
		return mMethodName;
	}
}
