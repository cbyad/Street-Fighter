package com.cps.services.tech;

public class FightCharDecorator implements FightChar{
	
	private FightChar delegate ;
	
	public FightCharDecorator(FightChar delagate) {
		this.delegate=delagate;
	}
	
	
	@Override
	public boolean isBlocking() {
		return delegate.isBlocking();
	}

	@Override
	public boolean isBlockstunne() {
		return delegate.isBlockstunne();
	}

	@Override
	public boolean isHitstunned() {
		return delegate.isHitstunned();
	}

	@Override
	public boolean isTeching() {
		return delegate.isTeching();
	}

	@Override
	public Tech tech() {
		return delegate.tech();
	}

	@Override
	public boolean techFrame() {
		return delegate.techFrame();
	}

	@Override
	public boolean techHasAlreadyHit() {
		return delegate.techHasAlreadyHit();
	}

	@Override
	public void startTech(Tech tech) {
		delegate.startTech(tech);
	}

}
