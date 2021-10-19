/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FRC Team 2077. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams.            */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;


public class DriveStation {

    public DriveStation(){
    }

    private static void bindDriverControl(Joystick primary) {
    }

    private void bindTechnicalControl(Joystick testing) {
    }

    /**
     * Condition control axis input to improve driveability.
     * Each axis has a center dead band in which the output for that axis is always zero.
     * Outside the dead band the output increases exponentially from zero to 1 or -1.
     */
    public static double adjustInputSensitivity(double input, double deadBand, double exponent) {
        return Math.pow(Math.max(0, Math.abs(input) - deadBand) / (1 - deadBand), exponent) * Math.signum(input);
    }

}
