package uk.tlscott.httpServer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import uk.tlscott.employeeDatabase.IEmployeeDAO;
import uk.tlscott.httpServer.handlers.*;

public class SimpleHttpServer {

	private static final int PORT = 8000;
	private HttpServer server;
	IEmployeeDAO dao;

	public SimpleHttpServer(IEmployeeDAO dao) throws Exception {
		this.dao = dao;
		server = HttpServer.create(new InetSocketAddress(PORT), 0);
		server.createContext("/info", new InfoHandler());
		server.createContext("/get", new GetHandler());
		server.createContext("/pdf", new PdfHandler());
		server.createContext("/calc", new CalcHandler());
		server.createContext("/result", new ResultHandler());
		server.createContext("/json", new JsonHandler(dao));
		server.setExecutor(null); // default implementation of threading
	}

	public void start() {
		server.start();
		System.out.println("The server is up and running on port " + PORT);
	}

	public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
		httpExchange.sendResponseHeaders(200, response.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	/**
	 * returns the URL parameters in a HashMap * @param query (?) string parameters
	 * @return map
	 */
	public static Map<String, String> queryToMap(String query) {
		Map<String, String> result = new HashMap<String, String>();

		for (String param : query.split("&")) {
			String pair[] = param.split("=");

			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], ""); 
			}
		}

		return result;
	}

	public static void sendFile(HttpExchange httpExchange, String filePath) throws FileNotFoundException, IOException {
		File file = new File(filePath);
		byte[] byteArray = getByteArrayFrom(file);
		// send the response
		httpExchange.sendResponseHeaders(200, file.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(byteArray, 0, byteArray.length);
		os.close();
	}

	public static byte[] getByteArrayFrom(File file) throws IOException {
		byte[] byteArray = new byte[(int)file.length()];
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis =  new BufferedInputStream(fis);
		bis.read(byteArray, 0, byteArray.length);
		bis.close();
		return byteArray;
	}

}
