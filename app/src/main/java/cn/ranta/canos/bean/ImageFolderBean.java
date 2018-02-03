package cn.ranta.canos.bean;

import java.io.Serializable;

/**
 * 图片所在文件夹类
 */
public class ImageFolderBean implements Serializable {

    private String imagePath;

    private int pictureCount;

    private String name;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPictureCount() {
        return pictureCount;
    }

    public void setPictureCount(int pictureCount) {
        this.pictureCount = pictureCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
