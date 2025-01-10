package frc.robot.FLYTLib.RevControllerLib;


import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

public class RevPidControl {

    SparkMax sparkmax; //comes from revmotorcontroller class
    SparkMaxConfig config;

    ClosedLoopConfig closedLoopConfig;
    SparkClosedLoopController closedLoopController;

    RevPidControl(SparkMax sparkmax, SparkMaxConfig config, ClosedLoopConfig closedLoopConfig, SparkClosedLoopController closedLoopController){
        this.sparkmax = sparkmax;
        this.config = config;
        this.closedLoopConfig = closedLoopConfig;
        this.closedLoopController = closedLoopController;
    }



    /*
    * Motion Control
    * https://codedocs.revrobotics.com/java/com/revrobotics/spark/config/closedloopconfig#d(double)
    *
    *0 - position
    *1 - velcoity
    *2 -advanced position
    *3 - advanced velocity
    *4 - voltage
    *5 - current
    *6 - dutycycle 
    */
    //HAS TO DO SOMETHING WITH THIS, SPEPARATE TARGET AND TYPE
    public void rev_setRef(double target, int controltype){ 
        if(controltype == 0){
            closedLoopController.setReference(target, ControlType.kPosition);
        }else if (controltype == 1){
            closedLoopController.setReference(target, ControlType.kVelocity);
        }else if(controltype == 2){
            closedLoopController.setReference(target, ControlType.kMAXMotionPositionControl);
        }else if(controltype == 3){
            closedLoopController.setReference(target, ControlType.kMAXMotionVelocityControl);
        }else if(controltype == 4){
            closedLoopController.setReference(target, ControlType.kVoltage);
        }else if(controltype == 5){
            closedLoopController.setReference(target, ControlType.kCurrent);
        }else if(controltype == 6){
            closedLoopController.setReference(target, ControlType.kDutyCycle);
        }
    }


    //set PID and velocity feedforward
    public void rev_PIDF(double p, double i, double d, double f){
        closedLoopConfig.pidf(p, i, d, f);
        rev_updateController(closedLoopConfig);
    }

    //set PID
    public void rev_PID(double p, double i, double d){
        closedLoopConfig.pid(p, i, d);
        rev_updateController(closedLoopConfig);
    }

    //prevents sets up izone??????????????
    public void rev_Izone(double zone){
        closedLoopConfig.iZone(zone);
        rev_updateController(closedLoopConfig);
    }

    //min output
    public void rev_minOut(double min){
        closedLoopConfig.minOutput(min);
        rev_updateController(closedLoopConfig);
    }

    //max output
    public void rev_maxOut(double max){
        closedLoopConfig.maxOutput(max);
        rev_updateController(closedLoopConfig);
    }

    //min and max output range
    public void rev_outputRange(double min, double max){
        closedLoopConfig.outputRange(min, max);
        rev_updateController(closedLoopConfig);
    }

    //antiwindup thingy
    public void rev_IMaxAccum(double max){
        closedLoopConfig.iMaxAccum(max);
        rev_updateController(closedLoopConfig);
    }

    //feedback sensor type
    public void rev_feedbackSensor(int type){
        if(type == 0)
            closedLoopConfig.feedbackSensor(ClosedLoopConfig.FeedbackSensor.kNoSensor);
        else if(type == 1)
            closedLoopConfig.feedbackSensor(ClosedLoopConfig.FeedbackSensor.kPrimaryEncoder);
        else if(type == 2)
            closedLoopConfig.feedbackSensor(ClosedLoopConfig.FeedbackSensor.kAbsoluteEncoder);
        else if(type == 3)
            closedLoopConfig.feedbackSensor(ClosedLoopConfig.FeedbackSensor.kAlternateOrExternalEncoder);
        else if(type == 4)
            closedLoopConfig.feedbackSensor(ClosedLoopConfig.FeedbackSensor.kAnalogSensor);
            rev_updateController(closedLoopConfig);
    }

    //updates motor controller
    private void rev_updateController(ClosedLoopConfig configuration){
        config.apply(configuration);
        sparkmax.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
    }

    
}
