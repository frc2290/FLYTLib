package frc.robot.FLYTLib.FLYTMotorLib.ControllerCfg;


public class ControllerCfg {

    private final int ID;
    private final ControllerEnums encoder;
    private final ControllerEnums motorType;


    private

    /**
     * Constructor takes motor ID, motor type (brushed or brushless)
     * and encoder type, none (for no external encoder use), absalute and relative.
     * run setupAdvanceControlleCfg() for extra controller configuration options.
     * If you have external encoder set, you need to run appropriate setupEncoder() functions
     * and probaly setupPID() and setupMotionProfile() too to configure everything correctly
     * 
     * 
     * @param ID
     * @param motorType
     * @param encoder
     */
    public ControllerCfg(int ID, ControllerEnums motorType, ControllerEnums encoder){
        this.ID = ID;
        this.motorType = motorType;
        this. encoder = encoder;
    }

    public int getMotorID(){
        return ID;
    }

    public ControllerEnums getMotor(){
        return motorType;
    }

    public ControllerEnums getEncoder(){
        return encoder;
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
    public void setupAdvanceControlleCfg(boolean brake, double vComp, int stalllimit, int freelimit, int limitrpm){
        
    }
    
    //setup code in other stuff specified
    public void setupEncoder(){

    }


    public void setupMotionProfile(){

    }

    public void setupPID(){

    }


}
