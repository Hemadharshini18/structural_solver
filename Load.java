package com.example.structuralsolver.model;

public class Load {
    private String type;
    private double magnitude;
    private double position;

    public Load() {}
    public Load(String type, double magnitude, double position) {
        this.type = type;
        this.magnitude = magnitude;
        this.position = position;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getMagnitude() { return magnitude; }
    public void setMagnitude(double magnitude) { this.magnitude = magnitude; }
    public double getPosition() { return position; }
    public void setPosition(double position) { this.position = position; }
}
