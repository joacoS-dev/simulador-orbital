package com.orbits;
import java.util.List;

public class Body {
    String name;
    Vector3D positionVector;
    Vector3D velocityVector;
    Vector3D acelerationVector;
    double mass;
    int color;
    
    public void setColor(int color) {
        this.color = color;
    }

    final static double G= 1.0; 

    public Body(String name,double mass, Vector3D positionVector, Vector3D velocityVector){
        this.name= name;
        this.positionVector= positionVector;
        this.velocityVector= velocityVector;
        this.mass= mass;
        this.acelerationVector= new Vector3D(0, 0,0);
        this.color= 0xF33;
    }

    public Vector3D calculateOrbitalForce(Body other){
        Vector3D distanceVector= other.positionVector.sub(this.positionVector);
        double distance= distanceVector.magnitude();
        Vector3D direction= distanceVector.normalize();
        double force= G * (this.mass * other.mass) / (distance * distance);
        Vector3D forceVector= direction.scale(force);
        return forceVector;
    }

    public void calculateAceleration(List<Body> bodies){
        Vector3D forceVector= new Vector3D(0,0,0);
        for (Body body : bodies) {
            if(body == this){
                continue;
            }else{
                Vector3D f= this.calculateOrbitalForce(body);
                forceVector= forceVector.add(f);
            }
        }
        this.acelerationVector= forceVector.divide(this.mass);
    }

    public void update(double dt){
        this.velocityVector= velocityVector.add(acelerationVector.scale(dt));
        this.positionVector= positionVector.add(velocityVector.scale(dt));
    }
}
