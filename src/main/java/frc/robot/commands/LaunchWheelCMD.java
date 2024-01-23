// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.LauncherSub;

public class LaunchWheelCMD extends Command {
  /** Creates a new LaunchWheelCMD. */
  private final Timer timer = new Timer();
  LauncherSub launcherSub;
  public LaunchWheelCMD(LauncherSub launcherSub) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.launcherSub = launcherSub;
    addRequirements(launcherSub);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    launcherSub.setLaunchWheel(Constants.launchSpeed);
    timer.reset();
    timer.start();
    while (timer.get() < Constants.timeToWait) {
      //do nothing
    }
    launcherSub.setLaunchWheel2(Constants.launchSpeed);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    launcherSub.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
