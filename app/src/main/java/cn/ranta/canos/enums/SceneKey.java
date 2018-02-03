package cn.ranta.canos.enums;

/**
 * 场景
 */
public enum SceneKey {

    /**
     * 灰化
     */
    Gray(1),

    Mosatic(2),

    Lomo(3),

    Nostalgic(4),

    Comics(5),

    Brown(6),

    SketchPencil(7);

    private int sceneKey;

    SceneKey(int sceneKey) {
        this.sceneKey = sceneKey;
    }

    public int IntValue() {
        return this.sceneKey;
    }

    public static SceneKey ValueOf(int sceneKey) {

        SceneKey returnValue = null;

        switch (sceneKey) {
            case 1:
                returnValue = Gray;

                break;
            case 2:
                returnValue = Mosatic;

                break;
            case 3:
                returnValue = Lomo;

                break;
            case 4:
                returnValue = Comics;

                break;
            case 5:
                returnValue = Mosatic;

                break;
            case 6:
                returnValue = Brown;

                break;
            case 7:
                returnValue = SketchPencil;

                break;
            default:
                break;
        }

        return returnValue;
    }
}
