package org.montclairrobotics.cyborg.simulation;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

// User controlled pulsing is not supported at this time.

/**
 * Class to write digital outputs. This class will write digital outputs. Other devices that are
 * implemented elsewhere will automatically allocate digital inputs and outputs as required.
 */
public class CBSimDigitalOutput extends SendableBase implements CBIDigitalOutput {
    CBSimDigitalOutputData simData;
    DigitalOutput digitalOutput;

    public class CBSimDigitalOutputData {
        public int channel;
        public boolean value;

        public CBSimDigitalOutputData(int channel) {
            this.channel = channel;
        }
    }

    /**
     * Create an instance of a digital output. Create an instance of a digital output given a
     * channel.
     *
     * @param channel the DIO channel to use for the digital output. 0-9 are on-board, 10-25 are on
     *                the MXP
     */
    public CBSimDigitalOutput(int channel) {
        simData = new CBSimDigitalOutputData(channel);
    }

    /**
     * Set the value of a digital output.
     *
     * @param value true is on, off is false
     */
    @Override
    public void set(boolean value) {
        simData.value = value;
    }

    /**
     * Gets the value being output from the Digital Output.
     *
     * @return the state of the digital output.
     */
    @Override
    public boolean get() {
        return simData.value;
    }

    /**
     * Get the GPIO channel number that this object represents.
     *
     * @return The GPIO channel number.
     */
    @Override
    public int getChannel() {
        return simData.channel;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Digital Output");
        builder.addBooleanProperty("Value", this::get, this::set);
    }
}
