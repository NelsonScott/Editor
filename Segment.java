import java.awt.*;

/**
 * A line segment-shaped Shape
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 */
public class Segment extends Shape {
	public Segment(int x1, int y1, int x2, int y2, Color c) {
		super(x1,y1,x2,y2,c);
	}

	public boolean contains(int x, int y) {
		// If (x,y) is within 3 pixels of being within the Segment's bounding box
		// AND within 3 pixels of the line containing the Segment, then we'll
		// say that it is close enough.
		return almostContainsPoint(x, y, Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2))
				&& distanceToPoint(x, y, x1, y1, x2, y2) <= 3;
	}

	/**
	 * Helper method that returns true if (x,y) is within a tolerance of 3 pixels of a
	 * given bounding box. Here, the bounding box is given by the coordinates of
	 * its left, top, right, and bottom.
	 * @author Thomas H. Cormen
	 */
	private static boolean almostContainsPoint(int x, int y, int left, int top, int right, int bottom) {
		return x >= left - 3 && y >= top - 3
				&& x <= right + 3 && y <= bottom + 3;
	}

	/**
	 * Helper method that returns the distance from Point p to the line
	 * containing a line segment whose endpoints are given.
	 * @author Thomas H. Cormen
	 */
	private static double distanceToPoint(int x, int y, int x1, int y1, int x2, int y2) {
		if (x1 == x2) // vertical segment?
			return (double) (Math.abs(x - x1)); // yes, use horizontal distance
		else if (y1 == y2) // horizontal segment?
			return (double) (Math.abs(y - y1)); // yes, use vertical distance
		else {
			// Here, we know that the segment is neither vertical nor
			// horizontal.
			// Compute m, the slope of the line containing the segment.
			double m = ((double) (y1 - y2)) / ((double) (x1 - x2));

			// Compute mperp, the slope of the line perpendicular to the
			// segment.
			double mperp = -1.0 / m;

			// Compute the (ix, iy) intersection of the line containing the
			// segment and the line that is perpendicular to the segment and that
			// contains (x,y).
			double ix = (((double) y1) - ((double) y) - (m * x1) + (mperp * x)) / (mperp - m);
			double iy = m * (ix - x1) + y1;

			// Return the distance between Point p and (x, y).
			return Math.sqrt(Math.pow(x - ix, 2) + Math.pow(y - iy, 2));
		}
	}

	public void draw(Graphics g) {
		g.setColor(color);
		((Graphics2D)g).setStroke(solidStroke);
		g.drawLine(x1, y1, x2, y2);
	}

	public void border(Graphics g) {
		// Green dotted line
		((Graphics2D)g).setStroke(dottedStroke);
		g.setColor(Color.green);
		g.drawLine(x1, y1, x2, y2);
	}

	public String toString() {
		return "segment "+super.toString();
	}
}
