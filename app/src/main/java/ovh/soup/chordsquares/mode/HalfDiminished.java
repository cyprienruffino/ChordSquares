package ovh.soup.chordsquares.mode;


import ovh.soup.chordsquares.Nature;

public class HalfDiminished extends Nature {

    public HalfDiminished(){
        this.intervals = new Integer[]{2, 1, 2, 1, 2, 2, 2};
    }
    @Override
    public String toString(){
        return "Ø7 (-7b5)";
    }
}
