import java.awt.Color;
import java.util.ArrayList;

/**
 * Representation of a message for updating a sketch
 * @author Kyung Ryun (Kevin) Kim & Scott Nelson
 */
public class Message {
	// Setting instance variables
	String[] arr;
	String name, command, message;
	int x1, x2, y1, y2, idx;
	Color color;
	
	/**
	 * Initializes it from a string representation used for communication
	 * @param msg
	 */
	public Message(String msg) {
		message = msg;
		arr = msg.split(" ");
		command = arr[0];
		
		if (command.equals("add")){
			// Setting up the variable names
			name = arr[1];
			x1 = Integer.parseInt(arr[2]);
			y1 = Integer.parseInt(arr[3]);
			x2 = Integer.parseInt(arr[4]);
			y2 = Integer.parseInt(arr[5]);
			color = new Color(Integer.parseInt(arr[6]));
		}
		
		if (command.equals("delete")){
			// Setting up the variable name
			idx = Integer.parseInt(arr[1]);
		}
		
		if (command.equals("recolor")){
			// Setting up the variable names
			color = new Color(Integer.parseInt(arr[1]));
			idx = Integer.parseInt(arr[2]);
		}
		
		if (command.equals("move")){
			// Setting up the variable names
			x2 = Integer.parseInt(arr[1]);
			y2 = Integer.parseInt(arr[2]);
			idx = Integer.parseInt(arr[3]);
		}
	}
	
	/**
	 * Updates the sketch according to the message
	 * This may result in a modification of the message to be passed on
	 */
	public void update(Sketch sketch) {
		if (command.equals("add")){
			if (name.equals("ellipse")){
				Ellipse e = new Ellipse(x1, y1, x2, y2, color);
				sketch.doAddEnd(e);
			}
			if (name.equals("rectangle")){
				Rectangle r = new Rectangle(x1, y1, x2, y2, color);
				sketch.doAddEnd(r);
			}
			if (name.equals("segment")){
				Segment s = new Segment(x1, y1, x2, y2, color);
				sketch.doAddEnd(s);
			}
		}
		else if (command.equals("delete")){
			sketch.doDelete(idx);
		}
		else if (command.equals("recolor")){
			sketch.doRecolor(idx, color);
		}
		else if (command.equals("move")){
			sketch.doMoveTo(idx, x2, y2);
		}
	}

	/**
	 * Converts to a string representation for communication
	 */
	public String toString() {
		return message;
	}
}
