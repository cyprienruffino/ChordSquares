package ovh.cyprienruffino.chordsquares.mode;

import ovh.cyprienruffino.chordsquares.Mode;

public class Major extends Mode {

    public Major(){
        this.intervals= new Integer[]{2, 2, 1, 2, 2, 2, 1};
    }
    @Override
    public String toString(){
        return "Major";
    }
}
