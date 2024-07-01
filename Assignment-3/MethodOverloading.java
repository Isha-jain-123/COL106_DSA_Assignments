package com.gradescope.assignment1;

import com.gradescope.assignment1.AbstractMethodOverloading;
import java.lang.Math;

public class MethodOverloading extends AbstractMethodOverloading {
    public double calculate(int a){
        return a*a;      // area of square = a^2
    }
    public double calculate(int a, int b){
        return a*b;      // area of rectangle = length x breadth
    }

    public double calculate(int a, int b, int c){
        double s = (a+b+c)/2;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));   // area of triangle
        return area;
    }
}

