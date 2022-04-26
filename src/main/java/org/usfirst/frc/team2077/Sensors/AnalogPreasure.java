package org.usfirst.frc.team2077.Sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogPreasure {

    AnalogInput analogPreasureObject;

    public AnalogPreasure(int idSlotNum_){
        analogPreasureObject = new AnalogInput(idSlotNum_);
    }

    public double getPreasure(){
        return analogPreasureObject.getValue();
    }

}
