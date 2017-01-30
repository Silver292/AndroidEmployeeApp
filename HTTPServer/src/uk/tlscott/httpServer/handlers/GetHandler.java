package uk.tlscott.httpServer.handlers;

import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import uk.tlscott.httpServer.SimpleHttpServer;

public class GetHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		StringBuilder response = new StringBuilder();
		Map<String, String> params = SimpleHttpServer.queryToMap(httpExchange.getRequestURI().getQuery());
		response.append("<html><body>");
		response.append("Forename : " + params.get("firstname") + "</br>");
		response.append("Surname  : " + params.get("lastname") + "</br>");
		response.append("</body></html>");
		SimpleHttpServer.writeResponse(httpExchange, response.toString());
	}

}
