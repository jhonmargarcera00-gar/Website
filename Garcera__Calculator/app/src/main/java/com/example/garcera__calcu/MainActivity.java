package com.example.garcera__calcu;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstNumber;
    private EditText etSecondNumber;
    private TextView tvResult;

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

        etFirstNumber = findViewById(R.id.et_first_number);
        etSecondNumber = findViewById(R.id.et_second_number);
        tvResult = findViewById(R.id.tv_result);

        findViewById(R.id.btn_plus).setOnClickListener(v -> calculate('+'));
        findViewById(R.id.btn_minus).setOnClickListener(v -> calculate('-'));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> calculate('*'));
        findViewById(R.id.btn_divide).setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char operator) {
        String s1 = etFirstNumber.getText().toString().trim();
        String s2 = etSecondNumber.getText().toString().trim();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);
            double result = 0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
            }


            if (result == (long) result) {
                tvResult.setText(String.format(Locale.getDefault(), "%d", (long) result));
            } else {
                tvResult.setText(String.format(Locale.getDefault(), "%.2f", result));
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}
