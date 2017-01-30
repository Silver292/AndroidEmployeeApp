package uk.tlscott.httpServer.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import uk.tlscott.httpServer.SimpleHttpServer;

public class ResultHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		StringBuilder response = new StringBuilder();
		Map<String, String> params = getPostParams(httpExchange.getRequestBody());
		int num1 = Integer.parseInt(params.get("num1"));
		int num2 = Integer.parseInt(params.get("num2"));
		int result = num1 * num2;

		response.append("<html><body>");
		response.append("<strong>Result</strong> : " + result + "</br>");
		response.append("</body></html>");
		SimpleHttpServer.writeResponse(httpExchange, response.toString());
	}

	private Map<String, String> getPostParams(InputStream requestBody) throws IOException {
		Map<String, String> params = new HashMap<String, String>();

		BufferedReader in = new BufferedReader(new InputStreamReader(requestBody));
		String line = "";
		String request = "";

		while ((line = in.readLine()) != null) {
			request = request + line;
		}

		String[] pairs = request.split("&");
		for (int i = 0; i < pairs.length; i++) {
			String pair = URLDecoder.decode(pairs[i], "UTF-8");
			String[] value = pair.split("=");
			params.put(value[0], value[1]);
		}

		return params;
	}

}