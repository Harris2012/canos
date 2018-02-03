package cn.ranta.canos.repository.entity;

public class SceneEntity {

    /**
     * 图片个数
     */
    private int imageCount;

    /**
     * 路径数据
     * 1,2;3,4;5,6;7,8
     */
    private String pathData;

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String getPathData() {
        return pathData;
    }

    public void setPathData(String pathData) {
        this.pathData = pathData;
    }

}
