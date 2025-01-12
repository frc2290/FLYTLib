package frc.robot.FLYTLib.Trasshhhh;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.AbsoluteEncoderConfig;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.MAXMotionConfig;
import com.revrobotics.spark.config.SoftLimitConfig;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;


public class RevControllers {

    AbsoluteEncoder absEncoder;
    RelativeEncoder relEncoder;
    EncoderConfig encoderConfig;
    AbsoluteEncoderConfig absEncoderConfig;
    public EncoderHandling encoderCfg;


    
    
    
    
    

    SparkMax sparkmax; //comes from revmotorcontroller class
    SparkMaxConfig config;

    MAXMotionConfig motionConfig;
    public MotionController motionController;
    SoftLimitConfig softLimitConfig;
    public SoftLimits softLimits;
    ClosedLoopConfig closedLoopConfig;
    SparkClosedLoopController closedLoopController;
    public RevPidControl pidControl;
    
    


    
    //defult motor encoder
    public RevControllers(SparkMax sparkmax) {

        this.sparkmax = sparkmax; //sync with removotcontroller class

        //creates new object for sparkmax cfg
        config = new SparkMaxConfig();

        //creates new object for Softlimits cfg
        softLimitConfig = new SoftLimitConfig();
        @SuppressWarnings("unused")
        SoftLimits softLimits = new SoftLimits(softLimitConfig, config, sparkmax); //inputs all of the object need for cfg

        //closed loop pid control creat new object
        closedLoopConfig = new ClosedLoopConfig();
        closedLoopController = sparkmax.getClosedLoopController(); //get it from sparkmax
        @SuppressWarnings("unused")
        RevPidControl pidControl = new RevPidControl(sparkmax, config, closedLoopConfig, closedLoopController);


        //creates new object for motino control cfg
        MAXMotionConfig motionConfig = new MAXMotionConfig();
        @SuppressWarnings("unused")
        MotionController motionController = new MotionController(motionConfig, sparkmax, config, closedLoopConfig);


        //THIS AREA NEEDS IMPROVEMENT
        absEncoder = sparkmax.getAbsoluteEncoder();
        relEncoder = sparkmax.getEncoder();
        relEncoder = sparkmax.getAlternateEncoder();
        //relEncoder = sparkmax.getAlternateEncoder()
        encoderCfg = new EncoderHandling(absEncoder, relEncoder, absEncoderConfig, encoderConfig);
    }














    

    //GET THIS FIXED
    //set controller to follow another controller and direction
    public void rev_follow(SparkBase leader, boolean invert){
        config.follow(leader,invert);
        rev_updateController();
    }

    //set the motor to brake mode or coast mode
    public void rev_idleMode(boolean breakMode){
        if(breakMode)
            config.idleMode(SparkBaseConfig.IdleMode.kBrake);
        else{
            config.idleMode(SparkBaseConfig.IdleMode.kCoast);
        }
        rev_updateController();
    }

    //set voltage compansation
    public void rev_voltageCompansation(double voltage){
        config.voltageCompensation(voltage);
        rev_updateController();
    }

    //current limit functions
    public void rev_currentLimit(int stallLimit){
        config.smartCurrentLimit(stallLimit);
        rev_updateController();
    }

    public void rev_currentLimit(int stallLimit, int freeLimit){
        config.smartCurrentLimit(stallLimit, freeLimit);
        rev_updateController();
    }

    //this specifies rpm under which it is considered stall current limit
    public void rev_currentLimit(int stallLimit, int freeLimit, int limitRpm){
        config.smartCurrentLimit(stallLimit, freeLimit, limitRpm);
        rev_updateController();
    }

    //updates the controller with new config
    private void rev_updateController(){
        sparkmax.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
    }

}

