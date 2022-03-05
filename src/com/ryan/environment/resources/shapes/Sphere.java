package com.ryan.environment.resources.shapes;
import com.ryan.environment.resources.HitDetection;
import com.ryan.render_engine.RasterOptions;
import com.ryan.components.Ray;
import com.ryan.components.Vector3D;

/*
A sphere, a really basic 3D object and the easiest to render. So I
made it first. Contains a Vector3D as the centre, and a double to
represent the radius.

Has a few overloaded functions for ray intersection and console display.
 */
public class Sphere extends Shape
{
    // CENTRE POINT OF THE SPHERE
    private Vector3D c;
    // RADIUS OF THE SPHERE
    private double r;

    public Sphere()
    {
        c = new Vector3D();
        r = 5;
        this.type = Shape.SPHERE;
    }

    public Sphere(Vector3D c, double r)
    {
        this();
        this.c = c;
        this.r = r;
        this.rgb = RasterOptions.Colors.WHITE;
    }

    public Sphere(Vector3D c, double r, int rgb)
    {
        this(c,r);
        this.rgb = rgb;
    }

    public double intersectDiscriminant(Ray ray, double t)
    {
        Vector3D d = ray.eval(t);
        Vector3D e = ray.getOrigin();
        Vector3D c = this.c;
        double R = this.r;

        // render distance issue likely exists within this or rayIntersect
        return (Math.pow( d.dot(e.sub(c)), 2 ) - ( d.dot(d) * ( e.sub(c).dot( e.sub(c) ) ) - Math.pow(R, 2) ) );
    }

    /*
    See Fundamentals of Computer Graphics 5th Edition, p87
     */
    @Override
    public HitDetection rayIntersect(Ray ray, double t)
    {
        double dscrm = intersectDiscriminant(ray, t);

        if(dscrm >= 0)
        {
            Vector3D d = ray.eval(t);
            Vector3D e = ray.getOrigin();
            Vector3D c = this.c;

            // this math may prove to be wrong later!

            //new Vector3D(0,0,0).sub(d).dot(e.sub(c))
            // i think we take the - first, then the +
            double t1 = ( ( 0.0 - d.dot(e.sub(c)) ) - dscrm ) / d.dot(d);
            double t2 = -1.0;

            if (dscrm > 0)
                t2 = ( 0.0 - d.dot(e.sub(c)) + dscrm ) / d.dot(d);

            return new HitDetection( this, t1, t2, ray.eval(t1) );
        }

        return null;
    }

    @Override
    public void consoleDisplay()
    {
        System.out.println("Sphere");
        System.out.println("Radius:\t" + this.r);
    }

    public Sphere transform(Vector3D t)
    {
        return new Sphere( this.c.add(t), this.r );
    }

    public Vector3D getC()
    {
        return c;
    }

    public double getR()
    {
        return r;
    }

    public void setC(Vector3D c)
    {
        this.c = c;
    }

    public void setR(double r)
    {
        this.r = r;
    }
}