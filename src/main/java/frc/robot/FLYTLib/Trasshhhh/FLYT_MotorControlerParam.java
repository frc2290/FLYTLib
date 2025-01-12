package frc.robot.FLYTLib.Trasshhhh;

import frc.robot.FLYTLib.Trashss.FT_MotorControllerConfiguration;

public class FLYT_MotorControlerParam extends FT_MotorControllerConfiguration{

    private FLYT_MotorControllers motorType;
    private boolean closedLoop;
    private FLYT_MotorControllers encoderType;
    private FLYT_MotorControllers motorControlMode;
    private int id;

    public FLYT_MotorControlerParam(int id, FLYT_MotorControllers motortype, boolean closedloop, FLYT_MotorControllers encodertype, FLYT_MotorControllers motorControlMode){
        this.id = id;
        motorType = motortype;
        closedLoop = closedloop;
        encoderType = encodertype;
        this.motorControlMode = motorControlMode;
    }


    @Override
    public int flyt_motorID(){
        return id;
    }
    
    @Override
    public FLYT_MotorControllers flyt_getMotorType(){
        return motorType;
    }

    @Override
    public boolean flyt_getControlType(){
        return closedLoop;
    }

    @Override
    public FLYT_MotorControllers flyt_getExternalEncoder(){
        return encoderType;
    }

    @Override
    public FLYT_MotorControllers flyt__getDriveMode(){
        return motorControlMode;
    }

    /*@Override 
    public Object flyt_advanced(){

    }*/

    
}
