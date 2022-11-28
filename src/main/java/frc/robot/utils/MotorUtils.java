package frc.robot.utils;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class MotorUtils {
    
      /**
   * 
   * @param speedLeft left side speed
   * @param speedRight right side speed
   */
  public static void setMotorSpeed(double speedLeft, double speedRight, VictorSP motorOneLeft, VictorSP motorTwoLeft, VictorSP motorThreeLeft, VictorSP motorOneRight, VictorSP motorTwoRight, VictorSP motorThreeRight) {

    /**
     * Left motor setting
     */
    motorOneLeft.set(-speedLeft);
    motorTwoLeft.set(-speedLeft);
    motorThreeLeft.set(-speedLeft);

    /**
     * Right motor setting
     */
    motorOneRight.set(speedRight);
    motorTwoRight.set(speedRight);
    motorThreeRight.set(speedRight);
  }

  /**
   * 
   * @param straight 
   * @param turn turning???
   */
  public static void setDrive(double straight, double turn, VictorSP motorOneLeft, VictorSP motorTwoLeft, VictorSP motorThreeLeft, VictorSP motorOneRight, VictorSP motorTwoRight, VictorSP motorThreeRight) {

    // Set left motor thingys
    motorOneLeft.set(-(straight + turn));
    motorTwoLeft.set(-(straight + turn));
    motorThreeLeft.set(-(straight + turn));

    // Set right motor thingys
    motorOneRight.set(straight - turn);
    motorTwoRight.set(straight - turn);
    motorThreeRight.set(straight - turn);
  }
}
