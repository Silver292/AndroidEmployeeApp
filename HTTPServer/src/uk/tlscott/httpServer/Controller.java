package uk.tlscott.httpServer;

public class Controller {

	public static void main(String[] args) {
		try {
			SimpleHttpServer server = new SimpleHttpServer(null);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
