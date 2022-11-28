// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.MotorUtils;
import frc.robot.utils.PIDUtils;


public class Robot extends TimedRobot {

  /**
   * Define motors
   */
  VictorSP motorOneLeft = new VictorSP(0); // First port
  VictorSP motorTwoLeft = new VictorSP(1); // Second port
  VictorSP motorThreeLeft = new VictorSP(2); // Third port

  VictorSP motorOneRight = new VictorSP(7); // Sixth port
  VictorSP motorTwoRight = new VictorSP(8); // Seventh port
  VictorSP motorThreeRight = new VictorSP(9); // Eighth port

  /**
   * PID controller and gyro variables
   */
  AHRS gyro = new AHRS(SPI.Port.kMXP);

  PIDController turningPID;

  /**
   * Gamepad variables
   */
  Joystick gamepad = new Joystick(0);
  //JoystickButton leftBottom = new JoystickButton(gamepad, 0);


  @Override
  public void robotInit() {

    // Put PID input fields into dashboard
    SmartDashboard.putNumber("P", 0);
    SmartDashboard.putNumber("I", 0);
    SmartDashboard.putNumber("D", 0);

    // Put angle to turn input field into dashboard
    SmartDashboard.putNumber("Angle to turn", 0);

  }

  @Override
  public void robotPeriodic() {

    // Always display the gyro rotation on the robot
    SmartDashboard.putNumber("Gyro rotation", gyro.getAngle());
  }

  @Override
  public void autonomousInit() {

    // Reset gyro on auto init
     gyro.reset();

     // Get PID inputs from dashboard fields
     double P = SmartDashboard.getNumber("P", 0);
     double I = SmartDashboard.getNumber("I", 0);
     double D = SmartDashboard.getNumber("D", 0);

     // Create PID controller with input from dashboard fields
     turningPID = new PIDController(P, I, D);
 
     // Tolerance for angler (max 0.5 degrees off target)
     turningPID.setTolerance(0.5);
 
     // Integrator range (no clue what this does)
     turningPID.setIntegratorRange(0, 0.1);
  }

  @Override
  public void autonomousPeriodic() {

    // Get the angle to turn from field on dashboard (default is 0 degrees)
    double angleToTurn = SmartDashboard.getNumber("Angle to turn", 0);

    // Set the angle to turn when auto is turned on
    PIDUtils.turnToAngle(angleToTurn, turningPID, gyro, motorOneLeft, motorTwoLeft, motorThreeLeft, motorOneRight, motorTwoRight, motorThreeRight);

    /* Please don't use this (Time = bad)
    long timeSinceAutoStarted = System.currentTimeMillis() - autoStartTime;

    if (timeSinceAutoStarted < 266.666666667) {
      this.setMotorSpeed(-1.0, -1.0);
    } else if (timeSinceAutoStarted < 550.666666667) {
      this.setMotorSpeed(1.0,- 1.0);
    } else if (timeSinceAutoStarted < 1200) {
      this.setMotorSpeed(-1.0, -1.0);
    } else if (timeSinceAutoStarted < 1450){
      this.setMotorSpeed(-1.0, 1.0);
    } else {
      this.setMotorSpeed(0.0, 0.0);
    }
    */
  }

  @Override
  public void teleopInit() {

    // Set motor speed to 0
    MotorUtils.setMotorSpeed(0.0, 0.0, motorOneLeft, motorTwoLeft, motorThreeLeft, motorOneRight, motorTwoRight, motorThreeRight);
  }

  @Override
  public void teleopPeriodic() {

    // Get axis input from joystick
    double x = gamepad.getRawAxis(0);
    double y = gamepad.getRawAxis(1);

    // Get input and translate to motor speed
    MotorUtils.setDrive(x, y, motorOneLeft, motorTwoLeft, motorThreeLeft, motorOneRight, motorTwoRight, motorThreeRight);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
