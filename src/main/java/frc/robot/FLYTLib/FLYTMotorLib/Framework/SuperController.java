package frc.robot.FLYTLib.FLYTMotorLib.Framework;

public abstract class SuperController {










    //CONTROLLING STUFF
    abstract void avanceControl(double voltageComp, double currentLim);

    abstract void followeMe(int id, boolean invert);

    abstract void encocderCfg(int cpr_zero);

    abstract void motionProfile(double maxVel, double maxAcc);

    abstract void pidSetup(double p, double i, double d, double ff);

    abstract void pidTune(double max, double min, double izone, double imax);
    
}
