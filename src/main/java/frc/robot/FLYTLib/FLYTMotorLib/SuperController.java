package frc.robot.FLYTLib.FLYTMotorLib;

public abstract class SuperController {


    protected double conversionFactor = 1;


    //Control
    public abstract void set(double set);

    public abstract void disable();

    public abstract double getPos();

    public abstract double getVel();

    public abstract double getAcc();

    public abstract double getTemp();

    public abstract double getCurrent();

    public abstract double getVol();

    public abstract double getMotorID();
    


    //Config STUFF
    public abstract void pidTune(double p, double i, double d, double ff);

    public abstract void pidSetup(double min, double max, double izone, double imax, boolean primaryEnc, int controlType);

    public abstract void motionProfile(double maxVel, double maxAcc);

    public abstract void followeMe(int leaderID, boolean invert);

    public abstract void encocderCfg(int countsPerRev, double setPos);

    public abstract void avdanceControl(double voltageComp, int currentStallLim, int currentFreeLim, double conversionFactor);

    
    
}
