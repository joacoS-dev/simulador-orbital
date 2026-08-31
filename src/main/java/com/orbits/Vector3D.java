package com.orbits;

public final class Vector3D {
    public double x;
    private double y;
    private double z;

    public Vector3D(double x, double y, double z){
        this.x= x;
        this.y= y;
        this.z= z;
    }

    public Vector3D add(Vector3D other){
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3D sub(Vector3D other){
        return new Vector3D(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public double magnitude(){
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3D scale(double factor){
        return new Vector3D(x * factor, y * factor, z * factor);
    }

    public Vector3D normalize(){
        double mag = this.magnitude();
        if(mag == 0.0){
            throw new IllegalStateException("Bodys collision!");
        }else{
            return this.scale(1 / mag);
        }
    }

    public Vector3D divide(double factor){
        if(factor == 0.0){
            throw new IllegalStateException("Infinity crash");
        }else{
             return new Vector3D(x / factor, y / factor, z / factor);
        }
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}