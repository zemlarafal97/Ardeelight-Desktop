import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessingThread extends Thread {
    private ScreenSettings sS;
    private ScreenResource screenResource;
    private int threadId;
    private BufferedImage bufferedImage;

    public ImageProcessingThread(ScreenSettings screenSettings, ScreenResource screenResource, int threadId, BufferedImage bufferedImage) {
        this.sS = screenSettings;
        this.screenResource = screenResource;
        this.threadId = threadId;
        this.bufferedImage = bufferedImage;
    }

    @Override
    public void run() {
        int averageRed = 0;
        int averageGreen = 0;
        int averageBlue = 0;

        int divider = 1;

        int xWidth = sS.getScreenResoloutionX() / 20;
        int yWidth = sS.getScreenResolutionY() / 10;


        ///LEWY
        if (threadId < sS.getVerticalLeds()) {
            for (int x = 0; x < xWidth; x++) {
                for (int y = sS.getScreenResolutionY() - threadId * (sS.getScreenResolutionY() / sS.getVerticalLeds()) - 1; y >= sS.getScreenResolutionY() - (sS.getScreenResolutionY() / sS.getVerticalLeds()) * (threadId + 1); y--) {
                    Color color = new Color(bufferedImage.getRGB(x, y));
                    averageRed += color.getRed();
                    averageGreen += color.getGreen();
                    averageBlue += color.getBlue();
                }
            }

            divider = (sS.getScreenResolutionY() / sS.getVerticalLeds()) * xWidth;
            ///GORA
        } else if (threadId < sS.getVerticalLeds() + sS.getHorizontalLeds()) {

            int id = threadId - sS.getVerticalLeds();
            for (int x = (sS.getScreenResoloutionX() / sS.getHorizontalLeds()) * id; x < (sS.getScreenResoloutionX() / sS.getHorizontalLeds() * (id + 1)); x++) {
                for (int y = 0; y < yWidth; y++) {
                    Color color = new Color(bufferedImage.getRGB(x, y));
                    averageRed += color.getRed();
                    averageGreen += color.getGreen();
                    averageBlue += color.getBlue();
                }
            }

            divider = (yWidth * sS.getScreenResoloutionX() / sS.getHorizontalLeds());

            ///PRAWY
        } else if (threadId < sS.getVerticalLeds() * 2 + sS.getHorizontalLeds()) {
            int id = threadId - (sS.getVerticalLeds() + sS.getHorizontalLeds());

            for (int x = sS.getScreenResoloutionX() - xWidth; x < sS.getScreenResoloutionX(); x++) {
                for (int y = id * sS.getScreenResolutionY() / sS.getVerticalLeds(); y < (id + 1) * sS.getScreenResolutionY() / sS.getVerticalLeds(); y++) {
                    Color color = new Color(bufferedImage.getRGB(x, y));
                    averageRed += color.getRed();
                    averageGreen += color.getGreen();
                    averageBlue += color.getBlue();
                }
            }


            divider = (sS.getScreenResolutionY() / sS.getVerticalLeds()) * xWidth;
            ///DOL
        } else {
            int id = threadId - (sS.getVerticalLeds() * 2 + sS.getHorizontalLeds());

            for (int y = sS.getScreenResolutionY() - yWidth; y < sS.getScreenResolutionY(); y++) {
                for (int x = sS.getScreenResoloutionX() - id * (sS.getScreenResoloutionX() / sS.getHorizontalLeds()) - 1; x >= sS.getScreenResoloutionX() - (id + 1) * (sS.getScreenResoloutionX() / sS.getHorizontalLeds()); x--) {
                    Color color = new Color(bufferedImage.getRGB(x, y));
                    averageRed += color.getRed();
                    averageGreen += color.getGreen();
                    averageBlue += color.getBlue();
                }
            }


            divider = (yWidth * sS.getScreenResoloutionX() / sS.getHorizontalLeds());
        }

        averageRed /= divider;
        averageGreen /= divider;
        averageBlue /= divider;


        screenResource.setAverageRedTabByIndex(averageRed, threadId);
        screenResource.setAverageGreenTabByIndex(averageGreen, threadId);
        screenResource.setAverageBlueTabByIndex(averageBlue, threadId);

    }
}
