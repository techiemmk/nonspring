package edu.mmk.web.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/time")
public class TimeService {

	@GET
	@Produces("text/plain")
	public String getTime() {
		return new Date().toString();
	}
}
