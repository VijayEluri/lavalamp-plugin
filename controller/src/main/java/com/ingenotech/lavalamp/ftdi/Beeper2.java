package com.ingenotech.lavalamp.ftdi;



public class Beeper2 extends Thread implements Beeper {
	
	private DeviceWriter device;
	private long runFor = 2500;
	private volatile boolean run = true;
	
	public Beeper2(DeviceWriter device) {
		this.device = device;
	}
	
	public void setRunFor(long msDuration) {
		this.runFor = msDuration;
	}
	
	public void cancel() {
		this.run = false;
		this.interrupt();
	}
	
	
	public void run() {
		long start = System.currentTimeMillis();
		long delay = 500;

		boolean beep = true;
		do {
			device.setBeep(beep);
			beep = !beep;
			try {
				Thread.sleep(delay);
			} catch (InterruptedException ix) {
			}
		} while (run && (System.currentTimeMillis()-start) < runFor);
		
		device.setBeep(false);
	}
}
