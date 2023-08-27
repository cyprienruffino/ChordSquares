package ovh.cyprienruffino.chordsquares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private final Spinner[][] spinnerGrid = {
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
                findViewById(R.id.spinner31),
                findViewById(R.id.spinner33),
                findViewById(R.id.spinner35),
                findViewById(R.id.spinner37),
            },
            {
                findViewById(R.id.spinner31),
                findViewById(R.id.spinner33),
                findViewById(R.id.spinner35),
                findViewById(R.id.spinner37),
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0;i<spinnerGrid.length; i++){
            for (int j=0;j<spinnerGrid.length; i++){

            }
        }
    }

    private void setChords(Note note, Mode mode){
        Chord root = Chord.fromRoot(note, mode);
        Chord third = Chord.fromThird(note, mode);
        Chord fifth = Chord.fromFifth(note, mode);
        Chord seventh = Chord.fromSeventh(note, mode);


    }
}