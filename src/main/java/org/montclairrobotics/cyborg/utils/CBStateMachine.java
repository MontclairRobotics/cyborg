package org.montclairrobotics.cyborg.utils;

import java.util.Date;

public abstract class CBStateMachine<T> {
	protected T currentState;
	protected T nextState;
	protected int cyclesInState; 
	private long stateStartTime;
	/**
	 * Duration of the current state in seconds.
	 */
	protected double secondsInState;
	protected boolean loop;
	protected CBStateMachineLoopMode loopMode = CBStateMachineLoopMode.OneShot;
	
	/**
	 * The state machine loop modes control whether the machine can transition multiple states in a single update
	 * If this state is set to Looping doTransition() and doCurrentState() will be called for each transition, 
	 * but those transitions will be invisible outside of what this class and any overridden methods do. 
	 */
	protected enum CBStateMachineLoopMode {OneShot, Looping};
	
	protected CBStateMachine(T start) {
		setState(start);
	}

	public CBStateMachine<T> setState(T state) {
		currentState = state;
		return this;
	}
	
	public CBStateMachine<T> setLoopMode(CBStateMachineLoopMode loopMode) {
		this.loopMode = loopMode;
		return this;
	}
	
	public boolean isTransition(T from, T to) {
		return (currentState==from && nextState==to);
	}
	
	public boolean isTransitionFrom(T from) {
		return (currentState==from);
	}
	
	public boolean isTransitionTo(T to) {
		return (nextState==to);
	}
	
	public void update() {
		if(stateStartTime==0) {
			stateStartTime = new Date().getTime();
		}
		loop = true;
		while(loop) {
			nextState=currentState;
			secondsInState = (new Date().getTime()-stateStartTime)/1000.0;
			calcNextState();
			loop = currentState!=nextState;
			if(loop) {
				cyclesInState = 0;
				stateStartTime = new Date().getTime();
				secondsInState = 0;
				doTransition();
			}
			currentState=nextState;
			doCurrentState();
			cyclesInState++;
			loop = loop && (loopMode == CBStateMachineLoopMode.Looping);
		}
	}
	
	public CBStateMachine<T> exit()
	{
		loop = false;
		return this;
	}
	
	protected abstract void calcNextState();
	protected abstract void doTransition();
	protected abstract void doCurrentState();
	
}
