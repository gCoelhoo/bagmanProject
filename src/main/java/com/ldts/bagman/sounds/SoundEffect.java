package com.ldts.bagman.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public abstract class SoundEffect {
    private String soundPath;
    private Clip clip;
    private float volume;

    public SoundEffect() {
        this.soundPath = createSoundEffect();
        if (setVolume() < 0f || setVolume() > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        else
            this.volume = setVolume();
    }

    private void setFile(){
        try {
            File file = new File(this.soundPath);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(20f * (float) Math.log10(volume));
        }
        catch (Exception e) {

        }
    }

    public void play() {
        this.setFile();
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        this.setFile();
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public abstract float setVolume();
    protected abstract String createSoundEffect();

}
