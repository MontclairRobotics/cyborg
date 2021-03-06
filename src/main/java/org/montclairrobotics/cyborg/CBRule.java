package org.montclairrobotics.cyborg;

import org.montclairrobotics.cyborg.utils.CBModule;

/**
 * Base class of all Rule behaviors. These are meant to
 * "pre-screen" requtest data before they are seen by
 * behaviors.
 */
public abstract class CBRule extends CBModule {

	public CBRule(Cyborg robot) {
		super(robot);
	}

	/**
	 * Called in every update period.
	 */
	public void update() {
	}
}
