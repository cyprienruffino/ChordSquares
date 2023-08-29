package ovh.soup.chordsquares.logic.modes;

import ovh.soup.chordsquares.logic.Nature;

public class MinorMajorSeventh extends Nature {
    public MinorMajorSeventh(){
        this. intervals = new Integer[]{2, 1, 2, 2, 1, 3, 1};
    }

    @Override
    public String toString(){
        return "-maj7";
    }
}
