public class SerialSender {
    private SerialConfiguration serialConfiguration;
    private ScreenResource screenResource;
    private byte[] buffer;
    private int bytesToWrite;

    public SerialSender(SerialConfiguration serialConfiguration, ScreenResource screenResource, int bytesToWrite) {
        this.serialConfiguration = serialConfiguration;
        this.screenResource = screenResource;
        this.bytesToWrite = bytesToWrite;
        buffer = new byte[bytesToWrite];
    }

    public void send() {

        for(int i=0; i<bytesToWrite/3; i++) {
            buffer[i*3+0] = (byte)screenResource.getAverageGreenTab()[i];
            buffer[i*3+1] = (byte)screenResource.getAverageRedTab()[i];
            buffer[i*3+2] = (byte)screenResource.getAverageBlueTab()[i];
        }

        serialConfiguration.getSerialPort().writeBytes(buffer,bytesToWrite);
    }



}



