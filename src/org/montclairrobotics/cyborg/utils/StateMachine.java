package org.montclairrobotics.cyborg.utils;

public abstract class StateMachine<T> {
	protected T currState;
	protected T nextState;
	
	protected int cycles; 
		
	protected StateMachine(T start) {
		currState = start;
	}

	public void update() {
		boolean loop = true;
		while(loop) {
			calcNextState();
			loop = currState!=nextState;
			if(loop) {
				cycles = 0;
				doTransition();
			}
			currState=nextState;
			doCurrState();
			cycles++;
		}
	}
	
	protected void calcNextState() {};
	protected void doTransition() {};
	protected void doCurrState() {};
	
}