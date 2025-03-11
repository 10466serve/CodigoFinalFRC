// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.EstadoCoral;

public class CoralScoreSystem extends SubsystemBase {
  public SparkMax coralMotor = new SparkMax(Constants.ConstanteSistemaCoral.MotorID, MotorType.kBrushed);
  RelativeEncoder coralEncoder = coralMotor.getEncoder();

  SparkMaxConfig configSparkMotor = new SparkMaxConfig();

  public EstadoCoral currentState = EstadoCoral.PARADO;

  public CoralScoreSystem() {
    configSparkMotor
      .inverted(true)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(60);
  
    coralMotor.configure(configSparkMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // leftMotorBack.follow(leftMotorFront);
    // rightMotorBack.follow(rightMotorFront);
  }

  @Override
  public void periodic() {
    if(currentState == EstadoCoral.ATIVADO){
      coralMotor.set(currentState.velocidade);
    } else{
      coralMotor.stopMotor();;
    }
  }

  public double getCoralEncoderPosition() {
    return coralEncoder.getPosition();
  }

  public void setCoralEncoderPosition(double position) {
    coralEncoder.setPosition(position);
  }

  public void SetCurrentState(EstadoCoral state){
    this.currentState = state;
  }
}
