/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2077;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2077.commands.CommandBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

    //Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        //autonomousCommand = new ExampleCommand();

        // Initialize all subsystems
        CommandBase.init();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        //autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //autonomousCommand.cancel();
        
        // Allow debugging by setting PID values via the Smart Dashboard
        CommandBase.oi.stickTranDeadzone = SmartDashboard.getNumber("Stick Tran Deadzone",0d);
        CommandBase.oi.stickRotDeadzone = SmartDashboard.getNumber("Stick Rot Deadzone",0d);
        CommandBase.driveSubsys.deadAngle = SmartDashboard.getNumber("Dead Angle",0d);
        CommandBase.driveSubsys.Pvalue = SmartDashboard.getNumber("Pvalue",0d);
        CommandBase.driveSubsys.Ivalue = SmartDashboard.getNumber("Ivalue",0d);
        CommandBase.driveSubsys.Dvalue = SmartDashboard.getNumber("Dvalue",0d);
    }

    /**
     * This function is called periodically during teleoperator control
     */
    @Override
    public void teleopPeriodic() {}
    
    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {}
}
