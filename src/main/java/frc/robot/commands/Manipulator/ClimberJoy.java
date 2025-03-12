// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Manipulator;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/** An example command that uses an example subsystem. */
public class ClimberJoy extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  double valorY;
  
  private final ClimberSystem ClimberSystem;
  private CommandXboxController joystick2;
  
  public ClimberJoy(ClimberSystem sistemaclimber, CommandXboxController joy2) {
    this.ClimberSystem = sistemaclimber;
    this.joystick2 = joy2;

    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(ClimberSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
   {
    valorY = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  
  public void execute() {
    valorY = joystick2.getRawAxis(Constants.JoysticsDeControle.valorY2) * 0.2;
    RobotContainer.sistemaClimber.ClimberMotor.set(valorY);
  }
}
