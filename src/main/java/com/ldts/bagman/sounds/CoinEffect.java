package com.ldts.bagman.sounds;

public class CoinEffect extends SoundEffect{
    @Override
    public float setVolume() {
        return 1.0f;
    }

    @Override
    protected String createSoundEffect() {
        return "src/main/resources/Sounds/coin.wav";
    }
}
