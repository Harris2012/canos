package cn.ranta.canos.enums;

public enum RequestCode {

    /*拍照*/
    PhotoCamera(1),

    /*浏览图片*/
    PickImage(2),

    /*预览*/
    Preview(3),

    /*设计场景*/
    DesignScene(4);

        /*裁切图片*/
    //CropImage(69);

    private int requestCode;

    RequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int IntValue() {
        return this.requestCode;
    }

    public static RequestCode ValueOf(int requestCode) {

        RequestCode returnValue = null;

        switch (requestCode) {
            case 1:
                returnValue = PhotoCamera;

                break;
            case 2:
                returnValue = PickImage;

                break;
            case 4:
                returnValue = DesignScene;

                break;
            default:
                break;
        }

        return returnValue;
    }
}