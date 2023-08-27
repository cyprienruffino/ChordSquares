package ovh.cyprienruffino.chordsquares.mode;


import ovh.cyprienruffino.chordsquares.Mode;

public class NaturalMinor extends Mode {

    public NaturalMinor(){
        this.intervals = new Integer[]{2, 1, 2, 2, 1, 2, 2};
    }
    @Override
    public String toString(){
        return "Natural Minor";
    }
}
