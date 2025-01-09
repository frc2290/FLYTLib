package frc.robot.FLYTLib;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.AbsoluteEncoderConfig;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.MAXMotionConfig;
import com.revrobotics.spark.config.SoftLimitConfig;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;


public class RevControllers {

    AbsoluteEncoder absEncoder;
    RelativeEncoder relEncoder;
    SparkBaseConfig config;
    SoftLimitConfig softLimitConfig;
    MAXMotionConfig motionConfig;
    ClosedLoopConfig closedLoopConfig;
    AbsoluteEncoderConfig absEncoderConfig;
    EncoderConfig encoderConfig;
    SparkMax sparkmax;


    //defult motor encoder
    public RevControllers(int id, boolean brushless) {

        //checks if we want to control brushed or brushless motor
        //true for brushless, false for brushed
        if(brushless)
        {
            sparkmax = new SparkMax(id, MotorType.kBrushless);
        }
        else
        {
            sparkmax = new SparkMax(id, MotorType.kBrushed);
        }
    
        //movement soft limits disabled
        reverseSoftLimitEnabled(false);
        farwardSoftLimitEnabled(false);
    }

    //connected encoders
    public RevControllers(int id, boolean brushless, boolean encodertype) {

        //checks if we want to control brushed or brushless motor
        //true for brushless, false for brushed
        if(brushless)
        {
            sparkmax = new SparkMax(id, MotorType.kBrushless);
        }
        else
        {
            sparkmax = new SparkMax(id, MotorType.kBrushed);
        }

        if(encodertype)
        {
            absEncoder = sparkmax.getAbsoluteEncoder();
        }
        else
        {
            relEncoder = sparkmax.getAlternateEncoder();
        }

        //movement soft limits disabled
        reverseSoftLimitEnabled(false);
        farwardSoftLimitEnabled(false);
    }




    /*
     * GETTERS
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
     * Encoders
     * https://codedocs.revrobotics.com/java/com/revrobotics/spark/config/absoluteencoderconfig#zeroCentered(boolean)
     * https://codedocs.revrobotics.com/java/com/revrobotics/spark/config/encoderconfig
     */


    
    //returns value in rotations
    private double getAbsPos(){
        return relEncoder.getPosition();
    }

    //returns value in rotations
    private double getAbsVel(){
        return relEncoder.getVelocity();
    }

    //creates new zero offset
    private void zeroOffset(double offset){
        absEncoderConfig.zeroOffset(offset);
    }

    //zero centering enable (0 to 1  now -0.5 to 0.5)
    private void zeroCentered(boolean zeroCentered){
        absEncoderConfig.zeroCentered(zeroCentered);
    }


    //countsPerRevolution(int cpr)
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

    //encoder cpr
    private void countsPerRevolution(int cpr){
        encoderConfig.countsPerRevolution(cpr);
    }
    //calcualtes average of velocity in number of smaples (1 to 64, defaut 64)
    private void encoderVelDepthAverage(int depth){
        encoderConfig.quadratureAverageDepth(depth);
    }
    //calculation rate of velocity (1 to 100ms)
    private void encoderVelPeriod(int period){
        encoderConfig.quadratureMeasurementPeriod(period);
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

    //allowed closed loop error
    public void allowedErr(double err){
        motionConfig.allowedClosedLoopError(err);
        updateMotionControl(motionConfig);
    }

    //set motion control mode
    public void posMode(){
        motionConfig.positionMode(MAXMotionConfig.MAXMotionPositionMode.kMAXMotionTrapezoidal);
        updateMotionControl(motionConfig);
    }
   



    /*
     * Motion Control
     * https://codedocs.revrobotics.com/java/com/revrobotics/spark/config/closedloopconfig#d(double)
     */

     //set PID and velocity feedforward
     public void PIDF(double p, double i, double d, double f){
        closedLoopConfig.pidf(p, i, d, f);
        updateClosedLoopController(closedLoopConfig);
     }

    //set PID
    public void PID(double p, double i, double d){
        closedLoopConfig.pid(p, i, d);
        updateClosedLoopController(closedLoopConfig);
    }

    //prevents sets up izone
    public void Izone(double zone){
        closedLoopConfig.iZone(zone);
        updateClosedLoopController(closedLoopConfig);
    }

    //min output
    public void minOut(double min){
        closedLoopConfig.minOutput(min);
        updateClosedLoopController(closedLoopConfig);
    }

    //max output
    public void maxOut(double max){
        closedLoopConfig.maxOutput(max);
        updateClosedLoopController(closedLoopConfig);
    }

    //min and max output range
    public void outputRange(double min, double max){
        closedLoopConfig.outputRange(min, max);
        updateClosedLoopController(closedLoopConfig);
    }

    //antiwindup thingy
    public void IMaxAccum(double max){
        closedLoopConfig.iMaxAccum(max);
        updateClosedLoopController(closedLoopConfig);
    }

    //feedback sensor type
    public void feedbackSensor(int type){
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
        updateClosedLoopController(closedLoopConfig);
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

    //updates the controller with new soft limit config
    private void updateCfgController(SoftLimitConfig configuration){
        config.apply(configuration);
        updateController();
    }
    
    private void updateClosedLoopController(ClosedLoopConfig configuration){
        config.apply(configuration);
        updateController();
    }
    //updates the controller with new motion control config
    private void updateMotionControl(MAXMotionConfig configuration){
        closedLoopConfig.apply(configuration);
    }

}
