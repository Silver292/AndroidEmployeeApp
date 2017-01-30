package uk.tlscott.httpServer.handlers;

import java.io.IOException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import uk.tlscott.httpServer.SimpleHttpServer;

public class PdfHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		// add the required response header for a PDF file
		Headers h = httpExchange.getResponseHeaders();
		h.add("Content-Type", "application/pdf");
		// provide a pdf file
		SimpleHttpServer.sendFile(httpExchange, "test.pdf");

	}



}