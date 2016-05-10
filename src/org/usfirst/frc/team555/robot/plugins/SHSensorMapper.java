package org.usfirst.frc.team555.robot.plugins;

import org.montclairrobotics.cyborg.CBGeneralMapper;
import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.devices.CBContourReport;
import org.montclairrobotics.cyborg.devices.CBDashboardChooser;
import org.usfirst.frc.team555.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SHSensorMapper extends CBGeneralMapper {
	Robot robot;
	SHGeneralRequestData grd;
	CBDashboardChooser<Integer> autoChooser;
	CBContourReport contourRpt;

	@SuppressWarnings("unchecked")
	public SHSensorMapper(Robot robot) {
		super(robot);
		
		this.robot = robot;	
		this.grd = (SHGeneralRequestData) Cyborg.generalRequestData;
		
		this.autoChooser = (CBDashboardChooser<Integer>)Cyborg.hardwareAdapter.getDevice(robot.devices.autoSelect);
		this.contourRpt = (CBContourReport)Cyborg.hardwareAdapter.getDevice(robot.devices.xTarget);
	}

	@Override
	public void update() {

		if(autoChooser!=null)
			grd.selectedAuto = autoChooser.getSelected();
		
		grd.targetX = -1;
		if(contourRpt.centerX>=0) {
			grd.targetX = contourRpt.centerX; //Array[contourRpt.largest];
		} else {
			grd.targetX = 200.0;
		}
		SmartDashboard.putNumber("grd.targetX", grd.targetX);
		
	}
	
	

}
 