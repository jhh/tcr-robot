package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj2.command.SubsystemBase
import mu.KotlinLogging
import org.strykeforce.telemetry.TelemetryService

private val logger = KotlinLogging.logger {}

class TalonSubsystem(val telemetryService: TelemetryService) : SubsystemBase() {

    val talon = TalonSRX(1).also { telemetryService.register(it) }

    fun run(output: Double) {
        logger.info { "starting talon at $output percent output" }
        talon.set(TalonSRXControlMode.PercentOutput, output)
    }
}