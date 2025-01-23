package frc.robot.FLYTLib.FLYTDashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.FLYTLib.FLYTMotorLib.SuperController;

public class MotorDashboard extends SuperDashboard{
    
    NetworkTable table;
    SuperController controller;
    
    public MotorDashboard(SuperController m_controller){
        controller = m_controller;
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        table = inst.getTable("Motor" + String.valueOf(controller.getMotorID()));
    }


    public void update(){
        motorState();
    }

    private void motorState(){
        set("MotorID", controller.getMotorID());
        set("MotorVoltageComp", controller.getPos());
        set("MotorCurrentLim", controller.getVel());
        set("MotorTemp", controller.getVol());
        set("MotorCurrent", controller.getCurrent());
        set("MotorVoltage", controller.getTemp());

    }


    //set things on the dashboard
    private void set(String name, double value){
        table.getEntry(name).setValue(value);
    }



    @Override
    public void periodic() {
        update();
    }
    


}
