import java.awt.*;

/**
 * A rectangle-shaped Shape
 * @Author Kyung Ryun (Kevin) Kim & Scott Nelson
 */
public class Rectangle extends Shape {
	public Rectangle(int x1, int y1, int x2, int y2, Color c) {
		
		// Infer upper left, lower right and color
		super(x1, y1, x2, y2, c);
	}
	
	// Checking whether the pointer in inside of a rectangle
	public boolean contains(int x, int y) {
		setCorners (x1, y1, x2, y2);
		return (x1 < x && x <x2 && y1 < y && y < y2);
	}

	public void setCorners(int x1, int y1, int x2, int y2) {
		// Infer upper left and lower right
		this.x1 = Math.min(x1, x2); this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2); this.y2 = Math.max(y1, y2);
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x1, y1, x2 - x1, y2 - y1);
	}

	public void border(Graphics g) {
		((Graphics2D)g).setStroke(dottedStroke);
		g.setColor(Color.green);
		g.drawRect(x1, y1, x2 - x1, y2 - y1);
	}

	public String toString() {
		return "rectangle "+super.toString();
	}
}

