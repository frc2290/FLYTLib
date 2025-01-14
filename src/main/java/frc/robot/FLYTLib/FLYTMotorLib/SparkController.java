package frc.robot.FLYTLib.FLYTMotorLib;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.AbsoluteEncoderConfig;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.FLYTLib.FLYTMotorLib.ControllerCfg.ControllerCfg;
import frc.robot.FLYTLib.FLYTMotorLib.ControllerCfg.ControllerEnums;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class SparkController {

    //encoder stuff
    AbsoluteEncoder absEncoder;
    RelativeEncoder relEncoder;
    EncoderConfig encoderConfig;
    AbsoluteEncoderConfig absEncoderConfig; 
    boolean encoderConnected = true;

    //closed loop control stuff
    ClosedLoopConfig closedLoopCfg;
    SparkClosedLoopController closedLoopController;

    //spark max related stuff
    SparkMax sparkMax;
    SparkMaxConfig config;
    ControllerEnums encoderType;



    
    public SparkController(ControllerCfg motorParams){

        //setup sparkmax class
        sparkMax = new SparkMax(motorParams.ID, motorParams.brushless ? MotorType.kBrushless : MotorType.kBrushed);

        //set encoder stuff (ADD CONVERSTION FACTOR)
        this.encoderType = motorParams.encoderType;

        //check what kind of encoder we have here
        if(encoderType == ControllerEnums.ABSALUTE){
            absEncoder = sparkMax.getAbsoluteEncoder();
            //takes in postion and sets it as a new zero
            absEncoderConfig.zeroOffset(motorParams.encoderZero);

        }else if(encoderType == ControllerEnums.RELATIVE){
            relEncoder = sparkMax.getAlternateEncoder();
            //ERROR IF CPR IS ZERO
            encoderConfig.countsPerRevolution(motorParams.encoderCPR);

        }else if(encoderType == ControllerEnums.NONE && motorParams.brushless){
            relEncoder = sparkMax.getEncoder();
            encoderConfig.countsPerRevolution(motorParams.encoderCPR);

        }else if(encoderType == ControllerEnums.NONE && !motorParams.brushless){
            encoderConnected = false;
        }
        





        ControllerCfg motor1 = new ControllerCfg(1,ControllerEnums.BRUSHED, ControllerEnums.NONE);
        motor1.setupAdvanceControlleCfg();
        

        //motor idle mode
        if(brakeMode){
        config.idleMode(SparkBaseConfig.IdleMode.kBrake);
        }else{
            config.idleMode(SparkBaseConfig.IdleMode.kBrake);
        }

        //assign pidf values
        if (p > 0){
            closedLoopCfg.pidf(p, 0, d, ff);
        }

        //pid output range
        if(range > 0){
            closedLoopCfg.outputRange(range, range);
        }

        if(followID > -1){
            //bluh bluh bluh re-run all of this and create motor clone and make it follow this 
        }

        //voltage comopensation
        if(vComp > 0){
            config.voltageCompensation(vComp);
        }

        //set curernt limit
        config.smartCurrentLimit(cstallLim,freeLim,limRpm);

        





        //updates all of the cfg stuff
        sparkMax.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
        

    }



    //get position
    public double getPos(){
        
        
        if(encoderType == 1){
            return absEncoder.getPosition();

        }else if(encoderType == 2 && encoderType == 3){
            return relEncoder.getPosition();

        }else{
            return 0;

        }

    }


    public void set(double speed){
        sparkMax.set(speed);
    }

    public double m_getTemp(){
        return sparkMax.getMotorTemperature();
    }

    public double m_getCurrent(){
        return sparkMax.getOutputCurrent();
    }

    public double m_getVoltage(){
        return sparkMax.getBusVoltage();
    }



}
