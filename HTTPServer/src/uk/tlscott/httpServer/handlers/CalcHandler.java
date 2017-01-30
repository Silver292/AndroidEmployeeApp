package uk.tlscott.httpServer.handlers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import uk.tlscott.httpServer.SimpleHttpServer;

public class CalcHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		Headers h = httpExchange.getResponseHeaders();
		h.add("Content-Type", "text/html");
		File file = new File("calc.html");
		byte[] byteArray = SimpleHttpServer.getByteArrayFrom(file);
		httpExchange.sendResponseHeaders(200, file.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(byteArray, 0, byteArray.length);
		os.close();
	}

}