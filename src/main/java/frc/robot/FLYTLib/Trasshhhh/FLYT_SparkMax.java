package frc.robot.FLYTLib.Trasshhhh;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.FLYTLib.Trashss.FT_MotorController;

import com.revrobotics.spark.SparkMax;

public class FLYT_SparkMax extends FT_MotorController {

    FLYT_MotorControlerParam motorBasicCfg;
    SparkMax sparkMax;
    
    /**
     * Creates  motor function with input from constants params
     * @param motorBasicCfg
     */
    public FLYT_SparkMax(FLYT_MotorControlerParam motorBasicCfg){
        this.motorBasicCfg = motorBasicCfg;
        if(motorBasicCfg.flyt_getMotorType() == FLYT_MotorControllers.SPARK_MAX_BRUSHED){
            sparkMax = new SparkMax(motorBasicCfg.flyt_motorID(), MotorType.kBrushed);
        }else if (motorBasicCfg.flyt_getMotorType() == FLYT_MotorControllers.SPARK_MAX_BRUSHLESS) {
            sparkMax = new SparkMax(motorBasicCfg.flyt_motorID(), MotorType.kBrushless);
        }
    }


    /**
     * Set motor input (-1,1) if no closed loop controle specified
     * Otherwise it is closed loop control target set
     */
    @Override
    public void fm_set(){

    }

    /**
     * Disable the motor
     */
    @Override
    public void fm_disable(){
        sparkMax.disable();
    }

    /**
     * Get motor tempreture readout
     */
    @Override
    public void fm_getPos(){
     
    }

    /**
     * Get motor tempreture readout
     */
    @Override
    public void fm_getTemp(){
        sparkMax.getMotorTemperature();
    }

    /**
     * Get current readout
     */
    @Override
    public void fm_getCurr(){
        sparkMax.getOutputCurrent();
    }

    /**
     * Get current voltage readout
     */
    @Override
    public void fm_getVol(){
        sparkMax.getBusVoltage();
    }


    
}
