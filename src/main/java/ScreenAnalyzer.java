import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenAnalyzer {
    private ScreenSettings screenSettings;
    private SerialConfiguration serialConfiguration;
    private BufferedImage bufferedImage;
    private ImageProcessingThread[] threads;
    private ScreenResource screenResource;
    private SerialSender serialSender;
    private Robot robot;
    private Rectangle rectangle;

    public ScreenAnalyzer(ScreenSettings screenSettings, SerialConfiguration serialConfiguration) {
        this.screenSettings = screenSettings;
        this.serialConfiguration = serialConfiguration;
        threads = new ImageProcessingThread[screenSettings.getVerticalLeds() * 2 + screenSettings.getHorizontalLeds() * 2];
        screenResource = new ScreenResource(screenSettings);
        serialSender = new SerialSender(serialConfiguration, screenResource, screenSettings.getTotalLeds() * 3);
        rectangle = new Rectangle(0, 0, screenSettings.getScreenResoloutionX(), screenSettings.getScreenResolutionY());
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        serialConfiguration.getSerialPort().openPort();

        for (int i = 3; i >= 0; i--) {
            System.out.println(i + "s to start...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Screen analyzer started...");

        while (true) {
            double start = System.nanoTime();

            //Infinite loop

            bufferedImage = robot.createScreenCapture(rectangle);

            //Tworzymy watki idac: LEWY, GORA, PRAWY, DOL
            for (int i = 0; i < screenSettings.getTotalLeds(); i++) {
                threads[i] = new ImageProcessingThread(screenSettings, screenResource, i, bufferedImage);
            }

            for (int i = 0; i < screenSettings.getTotalLeds(); i++) {
                threads[i].start();
            }

            for (int i = 0; i < screenSettings.getTotalLeds(); i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            double end = System.nanoTime();

            System.out.println("FPS: " + 1.0/((end-start)/1000000000.0));

            serialSender.send();

        }

    }
}