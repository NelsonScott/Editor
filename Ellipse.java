import java.awt.*;

/**
 * An ellipse-shaped Shape
 * Keeps the corners at the upper left and lower right, for easy Java drawing
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 */
public class Ellipse extends Shape {
	public Ellipse(int x1, int y1, int x2, int y2, Color c) {
		// Infer upper left and lower right
		super(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1,  y2), c);
	}

	public boolean contains(int x, int y) {
		double a = (x2-x1)/2.0, b = (y2-y1)/2.0;
		double dx = x - (x1 + a); // horizontal distance from center
		double dy = y - (y1 + b); // vertical distance from center

		// Apply the standard geometry formula. (See CRC, 29th edition, p. 178.)
		return Math.pow(dx / a, 2) + Math.pow(dy / b, 2) <= 1;
	}

	public void setCorners(int x1, int y1, int x2, int y2) {
		// Infer upper left and lower right
		this.x1 = Math.min(x1, x2); this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2); this.y2 = Math.max(y1,  y2);
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x1, y1, x2-x1, y2-y1);
	}

	public void border(Graphics g) {
		((Graphics2D)g).setStroke(dottedStroke);
		g.setColor(Color.green);
		g.drawOval(x1, y1, x2-x1, y2-y1);
	}

	public String toString() {
		return "ellipse "+super.toString();
	}
}
