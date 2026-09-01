package com.orbits;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
    public List<Body> systemBodies= new ArrayList<>();
    String name;

    public SolarSystem(){
        Body sun     = new Body("sun", 1, new Vector3D(0, 0, 0), new Vector3D(0, 0, 0), 0);
        Body earth   = new Body("earth", 3.0035e-6, new Vector3D(0.9833, 0, 0), new Vector3D(0, 6.39, 0), 0);
        Body mars    = new Body("mars", 3.2130e-7, new Vector3D(1.3814, 0, 0), new Vector3D(0, 5.59, 0), 0);
        Body mercury = new Body("mercury", 1.6601e-7, new Vector3D(0.3075, 0, 0), new Vector3D(0, 12.39, 0), 0);
        Body venus   = new Body("venus", 2.4478e-6, new Vector3D(0.7184, 0, 0), new Vector3D(0, 7.50, 0), 0);
        
        systemBodies.add(sun);
        systemBodies.add(earth);
        systemBodies.add(mars);
        systemBodies.add(mercury);
        systemBodies.add(venus);
    }
    public List<Body> getSystemBodies() {
        return systemBodies;
    }
}