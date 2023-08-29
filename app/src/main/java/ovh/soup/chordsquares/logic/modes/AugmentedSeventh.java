package ovh.soup.chordsquares.logic.modes;


import ovh.soup.chordsquares.logic.Nature;

public class AugmentedSeventh extends Nature {

    public AugmentedSeventh(){
        this.intervals = new Integer[]{2, 2, 1, 3, 2, 1, 2};
    }
    @Override
    public String toString(){
        return "+7 (7#5)";
    }
}
