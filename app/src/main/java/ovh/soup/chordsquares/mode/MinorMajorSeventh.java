package ovh.soup.chordsquares.mode;

import ovh.soup.chordsquares.Nature;

public class MinorMajorSeventh extends Nature {
    public MinorMajorSeventh(){
        this. intervals = new Integer[]{2, 1, 2, 2, 1, 3, 1};
    }

    @Override
    public String toString(){
        return "-maj7";
    }
}
