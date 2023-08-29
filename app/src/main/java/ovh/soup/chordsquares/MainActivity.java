package ovh.soup.chordsquares;

import static ovh.soup.chordsquares.logic.Note.authorizedNotes;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import ovh.soup.chordsquares.logic.Chord;
import ovh.soup.chordsquares.logic.Nature;
import ovh.soup.chordsquares.logic.Note;
import ovh.soup.chordsquares.logic.modes.AugmentedSeventh;
import ovh.soup.chordsquares.logic.modes.Diminished;
import ovh.soup.chordsquares.logic.modes.HalfDiminished;
import ovh.soup.chordsquares.logic.modes.Major;
import ovh.soup.chordsquares.logic.modes.Seventh;
import ovh.soup.chordsquares.logic.modes.NaturalMinor;
import ovh.soup.chordsquares.logic.modes.MinorMajorSeventh;

public class MainActivity extends AppCompatActivity {

    private  Spinner[][] spinnerGrid;
    private ArrayAdapter<String> spinnersAdapter;

    private static final Nature[] basicNatures = {new Major(), new NaturalMinor(), new Seventh()};
    private static final Nature[] advancedNatures = {new Major(), new NaturalMinor(), new Seventh(), new MinorMajorSeventh(), new HalfDiminished(), new Diminished(), new AugmentedSeventh()};
    private Nature[] natures = basicNatures;

    private Note currentNote;
    private Nature currentNature;
    private String[] noteStringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_check).setOnClickListener(this::buttonCheckOnClick);
        findViewById(R.id.button_solution).setOnClickListener(this::buttonSolutionOnClick);
        findViewById(R.id.button_reset).setOnClickListener(v -> resetGrid());
        findViewById(R.id.button_random).setOnClickListener(this::buttonRandomOnClick);

        ((CheckBox)findViewById(R.id.checkBoxAdvanced)).setOnCheckedChangeListener(this::checkBoxAdvancedOnClick);
        ((Spinner) findViewById(R.id.notespinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentNote = authorizedNotes[position];
                resetGrid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((Spinner) findViewById(R.id.naturespinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentNature = natures[position];
                resetGrid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.currentNote = new Note("C");
        this.currentNature = new Major();

        populateChordGrid();
        populateNotes();
        populateNatures();
        resetGrid();
    }

    private void populateChordGrid() {
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
        ArrayList<String> noteStringArrayList = new ArrayList<>();
        noteStringArrayList.add("");
        for (Note.RawNote note: Note.RawNote.values()){
            for (Note.Alteration alteration: Note.Alteration.values()){
                noteStringArrayList.add(note.toString() + alteration.toString());
            }
        }
        noteStringArray = noteStringArrayList.toArray(new String[0]);
        spinnersAdapter = new ArrayAdapter<>(this, R.layout.spinner_list, noteStringArray);
        for (Spinner[] spinners : spinnerGrid) {
            for (Spinner spinner : spinners) {
                spinner.setAdapter(spinnersAdapter);
            }
        }
    }

    private void populateNotes() {
        Spinner noteSpinner = findViewById(R.id.notespinner);
        ArrayList<String> noteStringArray = new ArrayList<>();
        for (Note note: authorizedNotes){
            noteStringArray.add(note.toString());
        }
        noteSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_list, noteStringArray.toArray(new String[0])));
        noteSpinner.setSelection(4);
    }

    private void populateNatures() {
        ArrayList<String> natureStringArray = new ArrayList<>();
        for (Nature nature: natures){
            natureStringArray.add(nature.toString());
        }
        Spinner natureSpinner = findViewById(R.id.naturespinner);
        natureSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_list, natureStringArray.toArray(new String[0])));
        natureSpinner.setSelection(0);
    }

    private void resetGrid(){
        for (int i=0; i< spinnerGrid.length; i++) {
            for (int j = 0; j < spinnerGrid[0].length; j++) {
                spinnerGrid[i][j].setSelection(spinnersAdapter.getPosition(""));
                spinnerGrid[i][j].setBackgroundColor(Color.WHITE);

                if (i+j == spinnerGrid.length-1){
                    spinnerGrid[i][j].setSelection(spinnersAdapter.getPosition(currentNote.toString()));
                    spinnerGrid[i][j].setEnabled(false);

                }
            }
        }
    }

    private Note[][] computeNotes() {
        Note[][] values = new Note[spinnerGrid.length][spinnerGrid[0].length];

        Chord[] chords = {
                Chord.fromRoot(currentNote, currentNature),
                Chord.fromThird(currentNote, currentNature),
                Chord.fromFifth(currentNote, currentNature),
                Chord.fromSeventh(currentNote, currentNature),
        };

        for (int i=0; i<spinnerGrid.length; i++) {
            values[i][3] = chords[i].root;
            values[i][2] = chords[i].third;
            values[i][1] = chords[i].fifth;
            values[i][0] = chords[i].seventh;
        }

        return values;
    }

    private boolean[][] checkChord(){
        boolean[][] values = new boolean[spinnerGrid.length][spinnerGrid[0].length];

        Chord[] chords = {
                Chord.fromRoot(currentNote, currentNature),
                Chord.fromThird(currentNote, currentNature),
                Chord.fromFifth(currentNote, currentNature),
                Chord.fromSeventh(currentNote, currentNature),
        };

        for (int i=0; i<spinnerGrid.length; i++) {
            values[i][3] = spinnerGrid[i][3].getSelectedItem().toString().equals(chords[i].root.toString());
            values[i][2] = spinnerGrid[i][2].getSelectedItem().toString().equals(chords[i].third.toString());
            values[i][1] = spinnerGrid[i][1].getSelectedItem().toString().equals(chords[i].fifth.toString());
            values[i][0] = spinnerGrid[i][0].getSelectedItem().toString().equals(chords[i].seventh.toString());
        }

        return values;
    }

    private void buttonCheckOnClick(View v) {
        boolean[][] results = checkChord();
        for (int i = 0; i < spinnerGrid.length; i++) {
            for (int j = 0; j < spinnerGrid[0].length; j++) {
                if (results[i][j]) {
                    spinnerGrid[i][j].setBackgroundColor(Color.GREEN);
                } else {
                    spinnerGrid[i][j].setBackgroundColor(Color.RED);
                }
            }
        }
    }

    private void buttonSolutionOnClick(View v) {
        Note[][] results = computeNotes();
        for (int i = 0; i < spinnerGrid.length; i++) {
            for (int j = 0; j < spinnerGrid[0].length; j++) {
                spinnerGrid[i][j].setSelection(Arrays.asList(noteStringArray).indexOf(results[i][j].toString()));
                ;
            }
        }
    }

    private void buttonRandomOnClick(View v) {
        this.currentNote = authorizedNotes[new Random().nextInt(authorizedNotes.length)];

        ArrayList<String> noteStringArray = new ArrayList<>();
        for (Note note: authorizedNotes) noteStringArray.add(note.toString());
        ((Spinner) findViewById(R.id.notespinner)).setSelection(noteStringArray.indexOf(currentNote.toString()));

        int pickedNature = new Random().nextInt(natures.length);
        this.currentNature = natures[pickedNature];
        ((Spinner) findViewById(R.id.naturespinner)).setSelection(pickedNature);
        resetGrid();
    }

    private void checkBoxAdvancedOnClick(CompoundButton buttonView, boolean isChecked) {
        natures = isChecked ? advancedNatures : basicNatures;
        populateNatures();
    }
}