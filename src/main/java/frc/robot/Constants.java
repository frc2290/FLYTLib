package frc.robot;


public final class Constants {

  //Motor control configurations, done through objects being passed into MotorController constructor
  public class Motors{
    
    int motor1ID = 1;
    //brushed or brushless
    boolean brushless = false;  
    //encoder type 1 - absalute, 2 - relative, 3 - internatl(relative), 4 - none
    int encoderType = 4;
  }
  






















  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
