package com.cogy.launcher;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LauncherActivity extends AppCompatActivity {
    private Spinner languageSpinner;
    private EditText nameInput;
    private EditText pathInput;
    private EditText argumentsInput;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Launcher");

        languageSpinner = findViewById(R.id.spinner_language);
        nameInput = findViewById(R.id.input_name);
        pathInput = findViewById(R.id.input_path);
        argumentsInput = findViewById(R.id.input_arguments);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);

        String[] languages = {"Java", "Kotlin", "C", "C++", "C#"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            languages
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        saveButton.setOnClickListener(v -> saveLauncher());
        cancelButton.setOnClickListener(v -> finish());
    }

    private void saveLauncher() {
        String name = nameInput.getText().toString();
        String language = (String) languageSpinner.getSelectedItem();
        String path = pathInput.getText().toString();
        String arguments = argumentsInput.getText().toString();

        if (!name.isEmpty() && !path.isEmpty()) {
            LauncherManager manager = new LauncherManager();
            manager.addLauncher(name, language, path, arguments);
            Toast.makeText(this, "Launcher saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
