package com.orbits;

public final class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y){
        this.x= x;
        this.y= y;
    }
    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D sub(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public double magnitude(Vector2D other){
        return Math.hypot(other.x, other.y);
    }
}
