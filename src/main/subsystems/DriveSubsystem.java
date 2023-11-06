// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import com.revrobotics.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  public static class Hardware () {
    WPI_TalonSRX lFrontMotor;
    WPI_TalonSRX rFrontMotor;
    WPI_TalonSRX lMiddleMotor;
    WPI_TalonSRX rMiddleMotor;
    WPI_TalonSRX lRearMotor;
    WPI_TalonSRX rRearMotor;
  }

  public Hardware(WPI_TalonSRX lFrontMotor,
                  WPI_TalonSRX rFrontMotor,
                  WPI_TalonSRX lMiddleMotor,
                  WPI_TalonSRX rMiddleMotor,
                  WPI_TalonSRX lRearMotor,
                  WPI_TalonSRX rRearMotor) {
    this.lFrontMotor = lFrontMotor;
    this.rFrontMotor = rFrontMotor;
    this.lMiddleMotor = lMiddleMotor;
    this.rMiddleMotor = rMiddleMotor;
    this.lRearMotor = lRearMotor;
    this.rRearMotor = rRearMotor;
  }

  private WPI_TalonSRX m_lFrontMotor;
  private WPI_TalonSRX m_rFrontMotor;
  private WPI_TalonSRX m_lMiddleMotor;
  private WPI_TalonSRX m_rMiddleMotor;
  private WPI_TalonSRX m_lRearMotor;
  private WPI_TalonSRX m_rRearMotor;

  private Differential_Drive m_tankDrive; 

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem(Hardware tankDriveHardware) {
    this.m_lFrontMotor = tankDriveHardware.lFrontMotor;
    this.m_rFrontMotor = tankDriveHardware.rFrontMotor;
    this.m_lMiddleMotor = tankDriveHardware.lMiddleMotor;
    this.m_rMiddleMotor = tankDriveHardware.rMiddleMotor;
    this.m_lRearMotor = tankDriveHardware.lRearMotor;
    this.m_rRearMotor = tankDriveHardware.rRearMotor;

    m_tankDrive = new Differential_Drive(m_lFrontMotor, m_rFrontMotor)

    m_rFrontMotor.setInverted(true);
    m_rMiddleMotor.setInverted(true);
    m_rRearMotor.setInverted(true);

    m_lMiddleMotor.follow(m_lFrontMotor);
    m_rMiddleMotor.follow(m_rFrontMotor);
    m_lRearMotor.follow(m_lFrontMotor);
    m_rRearMotor.follow(m_rFrontMotor);
  }

  public static Hardware initializeHardware() {
    //Hardware object
    Hardware tankDriveHardware = new Hardware(
      new WPI_TalonSRX(Constants.DriveHardware.LEFT_FRONT_MOTOR, MotorType.kBrushed),
      new WPI_TalonSRX(Constants.DriveHardware.RIGHT_FRONT_MOTOR, MotorType.kBrushed),
      new WPI_TalonSRX(Constants.DriveHardware.LEFT_MIDDLE_MOTOR, MotorType.kBrushed),
      new WPI_TalonSRX(Constants.DriveHardware.RIGHT_MIDDLE_MOTOR, MotorType.kBrushed),
      new WPI_TalonSRX(Constants.DriveHardware.LEFT_REAR_MOTOR, MotorType.kBrushed),
      new WPI_TalonSRX(Constants.DriveHardware.RIGHT_REAR_MOTOR, MotorType.kBrushed),
    )

    return tankDriveHardware
  }

  public void teleop(double speed, double turn) {
    m_tankDrive.arcadeDrive(speed,turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
