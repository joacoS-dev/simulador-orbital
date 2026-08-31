package com.orbits;
import java.util.List;

public class Simulation {
    public List<Body> bodies;

    public Simulation(List<Body> bodies){
        this.bodies= bodies;
    }
    
    public void steps(double dt){
        for (Body body : bodies) {
            body.calculateAceleration(bodies);
        }
        for (Body body: bodies){
            body.update(dt);
        }
    }
}
