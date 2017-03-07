package com.kekman.game.Entities.Definitions;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Random;

/**
 * Created by bluedragonfly on 07/03/17.
 */

public class Ghost extends Enemy {
    protected Ghost(final String name, final TextureAtlas atlas, int tileX, int tileY) {super(name,atlas, tileX, tileY);}

    private float timer = 1.0f;
    private float currentTimer = 0.0f;

    @Override
    public void act(float delta){
        currentTimer += delta;
        if(currentTimer > timer){
            currentTimer = 0.0f;
            Random r = new Random();
            int Low = 0;
            int High = 4;
            int rand = r.nextInt(High-Low) + Low;
            switch(rand){
                case 0: {
                    setDirectionUp();
                    break;
                }
                case 1: {
                    setDirectionDown();
                    break;

                }
                case 2: {
                    setDirectionLeft();
                    break;
                }
                case 3: {
                    setDirectionRight();
                    break;
                }
            }
        }

        super.act(delta);
    }
}
