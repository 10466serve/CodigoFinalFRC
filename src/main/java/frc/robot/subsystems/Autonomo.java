package frc.robot.subsystems;

import java.security.cert.X509CRL;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotContainer;


public class Autonomo {

    int contador = 0;
    Timer tempo = new Timer();

    public Autonomo(){}

    public class AutoDireita {

        public void andar(double setPoint, double variavelProcesso, double speed, double rotacao) {
            if(variavelProcesso <= setPoint) {
              RobotContainer.sistemaTracao.arcadeMode(speed, rotacao);
            }else {
              RobotContainer.sistemaTracao.arcadeMode(0, 0);
              contador++;
              tempo.reset();
            }
          }
    }
}