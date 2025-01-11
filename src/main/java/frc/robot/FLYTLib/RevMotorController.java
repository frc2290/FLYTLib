package frc.robot.FLYTLib;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.FLYTLib.RevControllerLib.RevControllers;



public class RevMotorController {

    public RevControllers advacedControl; //access to advance functions
    SparkMax sparkmax;

    /*
     * PLAN:
     * ADD CONSTRUCTOR FOR ENCODER AND PID  CONTROL OPTION
     * ADD CONVERSTION FACTOR FOR DEGREES AND RADIANS
     */


    // Constructor: id number; true for brushless, 
    public RevMotorController(int id, boolean brushless){
        sparkmax = new SparkMax(id, brushless ? MotorType.kBrushless : MotorType.kBrushed);
        advacedControl = new RevControllers(sparkmax);
    }



    //set motor output power (-1 to 1) no matter what voltage
    public void m_set(double value){
        sparkmax.set(value);
    }
    
    //set motor output power relative to available voltage
    public void m_setV(double value){
        sparkmax.setVoltage(value);
    }
    
    //disable motor
    public void m_disable(){
        sparkmax.disable();
    } 





    //get motor position
    public double m_getPos(){
    
        return 0;
    }

    //get motor velocity
    public double m_getVel(){
        return 0;
    }

    //get motor acceleration
    public double m_getAcc(){
        return 0;
    }

    //get motor tempreture (motor or controlelr Temp?!?)
    public double m_getTemp(){
        return sparkmax.getMotorTemperature();
    }

    //motor controller current output
    public double m_getCurrent(){
        return sparkmax.getOutputCurrent();
    }

    //voltage controller is getting
    public double m_getVoltage(){
        return sparkmax.getBusVoltage();
    }

}
