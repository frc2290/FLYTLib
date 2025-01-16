package frc.robot.FLYTLib.FLYTMotorLib;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.AbsoluteEncoderConfig;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Constants.ControllerCfg;

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

    //encoder params
    int encoderZero = 0;
    int encoderCPR = 0;

    //pid stuff
    double p, i, d, ff, max, min, izon, imax;



    public SparkController(int m_id, boolean m_brushless, boolean m_break){
        //setup sparkmax class
        sparkMax = new SparkMax(m_id, m_brushless ? MotorType.kBrushless : MotorType.kBrushed);
    }



    public SparkController(int m_id, boolean m_brushless, boolean m_break,  ControllerEnums e_type, int encoderzero, int encoderCPR){
        //setup sparkmax class
        sparkMax = new SparkMax(m_id, m_brushless ? MotorType.kBrushless : MotorType.kBrushed);
        //creat motor config
        config = new SparkMaxConfig();
        //Encoderconfig
        encoderConfig = new EncoderConfig();
        //absEncoderConfig
        absEncoderConfig = new AbsoluteEncoderConfig();

        //set encoder stuff (ADD CONVERSTION FACTOR)
        this.encoderType = e_type;
        this.encoderZero = encoderzero; //update the encoder zero position if absalute
        this.encoderCPR = encoderCPR; //update encoder cpr


        //check what kind of encoder we have here
        if(encoderType == ControllerEnums.ABSALUTE){
            absEncoder = sparkMax.getAbsoluteEncoder();
            //takes in postion and sets it as a new zero
            absEncoderConfig.zeroOffset(encoderZero);
            config.apply(absEncoderConfig);

            

        }else if(encoderType == ControllerEnums.RELATIVE){
            relEncoder = sparkMax.getAlternateEncoder();
            //ERROR IF CPR IS ZERO
            encoderConfig.countsPerRevolution(encoderCPR);
            config.apply(encoderConfig);

        }else if(encoderType == ControllerEnums.NONE && m_brushless){
            relEncoder = sparkMax.getEncoder();
            encoderConfig.countsPerRevolution(encoderCPR);
            config.apply(encoderConfig);


        }else if(encoderType == ControllerEnums.NONE && !m_brushless){
            encoderConnected = false;
        }
    }






    public SparkController(int m_id, boolean m_brushless, boolean m_break,  ControllerEnums e_type, int encoderzero, int encoderCPR, double maxAcc, double maxVcc, double alloudError, double p, double i, double d, double ff, double max, double min, double izon, double imax){
        //setup sparkmax class
        sparkMax = new SparkMax(m_id, m_brushless ? MotorType.kBrushless : MotorType.kBrushed);
        //creat motor config
        config = new SparkMaxConfig();
        //Encoderconfig
        encoderConfig = new EncoderConfig();
        //absEncoderConfig
        absEncoderConfig = new AbsoluteEncoderConfig();

        //set encoder stuff (ADD CONVERSTION FACTOR)
        this.encoderType = e_type;
        this.encoderZero = encoderzero; //update the encoder zero position if absalute
        this.encoderCPR = encoderCPR; //update encoder cpr



        //check what kind of encoder we have here
        if(encoderType == ControllerEnums.ABSALUTE){
            absEncoder = sparkMax.getAbsoluteEncoder();
            //takes in postion and sets it as a new zero
            absEncoderConfig.zeroOffset(encoderZero);
            config.apply(absEncoderConfig);

            

        }else if(encoderType == ControllerEnums.RELATIVE){
            relEncoder = sparkMax.getAlternateEncoder();
            //ERROR IF CPR IS ZERO
            encoderConfig.countsPerRevolution(encoderCPR);
            config.apply(encoderConfig);

        }else if(encoderType == ControllerEnums.NONE && m_brushless){
            relEncoder = sparkMax.getEncoder();
            encoderConfig.countsPerRevolution(encoderCPR);
            config.apply(encoderConfig);


        }else if(encoderType == ControllerEnums.NONE && !m_brushless){
            encoderConnected = false;
        }
    
        this.p = p;
        this.i = i;
        this.d = d;
        this.ff = ff;
        this.max = max;
        this.min = min;
        this.izon = izon;
        this.imax = imax;   

        




                
            



    }











        

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
