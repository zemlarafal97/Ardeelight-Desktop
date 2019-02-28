public class ScreenResource {
    private ScreenSettings screenSettings;
    private int[] averageRedTab;
    private int[] averageGreenTab;
    private int[] averageBlueTab;

    public ScreenResource(ScreenSettings screenSettings) {
        this.screenSettings = screenSettings;
        this.averageRedTab = new int[this.screenSettings.getTotalLeds()];
        this.averageGreenTab = new int[this.screenSettings.getTotalLeds()];
        this.averageBlueTab = new int[this.screenSettings.getTotalLeds()];
    }

    public void setAverageRedTabByIndex(int value, int index) {
        this.averageRedTab[index] = value;
    }

    public void setAverageGreenTabByIndex(int value, int index) {
        this.averageGreenTab[index] = value;
    }

    public int[] getAverageRedTab() {
        return averageRedTab;
    }

    public int[] getAverageGreenTab() {
        return averageGreenTab;
    }

    public int[] getAverageBlueTab() {
        return averageBlueTab;
    }

    public void setAverageBlueTabByIndex(int value, int index) {
        this.averageBlueTab[index] = value;
    }

}
