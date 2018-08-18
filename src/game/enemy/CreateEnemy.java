package game.enemy;

import action.*;
import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateEnemy extends GameObject {

    private Random random;

    public CreateEnemy() {
        this.random = new Random();
        this.configAction();
    }

    public void configAction() {
        List<Enemy> enemies = new ArrayList<>();
        Action createAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                Enemy enemy = GameObjectManager.instance.recycle(Enemy.class);
                enemy.position.set(random.nextInt(1024), random.nextInt(600));
                enemy.velocity.set(random.nextInt(3) + 1, random.nextInt(3) + 1);
                enemies.add(enemy);
                return true;
            }
        };

        Action waitAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                enemies.removeIf(enemy -> !enemy.isAlive);
                return enemies.isEmpty();
            }
        };

        this.addAction(
                new SequenceAction(
                        new WaitAction(20),
                        new LimitAction(
                                4,
                                new SequenceAction(
                                      createAction,
                                      waitAction
                                )
                        )
                )
        );
    }
}
