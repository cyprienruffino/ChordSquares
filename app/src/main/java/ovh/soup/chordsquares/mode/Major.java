package ovh.soup.chordsquares.mode;

import ovh.soup.chordsquares.Nature;

public class Major extends Nature {

    public Major(){
        this.intervals= new Integer[]{2, 2, 1, 2, 2, 2, 1};
    }
    @Override
    public String toString(){
        return "Maj7";
    }
}
