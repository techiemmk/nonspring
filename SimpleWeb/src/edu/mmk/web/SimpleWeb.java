package edu.mmk.web;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Date;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class SimpleWeb{
	
	HttpServer server = null;
	
	private String contextPath;
	private int port;
	
	public void start() throws Exception{
		server = HttpServer.create(new InetSocketAddress(this.port), 0);
		server.createContext(this.contextPath, new MyWebHandler(this));
		server.setExecutor(null);
		server.start();
		System.out.println("Server started at port "+this.port+" with context path "+this.contextPath+" !!!");
		System.out.println("Type "+this.contextPath+"/stop to stop the Server");
	}

	public void stop(){
		System.out.println("Shutting down the server...");
		server.stop(0);
	}
	
	public SimpleWeb(final String contextPath, final int port){
		this.contextPath = contextPath;
		this.port = port;
	}

	public static void main(String args[]) throws Exception{
		boolean useDefaults = true;
		if(args != null && args.length >= 1){
			if(args[0] != null && args[0].length() != 0){
				useDefaults = false;
			}
		}
		int defaultPort = 1122;
		String contextPath = "/myserver";
		if(!useDefaults)
			contextPath = "/" + args[0];
		SimpleWeb myserver = new SimpleWeb(contextPath, defaultPort);
		myserver.start();
	}

	class MyWebHandler implements HttpHandler{

		SimpleWeb parent;

		public MyWebHandler(SimpleWeb myServer){
			this.parent = myServer;
		}

		@Override
		public void handle(HttpExchange exchange)throws IOException{
			URI uri = exchange.getRequestURI();
			System.out.println("Request URI: "+uri.toString());					
			if(uri.getRawPath().contains("stop")){
				String response = "Shutting down the server... thanks for using my server...";
				exchange.sendResponseHeaders(200, response.length());
				OutputStream os = exchange.getResponseBody();
				os.write(response.getBytes());
				os.close();
				parent.stop();
			}
			else{
				String response = "Hello... Welcome to my own server... You reached here at "+new Date().toString();
				exchange.sendResponseHeaders(200, response.length());
				OutputStream os = exchange.getResponseBody();
				os.write(response.getBytes());
				os.close();
			}
		}
	}
}