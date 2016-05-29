package org.montclairrobotics.cyborg.utils;

import java.util.Date;

public abstract class CBStateMachine<T> {
	protected T currentState;
	protected T nextState;
	protected int cycles; 
	private long stateStartTime;
	protected double stateDuration;
	protected boolean loop;
	protected CBStateMachineLoopMode loopMode = CBStateMachineLoopMode.OneShot;
	
	enum CBStateMachineLoopMode {OneShot, Looping};
	
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
	
	public void update() {
		if(stateStartTime==0) {
			stateStartTime = new Date().getTime();
		}
		loop = true;
		while(loop) {
			nextState=currentState;
			calcNextState();
			loop = currentState!=nextState;
			if(loop) {
				cycles = 0;
				stateStartTime = new Date().getTime();
				doTransition();
			}
			currentState=nextState;
			stateDuration = (new Date().getTime()-stateStartTime)/1000.0;
			doCurrentState();
			cycles++;
			loop = loop && (loopMode == CBStateMachineLoopMode.Looping);
		}
	}
	
	public CBStateMachine<T> exit()
	{
		loop = false;
		return this;
	}
	
	protected void calcNextState() {};
	protected void doTransition() {};
	protected void doCurrentState() {};
	
}
