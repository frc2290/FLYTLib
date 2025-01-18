package frc.robot.FLYTLib.FLYTMotorLib;

public abstract class SuperController {



    //Control
    abstract void set();

    abstract void disable();

    abstract double getPos();

    abstract double getVel();

    abstract double getAcc();

    abstract double getTemp();

    abstract double getCurrent();

    abstract double getVol();
    


    //Config STUFF
    abstract void pidTune(double p, double i, double d, double f, double vf);

    abstract void pidSetup(double max, double min, double izone, double imax);

    abstract void motionProfile(double maxVel, double maxAcc);

    abstract void followeMe(int leaderID, boolean invert);

    abstract void encocderCfg(int countsPerRev, int zero);

    abstract void avanceControl(double voltageComp, int currentStallLim, int currentFreeLim, double converionsFactor);

    
    
}
