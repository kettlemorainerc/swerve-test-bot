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
public class Polar {
//    ----------------- Student Section -----------------
//    TODO


    private double radius_; //Radius or Manguntude
    private double angle_;

    public double getRadius(){
        return radius_;
    }
    public double getAngle(){
        return angle_;
    }

    //Called when someone executes new polar(#,#):
    public Polar(double radius, double angle){
        radius_ = radius;
        angle_  = angle;
    }

    public double polar(Cartesian c){
        double n = c.getNorth();
        double e = c.getEast();
        radius_  = Math.sqrt(n*n + e*e);
        angle_   = (n / e); //Getting the tangent
//        angle_   = Math.atan2(n,e); //Getting the tangent

        return(0);
    }


}