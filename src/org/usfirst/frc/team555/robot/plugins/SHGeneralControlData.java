package org.usfirst.frc.team555.robot.plugins;

import org.montclairrobotics.cyborg.CBGeneralControlData;
import org.montclairrobotics.cyborg.utils.CBTriState;

public class SHGeneralControlData extends CBGeneralControlData {

	public SHGeneralControlData() {
	}
	
	public CBTriState ArmDown	= new CBTriState();
	public CBTriState HalfUp	= new CBTriState();
	public CBTriState ShootOut	= new CBTriState();
	public double SpinSpeed;
}
