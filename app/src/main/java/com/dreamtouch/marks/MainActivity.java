package com.dreamtouch.marks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.dreamtouch.marks.models.Module;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String TAG = "MainActivity";
    private final List<Module> moduleList = new ArrayList<>();
    private Spinner spinner;
    private MaterialButton save;
    private MaterialButton results;
    private TextInputLayout noteInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        loadModuleList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        results.setOnClickListener(view -> {
            navigateToResult();
        });
        spinner.setOnItemSelectedListener(this);
        noteInput.getEditText().addTextChangedListener(marksValidator);
        save.setOnClickListener(id -> {
            saveModuleMark();
        });
    }

    private void navigateToResult() {
        Intent resultsIntent = new Intent(this, ResultsActivity.class);
        resultsIntent.putParcelableArrayListExtra("modules", (ArrayList<? extends Parcelable>) moduleList);
        startActivity(resultsIntent);
    }

    private void saveModuleMark() {
        String name = (String) spinner.getSelectedItem();
        Module module = getSelectedModule(name);
        if (module != null) {
            int index = moduleList.indexOf(module);
            float note = Float.parseFloat(noteInput.getEditText().getText().toString().trim());
            module.setMarks(note);
            moduleList.set(index, module);
        }
    }

    private TextWatcher marksValidator = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() <= 0) {
                noteInput.setError("La note ne peut pas être vide");
                save.setEnabled(false);
            } else {
                noteInput.setError(null);
                save.setEnabled(true);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private Module getSelectedModule(String name) {
        for (Module m : moduleList) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    private void initViews() {
        spinner = findViewById(R.id.spinner);
        save = findViewById(R.id.save);
        results = findViewById(R.id.results);
        noteInput = findViewById(R.id.mark);
    }

    private void loadModuleList() {
        String[] names = getResources().getStringArray(R.array.modules);
        for (String name : names) {
            moduleList.add(new Module(name, 0));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}