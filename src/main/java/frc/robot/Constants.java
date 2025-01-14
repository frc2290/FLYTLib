package frc.robot;

import frc.robot.FLYTLib.FLYTMotorLib.ControllerCfg.ControllerEnums;



public class Constants {

  public class ControllerCfg {

    //motor id, endoder type, motor type, breake mode on/off, voltageComp, current limits
    public final int ID;
    public final ControllerEnums encoderType;
    public final boolean brushless;
    public boolean brake = false;
    public double voltageComp = 0;
    public int stalllimit = 0,freelimit = 0,limitrpm = 0;

    //encoder stuff
    public int encoderCPR = 0;
    public double encoderZero = 0;

    //pid stuff
    public double maxAcc, maxVel, allowdPidError = 0;
    public double p, i, d, ff, max, min, izone, imax = 0;
    



    /**
     * Constructor takes motor ID, motor type (brushed or brushless)
     * and encoder type, none (for no external encoder use), absalute and relative.
     * run setupAdvanceControlleCfg() for extra controller configuration options.
     * If you have external encoder set, you need to run appropriate setupEncoder() functions
     * and probaly setupPIDF() and setupMotionProfile() too to configure everything correctly
     * 
     * 
     * @param ID
     * @param motorType
     * @param encoder
     */
    public ControllerCfg(int ID, boolean brushless, ControllerEnums encoderType){
        this.ID = ID;
        this.brushless = brushless;
        this. encoderType = encoderType;
    }


    /**
     * <p>This function sets up advanced motor controller params<p>
     * <p>break mode - true, false; <p>
     * <p>Voltage compensation; (put zero to disable)<p>
     * <p>stall current limit; (put zero to disable but freelimit and limitrpm also has to be disabled)<p>
     * <p>free current limit - current limit at free speed; (put zero to disable but limit rpm also has to be disabled)<p>
     * <p>limitRpm - rpm less than this value will be set to the stallLmit, 
     * RPM values greater than limitRPM will sacle linearaly to freeLimmit (place zero to disable)<p>
     */
    public void setupAdvanceControlleCfg(boolean brake, double voltagecomp, int stalllimit, int freelimit, int limitrpm){
        this.brake = brake;
        this.voltageComp = voltagecomp;
        this.stalllimit = stalllimit;
        this.freelimit = freelimit;
        this.limitrpm = limitrpm;
    }
    
    /**
     * Setup Encoder absalute
     * @param setzero
     */
    public void setupEncoder(double setzero){
        encoderZero = setzero;
    }
    
    /**
     * Setup Encoder relative
     * @param setzero
     * @param cpr
     */
    public void setupEncoder(double setzero, int cpr){
        encoderZero = cpr;
    }

    /**
     *Setup Motion Profile 
     */
    public void setupMotionProfile(double maxAcc, double maxVel){
        this.maxAcc = maxAcc;
        this.maxVel = maxVel;
    }

    /**
     * Setup Motion Profile
     * @param maxAcc
     * @param maxVel
     * @param allowedPidError
     */
    public void setupMotionProfile(double maxAcc, double maxVel, double allowedPidError){
        this.maxAcc = maxAcc;
        this.maxVel = maxVel;
        this.allowdPidError = allowedPidError;
    }

    /**
     * PIDF setup
     * @param p - proportional
     * @param i - intergral
     * @param d - derivitive
     * @param ff - feedfarward
     * @param min - minoutput
     * @param max - maxoutput
     * @param izone - integral izone
     * @param imax -  integral max
     */
    public void setupPIDF(double p, double i, double d, double ff, double min, double max, double izone, double imax){
        tunePIDF(p,i,d,ff);
        this.min = min; 
        this.max = max;
        this.izone = izone;
        this.imax = imax;        
    }


    /**
     * Tune Pidf in realtime
     * @param p - proportional
     * @param i - integral 
     * @param d - derivitive
     * @param ff - feedfarward
     */
    public void tunePIDF(double p, double i, double d, double ff){
        this.p = p;
        this.i = i;
        this.d = d;
        this.ff = ff;        
    }


    /*private void rev_updateController(){
        sparkmax.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
    }*/


}
  






















  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
