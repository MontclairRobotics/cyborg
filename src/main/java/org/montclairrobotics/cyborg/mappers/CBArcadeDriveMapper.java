package org.montclairrobotics.cyborg.mappers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.devices.CBAxis;
import org.montclairrobotics.cyborg.devices.CBButton;
import org.montclairrobotics.cyborg.devices.CBDeviceID;
import org.montclairrobotics.cyborg.devices.CBJoystickIndex;

public class CBArcadeDriveMapper extends CBTeleOpMapper {
	private CBAxis fwdAxis, strAxis, rotAxis;
	private CBButton gyroLock; 
	private double  xScale, yScale, rScale;

	public CBArcadeDriveMapper(Cyborg robot) {
		super(robot);
	}

	public CBArcadeDriveMapper setAxes(CBDeviceID fwdDeviceID, CBDeviceID strDeviceID, CBDeviceID rotDeviceID) {
		// Undefined axes will return 0 deflection. ("InitHeavy/RunLight")
		fwdAxis = Cyborg.hardwareAdapter.getDefaultedAxis(fwdDeviceID);
		strAxis = Cyborg.hardwareAdapter.getDefaultedAxis(strDeviceID);
		rotAxis = Cyborg.hardwareAdapter.getDefaultedAxis(rotDeviceID);
		
		// Force gyroLock to undefined even though we may set it later ("InitHeavy/RunLight")
		gyroLock = new CBButton(CBJoystickIndex.undefined()); 

		// Set default scale
		xScale = 1;
		yScale = 1; 
		rScale = 1;
		
		return this;
	}

	public CBArcadeDriveMapper setGyroLockButton(CBDeviceID buttonDeviceID) {
		this.gyroLock = Cyborg.hardwareAdapter.getDefaultedButton(buttonDeviceID);
		return this;
	}

	public CBArcadeDriveMapper setAxisScales(double xScale, double yScale, double rScale) {
		this.xScale = xScale;
		this.yScale = yScale;
		this.rScale = rScale;
		return this;
	}

	@Override
	public void update() {
		if(Cyborg.requestData.driveData instanceof CBStdDriveRequestData) {
			CBStdDriveRequestData drd = (CBStdDriveRequestData)Cyborg.requestData.driveData;

			drd.active = true;
			drd.direction.setXY(xScale*strAxis.get(), yScale*fwdAxis.get()); 
			drd.rotation = rScale*rotAxis.get(); 
			drd.gyroLockActive = gyroLock.getState();
			SmartDashboard.putNumber("Mapper speed:", drd.direction.getY());
		} else {
			Cyborg.requestData.driveData.active = false; // If we don't know what type of request it is shut down drive
            throw new RuntimeException("Unknown Cyborg.requestData.driveData type in CBArcadeDriveMapper.");
		}
	}
}
