package com.ldts.bagman.sounds;

public class Soundtrack extends SoundEffect{
    @Override
    public float setVolume() {
        return 0.1f;
    }

    @Override
    protected String createSoundEffect() {
        return "src/main/resources/Sounds/soundtrack.wav";
    }
}
