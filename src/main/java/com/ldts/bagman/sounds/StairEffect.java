package com.ldts.bagman.sounds;

public class StairEffect extends SoundEffect{
    @Override
    public float setVolume() {
        return 0.8f;
    }

    @Override
    protected String createSoundEffect() {
        return "src/main/resources/Sounds/stair.wav";
    }
}
