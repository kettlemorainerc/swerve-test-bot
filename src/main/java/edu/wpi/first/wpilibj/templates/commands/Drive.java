/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;


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
    
    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.driveSubsys);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double dt = time.get() - lastTime;
        
        driveSubsys.drive(CommandBase.oi.getMag(), CommandBase.oi.getDir(), CommandBase.oi.getRot(), dt);
        lastTime = time.get();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
