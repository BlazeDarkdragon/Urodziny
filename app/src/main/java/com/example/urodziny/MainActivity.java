package com.example.urodziny;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> notatki;
    ArrayAdapter<String> adapter;
    private static final String sciezka = "dane.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        EditText editText1 = findViewById(R.id.editText);
        Button button1 = findViewById(R.id.button);
        ListView lista1 = findViewById(R.id.lista);


        notatki = new ArrayList<>();
        notatki.add("Zakupy: chleb, masło, ser");
        notatki.add("Do zrobienia: obiad, umyć podłogi");
        notatki.add("weekend: kino, spacer z psem");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notatki);
        lista1.setAdapter(adapter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notatki);
        lista1.setAdapter(adapter);

    }
    public void Dodawanie(View view) {

        EditText editText1 = findViewById(R.id.editText);
        Button button1 = findViewById(R.id.button);
        ListView lista1 = findViewById(R.id.lista);

        String notatka = editText1.getText().toString().trim();
        if (!notatka.isEmpty()) {
            notatki.add(notatka);
            adapter.notifyDataSetChanged();
            editText1.setText("");
        }
    }

    public void zapisanie(View view) {
        try {
            FileOutputStream fos = openFileOutput(sciezka, MODE_PRIVATE);
            for (String linia : notatki) {
                fos.write((linia + "\n").getBytes());
            }
            fos.close();
            Toast.makeText(this, "Zapisano!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Błąd!", Toast.LENGTH_LONG).show();
        }
    }

}