public class ScreenSettings {
    private int screenResoloutionX;
    private int screenResolutionY;
    private int horizontalLeds;
    private int verticalLeds;

    public ScreenSettings(int screenResoloutionX, int screenResolutionY, int horizontalLeds, int verticalLeds) {
        this.screenResoloutionX = screenResoloutionX;
        this.screenResolutionY = screenResolutionY;
        this.horizontalLeds = horizontalLeds;
        this.verticalLeds = verticalLeds;
    }

    public int getScreenResoloutionX() {
        return screenResoloutionX;
    }

    public void setScreenResoloutionX(int screenResoloutionX) {
        this.screenResoloutionX = screenResoloutionX;
    }

    public int getScreenResolutionY() {
        return screenResolutionY;
    }

    public void setScreenResolutionY(int screenResolutionY) {
        this.screenResolutionY = screenResolutionY;
    }

    public int getHorizontalLeds() {
        return horizontalLeds;
    }

    public void setHorizontalLeds(int horizontalLeds) {
        this.horizontalLeds = horizontalLeds;
    }

    public int getVerticalLeds() {
        return verticalLeds;
    }

    public void setVerticalLeds(int verticalLeds) {
        this.verticalLeds = verticalLeds;
    }

    public int getTotalLeds() {
        return (this.horizontalLeds*2 + this.verticalLeds*2);
    }
}
