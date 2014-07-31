import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Handles communication to/from the server for the editor
 * Dartmouth CS 10, Winter 2014
 * @author Kyung Ryun (Kevin) Kim & Scott Nelson
 */
public class EditorCommunicator extends Thread {
	private PrintWriter out;		// to server
	private BufferedReader in;		// from server
	protected Editor editor;		// handling communication for

	/**
	 * Establishes connection and in/out pair
	 */
	public EditorCommunicator(String serverIP, Editor editor) {
		this.editor = editor;
		if (serverIP == null) {
			System.out.println("operating in standalone mode");
			out = new PrintWriter(System.out);
		}
		else {
			System.out.println("connecting to " + serverIP + "...");
			try {
				Socket sock = new Socket(serverIP, 4242);
				out = new PrintWriter(sock.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				System.out.println("...connected");
			}
			catch (IOException e) {
				System.err.println("couldn't connect");
			}
		}
	}

	/**
	 * Sends message to the server
	 */
	public void send(String msg) {
		out.println(msg);
	}

	/**
	 * Keeps listening for and handling (your code) messages from the server
	 */
	public void run() {
		try {
			// Handle messages then send it to the server
			String line;
			Sketch sketch = editor.getSketch();
			while ((line = in.readLine()) != null) {
				Message msg = new Message(line);
				msg.update(editor.getSketch());
				editor.repaint();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}	

	// Send editor requests to the server
	public void requestDelete (int selected) {
		send("delete " + selected);
	}
	public void requestAdd (Shape shape){
		send("add "+ shape.toString());
	}
	public void requestRecolor (int selected, Color c){
		send("recolor " + c.getRGB() + " " + selected);
	}
	public void requestMove (int selected, int x2, int y2){
		send("move " + x2 + " " + y2 + " " + selected);
	}
}