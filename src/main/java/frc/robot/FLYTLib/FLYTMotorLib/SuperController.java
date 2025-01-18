package frc.robot.FLYTLib.FLYTMotorLib;

public abstract class SuperController {


    protected double conversionFactor = 1;


    //Control
    abstract void set(double set);

    abstract void disable();

    abstract double getPos();

    abstract double getVel();

    abstract double getAcc();

    abstract double getTemp();

    abstract double getCurrent();

    abstract double getVol();
    


    //Config STUFF
    abstract void pidTune(double p, double i, double d, double ff);

    abstract void pidSetup(double min, double max, double izone, double imax, boolean primaryEnc, int controlType);

    abstract void motionProfile(double maxVel, double maxAcc);

    abstract void followeMe(int leaderID, boolean invert);

    abstract void encocderCfg(int countsPerRev, double setPos);

    abstract void avdanceControl(double voltageComp, int currentStallLim, int currentFreeLim, double conversionFactor);

    
    
}
