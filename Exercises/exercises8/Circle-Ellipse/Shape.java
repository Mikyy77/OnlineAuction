public class Shape {

    // making a superclass for both Ellipse and Circle types

        private Point f1;
        private Point f2;
        private int a;
        private int b;

        // we're not checking whether these parameters really represent an ellipse
        public Shape(Point f1, Point f2, int a, int b) {
            this.f1 = f1;
            this.f2 = f2;
            this.a = a;
            this.b = b;
        }

    public Point getF1() {
        return f1;
    }
    public Point getF2() {
        return f2;
    }
    public void setF1(Point p) {
        f1.setX(p.getX());
        f1.setY(p.getY());
    }
    public void setF2(Point p) {
        f2.setX(p.getX());
        f2.setY(p.getY());
    }
    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public void setA(int d) {
        a = d;
        // postcondition: a = d -- this is obvious from the code
        // postcondition: b = b@pre -- but we may be not aware that we guarantee not to touch b
        // (@pre provides the variable value before running the method)
    }
    public void setB(int d) {
        b = d;
        // postcondition: b = d
        // postcondition: a = a@pre -- the same problem...
    }
}
