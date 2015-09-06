package org.ryyaan2004.chat.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ApplicationService extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> restClass = new HashSet<Class<?>>();
		restClass.add(UserService.class);
		return restClass;
	}
}
