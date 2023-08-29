package ovh.soup.chordsquares.logic.modes;

import ovh.soup.chordsquares.logic.Nature;

public class Seventh extends Nature {

    public Seventh(){
        this.intervals= new Integer[]{2, 2, 1, 2, 2, 1, 2};
    }
    @Override
    public String toString(){
        return "7";
    }
}
