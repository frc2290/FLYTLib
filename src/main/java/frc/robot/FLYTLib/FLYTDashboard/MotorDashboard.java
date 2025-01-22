package frc.robot.FLYTLib.FLYTDashboard;

import frc.robot.FLYTLib.FLYTMotorLib.SuperController;

public class MotorDashboard extends SuperDashboard {
    
    public MotorDashboard(SuperController controller)
    {
        super(String.valueOf(controller.getMotorID()));
    }

    public void set(String name, double value){
        inputField(name, value);
    }

    
}
