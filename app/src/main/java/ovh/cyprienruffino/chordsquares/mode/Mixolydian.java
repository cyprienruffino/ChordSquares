package ovh.cyprienruffino.chordsquares.mode;

import ovh.cyprienruffino.chordsquares.Mode;

public class Mixolydian extends Mode {

    public Mixolydian(){
        this.intervals= new Integer[]{2, 2, 1, 2, 2, 1, 2};
    }
    @Override
    public String toString(){
        return "Mixolydian";
    }
}
