// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RollerConstants;
import frc.robot.FLYTLib.FLYTDashboard.MotorDashboard;
import frc.robot.FLYTLib.FLYTDashboard.SuperDashboard;
import frc.robot.FLYTLib.FLYTMotorLib.SparkController;
import frc.robot.FLYTLib.FLYTMotorLib.SuperController;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

/** Class to run the rollers over CAN */
public class CANRollerSubsystem extends SubsystemBase {
  private final SparkMax rollerMotor;

  public CANRollerSubsystem() {

    SuperController rollermotor = new SparkController(7, true, true);
    // Set up the roller motor as a brushed motor
    rollerMotor = new SparkMax(RollerConstants.ROLLER_MOTOR_ID, MotorType.kBrushed);

    // Set can timeout. Because this project only sets parameters once on
    // construction, the timeout can be long without blocking robot operation. Code
    // which sets or gets parameters during operation may need a shorter timeout.
    rollerMotor.setCANTimeout(250);

    // Create and apply configuration for roller motor. Voltage compensation helps
    // the roller behave the same as the battery
    // voltage dips. The current limit helps prevent breaker trips or burning out
    // the motor in the event the roller stalls.
    SparkMaxConfig rollerConfig = new SparkMaxConfig();
    rollerConfig.voltageCompensation(RollerConstants.ROLLER_MOTOR_VOLTAGE_COMP);
    rollerConfig.smartCurrentLimit(RollerConstants.ROLLER_MOTOR_CURRENT_LIMIT);
    rollerMotor.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SuperDashboard rollerM = new MotorDashboard(rollermotor);
    
    
  }

  @Override
  public void periodic() {
    
  }

  /** This is a method that makes the roller spin */
  public void runRoller(double forward, double reverse) {
    rollerMotor.set(forward - reverse);
  }
}