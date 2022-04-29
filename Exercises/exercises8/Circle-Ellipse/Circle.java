
public class Circle extends Shape {
	public Circle(Point c, int r) {
		super(c, c, r, r);
	}
	// the same problem recurs with the foci -- shows up when moving a circle
	public void setF1(Point p) {
		super.setF1(p);
		super.setF2(p);
	}
	public void setF2(Point p) {
		super.setF1(p);
		super.setF2(p);
	}  
	public void setA(int d) {
		super.setA(d);
		super.setB(d);
		// postcondition: a = d
		// postcondition: b = d -- but in the overriden method we promised not to touch b,
		// 					and now we're giving this up, weakening the postcondition
	}
	public void setB(int d) {
		super.setA(d);
		super.setB(d);
		// postcondition: b = d
		// postcondition: a = d -- the same as with setA()
	}
}
