package cn.ranta.canos.repository.entity;

public class BgImageEntity {

    /**
     * Key
     */
    private int imageKey;

    /**
     * 背景路径
     */
    private String imagePath;

    public int getImageKey() {
        return imageKey;
    }

    public void setImageKey(int imageKey) {
        this.imageKey = imageKey;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
