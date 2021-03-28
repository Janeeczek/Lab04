package pollub.ism.lab04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static String KEY_SCOREX = "Wartosc licznika scoreX",
            KEY_SCOREO = "Wartosc licznika scoreO",
            KEY_TURA = "Wartosc licznika tura",
            KEY_RUNDA = "Wartosc licznika runda";
    //private static List<Button> =
    private static List<Object> butId = new ArrayList<>(Arrays.asList(R.id.b1,R.id.b2,R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7,R.id.b8,R.id.b9));
    private List<Button> buttons;
    private int scoreX, scoreO,tura,runda;
    private TextView twynik_o,twynik_x,trunda;
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCOREX, scoreX);
        outState.putInt(KEY_SCOREO, scoreO);
        outState.putInt(KEY_TURA, tura);
        outState.putInt(KEY_RUNDA, runda);
        //outState.put
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreX = savedInstanceState.getInt(KEY_SCOREX, 0);
        scoreO = savedInstanceState.getInt(KEY_SCOREO, 0);
        tura = savedInstanceState.getInt(KEY_TURA, 0);
        runda = savedInstanceState.getInt(KEY_RUNDA, 0);
        aktualizujText();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicjalizujDane();
        buttons = inicjalizujPrzyciski();
        inicjalizujText();
        aktualizujText();

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public List<Button> inicjalizujPrzyciski(){
        List<Button> butt = new ArrayList<>();
        for (int i = 0;i<9;i++) {
            Button b = (Button)findViewById((int) butId.get(i));
            butt.add(b);
            b.setText(R.string.nic);
        }
        return butt;
    }
    public void inicjalizujDane() {
        tura = 0;
        runda = 1;
        scoreX = 0;
        scoreO = 0;
    }
    public void inicjalizujText() {
        twynik_o = (TextView)findViewById(R.id.wynik_o);
        twynik_x = (TextView)findViewById(R.id.wynik_x);
        trunda = (TextView)findViewById(R.id.runda);
    }
    public void aktualizujText() {
        twynik_o.setText(String.valueOf(scoreO));
        twynik_x.setText(String.valueOf(scoreX));
        trunda.setText(String.valueOf(runda));
    }
    public int kliknieciePrzycisku(View view){
        Button b = (Button)view;
        String nazwaPrzycisku = view.getResources().getResourceEntryName(view.getId());
        String tekstPrzycisku = b.getText().toString();
        if (!buttons.contains(b)) {
            Toast.makeText(this,"Bład inicjalizacji tego przycisku: " + nazwaPrzycisku , Toast.LENGTH_LONG).show();
            return 0;
        }
        if (tura == 0) { //pierwsza tura X
            b.setText(R.string.x);
            //Toast.makeText(this,"Gracz X wcisnął przycisk "+ b.getId(), Toast.LENGTH_LONG).show();
            System.out.println("Gracz X wcisnął przycisk "+ b.getId());
            tura++;
        } else { //kolejne tury
            if (tura % 2 ==0) {//tura gracza X
                if (tekstPrzycisku == getResources().getString(R.string.nic)){
                    b.setText(R.string.x);
                    //Toast.makeText(this,"Gracz X wcisnął przycisk "+ b.getId() , Toast.LENGTH_LONG).show();
                    System.out.println("Gracz X wcisnął przycisk "+ b.getId());
                    tura++;
                }
            } else { //tura gracza O
                if (tekstPrzycisku == getResources().getString(R.string.nic)){
                    b.setText(R.string.o);
                    //Toast.makeText(this,"Gracz O wcisnął przycisk "+ b.getId(), Toast.LENGTH_LONG).show();
                    System.out.println("Gracz O wcisnął przycisk "+ b.getId());
                    tura++;
                }
            }
        }

        //if(buttons.get(0).getText().toString().equals("X"))Toast.makeText(this,"Wygrał gracz X ", Toast.LENGTH_LONG).show();

        if (sprawdzCzyWygralX()) {
            inicjalizujPrzyciski();
            scoreX++;
            tura = 0;
            runda++;
            aktualizujText();

            Toast.makeText(this,"Wygrał gracz X ", Toast.LENGTH_LONG).show();
        }
        if (sprawdzCzyWygralO()) {
            inicjalizujPrzyciski();
            scoreO++;
            tura = 0;
            runda++;
            aktualizujText();

            Toast.makeText(this,"Wygrał gracz O ", Toast.LENGTH_LONG).show();
        }
        if(sprawdzCzyNiktWygral()) {
            inicjalizujPrzyciski();
            tura = 0;
            runda++;
            aktualizujText();
            Toast.makeText(this,"Nikt nie wygrał!", Toast.LENGTH_LONG).show();
        }

        return 1;
    }
    private boolean sprawdzCzyWygralX() {
        if (buttons.get(0).getText().toString().equals("X") && buttons.get(1).getText().toString().equals("X") && buttons.get(2).getText().toString().equals("X") ) return true;
        else if (buttons.get(3).getText().toString().equals("X") && buttons.get(4).getText().toString().equals("X") && buttons.get(5).getText().toString().equals("X") ) return true;
        else if (buttons.get(6).getText().toString().equals("X") && buttons.get(7).getText().toString().equals("X") && buttons.get(8).getText().toString().equals("X") ) return true;
        else if (buttons.get(0).getText().toString().equals("X") && buttons.get(3).getText().toString().equals("X") && buttons.get(6).getText().toString().equals("X") ) return true;
        else if (buttons.get(1).getText().toString().equals("X") && buttons.get(4).getText().toString().equals("X") && buttons.get(7).getText().toString().equals("X") ) return true;
        else if (buttons.get(2).getText().toString().equals("X") && buttons.get(5).getText().toString().equals("X") && buttons.get(8).getText().toString().equals("X") ) return true;
        else if (buttons.get(0).getText().toString().equals("X") && buttons.get(4).getText().toString().equals("X") && buttons.get(8).getText().toString().equals("X") ) return true;
        else return buttons.get(2).getText().toString().equals("X") && buttons.get(4).getText().toString().equals("X") && buttons.get(6).getText().toString().equals("X");
    }
    private boolean sprawdzCzyWygralO() {
        if (buttons.get(0).getText().toString().equals("O") && buttons.get(1).getText().toString().equals("O") && buttons.get(2).getText().toString().equals("O") ) return true;
        else if (buttons.get(3).getText().toString().equals("O") && buttons.get(4).getText().toString().equals("O") && buttons.get(5).getText().toString().equals("O") ) return true;
        else if (buttons.get(6).getText().toString().equals("O") && buttons.get(7).getText().toString().equals("O") && buttons.get(8).getText().toString().equals("O") ) return true;
        else if (buttons.get(0).getText().toString().equals("O") && buttons.get(3).getText().toString().equals("O") && buttons.get(6).getText().toString().equals("O") ) return true;
        else if (buttons.get(1).getText().toString().equals("O") && buttons.get(4).getText().toString().equals("O") && buttons.get(7).getText().toString().equals("O") ) return true;
        else if (buttons.get(2).getText().toString().equals("O") && buttons.get(5).getText().toString().equals("O") && buttons.get(8).getText().toString().equals("O") ) return true;
        else if (buttons.get(0).getText().toString().equals("O") && buttons.get(4).getText().toString().equals("O") && buttons.get(8).getText().toString().equals("O") ) return true;
        else return buttons.get(2).getText().toString().equals("O") && buttons.get(4).getText().toString().equals("O") && buttons.get(6).getText().toString().equals("O");
    }
    private boolean sprawdzCzyNiktWygral() {
        for (Button b : buttons) {
            if(b.getText().toString().equals("")) return false;
        }
        return true;
    }
}