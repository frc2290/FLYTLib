package frc.robot.FLYTLib.FLYTDashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public abstract class SuperDashboard {

    NetworkTable table;

    SuperDashboard(String tableName){
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        table = inst.getTable(tableName);
    }

    protected void inputField(String name, double value){
        table.getEntry(name).setValue(1.0);
    }

    
}
