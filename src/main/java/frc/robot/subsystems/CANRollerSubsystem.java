// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RollerMotorConstants;
import frc.robot.FLYTLib.FLYTDashboard.OldStuff.MotorDashboard;
import frc.robot.FLYTLib.FLYTMotorLib.FlytMotorController;
import frc.robot.FLYTLib.FLYTMotorLib.SparkController;

/** Class to run the rollers over CAN */
public class CANRollerSubsystem extends SubsystemBase {
  private final FlytMotorController rollerMotor;
  //private final MotorDashboard rollerDashboard;

  public CANRollerSubsystem() {

    //initialize FLYT Motor
    rollerMotor = new SparkController("Roller Motor", RollerMotorConstants.motor_id, RollerMotorConstants.brushless, RollerMotorConstants.break_mode, true);
    //rollerMotor.encocderCfg(0, 0);
    rollerMotor.advanceControl(RollerMotorConstants.motor_voltageComp, RollerMotorConstants.motor_currentLim, 0, 0);

    //initialize FLYT motor Dashboard
    //rollerDashboard = new MotorDashboard(rollerMotor);

    // Set up the roller motor as a brushed motor
    ////rollerMotor = new SparkMax(RollerConstants.ROLLER_MOTOR_ID, MotorType.kBrushed);

    // Set can timeout. Because this project only sets parameters once on
    // construction, the timeout can be long without blocking robot operation. Code
    // which sets or gets parameters during operation may need a shorter timeout.
    ////rollerMotor.setCANTimeout(250);

    // Create and apply configuration for roller motor. Voltage compensation helps
    // the roller behave the same as the battery
    // voltage dips. The current limit helps prevent breaker trips or burning out
    // the motor in the event the roller stalls.
    //SparkMaxConfig rollerConfig = new SparkMaxConfig();
    //rollerConfig.voltageCompensation(RollerConstants.ROLLER_MOTOR_VOLTAGE_COMP);
    //rollerConfig.smartCurrentLimit(RollerConstants.ROLLER_MOTOR_CURRENT_LIMIT);
    //rollerMotor.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
   
    
  }

  @Override
  public void periodic() {
    //update dashaboard
    //rollerDashboard.update();
    rollerMotor.updateDashboard();
  }

  /** This is a method that makes the roller spin */
  public void runRoller(double speed){
    rollerMotor.set(speed);
  }
}
