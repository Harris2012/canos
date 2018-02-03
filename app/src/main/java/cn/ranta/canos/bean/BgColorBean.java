package cn.ranta.canos.bean;

public class BgColorBean {

    private int colorKey;

    private int colorValue;

    public BgColorBean() {
    }

    public BgColorBean(int colorKey, int colorValue) {
        this.colorKey = colorKey;
        this.colorValue = colorValue;
    }

    public int getColorKey() {
        return colorKey;
    }

    public void setColorKey(int colorKey) {
        this.colorKey = colorKey;
    }

    public int getColorValue() {
        return colorValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }
}
