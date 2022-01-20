/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author iordic
 */
public class DCDevice {
	private String model;
	private int voltage;
	private int current;
	private int power;
	private int tempUnits;
	private int temperature;
	private int cv_cc;		// mode cv or cc
	private int on_off;		// switch on/off
	private int fault;		// trigger associated
	private int voltageSet;
	private int currentSet;	
	private int vProtection;
	private int cProtection;
	private int pProtection;
	private int checkedTimeProtection;
	private int THourProtection;
	private int TMinuteProtection;
	private int TSecondProtection;
	
	final static public int MAX_VOLTAGE = 3200;	// 32,00 V
	final static public int MAX_CURRENT = 5000;	// 5,000 A
	final static public int MAX_POWER = 8000;		// 80,00 W
	final static public String DC_6006L = "KB";
	final static public String DC_580 = "MB";
	
    
    final static int OK = 0;    // (0) Normal status
	final static int OVP = 1;   // (1) Overvoltage protection
	final static int OCP = 2;   // (2) Overcurrent protection
	final static int OPP = 3;   // (3) Overpower protection
	final static int OTP = 4;   // (4) Overtemperature protection
	final static int OHP = 5;   // (5) Overtime protection
    
	private enum modes {
		CV,		// (0) Constant voltage
		CC		// (1) Constant current
	}
	
	public DCDevice(String model) {
		this.model = model;
		voltage = 0;
		current = 0;
		power = 0;
		tempUnits = 0;
		temperature = 0;
		cv_cc = 0;
		on_off = 0;
		fault = 0;
		voltageSet = 0;
		currentSet = 0;	
		vProtection = 0;
		cProtection = 0;
		pProtection = 0;
		checkedTimeProtection = 0;
		THourProtection = 0;
		TMinuteProtection = 0;
		TSecondProtection = 0;
	}

    public String getDevice() {
        if (model.equals(DC_580))
            return "DC-580";
        else if (model.equals(DC_6006L))
            return "DC-6006L";
        else
            return "unknown";
    }
    	
	public String getModel() {
		return model;
	}
	
    
    
    public void translateDCInfo(String info) {
        String [] rawValues = info.split("A");
        // TODO: identify format by number of elements
        int nValues = rawValues.length;
        if (nValues < 7) {
            System.out.println("Invalid state. Aborted.");
            return;
        }
        // First 7 values are equal for all cases
        this.voltage = Integer.parseInt(rawValues[0]);
        this.current = Integer.parseInt(rawValues[1]);
        this.power = Integer.parseInt(rawValues[2]);
        this.tempUnits = Integer.parseInt(rawValues[3]);
        this.temperature = Integer.parseInt(rawValues[4]);
        this.cv_cc = Integer.parseInt(rawValues[5]);
        this.fault = Integer.parseInt(rawValues[6]);
        switch (nValues) {
            case 7:
                break;
            case 9:
                this.voltageSet = Integer.parseInt(rawValues[7]);
                this.currentSet = Integer.parseInt(rawValues[8]);
                break;
            case 15:
                this.vProtection = Integer.parseInt(rawValues[7]);
                this.cProtection = Integer.parseInt(rawValues[8]);
                this.pProtection = Integer.parseInt(rawValues[9]);
                this.checkedTimeProtection = Integer.parseInt(rawValues[10]);
                this.THourProtection = Integer.parseInt(rawValues[11]);
                this.TMinuteProtection = Integer.parseInt(rawValues[12]);
                this.TSecondProtection = Integer.parseInt(rawValues[13]);
                this.on_off = Integer.parseInt(rawValues[14]);
                break;
            case 17:
                this.voltageSet = Integer.parseInt(rawValues[7]);
                this.currentSet = Integer.parseInt(rawValues[8]);
                this.vProtection = Integer.parseInt(rawValues[9]);
                this.cProtection = Integer.parseInt(rawValues[10]);
                this.pProtection = Integer.parseInt(rawValues[11]);
                this.checkedTimeProtection = Integer.parseInt(rawValues[12]);
                this.THourProtection = Integer.parseInt(rawValues[13]);
                this.TMinuteProtection = Integer.parseInt(rawValues[14]);
                this.TSecondProtection = Integer.parseInt(rawValues[15]);
                this.on_off = Integer.parseInt(rawValues[16]);
                break;
            default:
                System.out.println("Invalid state. Aborted.");
        }
    }

    public int getVoltage() {
        return voltage;
    }

    public int getCurrent() {
        return current;
    }

    public int getPower() {
        return power;
    }

    public String getTempUnits() {
        switch (tempUnits) {
            case 0:
                return "ºC";
            case 1:
                return "ºF";
            default:
                return "ºC";
        }
    }

    public int getTemperature() {
        return temperature;
    }

    public String getCvCc() {
        return cv_cc == 0 ? "CV" : "CC";
    }

    public String getOnOff() {
        return on_off > 0 ? "ON" : "OFF";
    }

    public String getFault() {
        switch (fault) {
            case OK:
                return "OK";
            case OVP:
                return "OVP";
            case OCP:
                return "OCP";
            case OPP:
                return "OPP";
            case OTP:
                return "OTP";
            case OHP:
                return "OHP";
            default:
                return "DDD";       
        }
    }

    public int getVoltageSet() {
        return voltageSet;
    }

    public int getCurrentSet() {
        return currentSet;
    }

    public int getvProtection() {
        return vProtection;
    }

    public int getcProtection() {
        return cProtection;
    }

    public int getpProtection() {
        return pProtection;
    }

    public int getCheckedTimeProtection() {
        return checkedTimeProtection;
    }

    public int getTHourProtection() {
        return THourProtection;
    }

    public int getTMinuteProtection() {
        return TMinuteProtection;
    }

    public int getTSecondProtection() {
        return TSecondProtection;
    }

    public void setOnOff(int on_off) {
        this.on_off = on_off;
    }
    
    
}