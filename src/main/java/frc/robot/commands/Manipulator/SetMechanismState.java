package frc.robot.commands.Manipulator;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.RobotContainer;
import frc.robot.Constants.EstadoClimber;
import frc.robot.Constants.EstadoCoral;
import frc.robot.Constants.EstadoTracao;
import frc.robot.Constants.PuxarAlgaEstado;
import frc.robot.Constants.DescerAlgaEstado;
public class SetMechanismState extends InstantCommand {
  
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
      private EstadoCoral currentStateCoral = EstadoCoral.PARADO;
      private EstadoTracao currentStateDriveTrain = EstadoTracao.PARADO;
      private DescerAlgaEstado currentStateDesceAlga = DescerAlgaEstado.PARADO;
      private EstadoClimber currentStateClimber = EstadoClimber.PARADO;
      private PuxarAlgaEstado currentStatePuxarAlga = PuxarAlgaEstado.PARADO;

    boolean currentStateCoralOnly = false;
    boolean currentStateDriveTrainOnly = false;
    boolean currentStateDesceAOnly = false;
    boolean currentStatePuxaAOnly = false;
    boolean currentStateClimberOnly = false;

  public SetMechanismState(EstadoCoral estado) {
    this.currentStateCoral = estado;
    addRequirements(RobotContainer.sistemaCoral);
    currentStateCoralOnly = true;
  }
  
  public SetMechanismState(EstadoTracao estado) {
    this.currentStateDriveTrain = estado;
    addRequirements(RobotContainer.sistemaTracao);
    currentStateDriveTrainOnly = true;
  }
  
  public SetMechanismState(DescerAlgaEstado estado) {
    this.currentStateDesceAlga = estado;
    addRequirements(RobotContainer.sistemaDescerAlga);
    currentStateDesceAOnly = true;
  }
  
  public SetMechanismState(PuxarAlgaEstado estado) {
    this.currentStatePuxarAlga = estado;
    addRequirements(RobotContainer.sistemaPuxarAlga);
    currentStatePuxaAOnly = true;
            
    }
  public SetMechanismState(EstadoClimber estado) {
    this.currentStateClimber = estado;
    addRequirements(RobotContainer.sistemaClimber);
    currentStateClimberOnly = true;

}
            
  @Override
  public void initialize() {
    if(currentStateCoralOnly) {
      RobotContainer.sistemaCoral.SetCurrentState(this.currentStateCoral);
    } else if(currentStateDriveTrainOnly){
      RobotContainer.sistemaTracao.SetCurrentState(this.currentStateDriveTrain);
    }else if(currentStateDesceAOnly){
      RobotContainer.sistemaDescerAlga.SetCurrentState(this.currentStateDesceAlga);
    }else if(currentStateClimberOnly) {
      RobotContainer.sistemaClimber.SetcurrentState(this.currentStateClimber);
    }else if(currentStatePuxaAOnly){
      RobotContainer.sistemaPuxarAlga.SetCurrentState(this.currentStatePuxarAlga);
    }
  } 
}