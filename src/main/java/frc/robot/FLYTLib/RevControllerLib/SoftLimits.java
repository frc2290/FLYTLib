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
        farwardSoftLimitEnabled(true);
        softLimitConfig.forwardSoftLimit(limit);
        rev_updateController(softLimitConfig);
    }

    //soft limits position limit in reverse
    public void rev_softReverseLim(double limit){
        reverseSoftLimitEnabled(true);
        softLimitConfig.reverseSoftLimit(limit);
        rev_updateController(softLimitConfig);

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
