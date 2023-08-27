package ovh.cyprienruffino.chordsquares;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Chord {

    public static ArrayList<Integer> natural_intervals = new ArrayList<>(Arrays.asList(
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

    public Mode mode;

    private Chord(Mode mode){
        this.mode = mode;
    }

    public static Chord fromRoot(Note root, Mode mode){
        Chord chord = new Chord(mode);
        chord.root = root;
        chord.third = Chord.compute_note(root, 1, 3, mode);
        chord.fifth = Chord.compute_note(root, 1, 5, mode);
        chord.seventh = Chord.compute_note(root, 1, 7, mode);
        return chord;
    }

    public static Chord fromThird(Note third, Mode mode){
        Chord chord = new Chord(mode);
        chord.root = Chord.compute_note(third, 3, 1, mode);
        chord.third = third;
        chord.fifth = Chord.compute_note(third, 3, 5, mode);
        chord.seventh = Chord.compute_note(third, 3, 7, mode);
        return chord;
    }


    public static Chord fromFifth(Note fifth, Mode mode){
        Chord chord = new Chord(mode);
        chord.root = Chord.compute_note(fifth, 5, 1, mode);
        chord.third = Chord.compute_note(fifth, 5, 3, mode);
        chord.fifth = fifth;
        chord.seventh = Chord.compute_note(fifth, 5, 7, mode);
        return chord;
    }

    public static Chord fromSeventh(Note seventh, Mode mode){
        Chord chord = new Chord(mode);
        chord.root = Chord.compute_note(seventh, 7, 1, mode);
        chord.third = Chord.compute_note(seventh, 7, 3, mode);
        chord.fifth = Chord.compute_note(seventh, 7, 5, mode);
        chord.seventh = seventh;
        return chord;
    }


    public static Note compute_note(Note base, int degree, int target, Mode mode){
        int root_position = Utils.mod(Note.notes.indexOf(base.note) - degree + 1 , Note.notes.size());
        Note.RawNote target_note = Note.notes.get(Utils.mod(root_position +  target - 1, Note.notes.size()));

        int interval = 0;
        for (int i=0; i<(target-1); i++){
            interval += mode.intervals[Utils.mod(i, mode.intervals.length)];
        }

        int unmodified_interval = 0;
        for (int i=0; i<(target-1); i++){
            unmodified_interval += Chord.natural_intervals.get(Utils.mod(root_position + i, Chord.natural_intervals.size()));
        }

        return new Note(
                target_note,
                Chord.alterations.get(interval - unmodified_interval + base.alteration.value())
        );
    }

}
