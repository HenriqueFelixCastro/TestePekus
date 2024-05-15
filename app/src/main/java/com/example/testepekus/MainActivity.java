package com.example.testepekus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText editTextValueA, editTextValueB;
    private Button buttonAddition, buttonSubtraction, buttonMultiplication, buttonDivision, buttonViewRecords;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        editTextValueA = findViewById(R.id.editTextValueA);
        editTextValueB = findViewById(R.id.editTextValueB);
        buttonAddition = findViewById(R.id.buttonAddition);
        buttonSubtraction = findViewById(R.id.buttonSubtraction);
        buttonMultiplication = findViewById(R.id.buttonMultiplication);
        buttonDivision = findViewById(R.id.buttonDivision);
        buttonViewRecords = findViewById(R.id.buttonViewRecords);

        buttonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    performOperation("+");
                }
            }
        });

        buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    performOperation("-");
                }
            }
        });

        buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    performOperation("*");
                }
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    performOperation("/");
                }
            }
        });

        buttonViewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayRecordsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void performOperation(String operation) {
        double valueA = Double.parseDouble(editTextValueA.getText().toString());
        double valueB = Double.parseDouble(editTextValueB.getText().toString());
        double result = 0;

        switch (operation) {
            case "+":
                result = valueA + valueB;
                break;
            case "-":
                result = valueA - valueB;
                break;
            case "*":
                result = valueA * valueB;
                break;
            case "/":
                if (valueB != 0) {
                    result = valueA / valueB;
                } else {
                    Toast.makeText(MainActivity.this, "Não é possível dividir por zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }

        long id = dbHelper.addCalculation(valueA, valueB, operation, result);
        if (id != -1) {
            Toast.makeText(MainActivity.this, "Cálculo armazenado com sucesso", Toast.LENGTH_SHORT).show();
            editTextValueA.setText("");
            editTextValueB.setText("");
        } else {
            Toast.makeText(MainActivity.this, "Falha ao armazenar o cálculo", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateFields() {
        if (editTextValueA.getText().toString().isEmpty() || editTextValueB.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor, preencha ambos os campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}