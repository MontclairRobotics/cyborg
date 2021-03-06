package org.montclairrobotics.cyborg.behaviors;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.data.CBStdDriveControlData;
import org.montclairrobotics.cyborg.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.utils.*;

public class CBStdDriveBehavior extends CBBehavior {
	CBEdgeTrigger gyroLockState;
	CBErrorCorrection gyroLockTracker=null;

	CBStdDriveRequestData drd;
	CBStdDriveControlData dcd;

	public CBStdDriveBehavior(Cyborg robot) {
		super(robot);
		drd = (CBStdDriveRequestData)Cyborg.requestData.driveData;
		dcd = (CBStdDriveControlData)Cyborg.controlData.driveData;
		gyroLockState = new CBEdgeTrigger();
	}

	public CBStdDriveBehavior setGyroLockTracker(CBErrorCorrection pid) {
		this.gyroLockTracker = pid;
		return this;
	}

	@Override
	public void update() {
		super.update();

		dcd.active = drd.active;
		if(dcd.active) {
			dcd.direction.copy(drd.direction);
			dcd.rotation = drd.rotation;

			gyroLockState.update(drd.gyroLockActive);
			if(gyroLockTracker!=null) {
				if(gyroLockState.getRisingEdge()) gyroLockTracker.setTarget(drd.gyroLockValue);
				if(gyroLockState.getState()) dcd.rotation = gyroLockTracker.update(drd.gyroLockValue);
			}

			//
			// Turn off request.active to indicate that command was handled. 
			// This will prevent re-processing a given request. For example
			// Autonomous may only issue drive requests periodically.
			//  
			drd.active = false;
		}
	}
}
