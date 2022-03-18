package frc.robot.commands

import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.WaitCommand
import frc.robot.subsystems.TalonSubsystem

class TalonRunCommand(val talonSubsystem: TalonSubsystem) : SequentialCommandGroup() {

    init {
        addRequirements(talonSubsystem)
        addCommands(
            InstantCommand({ talonSubsystem.run(0.25) }, talonSubsystem),
            WaitCommand(1.0),
            InstantCommand({ talonSubsystem.run(0.5) }, talonSubsystem),
            WaitCommand(1.0),
            InstantCommand({ talonSubsystem.run(0.25) }, talonSubsystem),
            WaitCommand(1.0),
            InstantCommand({ talonSubsystem.run(0.0) }, talonSubsystem),
        )
    }

}