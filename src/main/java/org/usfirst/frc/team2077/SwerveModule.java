/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team2077;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * SwerveModule
 * 
 * This class encapsulates a complete swerve module including a magnitude
 * motor, a direction motor, and a hall-effect encoder. It performs the
 * necessary calculations to interpret the encoder counts into a wheel direction
 * as well as run a PID loop to track a desired target rotation.
 * 
 * @author robokm
 */
public class SwerveModule {
    private static final double countsPerWheelRev = 414;

    private final SpeedController magCtrl;
    private final SpeedController dirCtrl;

    private final Encoder encoder;

    private double deadAngle = 1.0;
    private double Pvalue = 1.0;
    private double Ivalue = 0.0;
    private double Dvalue = 0.0;

    private double lastError = 0.0;
    private double errorAccum = 0.0;

    private final double upCoordinate; // final means set in constructor and can't change
    private final double rightCoordinate;


    /* SwerveModule
     *
     * Constructs the object and maps the hardware to the software objects.
     * rotTarget - specifies the target angle the module should assume to perform
     *             an in-place rotation of the robot
     */
    public SwerveModule(int magMotorID, int dirMotorID, int encoderChnA, int encoderChnB, double up, double right) {
        magCtrl = new Victor(magMotorID);
        dirCtrl = new Talon(dirMotorID);

        encoder = new Encoder(encoderChnA, encoderChnB);
        encoder.reset();
        encoder.setReverseDirection(true); // NOTE: potentially depends upon hardware!

        upCoordinate = up;
        rightCoordinate = right;
    }

    // up/forward/+, down/back/- location of module on robot as provided to constructor
    public double getUpCoordinate() {
        return upCoordinate;
    }

    // right/+, left/- location of module on robot as provided to constructor
    public double getRightCoordinate() {
        return rightCoordinate;
    }

    /* setPID
     *
     * Sets the PID gain values for the module.
     * P - proportional gain
     * I - integral gain
     * D - differential gain
     */
    public void setPID(double deadAng, double P, double I, double D) {
        deadAngle = deadAng;
        Pvalue = P;
        Ivalue = I;
        Dvalue = D;
    }

    // TODO: delete me
    /* goSwerve
     *
     * The main PID loop that handles rotating the module to the correct angle
     * and enabling the magnitude motor when the rotation is achieved.
     * mag - 0 to 1 how fast the module should drive
     * dir - -180 to 180 what direction the module should seek
     * rot - -1 to 1 any overall robot rotation the module should coordinate
     * dt - the time in between calls to goSwerve
     */
    public void goSwerveOLD(double mag, double dir, double rot, double dt) {
        double deltaDir = 0.0;

        // Check if the module should be in translation or rotation mode
        if (Math.abs(rot) > 0.0) {
            // Rotation mode
            deltaDir = getWheelDir() - 0;
        } else {
            // Translation mode
            deltaDir = getWheelDir() - dir;
        }

        // Map the delta onto the same -180 to 180 polar coordinate system
        // used by the FIRST joystick class.
        if (deltaDir > 180.0) {
            deltaDir -= 360.0;
        } else if (deltaDir < -180.0) {
            deltaDir += 360.0;
        }

        // Start PID
        double error = deltaDir / 180.0;
        errorAccum += error * dt;

        // Set the direction motor to value specified by PID
        dirCtrl.set(Pvalue * error + Ivalue * errorAccum + Dvalue * ((error - lastError / dt)));

        // If the remaining error is small enough (small delta angle), then
        // engage the magnitude motor
        if (Math.abs(deltaDir) > deadAngle) {
            magCtrl.set(0.0); // Outside of deadzone
        } else {
            // Inside of deadzone
            if (Math.abs(rot) > 0.0) {
                // Rotation mode
                magCtrl.set(rot);
            } else {
                // Translation mode
                magCtrl.set(mag);
            }
        }

        lastError = error;

        //SmartDashboard.putNumber("Delta Dir", deltaDir);
        //SmartDashboard.putNumber("Delta Time", dt);
    }

    // TODO: add comments
    public void goSwerve(double mag, double dir, double dt) {

        // angle error
        double deltaDir = getWheelDir() - dir;

        // Map the delta onto the same -180 to 180 polar coordinate system
        // used by the FIRST joystick class.
        deltaDir = deltaDir % 360;
        if (deltaDir > 180.0) {
            deltaDir -= 360.0;
        } else if (deltaDir < -180.0) {
            deltaDir += 360.0;
        }

        // Start PID
        double error = deltaDir / 180.0;
        errorAccum += error * dt;

        // Set the direction motor to value specified by PID
        dirCtrl.set(Pvalue * error + Ivalue * errorAccum + Dvalue * ((error - lastError / dt)));

        // If the remaining error is small enough (small delta angle), then
        // engage the magnitude motor
        if (Math.abs(deltaDir) > deadAngle) {
            magCtrl.set(0.0); // Outside of deadzone
        } else {
            // Inside of deadzone
            magCtrl.set(mag);
        }

        lastError = error;

        //SmartDashboard.putNumber("Delta Dir", deltaDir);
        //SmartDashboard.putNumber("Delta Time", dt);
    }


    /* getWheelDir
     *
     * Handles getting the raw encoder value and reinterpreting it as an angle
     * in which the module is facing, mapping it into the same -180 to 180 polar
     * coordinate system used by the FIRST joystick class.
     */
    public double getWheelDir() {
        double angle = encoder.get() * (360.0 / countsPerWheelRev);
        angle = angle % 360.0;

        if (angle > 180.0) {
            angle -= 360.0;
        } else if (angle < -180.0) {
            angle += 360.0;
        }

        return angle;
    }

    public void stop() {
        magCtrl.set(0.0);
        dirCtrl.set(0.0);
    }

    public double getRawEncoder() {
        return encoder.get();
    }
}