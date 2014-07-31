import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Shortcircuits communication to/from the server for the editor
 * @author Kyung Ryun (Kevin) Kim & Scott Nelson
 */
public class EditorCommunicatorStandalone extends EditorCommunicator {
	public EditorCommunicatorStandalone(Editor editor) {
		super(null, editor);
	}

	// Handle editor requests by doing them immediately on the editor's sketch
	public void requestDelete (int selected) {
		editor.getSketch().doDelete(selected);
		editor.repaint();
	}
	
	public void requestAdd (Shape shape){
		editor.getSketch().doAddEnd(shape);
		editor.repaint();
	}
	public void requestRecolor (int selected, Color c){
		editor.getSketch().doRecolor(selected, c);
		editor.repaint();
	}
	public void requestMove (int selected, int x1, int y1){
		editor.getSketch().doMoveTo(selected, x1, y1);
		editor.repaint();
	}
}
