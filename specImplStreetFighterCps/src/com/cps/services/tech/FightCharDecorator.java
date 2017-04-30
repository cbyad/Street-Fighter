package com.cps.services.tech;

public class FightCharDecorator implements FightChar{
	
	private FightChar delegate ;
	
	public FightCharDecorator(FightChar delagate) {
		this.delegate=delagate;
	}
	
	
	public boolean isBlocking() {
		return delegate.isBlocking();
	}

	public boolean isBlockstunne() {
		return delegate.isBlockstunne();
	}

	public boolean isHitstunned() {
		return delegate.isHitstunned();
	}

	public boolean isTeching() {
		return delegate.isTeching();
	}

	public Tech tech() {
		return delegate.tech();
	}

	public boolean techFrame() {
		return delegate.techFrame();
	}

	public boolean techHasAlreadyHit() {
		return delegate.techHasAlreadyHit();
	}

	public void startTech(Tech tech) {
		delegate.startTech(tech);
	}

}
