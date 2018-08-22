package scene;

import base.GameObjectManager;
import game.Demo;
import game.background.Background;
import game.enemy.Enemy;
import game.enemyfollow.EnemyFollow;
import game.player.Player;
import game.star.Star;
import util.Utils;

import javax.sound.sampled.Clip;

public class GamePlayScene implements Scene {

    private Clip clip;

    @Override
    public void init() {
        GameObjectManager.instance.recycle(Background.class);
        GameObjectManager.instance.recycle(Star.class);
        GameObjectManager.instance.recycle(EnemyFollow.class);
        GameObjectManager.instance.recycle(Enemy.class);
        this.setupPlayer();
        GameObjectManager.instance.recycle(Demo.class);
        this.clip = Utils.loadAudio("resources/audio/shot.wav");
//        this.clip.loop(-1);
//        this.clip.start();
    }

    private void setupPlayer() {
        Player player = GameObjectManager.instance.recycle(Player.class);
        player.position.set(200, 300);
        player.velocity.set(3.5f, 0);
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
        this.clip.stop();
    }
}
