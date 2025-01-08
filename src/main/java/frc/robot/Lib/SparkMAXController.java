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



}
