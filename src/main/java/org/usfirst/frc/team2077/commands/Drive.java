/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team2077.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2077.OI;
import org.usfirst.frc.team2077.subsystems.DriveSubsys;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Drive
 * 
 * The main command that uses the DriveSubsys to command four swerve modules
 * to coordinate overall robot motion.
 * 
 * @author robokm
 */
public class Drive extends CommandBase {
    private Timer time = new Timer();
    private double lastTime = 0.0;
    private final DriveSubsys driveSubsys;

    static OI oi = new OI();

    // Show what command your subsystem is running on the SmartDashboard
    //SmartDashboard.putData(driveSubsys);


    public Drive(DriveSubsys driveSubsys) {
        this.driveSubsys = driveSubsys;
        addRequirements(driveSubsys);
        System.out.println("Drive Constructor");
        SmartDashboard.putNumber("Stick Tran Deadzone", oi.stickTranDeadzone);
        SmartDashboard.putNumber("Stick Rot Deadzone", oi.stickRotDeadzone);
        SmartDashboard.putNumber("Dead Angle", driveSubsys.deadAngle);
        SmartDashboard.putNumber("Pvalue", driveSubsys.Pvalue);
        SmartDashboard.putNumber("Ivalue", driveSubsys.Ivalue);
        SmartDashboard.putNumber("Dvalue", driveSubsys.Dvalue);
    }


    // Called just before this Command runs the first time
    public void initialize() {

        System.out.println("Drive Initialize");


        time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        System.out.println("Drive Execute");

        double dt = time.get() - lastTime;

        driveSubsys.drive(oi.getMag(), oi.getDir(), oi.getRot(), dt);
        lastTime = time.get();
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        time.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
