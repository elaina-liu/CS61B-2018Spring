public class NBody
{
    public static double readRadius(String filename)
    {
        In in = new In(filename);
        in.readInt();
        double secondIteminFile = in.readDouble();
        return secondIteminFile;
    }
    public static Planet[] readPlanets(String filename)
    {
        In in = new In(filename);
        in.readInt();
        in.readDouble();
        Planet [] planet = new Planet [5];
        int i=0;
        while (i<planet.length)
        {
            planet[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
                    in.readDouble(),in.readDouble(),in.readString());
            i+=1;
        }
        return planet;
    }
    public static void main(String[] args)
    {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius=readRadius(filename);
        Planet[] planet = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(int i=0; i<=T; i+=dt)
        {
            double [] xForces = new double[5];
            double [] yForces = new double[5];
            for (int a=0; a<5; a++)
            {
                xForces[a]=planet[a].calcNetForceExertedByX(planet);
                yForces[a]=planet[a].calcNetForceExertedByY(planet);
            }
            for (int b=0;b<5;b++)
            {
                planet[b].update(dt,xForces[b],yForces[b]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int j=0; j< planet.length; j++)
            {
                planet[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            StdOut.printf("%d\n", planets.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
            

        }

    }
}