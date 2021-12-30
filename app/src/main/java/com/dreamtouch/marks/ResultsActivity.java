package com.dreamtouch.marks;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dreamtouch.marks.models.Module;
import com.dreamtouch.marks.recycler.RecyclerAdapter;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    public static final String TAG = "ResultsActivity";
    private RecyclerView recyclerView;
    private TextView average;
    private List<Module> moduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = findViewById(R.id.recycler);
        average = findViewById(R.id.average);


        if (getIntent().hasExtra("modules")) {
            moduleList = getIntent().getParcelableArrayListExtra("modules");
            displayList(moduleList);
            average.setText("Moyenne: " + calculateAverage());
        }
    }

    private float calculateAverage() {
        float sum = 0;
        for (Module m : moduleList) {
            sum += m.getMarks();
        }
        return sum / moduleList.size();
    }

    private void displayList(List<Module> moduleList) {
        RecyclerAdapter adapter = new RecyclerAdapter(moduleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
