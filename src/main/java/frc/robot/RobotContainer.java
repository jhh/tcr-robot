// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.NetworkButton;
import frc.robot.commands.TalonRunCommand;
import frc.robot.subsystems.TalonSubsystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.strykeforce.telemetry.TelemetryController;
import org.strykeforce.telemetry.TelemetryService;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final static Logger logger = LoggerFactory.getLogger(RobotContainer.class);
    // The robot's subsystems and commands are defined here...
    private final TelemetryService telemetryService = new TelemetryService(TelemetryController::new);
    private final TalonSubsystem talonSubsystem = new TalonSubsystem(telemetryService);


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        telemetryService.start();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        var entry = NetworkTableInstance.getDefault().getEntry("Trigger");
        new NetworkButton(entry).whenPressed(new TalonRunCommand(talonSubsystem));

        entry = NetworkTableInstance.getDefault().getEntry("TestTrigger");
        new NetworkButton(entry).whenPressed(new InstantCommand() {
            @Override
            public void initialize() {
                logger.info("TestTrigger whenPressed");
            }

            @Override
            public boolean runsWhenDisabled() {
                return true;
            }
        });
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }
}
