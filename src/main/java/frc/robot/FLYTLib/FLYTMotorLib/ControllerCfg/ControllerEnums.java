package frc.robot.FLYTLib.FLYTMotorLib.ControllerCfg;

public enum ControllerEnums {

    //motor type
    BRUSHED,
    BRUSHLESS,

    //Encoder type
    /**
    * NONE - no encoder for brushed, or defult to internal encoder for brushless
    */
    NONE,
    /**
    * ABSALUTE - external absalute encoder
    */
    ABSALUTE,
    /**
    * RELATIVE - external relative encoder
    */
    RELATIVE;    
}
