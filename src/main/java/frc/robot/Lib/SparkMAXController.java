package frc.robot.Lib;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.MAXMotionConfig;
import com.revrobotics.spark.config.SoftLimitConfig;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;


public class SparkMAXController {

    AbsoluteEncoder absEncoder;
    RelativeEncoder relEncoder;
    SparkBaseConfig config;
    SoftLimitConfig softLimitConfig;
    MAXMotionConfig motionConfig;

    SparkMax sparkmax;

    //defult motor encoder
    public SparkMAXController(int id, boolean brushless) {

        //checks if we want to control brushed or brushless motor
        //true for brushless, false for brushed
        if(brushless)
        {
            SparkMax sparkmax = new SparkMax(id, MotorType.kBrushless);
        }
        else
        {
            SparkMax sparkmax = new SparkMax(id, MotorType.kBrushed);
        }
    
        reverseSoftLimitEnabled(false);
    }

    //connected encoders
    public SparkMAXController(int id, boolean brushless, boolean encodertype) {

        //checks if we want to control brushed or brushless motor
        //true for brushless, false for brushed
        if(brushless)
        {
            SparkMax sparkmax = new SparkMax(id, MotorType.kBrushless);
        }
        else
        {
            SparkMax sparkmax = new SparkMax(id, MotorType.kBrushed);
        }

        if(encodertype)
        {
            absEncoder = sparkmax.getAbsoluteEncoder();
        }
        else
        {
            relEncoder = sparkmax.getEncoder();
        }

        reverseSoftLimitEnabled(false);
    

    }




    /*
     * GETTERS
     */
/*
    //returns value in rotations
    public double getAbsPos(){
        return relEncoder.getPosition();
    }
    //returns value in rotations
    public double getAbsVel(){
        return relEncoder.getVelocity();
    }
    
    //returns value in rotations
    public double getRelPosition(){
        return absEncoder.getPosition();
    }
    //returns value in "RPM"
    public double getRelVelocity(){
        return absEncoder.getVelocity();
    }
    //relative encoders have no "home", so we can set it
    public void setEncoderPos(double pos){
        relEncoder.setPosition(pos);
    }
*/

    //get motor position
    public double getPos(){
        return 0;
    }

    //get motor velocity
    public double getVel(){
        return 0;
    }

    //get motor acceleration
    public double getAcc(){
        return 0;
    }

    //get motor tempreture (motor or controlelr Temp?!?)
    public double getTemp(){
        return sparkmax.getMotorTemperature();
    }

    //motor controller current output
    public double getCurrent(){
        return sparkmax.getOutputCurrent();
    }

    //voltage controller is getting
    public double getVoltage(){
        return sparkmax.getBusVoltage();
    }




/*
    * SETTERS
    */

    //sets voltage
    public void set(double value){
    sparkmax.set(value);
    }

    //set voltage relative to available voltage, availble 12V, setV(0.5) = 6v, availble 6v, setV(0.5) = 3v
    public void setV(double value){
    sparkmax.setVoltage(value);
    }



    //disable motor
    public void disable(){
    sparkmax.disable();
    }


    /*
    * Motion Profile
    */

    //max velocity in RPM
    public void maxVel(double vel){
    motionConfig.maxVelocity(vel);
    }
    //max acceleration in RPM
    public void maxAcc(double acc){
        motionConfig.maxAcceleration(acc);
    }




    /*
    * Soft Limits
    */

//current limit functions
    public void currentLimit(int stallLimit){
        config.smartCurrentLimit(stallLimit);
        updateController();
    }
    public void currentLimit(int stallLimit, int freeLimit){
        config.smartCurrentLimit(stallLimit, freeLimit);
        updateController();
    }
    //this specifies rpm under which it is considered stall current limit
    public void currentLimit(int stallLimit, int freeLimit, int limitRpm){
        config.smartCurrentLimit(stallLimit, freeLimit, limitRpm);
        updateController();
    }

    //soft limits postion limit
    public void softFarwardLim(double limit){
        softLimitConfig.forwardSoftLimit(limit);
        farwardSoftLimitEnabled(true);
        updateCfgController(softLimitConfig);
    }
    //soft limits position limit in reverse
    public void softReverseLim(double limit){
        softLimitConfig.reverseSoftLimit(limit);
         reverseSoftLimitEnabled(true);
        updateCfgController(softLimitConfig);

    }
    
    //enable or disable soft limits
    private void farwardSoftLimitEnabled(boolean enable){
        softLimitConfig.reverseSoftLimitEnabled(enable);
    }
    //enable or disable soft limits
    private void reverseSoftLimitEnabled(boolean enable){
        softLimitConfig.reverseSoftLimitEnabled(enable);
    }
    



    /*
    * Advaced Functions Control Cfg
    */

    //set controller to follow another controller and direction
    public void follow(SparkBase leader, boolean invert){
        config.follow(leader,invert);
        updateController();
    }


    //set the motor to brake mode or coast mode
    public void idleMode(boolean breakMode){
        if(breakMode)
            config.idleMode(SparkBaseConfig.IdleMode.kBrake);
        else{
            config.idleMode(SparkBaseConfig.IdleMode.kCoast);
        }
        updateController();
    }

    //set voltage compansation
    public void voltageCompsation(double voltage){
        config.voltageCompensation(voltage);
        updateController();

    }

    //updates the controller with new config
    private void updateController(){
        sparkmax.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
    }

    private void updateCfgController(SoftLimitConfig configuration){
        config.apply(configuration);
        updateController();
    }
    private void updateCfgController(MAXMotionConfig configuration){
        config.apply(configuration);
        updateController();
    }
}
