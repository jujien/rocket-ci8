package game.star;

import action.*;
import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;

import java.util.Random;

public class CreateStar extends GameObject {

    private Random random;
//    private FrameCounter frameCounter;

    public CreateStar() {
        this.random = new Random();
//        this.frameCounter = new FrameCounter(30);
        this.configAction();
    }

    public void configAction() {
        // Cach 1:
        Action waitAction = new WaitAction(30);

        Action createAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                Star star = GameObjectManager.instance.recycle(Star.class);
                star.position.set(1024, random.nextInt(600));
                star.velocity.set(random.nextInt(3) + 1, 0);
                return true;
            }
        };

        Action sequenceAction = new SequenceAction(waitAction, createAction);

        Action repeatAction = new RepeatActionForever(sequenceAction);

        this.addAction(repeatAction);
    }

//    @Override
//    public void run() {
//        super.run();
//        if (this.frameCounter.run()) {
//            Star star = GameObjectManager.instance.recycle(Star.class);
//            star.position.set(1024, this.random.nextInt(600));
//            star.velocity.set(this.random.nextInt(3) + 1, 0);
//            this.frameCounter.reset();
//        }
//    }
}
