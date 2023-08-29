package ovh.soup.chordsquares.mode;


import ovh.soup.chordsquares.Nature;

public class Diminished extends Nature {

    public Diminished(){
        this.intervals = new Integer[]{2, 1, 2, 1, 2, 1, 3};
    }
    @Override
    public String toString(){
        return "o7";
    }
}
