package com.ryan.environment.shapes;
import com.ryan.components.Ray;

/*
The parent class of all shapes that can be used in 3D space.
 */
public class Shape
{
    public static String SPHERE = "Sphere";
    public static String SQUARE = "Square";
    public static String CUBE   = "Cube";
    public static String NULL   = "NULL";

    protected String type;
    protected int rgb;

    public Shape()
    {
        this.type = Shape.NULL;
    }

    public HitDetection rayIntersect(Ray ray, double mag)
    {
        return null;
    }

    public void consoleDisplay()
    {
        System.out.println("Abstract Shape");
    }

    public int getRgb()
    {
        return this.rgb;
    }

    public void setRgb(int rgb)
    {
        this.rgb = rgb;
    }
}