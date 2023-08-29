package ovh.soup.chordsquares.logic.modes;


import ovh.soup.chordsquares.logic.Nature;

public class HalfDiminished extends Nature {

    public HalfDiminished(){
        this.intervals = new Integer[]{2, 1, 2, 1, 2, 2, 2};
    }
    @Override
    public String toString(){
        return "Ø7 (-7b5)";
    }
}
