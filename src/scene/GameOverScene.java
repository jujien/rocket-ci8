package scene;

import base.GameObjectManager;
import game.background.Background;
import game.star.CreateStar;

public class GameOverScene implements Scene {
    @Override
    public void init() {
        GameObjectManager.instance.recycle(Background.class);
        GameObjectManager.instance.recycle(CreateStar.class);
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
    }
}
