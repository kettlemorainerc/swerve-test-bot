package org.usfirst.frc.team2077.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2077.OI;
//import edu.wpi.first.wpilibj.templates.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import org.usfirst.frc.team2077.subsystems.DriveSubsys;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase implements Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    //public static ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    public static DriveSubsys driveSubsys = new DriveSubsys();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        //SmartDashboard.putData(driveSubsys);

        SmartDashboard.putNumber("Stick Tran Deadzone", oi.stickTranDeadzone);
        SmartDashboard.putNumber("Stick Rot Deadzone", oi.stickRotDeadzone);
        SmartDashboard.putNumber("Dead Angle", driveSubsys.deadAngle);
        SmartDashboard.putNumber("Pvalue", driveSubsys.Pvalue);
        SmartDashboard.putNumber("Ivalue", driveSubsys.Ivalue);
        SmartDashboard.putNumber("Dvalue", driveSubsys.Dvalue);
    }

    public CommandBase() {
        super();
    }
}
