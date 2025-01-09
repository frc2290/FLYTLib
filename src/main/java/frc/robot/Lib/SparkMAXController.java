package frc.robot.Lib;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;


public class SparkMAXController {

    AbsoluteEncoder absEncoder;
    RelativeEncoder relEncoder;
    SparkMax sparkMax;

    //defult motor encoder
    public SparkMAXController(int id, boolean brushless) {

        //checks if we want to control brushed or brushless motor
        //true for brushless, false for brushed
        if(brushless)
        {
            SparkMax sparkMax = new SparkMax(id, MotorType.kBrushless);
        }
        else
        {
            SparkMax sparkMax = new SparkMax(id, MotorType.kBrushed);
        }
    
    }

    //connected encoders
    public SparkMAXController(int id, boolean brushless, boolean encodertype) {

        //checks if we want to control brushed or brushless motor
        //true for brushless, false for brushed
        if(brushless)
        {
            SparkMax sparkMax = new SparkMax(id, MotorType.kBrushless);
        }
        else
        {
            SparkMax sparkMax = new SparkMax(id, MotorType.kBrushed);
        }

        if(encodertype)
        {
            absEncoder = sparkMax.getAbsoluteEncoder();
        }
        else
        {
            relEncoder = sparkMax.getEncoder();
        }
    

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
        return sparkMax.getMotorTemperature();
    }

    //motor controller current output
    public double getCurrent(){
        return sparkMax.getOutputCurrent();
    }

    //voltage controller is getting
    public double getVoltage(){
        return sparkMax.getBusVoltage();
    }

    /*
     * SETTERS
     */

     //sets voltage
     public void set(double value){
        sparkMax.set(value);
     }

     //set voltage relative to available voltage, availble 12V, setV(0.5) = 6v, availble 6v, setV(0.5) = 3v
     public void setV(double value){
        sparkMax.setVoltage(value);
     }





     //disable motor
     public void disable(){
        sparkMax.disable();
     }


}
