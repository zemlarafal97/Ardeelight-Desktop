import com.fazecast.jSerialComm.SerialPort;

public class Main {

    public static void main(String[] args) {

        ScreenSettings screenSettings = new ScreenSettings(1920,1080,16,10);
        SerialConfiguration serialConfiguration = new SerialConfiguration(115200,8, SerialPort.ONE_STOP_BIT,SerialPort.NO_PARITY);
        ScreenAnalyzer screenAnalyzer = new ScreenAnalyzer(screenSettings,serialConfiguration);

        screenAnalyzer.start();


    }
}
