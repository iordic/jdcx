package serial;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import model.DCDevice;
import jssc.SerialPortEventListener;


public class SerialHandler implements SerialPortEventListener {

	SerialPort port;
	DCDevice device;
    boolean connected;
    
    // Constants serial
	public static final int BAUDRATE = 115200;
	public static final int DATABITS = 8;
	public static final int STOP_BITS = 1;
	public static final int PARITY = 0; // None
	
	public SerialHandler(String portName) {
        port = new SerialPort(portName);
        connected = false;
	}
	
	public void sendCommand(char command, String value) {
		
	}
    
    public void connect() {
        try {
            port.openPort();
            port.setParams(BAUDRATE, DATABITS, STOP_BITS, PARITY);
            
            // first send disconnect prevently
            port.writeString("W\r\n");
            port.writeString("Q\r\n");
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect() {
        try {
            port.writeString("W\r\n");
            port.closePort();
            connected = false;
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isConnected() {
        return connected;
    }
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		String response;
		if (event.isRXCHAR()) {
			try {
				Thread.sleep(50);	// wait to getting all together
				response = port.readString();
				if (response != null) {
					response = response.replace("\n", "").replace("\r", "");
					System.out.print("Received: " + response + " - ");
					if (response.contains(DCDevice.DC_580)) {
                        device = new DCDevice(DCDevice.DC_580);
                        connected = true;
						System.out.println("Conection opened to DC_580!");
					} else if (response.contains(DCDevice.DC_6006L)) {
                        device = new DCDevice(DCDevice.DC_6006L);
                        connected = true;
						System.out.println("Conection opened to DC_6006L!");
					} else {
						System.out.println("Unknown operation");
					}
				}
			} catch (SerialPortException e) {
				e.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		}
	}    
}
