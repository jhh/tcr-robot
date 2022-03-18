package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.strykeforce.telemetry.TelemetryService

class TalonSubsystem(val telemetryService: TelemetryService) : SubsystemBase() {

    val talon = TalonSRX(1).also { telemetryService.register(it) }

    fun run(output: Double) = talon.set(TalonSRXControlMode.PercentOutput, output)
}