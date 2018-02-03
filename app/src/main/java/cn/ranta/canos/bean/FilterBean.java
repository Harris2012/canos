package cn.ranta.canos.bean;

import cn.ranta.canos.enums.SceneKey;

public class FilterBean {

    private SceneKey sceneKey;

    private String sceneName;

    public FilterBean() {
    }

    public FilterBean(SceneKey sceneKey, String sceneName) {
        this.sceneKey = sceneKey;
        this.sceneName = sceneName;
    }

    public SceneKey getSceneKey() {
        return sceneKey;
    }

    public void setSceneKey(SceneKey sceneKey) {
        this.sceneKey = sceneKey;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
}
