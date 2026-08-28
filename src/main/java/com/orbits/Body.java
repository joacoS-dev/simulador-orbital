package com.orbits;

public class Body {
    Vector2D positionVector;
    Vector2D velocityVector;
    Vector2D acelerationVector;
    double mass;
    int color;
    
    public Body(double mass, Vector2D positionVector, Vector2D velocityVector){
        this.positionVector= positionVector;
        this.velocityVector= velocityVector;
        this.mass= mass;
        this.acelerationVector= new Vector2D(0, 0);
        this.color= 0xF33;
    }
}
