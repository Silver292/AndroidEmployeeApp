package uk.tlscott.httpServer.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import uk.tlscott.employeeDatabase.IEmployeeDAO;
import uk.tlscott.httpServer.JsonParser;
import uk.tlscott.httpServer.SimpleHttpServer;

public class JsonHandler implements HttpHandler{

	IEmployeeDAO dao;
	
	public JsonHandler(IEmployeeDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String response =  new JsonParser(dao).getAllEmployees();
		SimpleHttpServer.writeResponse(httpExchange, response.toString());
	}

}
