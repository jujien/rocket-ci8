package game.player;

import base.Attribute;
import base.FrameCounter;
import base.GameObjectManager;
import util.Utils;

import javax.sound.sampled.Clip;

public class PlayerShoot implements Attribute<Player> {

    private Clip clip = Utils.loadAudio("resources/audio/shot.wav");

    private FrameCounter frameCounter = new FrameCounter(40);

    @Override
    public void run(Player gameObject) {
        if (this.frameCounter.run()) {
            BulletPlayer bulletPlayer = new BulletPlayer();
            bulletPlayer.position.set(gameObject.position);
            bulletPlayer.velocity.set(gameObject.velocity.copy()).multiply(1.5f);
            this.clip.loop(1);
            this.clip.start();
            GameObjectManager.instance.add(bulletPlayer);

            this.frameCounter.reset();
        }
    }
}
