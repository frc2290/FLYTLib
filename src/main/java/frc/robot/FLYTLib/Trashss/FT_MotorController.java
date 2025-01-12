package frc.robot.FLYTLib.Trashss;



public abstract class FT_MotorController {

    /**
     * Set motor input (-1,1) if no closed loop controle specified
     * Otherwise it is closed loop control target set
     */
    abstract public void fm_set();

    /**
     * Disable the motor
     */
    abstract public void fm_disable();

    /**
     * Get motor tempreture readout
     */
    public void fm_getPos(){
     
    }

    /**
     * Get motor tempreture readout
     */
    abstract public void fm_getTemp();
    

    /**
     * Get current readout
     */
    abstract public void fm_getCurr();

    /**
     * Get current voltage readout
     */
    abstract public void fm_getVol();

    
}
