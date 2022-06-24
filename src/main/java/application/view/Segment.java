package application.view;

import javafx.geometry.Point3D;

public class Segment {
    private Point3D start;
    private Point3D end;

    public Segment(Point3D start, Point3D end) {
        this.start = start;
        this.end = end;
    }

    public Point3D getEnd() {
        return end;
    }

    public Point3D getStart() {
        return start;
    }
}
