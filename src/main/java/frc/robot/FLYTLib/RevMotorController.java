package frc.robot.FLYTLib;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.FLYTLib.RevControllerLib.RevControllers;



public class RevMotorController {

    public RevControllers advacedControl;
    SparkMax sparkmax;

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
    public double rev_getPos(){
        return 0;
    }

    //get motor velocity
    public double rev_getVel(){
        return 0;
    }

    //get motor acceleration
    public double rev_getAcc(){
        return 0;
    }

    //get motor tempreture (motor or controlelr Temp?!?)
    public double rev_getTemp(){
        return sparkmax.getMotorTemperature();
    }

    //motor controller current output
    public double rev_getCurrent(){
        return sparkmax.getOutputCurrent();
    }

    //voltage controller is getting
    public double rev_getVoltage(){
        return sparkmax.getBusVoltage();
    }

}
