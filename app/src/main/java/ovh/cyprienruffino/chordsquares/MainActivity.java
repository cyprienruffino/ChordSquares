package ovh.cyprienruffino.chordsquares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import ovh.cyprienruffino.chordsquares.mode.Major;

public class MainActivity extends AppCompatActivity {

    private  Spinner[][] spinnerGrid;
    private ArrayAdapter<String> adapter;
    private static ArrayList<String> buildNoteStringArray(){
        ArrayList<String> noteStringArray = new ArrayList<>();
        noteStringArray.add("");
        for (Note.RawNote note: Note.RawNote.values()){
            for (Note.Alteration alteration: Note.Alteration.values()){
                noteStringArray.add(note.toString() + alteration.toString());
            }
        }
        return noteStringArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.spinnerGrid = new Spinner[][]{
                {
                        findViewById(R.id.spinner11),
                        findViewById(R.id.spinner13),
                        findViewById(R.id.spinner15),
                        findViewById(R.id.spinner17),
                },
                {
                        findViewById(R.id.spinner31),
                        findViewById(R.id.spinner33),
                        findViewById(R.id.spinner35),
                        findViewById(R.id.spinner37),
                },
                {
                        findViewById(R.id.spinner51),
                        findViewById(R.id.spinner53),
                        findViewById(R.id.spinner55),
                        findViewById(R.id.spinner57),
                },
                {
                        findViewById(R.id.spinner71),
                        findViewById(R.id.spinner73),
                        findViewById(R.id.spinner75),
                        findViewById(R.id.spinner77),
                }
        };
        String[] noteStringArray = buildNoteStringArray().toArray(new String[0]);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, noteStringArray);
        for (Spinner[] spinners : spinnerGrid) {
            for (int j = 0; j < spinnerGrid.length; j++) {
                spinners[j].setAdapter(adapter);
            }
        }

        setChords(new Note("Cb"), new Major());
    }

    private void setChords(Note note, Mode mode){
        Chord root = Chord.fromRoot(note, mode);
        Chord third = Chord.fromThird(note, mode);
        Chord fifth = Chord.fromFifth(note, mode);
        Chord seventh = Chord.fromSeventh(note, mode);

        spinnerGrid[0][3].setSelection(adapter.getPosition(root.root.toString()));
        spinnerGrid[1][2].setSelection(adapter.getPosition(third.third.toString()));
        spinnerGrid[2][1].setSelection(adapter.getPosition(fifth.fifth.toString()));
        spinnerGrid[3][0].setSelection(adapter.getPosition(seventh.seventh.toString()));
    }
}