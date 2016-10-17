public class Point {
    private double x;
    private double y;
    public Point(double x, double y){
        this.SetX(x);
        this.SetY(y);
    }
    
    public double GetX(){
        return this.x;
    }
    
    public void SetX(double value) {
        this.x = value;
    }

    public double GetY(){
        return this.y;
    }

    public void SetY(double value) {
        this.y = value;
    }

}