import java.util.Scanner;

public class TriangleArea {
    private static final int PointsInTriangle = 3;
    private static final int SegmentsInATriangle = 3;
    public static void main(String[] args) {
        TriangleArea areaCalculator = new TriangleArea();
        areaCalculator.solveTriangleArea();
    }

    private void solveTriangleArea() {
        Point[] trianglePoints = this.createTriangle();
        Double[] segments = this.calculateTriangleSegments(trianglePoints);
        if(this.checkIfTriangle(segments)) {
            Double triangleArea = this.calculateTriangleArea(segments);
            System.out.println("Triangle area is:" + Math.round(triangleArea));
        } else {
            System.out.println("Triangle area is:" + 0);
        }

    }

    private Double calculateTriangleArea(Double[] segments) {
        Double halfPerimeter = 0d;
        for(int i = 0; i < SegmentsInATriangle; i++) {
            halfPerimeter += segments[i];
        }

        halfPerimeter /= 2;

        Double triangleArea = 1d;
        for(int i = 0; i < SegmentsInATriangle; i++) {
            triangleArea *= (halfPerimeter - segments[i]);
        }

        triangleArea = Math.sqrt(halfPerimeter * triangleArea);

        return triangleArea;
    }

    private Point[] createTriangle()
    {
        Scanner scanner = new Scanner(System.in);
        Point[] trianglePoints = new Point[PointsInTriangle];
        for(int i = 0; i < PointsInTriangle; i++)
        {
            double xCoord = scanner.nextDouble();
            double yCoord = Double.parseDouble(scanner.nextLine());
            trianglePoints[i] = new Point(xCoord, yCoord);
        }

        return trianglePoints;
    }

    private Double[] calculateTriangleSegments(Point[] trianglePoints) {
        Double[] segments = new Double[SegmentsInATriangle];
        for(int i = 0; i < SegmentsInATriangle - 1 ; i++) {
            segments[i] = this.calculateSegment(trianglePoints[i], trianglePoints[i + 1]);
        }

        segments[SegmentsInATriangle - 1] = this.calculateSegment(trianglePoints[SegmentsInATriangle - 1], trianglePoints[0]);

        return segments;
    }

    private Boolean checkIfTriangle(Double[] segments) {

        Boolean isTriangle = segments[0] + segments[1] > segments[2] &&
                segments[1] + segments[2] > segments[0] &&
                segments[2] + segments[0] > segments[1];

        return isTriangle;
    }

    private Double calculateSegment(Point firstPoint, Point secondPoint)
    {
        Double segment = Math.sqrt(Math.pow(firstPoint.GetX() - secondPoint.GetX(), 2)
                + Math.pow(firstPoint.GetY() - secondPoint.GetY(), 2));

        return segment;
    }
}