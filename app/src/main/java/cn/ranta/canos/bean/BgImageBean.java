package cn.ranta.canos.bean;

public class BgImageBean {

    private int imageKey;

    private String imagePath;

    public BgImageBean() {
    }

    public BgImageBean(int imageKey, String imagePath) {
        this.imageKey = imageKey;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImageKey() {
        return imageKey;
    }

    public void setImageKey(int imageKey) {
        this.imageKey = imageKey;
    }
}
