package frc.robot.FLYTLib.RevControllerLib;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SoftLimitConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

public class SoftLimits {

    SoftLimitConfig softLimitConfig;
    SparkMaxConfig config;
    SparkMax sparkmax;

    SoftLimits(SoftLimitConfig softLimitConfig, SparkMaxConfig config, SparkMax sparkmax){
        this.softLimitConfig = softLimitConfig;
        this.config = config;
        reverseSoftLimitEnabled(false);
        farwardSoftLimitEnabled(false);
        
    }


    //soft limits postion limit
    public void rev_softFarwardLim(double limit){
        
        softLimitConfig.forwardSoftLimit(limit);//set limits
        farwardSoftLimitEnabled(true);//enable limit
        rev_updateController(softLimitConfig);//update controller with new limit
    }

    //soft limits position limit in reverse
    public void rev_softReverseLim(double limit){
        
        softLimitConfig.reverseSoftLimit(limit);
        reverseSoftLimitEnabled(true);
        rev_updateController(softLimitConfig);

    }
    
    //disables all soft limits
    public void disableSoftLim(){
        farwardSoftLimitEnabled(false);
        reverseSoftLimitEnabled(false);
    }

    //enable or disable soft limits
    private void farwardSoftLimitEnabled(boolean enable){
        softLimitConfig.reverseSoftLimitEnabled(enable);
    }
    //enable or disable soft limits
    private void reverseSoftLimitEnabled(boolean enable){
        softLimitConfig.reverseSoftLimitEnabled(enable);
    }
    
    //updates the controller with new soft limit config
    private void rev_updateController(SoftLimitConfig configuration){
        config.apply(configuration);
        sparkmax.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
    }

}
