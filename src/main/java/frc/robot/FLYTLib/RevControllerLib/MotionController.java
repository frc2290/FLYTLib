package frc.robot.FLYTLib.RevControllerLib;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.MAXMotionConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

public class MotionController {
    
    MAXMotionConfig motionConfig;
    SparkMax sparkmax; //comes from revmotorcontroller class
    SparkMaxConfig config; 
    ClosedLoopConfig closedLoopConfig;


    MotionController(MAXMotionConfig motionConfig, SparkMax sparkmax, SparkMaxConfig config, ClosedLoopConfig closedLoopConfig){
        this.motionConfig = motionConfig;
        this.sparkmax = sparkmax;
        this.config = config;
        this.closedLoopConfig = closedLoopConfig;
    }


    //max velocity in RPM
    public void maxVel(double vel){
        motionConfig.maxVelocity(vel);
        rev_updateController(motionConfig);
    }

    //max acceleration in RPM
    public void maxAcc(double acc){
        motionConfig.maxAcceleration(acc);
        rev_updateController(motionConfig);
    }

    //allowed closed loop error
    public void allowedErr(double err){
        motionConfig.allowedClosedLoopError(err);
        rev_updateController(motionConfig);
    }

    //set motion control mode
    public void posMode(){
        motionConfig.positionMode(MAXMotionConfig.MAXMotionPositionMode.kMAXMotionTrapezoidal);
        rev_updateController(motionConfig);
    }

    //updates the controller with new motion control config
    private void rev_updateController(MAXMotionConfig configuration){
        closedLoopConfig.apply(configuration);
        sparkmax.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
    

    }
}
