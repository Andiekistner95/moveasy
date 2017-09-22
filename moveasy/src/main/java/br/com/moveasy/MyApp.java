package br.com.moveasy;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class MyApp extends ResourceConfig{

	public MyApp() {
		packages("br.com.moveasy.controllers");
	}
	
}
