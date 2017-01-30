package uk.tlscott.httpServer.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import uk.tlscott.httpServer.SimpleHttpServer;

public class InfoHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String response =  "Use /get?firstname=Alan&lastname=Crispin to see how to handle url parameters";
		SimpleHttpServer.writeResponse(httpExchange, response.toString());
	}

}