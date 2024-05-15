package com.example.testepekus;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class DisplayRecordsActivity extends AppCompatActivity {
    private ListView listViewRecords;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_records);
        dbHelper = new DatabaseHelper(this);
        listViewRecords = findViewById(R.id.listViewRecords);
        displayCalculations();
    }

    private void displayCalculations() {
        List<Calculation> calculations = dbHelper.getAllCalculations();
        ArrayAdapter<Calculation> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, calculations);
        listViewRecords.setAdapter(adapter);
    }
}