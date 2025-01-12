package frc.robot.FLYTLib;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;



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



}
