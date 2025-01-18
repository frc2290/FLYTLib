package frc.robot.FLYTLib.FLYTMotorLib;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.AbsoluteEncoderConfig;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.MAXMotionConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;















public class SparkController extends SuperController{

    /*
     * Rev Library
     */
    //motor controller
    SparkMax sparkMax; //General controller
    SparkMaxConfig config; //Controller configuration objects



    //encoders
    AbsoluteEncoder absEncoder; //absalute encoder
    RelativeEncoder relEncoder; //relative encoder
    EncoderConfig encoderConfig; //general encoder configuration
    AbsoluteEncoderConfig absEncoderConfig; //absalute


    //Motion contorler
    MAXMotionConfig motionConfig;



    //closed loop
    ClosedLoopConfig closedLoopCfg; //closed loop configuration
    SparkClosedLoopController closedLoopController; //closed loop control

 
    //private vars for internal calculation and specifications
    boolean e_encoderAvailable = false; //check if enxternal encoder connected
    boolean e_absalute = false; //check if specified encoder is absalute
    boolean pidREADY =  false;








    /**
     * Run this constructor for brushed and brushless motors with no connected external encoders.
     * You are required to run encoderCfg() if brushless is chosen.
     * @param m_id - motor id
     * @param m_brushless - motor type
     * @param m_break - motor idle mode
     */
    public SparkController(int m_id, boolean m_brushless, boolean m_break){
        //setup sparkmax object reference
        sparkMax = new SparkMax(m_id, m_brushless ? MotorType.kBrushless : MotorType.kBrushed);

        if(m_brushless){
            relEncoder = sparkMax.getEncoder();
        }
        //something should be added here later

    }



    /**
     * Run this constructor for brushed and brushless motors that have connected external encoder
     * @param m_id - motor id
     * @param m_brushless - motor type
     * @param m_break - motor idel mode
     * @param e_absalute - encoder type
     */
    public SparkController(int m_id, boolean m_brushless, boolean m_break, boolean e_absalute){
        //setup sparkmax object reference
        sparkMax = new SparkMax(m_id, m_brushless ? MotorType.kBrushless : MotorType.kBrushed);

        //if motor is brushless and has connected absalute encoder
        if(m_brushless && e_absalute){
            e_absalute = true;
            absEncoder = sparkMax.getAbsoluteEncoder();
            e_encoderAvailable = true;
        //if motor is brushless and encoder is not absalute
        }else if(m_brushless && !e_absalute){
            e_absalute = false;
            relEncoder = sparkMax.getAlternateEncoder();
            e_encoderAvailable = true;
        //if motor is brushed and connected absalute encoder
        }else if(!m_brushless && e_absalute){
            e_absalute = true;
            absEncoder = sparkMax.getAbsoluteEncoder();
            e_encoderAvailable = true;
            //if motor is brushed and conected relative encoder
        }else if(!m_brushless && !e_absalute){
            e_absalute = false;
            relEncoder = sparkMax.getAlternateEncoder();
            e_encoderAvailable = true;
        }
    }


    /**
     * Set relative speed, multiplies factor by availble voltage.
     * If pid is enabled, set postion, set velocity, etc.
     */
    public void set(){
        
    }

    /**
     * Disable motor
     */
    public void disable(){
        sparkMax.disable();        
    }

    /**
     * Get current postion
     * If no encoder configured, function will return 0
     */
    public double getPos(){
        if(e_encoderAvailable){
            if(e_absalute){
                return absEncoder.getPosition();
            }else{
                return relEncoder.getPosition();
            }

        }else{
            return 0;
        }
        
    }

    /**
     * Get current Velocity
     */
    public double getVel(){
        if(e_encoderAvailable){
            if(e_absalute){
                return absEncoder.getVelocity();
            }else{
                return relEncoder.getVelocity();
            }

        }else{
            return 0;
        }
    }

    ///
    /**
     * Get current Acceleration (UNDER DEVELOPMENT) RETURNS ONLY ZERO
     */
    public double getAcc(){
        return 0;
    }
    ///
    /**
     * Get current Temprature
     */
    public double getTemp(){
        return sparkMax.getMotorTemperature();
    }

    /** 
     * Get current Current
     */
    public double getCurrent(){
        return sparkMax.getOutputCurrent();
    }

    /**
     * Get current velocity
     */
    public double getVol(){
        return sparkMax.getBusVoltage();
    }
    

    /**
     * Tune pid values, (if p equals to zero, pid disabled)
     */
    public void pidTune(double p, double i, double d, double f, double vf){
        closedLoopCfg.pidf(p, i, d, f);
        closedLoopCfg.velocityFF(vf);
        config.apply(closedLoopCfg);
        ControllerUpdate();
        //FIX THIS
    }

    /**
     * PID setup, required to run before in implementing pid in code.
     */
    public void pidSetup(double min, double max, double izone, double imax){    
        closedLoopCfg.outputRange(min, imax);
        closedLoopCfg.iZone(izone);
        closedLoopCfg.iMaxAccum(imax);
        config.apply(closedLoopCfg);
        pidREADY = true;
        ControllerUpdate();
        //ADD MAX ALLOUD ERROR
        //FIX THIS

    }

    /**
     * Setup motion profile
     */
    public void motionProfile(double maxVel, double maxAcc){
        motionConfig.maxAcceleration(maxAcc);
        motionConfig.maxVelocity(maxVel);
        closedLoopCfg.apply(motionConfig);
        config.apply(closedLoopCfg);
        ControllerUpdate();
        //ERROR IF USED WITHOUT PID SETUP FIRST
    }

    /**
     * Setup followers
     */
    public void followeMe(int leaderID, boolean invert){
        config.follow(leaderID, invert);
        ControllerUpdate();
        //MAKE IT AUTOMATICCALY DONE
    }

    /**
     * Setup encoder parameters
     */
    public void encocderCfg(int countsPerRev, int setPos){
        if(e_absalute){
            absEncoderConfig.zeroOffset(setPos);
        }else{
            relEncoder.setPosition(setPos);
            encoderConfig.countsPerRevolution(countsPerRev);
        }
        
    }

    /**
     * Advanced controller configuration
     */
    public void avanceControl(double voltageComp, int currentStallLim, int currentFreeLim, double converionsFactor){
        config.voltageCompensation(voltageComp);
        config.smartCurrentLimit(currentStallLim, currentFreeLim);
        ControllerUpdate();
        //NO RPM LIMIT IMPLEMENTED

    }

    //Updates the controller with new config params
    private void ControllerUpdate(){
        sparkMax.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
    }

}