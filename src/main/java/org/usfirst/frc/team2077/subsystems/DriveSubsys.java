/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team2077.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

import org.usfirst.frc.team2077.RobotMap;
import org.usfirst.frc.team2077.SwerveModule;
import org.usfirst.frc.team2077.commands.Drive;

/**
 * DriveSubsys
 * 
 * Contains the four swerve modules and a global set of PID gain values. Handles
 * driving all swerve modules together.
 * 
 * @author robokm
 */
public class DriveSubsys implements Subsystem {
    public final SwerveModule northWest;
    public final SwerveModule northEast;
    public final SwerveModule southWest;
    public final SwerveModule southEast;
    
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

        setDefaultCommand(new Drive(this));
        
        northEast = new SwerveModule(RobotMap.magnitudeMotor1, RobotMap.directionMotor1, RobotMap.encoder1ChannelA, RobotMap.encoder1ChannelB, length/2, width/2);
        northEast.setPID(deadAngle, Pvalue, Ivalue, Dvalue);
        
        southEast = new SwerveModule(RobotMap.magnitudeMotor2, RobotMap.directionMotor2, RobotMap.encoder2ChannelA, RobotMap.encoder2ChannelB, -length/2, width/2);
        southEast.setPID(deadAngle, Pvalue, Ivalue, Dvalue);
        
        southWest = new SwerveModule(RobotMap.magnitudeMotor3, RobotMap.directionMotor3, RobotMap.encoder3ChannelA, RobotMap.encoder3ChannelB, -length/2, -width/2);
        southWest.setPID(deadAngle, Pvalue, Ivalue, Dvalue);
        
        northWest = new SwerveModule(RobotMap.magnitudeMotor4, RobotMap.directionMotor4, RobotMap.encoder4ChannelA, RobotMap.encoder4ChannelB, length/2, -width/2);
        northWest.setPID(deadAngle, Pvalue, Ivalue, Dvalue);
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
        northWest.goSwerveOLD(mag, dir, rot, dt);
        northEast.goSwerveOLD(mag, dir, rot, dt);
        southWest.goSwerveOLD(mag, dir, rot, dt);
        southEast.goSwerveOLD(mag, dir, rot, dt);
        
        System.out.println("URE: " + northEast.getRawEncoder() + " LRE: " + southEast.getRawEncoder() + " LLE: " + southWest.getRawEncoder() + " ULE: " + northWest.getRawEncoder());
        //System.out.println("Mag: " + mag + " dir: " + dir + " rot: " + rot);
    }
    
    public void drive(double mag, double dir, double rot, double dt) {
        
        if (Math.abs(rot) > 0) { // rotation mode
            //upperLeft.goSwerve(mag, 45, dt);
            //upperRight.goSwerve(mag, 135, dt);
            //lowerLeft.goSwerve(mag, -45, dt);
            //lowerRight.goSwerve(mag, -135, dt);
            northWest.goSwerve(mag, getSimpleRotationAngle(northWest), dt);
            northEast.goSwerve(mag, getSimpleRotationAngle(northEast), dt);
            southWest.goSwerve(mag, getSimpleRotationAngle(northEast), dt);
            southEast.goSwerve(mag, getSimpleRotationAngle(southEast), dt);
        } else { // translation mode
            northWest.goSwerve(mag, dir, dt);
            northEast.goSwerve(mag, dir, dt);
            southWest.goSwerve(mag, dir, dt);
            southEast.goSwerve(mag, dir, dt);
        }
        
        
        
        
        System.out.println("URE: " + northEast.getRawEncoder() + " LRE: " + southEast.getRawEncoder() + " LLE: " + southWest.getRawEncoder() + " ULE: " + northWest.getRawEncoder());
        //System.out.println("Mag: " + mag + " dir: " + dir + " rot: " + rot);
    }
}
