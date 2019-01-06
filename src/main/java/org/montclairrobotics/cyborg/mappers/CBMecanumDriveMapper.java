package org.montclairrobotics.cyborg.mappers;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.devices.CBAxis;
import org.montclairrobotics.cyborg.devices.CBButton;
import org.montclairrobotics.cyborg.devices.CBDeviceID;

public class CBMecanumDriveMapper extends CBTeleOpMapper {

    private CBAxis xAxis;
    private CBAxis yAxis;
    private CBAxis turnAxis;

    private double deadzone = 0.0;
    private CBButton gyroLock=null;

    public CBMecanumDriveMapper(Cyborg robot,
                                CBDeviceID xAxisDeviceID,
                                CBDeviceID yAxisID,
                                CBDeviceID turnAxisDeviceID) {
        super(robot);
        this.xAxis = Cyborg.hardwareAdapter.getAxis(xAxisDeviceID);
        this.yAxis = Cyborg.hardwareAdapter.getAxis(yAxisID);
        this.turnAxis = Cyborg.hardwareAdapter.getAxis(turnAxisDeviceID);
    }

    public CBMecanumDriveMapper setDeadZone(double deadzone) {
        this.deadzone = deadzone;
        return this;
    }

    public CBMecanumDriveMapper setGyroLockButton(CBDeviceID buttonID) {
        this.gyroLock = Cyborg.hardwareAdapter.getButton(buttonID);
        return this;
    }

    @Override
    public void update() {
        double leftStickX  = 0; // x-axis of first stick
        double rightStickX = 0; // x-axis of second stick
        double leftStickY  = 0; // y-axis of first stick


        //TODO: FINISH
    }
}
