import java.lang.Math;
public class Planet
{
    double xxPos,yyPos,xxVel,yyVel,mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img)
    {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    public Planet(Planet p)
    {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p)
    {
        double disx=(this.xxPos-p.xxPos)*(this.xxPos-p.xxPos);
        double disy=(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos);
        return Math.sqrt(disx+disy);
    }

    public double calcForceExertedBy(Planet p)
    {
        double G=6.67e-11;
        double distance=(calcDistance(p))*(calcDistance(p));
        return G*this.mass*p.mass/distance;
    }

    public double calcForceExertedByX(Planet p)
    {
        double Force=calcForceExertedBy(p);
        double totaldistance=calcDistance(p);
        double distancex=p.xxPos-this.xxPos;
        return Force*distancex/totaldistance;
    }
    public double calcForceExertedByY(Planet p)
    {
        double Force=calcForceExertedBy(p);
        double totaldistance=calcDistance(p);
        double distancey=p.yyPos-this.yyPos;
        return Force*distancey/totaldistance;
    }

    public double calcNetForceExertedByX(Planet[] p)
    {
        double netForcex=0.0;
        for(int i=0; i<p.length;i++)
        {
            if (!this.equals(p[i]))
                netForcex+=calcForceExertedByX(p[i]);
            else
                continue;
        }
        return netForcex;
    }

    public double calcNetForceExertedByY(Planet[] p)
    {
        double netForcey=0.0;
        for(int i=0; i<p.length;i++)
        {
            if (!this.equals(p[i]))
                netForcey+=calcForceExertedByY(p[i]);
            else
                continue;
        }
        return netForcey;
    }

    public void update(double time, double ForceX, double ForceY)
    {
        double accelerationX = ForceX/this.mass;
        double accelerationY = ForceY/this.mass;
        this.xxVel += time * accelerationX;
        this.yyVel += time * accelerationY;
        this.xxPos += time * this.xxVel;
        this.yyPos += time * this.yyVel;
    }

    public void draw()
    {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}