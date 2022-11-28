package frc.robot.utils;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class PIDUtils {
    
    /**
   * 
   * @param targetAngle target angle to turn to
   * @return a true or false boolean if it has turned to the angle
   */
  public static boolean turnToAngle(double targetAngle, PIDController turningPID, AHRS gyro, VictorSP motorOneLeft, VictorSP motorTwoLeft, VictorSP motorThreeLeft, VictorSP motorOneRight, VictorSP motorTwoRight, VictorSP motorThreeRight) {

    double output = turningPID.calculate(gyro.getAngle(), targetAngle);
 
    MotorUtils.setDrive(0, output, motorOneLeft, motorTwoLeft, motorThreeLeft, motorOneRight, motorTwoRight, motorThreeRight);
 
     if (turningPID.atSetpoint()){
       // If it's at the target angle set motor speed to 0 and return true
       MotorUtils.setDrive(0,0, motorOneLeft, motorTwoLeft, motorThreeLeft, motorOneRight, motorTwoRight, motorThreeRight);
       return true;
     } else {
       // If it's not at the target angle return false
       return false;
     }
    }
}
