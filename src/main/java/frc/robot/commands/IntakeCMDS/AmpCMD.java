// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCMDS;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.PivotTarget;

public class AmpCMD extends Command {
  /** Creates a new AmpCMD. */
  IntakeSubsystem intakeSubsystem;
  public AmpCMD(IntakeSubsystem intakeSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intakeSubsystem = intakeSubsystem;
    addRequirements(intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intakeSubsystem.pivot_target = PivotTarget.AMP;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //intakeSubsystem.goToAmp();
    double pivot_angle = intakeSubsystem.pivotTargetToAngle(intakeSubsystem.pivot_target);
    System.out.println("amp angle target: " + pivot_angle);

    double value = intakeSubsystem.intakeEncoder.getAbsolutePosition() - Constants.intake.k_pivotEncoderOffset;
    value *= 360;
    double voltage = intakeSubsystem.giveVoltage(pivot_angle, value);

    System.out.println("final voltage: " + voltage + "\n\n");

    intakeSubsystem.pivotMotor.setVoltage(voltage);
    System.out.println("s");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.stopIntake();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
