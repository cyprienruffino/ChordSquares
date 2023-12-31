package ovh.soup.chordsquares.logic;

import java.util.ArrayList;
import java.util.Arrays;



public class Note{
    public enum RawNote {
        A,
        B,
        C,
        D,
        E,
        F,
        G
    }
    public enum Alteration {
        NATURAL,
        FLAT,
        SHARP,
        DOUBLEFLAT,
        DOUBLESHARP;

        @Override
        public String toString() {
            switch (this.ordinal()) {
                case 0:
                    return "";
                case 1:
                    return "b";
                case 2:
                    return "#";
                case 3:
                    return "bb";
                case 4:
                    return "##";
                default:
                    return "";
            }
        }
        public Integer value(){
            switch (this.ordinal()) {
                case 0:
                    return 0;
                case 1:
                    return -1;
                case 2:
                    return 1;
                case 3:
                    return -2;
                case 4:
                    return 2;
                default:
                    return 0;
            }
        }
    }

    public static Alteration alterationFromString(String string){
        switch (string) {
            case "":
                return Alteration.NATURAL;
            case "b":
                return Alteration.FLAT;
            case "bb":
                return Alteration.DOUBLEFLAT;
            case "#":
                return Alteration.SHARP;
            case "##":
                return Alteration.DOUBLESHARP;
            default:
                return null;
        }
    }
    public static ArrayList<RawNote> notes = new ArrayList<>(Arrays.asList(
            RawNote.C, RawNote.D, RawNote.E, RawNote.F, RawNote.G, RawNote.A, RawNote.B));

    public static final Note[] authorizedNotes = {
            new Note("Ab"),
            new Note("A"),
            new Note ("Bb"),
            new Note("B"),
            new Note("C"),
            new Note("C#"),
            new Note("D"),
            new Note("Eb"),
            new Note("E"),
            new Note("F"),
            new Note("F#"),
            new Note("G")
    };
    
    public RawNote note;
    public Alteration alteration;

    public Note(RawNote note, Alteration alteration){
        this.note = note;
        this.alteration = alteration;
    }

    public Note(String noteString){
        this.note = RawNote.valueOf(noteString.substring(0,1));
        this.alteration = alterationFromString(noteString.substring(1));
    }

    @Override
    public String toString() {
        return note.toString() + alteration.toString();
    }
}