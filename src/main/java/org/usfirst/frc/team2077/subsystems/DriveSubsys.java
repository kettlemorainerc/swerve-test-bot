/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.templates.commands.*;

import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.SwerveModule;

/**
 * DriveSubsys
 * 
 * Contains the four swerve modules and a global set of PID gain values. Handles
 * driving all swerve modules together.
 * 
 * @author robokm
 */
public class DriveSubsys implements Subsystem {
    public final SwerveModule upperLeft;
    public final SwerveModule upperRight;
    public final SwerveModule lowerLeft;
    public final SwerveModule lowerRight;
    
    public double deadAngle = 10.0;
    public double Pvalue = 6.4;
    public double Ivalue = 0.005;
    public double Dvalue = 0.001;
    
    /* DriveSubsys
     * 
     * Constructs each swerve module with the proper hardware map and the 
     * correct module angle each should assume to perform overall robot rotation.
     */
    public DriveSubsys() {
        double length = 29.0; // TODO: move this constant to RobotMap;
        double width = 20.0; // TODO: move this constant to RobotMap;
        
        upperRight = new SwerveModule(RobotMap.magnitudeMotor1, RobotMap.directionMotor1, RobotMap.encoder1ChannelA, RobotMap.encoder1ChannelB, length/2, width/2);
        upperRight.setPID(deadAngle, Pvalue, Ivalue, Dvalue);
        
        lowerRight = new SwerveModule(RobotMap.magnitudeMotor2, RobotMap.directionMotor2, RobotMap.encoder2ChannelA, RobotMap.encoder2ChannelB, -length/2, width/2);
        lowerRight.setPID(deadAngle, Pvalue, Ivalue, Dvalue);
        
        lowerLeft = new SwerveModule(RobotMap.magnitudeMotor3, RobotMap.directionMotor3, RobotMap.encoder3ChannelA, RobotMap.encoder3ChannelB, -length/2, -width/2);
        lowerLeft.setPID(deadAngle, Pvalue, Ivalue, Dvalue);
        
        upperLeft = new SwerveModule(RobotMap.magnitudeMotor4, RobotMap.directionMotor4, RobotMap.encoder4ChannelA, RobotMap.encoder4ChannelB, length/2, -width/2);
        upperLeft.setPID(deadAngle, Pvalue, Ivalue, Dvalue);
    }
    
    // what direction should a module point if rotating about robot center w/ no translation?
    private double getSimpleRotationAngle(SwerveModule module) {
        double up = module.getUpCoordinate();
        double right = module.getRightCoordinate();
        
        double angle;
        
        // TODO: add code here to figure out angle based on coordinates
        angle = 0; // placeholder for code to be added, compiles but does not work right
        
        return angle;
    }
    
    // TODO: delete me
    public void driveOLD(double mag, double dir, double rot, double dt) {
        upperLeft.goSwerveOLD(mag, dir, rot, dt);
        upperRight.goSwerveOLD(mag, dir, rot, dt);
        lowerLeft.goSwerveOLD(mag, dir, rot, dt);
        lowerRight.goSwerveOLD(mag, dir, rot, dt);
        
        System.out.println("URE: " + upperRight.getRawEncoder() + " LRE: " + lowerRight.getRawEncoder() + " LLE: " + lowerLeft.getRawEncoder() + " ULE: " + upperLeft.getRawEncoder());
        //System.out.println("Mag: " + mag + " dir: " + dir + " rot: " + rot);
    }
    
    public void drive(double mag, double dir, double rot, double dt) {
        
        if (Math.abs(rot) > 0) { // rotation mode
            //upperLeft.goSwerve(mag, 45, dt);
            //upperRight.goSwerve(mag, 135, dt);
            //lowerLeft.goSwerve(mag, -45, dt);
            //lowerRight.goSwerve(mag, -135, dt);
            upperLeft.goSwerve(mag, getSimpleRotationAngle(upperLeft), dt);
            upperRight.goSwerve(mag, getSimpleRotationAngle(upperRight), dt);
            lowerLeft.goSwerve(mag, getSimpleRotationAngle(upperRight), dt);
            lowerRight.goSwerve(mag, getSimpleRotationAngle(lowerRight), dt);
        } else { // translation mode
            upperLeft.goSwerve(mag, dir, dt);
            upperRight.goSwerve(mag, dir, dt);
            lowerLeft.goSwerve(mag, dir, dt);
            lowerRight.goSwerve(mag, dir, dt);            
        }
        
        
        
        
        System.out.println("URE: " + upperRight.getRawEncoder() + " LRE: " + lowerRight.getRawEncoder() + " LLE: " + lowerLeft.getRawEncoder() + " ULE: " + upperLeft.getRawEncoder());
        //System.out.println("Mag: " + mag + " dir: " + dir + " rot: " + rot);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Drive());
    }
}
