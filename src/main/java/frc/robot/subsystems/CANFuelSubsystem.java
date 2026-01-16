// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.FuelConstants.*;

public class CANFuelSubsystem extends SubsystemBase {
  private final TalonFX feederRoller = new TalonFX(FEEDER_MOTOR_ID, new CANBus("rio"));
  private final TalonFX intakeLauncherRoller = new TalonFX(INTAKE_LAUNCHER_MOTOR_ID, new CANBus("rio"));

  private final VelocityTorqueCurrentFOC feederRollerRequest = new VelocityTorqueCurrentFOC(0.0).withSlot(0);
  private final VelocityTorqueCurrentFOC intakeLauncherRollerRequest = new VelocityTorqueCurrentFOC(0.0).withSlot(0);;

  /** Creates a new CANBallSubsystem. */
  public CANFuelSubsystem() {
    // create brushed motors for each of the motors on the launcher mechanism


    // put default values for various fuel operations onto the dashboard
    // all methods in this subsystem pull their values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    var feederConfig = new TalonFXConfiguration();
    feederConfig.Slot0.kS = 0;
    feederConfig.Slot0.kV = 0;
    feederConfig.Slot0.kA = 0;
    feederConfig.Slot0.kP = 3;
    feederConfig.Slot0.kI = 0;
    feederConfig.Slot0.kD = 0;

    feederRoller.getConfigurator().apply(feederConfig);

    var launcherRoller = new TalonFXConfiguration();
    feederConfig.Slot0.kS = 0;
    feederConfig.Slot0.kV = 0;
    feederConfig.Slot0.kA = 0;
    feederConfig.Slot0.kP = 3;
    feederConfig.Slot0.kI = 0;
    feederConfig.Slot0.kD = 0;

    intakeLauncherRoller.getConfigurator().apply(launcherRoller);


  }

  public void setFeederRollerSpeed(double speed) {
    feederRoller.setControl(feederRollerRequest.withVelocity(speed));
  }

  public void setIntakeLauncherRollerSpeed(double speed) {
     intakeLauncherRoller.setControl(intakeLauncherRollerRequest.withVelocity(speed));
  }

  // A command factory to set the rollers to values for intaking.
  public Command intakeCommand() {
    return this.runOnce(
        () -> {
          setFeederRollerSpeed(INTAKING_FEEDER_SPEED);
          setIntakeLauncherRollerSpeed(INTAKING_INTAKE_VOLTAGE);
        });
  }

  // A command factory to set the rollers to values for ejecting fuel out the intake.
  public Command ejectCommand() {
    return this.runOnce(
        () -> {
            setFeederRollerSpeed(-INTAKING_FEEDER_SPEED);
            setIntakeLauncherRollerSpeed(-INTAKING_INTAKE_VOLTAGE);
        });
  }

  // A command factory to set the rollers to values for launching.
  public Command launchCommand() {
    return this.runOnce(
        () -> {
            setFeederRollerSpeed(LAUNCHING_FEEDER_SPEED);
            setIntakeLauncherRollerSpeed(LAUNCHING_FEEDER_SPEED);
        });
  }

  // A command factory to stop the rollers.
  public Command stopCommand() {
    return this.runOnce(
        () -> {
            setFeederRollerSpeed(0);
            setIntakeLauncherRollerSpeed(0);
        });
  }

  // A command factory to spin up the launcher roller while spinning the feeder roller.
  public Command spinUpCommand() {
    return this.runOnce(
        () -> {
            setFeederRollerSpeed(SPIN_UP_FEEDER_SPEED);
            setIntakeLauncherRollerSpeed(LAUNCHING_LAUNCHER_SPEED);
        });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
