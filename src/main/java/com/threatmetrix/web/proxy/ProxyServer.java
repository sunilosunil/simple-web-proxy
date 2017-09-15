
package com.threatmetrix.web.proxy;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.threatmetrix.web.proxy.util.Config;
import com.threatmetrix.web.proxy.util.Utils;

@SuppressWarnings("restriction")
public class ProxyServer {
	static Config config;
	
	public static void main(String[] args) throws Exception {
		if(args == null | args.length < 1){
			config = new Config();
		} else {
			config = new Config(args);
		}
		HttpServer server = HttpServer.create(new InetSocketAddress(config.getPort()), 0);
		server.createContext("/"+config.getUrlHandler(), new MyHandler());
		server.start();
	}

	static class MyHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			URI requestURL = t.getRequestURI();
			String response = "This is the response for URI : " + requestURL.toString();
			String directURL = Utils.buildDirectURL(requestURL, config);
			if(directURL != null) {
			Content result = Request.Get(directURL)
		    .execute().returnContent();
			response = result.asString();
			} else {
				response = "Not allowed ! " + config;
			}
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
}
