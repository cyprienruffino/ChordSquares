package ovh.cyprienruffino.chordsquares.mode;

import ovh.cyprienruffino.chordsquares.Mode;

public class MelodicMinor extends Mode {
    public MelodicMinor(){
        this.intervals = new Integer[]{2, 1, 2, 2, 2, 2, 1};
    }
    @Override
    public String toString(){
        return "Melodic Minor";
    }
}
