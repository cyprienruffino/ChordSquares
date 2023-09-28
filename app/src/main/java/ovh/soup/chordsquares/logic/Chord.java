package ovh.soup.chordsquares.logic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Chord {

    public static ArrayList<Integer> naturalIntervals = new ArrayList<>(Arrays.asList(
            2, 2, 1, 2, 2, 2, 1));

    public static Map<Integer, Note.Alteration> alterations;
    static {
        alterations = new HashMap<>();
        alterations.put(-2, Note.Alteration.DOUBLEFLAT);
        alterations.put(-1, Note.Alteration.FLAT);
        alterations.put(0, Note.Alteration.NATURAL);
        alterations.put(1, Note.Alteration.SHARP);
        alterations.put(2, Note.Alteration.DOUBLESHARP);
        alterations = Collections.unmodifiableMap(alterations);
    }


    public Note root;
    public Note third;
    public Note fifth;
    public Note seventh;

    public Nature nature;

    private Chord(Nature nature){
        this.nature = nature;
    }

    public static Chord fromRoot(Note root, Nature nature){
        Chord chord = new Chord(nature);
        chord.root = root;
        chord.third = Chord.computeNote(root, 1, 3, nature);
        chord.fifth = Chord.computeNote(root, 1, 5, nature);
        chord.seventh = Chord.computeNote(root, 1, 7, nature);
        return chord;
    }

    public static Chord fromThird(Note third, Nature nature){
        Chord chord = new Chord(nature);
        chord.root = Chord.computeNote(third, 3, 8, nature);
        chord.third = third;
        chord.fifth = Chord.computeNote(third, 3, 12, nature);
        chord.seventh = Chord.computeNote(third, 3, 7, nature);
        return chord;
    }

    public static Chord fromFifth(Note fifth, Nature nature){
        Chord chord = new Chord(nature);
        chord.root = Chord.computeNote(fifth, 5, 8, nature);
        chord.third = Chord.computeNote(fifth, 5, 10, nature);
        chord.fifth = fifth;
        chord.seventh = Chord.computeNote(fifth, 5, 7, nature);
        return chord;
    }

    public static Chord fromSeventh(Note seventh, Nature nature){
        Chord chord = new Chord(nature);
        chord.root = Chord.computeNote(seventh, 7, 8, nature);
        chord.third = Chord.computeNote(seventh, 7, 10, nature);
        chord.fifth = Chord.computeNote(seventh, 7, 12, nature);
        chord.seventh = seventh;
        return chord;
    }

    public static Note computeNote(Note base, int degree, int target, Nature nature){
        Note.RawNote targetRawNote = Note.notes.get(Utils.mod(Note.notes.indexOf(base.note) +  target - degree, Note.notes.size()));

        if (target < degree)
            target += nature.intervals.length;

        int interval = 0;
        int naturalInterval = 0;
        for (int i=0; i<target-degree; i++){
            interval += nature.intervals[Utils.mod(i + degree - 1, nature.intervals.length)];
            naturalInterval += Chord.naturalIntervals.get(Utils.mod(i + Note.notes.indexOf(base.note), Chord.naturalIntervals.size()));
        }

        return new Note(
                targetRawNote,
                Chord.alterations.get(interval - naturalInterval + base.alteration.value())
        );
    }

}
