package frc.robot.FLYTLib;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.AbsoluteEncoderConfig;
import com.revrobotics.spark.config.EncoderConfig;

public class EncoderHandling {

    AbsoluteEncoder absEncoder;
    RelativeEncoder relEncoder; // can be used as extrenal and internal rel

    AbsoluteEncoderConfig absEncoderConfig;
    EncoderConfig encoderConfig;







    EncoderHandling(AbsoluteEncoder absEncoder, RelativeEncoder relEncoder,AbsoluteEncoderConfig absoluteEncoderConfig, EncoderConfig encoderConfig){
        this.absEncoder = absEncoder;
        this.relEncoder = relEncoder;
        this.absEncoderConfig = absoluteEncoderConfig;
        this.encoderConfig = encoderConfig;
    }
       /*
     * Encoders
     * https://codedocs.revrobotics.com/java/com/revrobotics/spark/config/absoluteencoderconfig#zeroCentered(boolean)
     * https://codedocs.revrobotics.com/java/com/revrobotics/spark/config/encoderconfig
     */


    
    //returns value in rotations
    public double getAbsPos(){
        return absEncoder.getPosition();
    }

    //returns value in rotations
    public double getAbsVel(){
        return absEncoder.getVelocity();
    }

    //creates new zero offset
    public void zeroOffset(double offset){
        absEncoderConfig.zeroOffset(offset);
    }

    //zero centering enable (0 to 1  now -0.5 to 0.5)
    public void zeroCentered(boolean zeroCentered){
        absEncoderConfig.zeroCentered(zeroCentered);
    }


    //returns value in rotations
    public double getRelPosition(){
        return relEncoder.getPosition();
    }

    //returns value in "RPM"
    public double getRelVelocity(){
        return relEncoder.getVelocity();
    }

    //relative encoders have no "home", so we can set it
    public void setEncoderPos(double pos){
        relEncoder.setPosition(pos);
    }

    //encoder cpr
    public void countsPerRevolution(int cpr){
        encoderConfig.countsPerRevolution(cpr);
    }

    //calcualtes average of velocity in number of smaples (1 to 64, defaut 64)
    public void encoderVelDepthAverage(int depth){
        encoderConfig.quadratureAverageDepth(depth);
    }

    //calculation rate of velocity (1 to 100ms)
    public void encoderVelPeriod(int period){
        encoderConfig.quadratureMeasurementPeriod(period);
    }


    
}



 