import java.awt.*;

/**
 * Abstract superclass for shapes, with a bounding box defined by a pair of points
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 */
public abstract class Shape {
	protected int x1, y1, x2, y2;		// opposite corners of bounding box
	protected Color color;				// for drawing

	// predefined strokes for drawing
	protected static Stroke solidStroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
	protected static Stroke dottedStroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {10,10}, 0);

	/**
	 * Initializes the shape with the two corners and the color
	 */
	public Shape(int x1, int y1, int x2, int y2, Color c) {
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
		this.color = c;
	}

	/**
	 * Moves the shape such that its upper-left corner is at the new coordinates (and the lower-right follows rigidly)
	 */
	public void moveTo(int x1new, int y1new) {
		x2 += x1new - x1; y2 += y1new - y1;
		x1 = x1new; y1 = y1new;
	}

	/**
	 * Moves the shape by dx in the x coordinate and dy in the y coordinate
	 */
	public void moveBy(int dx, int dy) {
		x1 += dx; y1 += dy;
		x2 += dx; y2 += dy;
	}

	/**
	 * Sets the corners of the bounding box
	 */
	public void setCorners(int x1, int y1, int x2, int y2) {
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
	}

	/**
	 * Recolors the shape
	 */
	public void setColor(Color c) {
		this.color = c;
	}

	/**
	 * Returns whether or not the point is inside the shape
	 */
	public abstract boolean contains(int x, int y);

	/**
	 * Draws the shape via the graphics
	 */
	public abstract void draw(Graphics g);

	/**
	 * Draws a border of the shape via the graphics
	 */
	public abstract void border(Graphics g);

	/**
	 * Returns a readable description of the shape's state
	 */
	public String toString() {
		return x1+" "+y1+" "+x2+" "+y2+" "+color.getRGB();
	}
}
