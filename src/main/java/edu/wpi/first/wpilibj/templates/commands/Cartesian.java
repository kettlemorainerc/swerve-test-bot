/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author robokm
 */
public class Cartesian {
    private double north_;// <0 is south
    private double east_; // <0 is west

    public double getNorth(){
        return north_;
    }
    public double getEast(){
        return east_;
    }
    
    public Cartesian(double north, double east){
        north_ = north;
        east_  = east;
    }
    
    public Cartesian(Polar p){
        double r = p.getRadius();
        double a = p.getAngle();
//        north_   =  ;
//        east_    =  ;
    }
    
//    public static void main(String[] args){
//        System.out.println("Hello");
//    }
}