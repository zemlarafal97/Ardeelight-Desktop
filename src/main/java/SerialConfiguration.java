import com.fazecast.jSerialComm.SerialPort;


public class SerialConfiguration {
    private SerialPort serialPort;

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public SerialConfiguration(int baudRate, int numDataBits, int numStopBits, int setParity) {
        if (SerialPort.getCommPorts().length <= 0) {
            System.out.println("Error: Nie poddlaczono Arduino do komputera!");
            System.exit(1);
        } else {
            serialPort = SerialPort.getCommPorts()[0];
            serialPort.setBaudRate(baudRate);
            serialPort.setNumDataBits(numDataBits);
            serialPort.setNumStopBits(numStopBits);
            serialPort.setParity(setParity);
        }

    }
}
