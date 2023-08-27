package ovh.cyprienruffino.chordsquares.mode;

import ovh.cyprienruffino.chordsquares.Mode;

public class HarmonicMinor extends Mode {
    public HarmonicMinor(){
        this. intervals = new Integer[]{2, 1, 2, 2, 1, 3, 1};
    }

    @Override
    public String toString(){
        return "Harmonic Minor";
    }
}
