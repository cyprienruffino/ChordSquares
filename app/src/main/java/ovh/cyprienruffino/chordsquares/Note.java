package ovh.cyprienruffino.chordsquares;

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
                    return null;
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
                    return null;
            }
        }
    }

    public static ArrayList<RawNote> notes = new ArrayList<>(Arrays.asList(
            RawNote.C, RawNote.D, RawNote.E, RawNote.F, RawNote.G, RawNote.A, RawNote.B));

    public RawNote note;
    public Alteration alteration;

    public Note(RawNote note, Alteration alteration){
        this.note = note;
        this.alteration = alteration;
    }

    @Override
    public String toString() {
        return note.toString() + alteration.toString();
    }
}