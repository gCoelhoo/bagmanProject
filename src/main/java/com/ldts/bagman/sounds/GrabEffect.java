package com.ldts.bagman.sounds;

public class GrabEffect extends SoundEffect{

    @Override
    public float setVolume() {
        return 1.0f;
    }

    @Override
    protected String createSoundEffect() {
        return "src/main/resources/Sounds/grab.wav";
    }
}
