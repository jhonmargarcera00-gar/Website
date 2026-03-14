package com.example.prelimexam_garcera_555;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etAgentName, etExperience, etSalary, etSales;
    private EditText etCommissionRate, etTotalCommission, etNetPay;
    private Button btnCompute, btnClear, btnExit;

    private DecimalFormat df = new DecimalFormat("#,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etAgentName = findViewById(R.id.etAgentName);
        etExperience = findViewById(R.id.etExperience);
        etSalary = findViewById(R.id.etSalary);
        etSales = findViewById(R.id.etSales);
        etCommissionRate = findViewById(R.id.etCommissionRate);
        etTotalCommission = findViewById(R.id.etTotalCommission);
        etNetPay = findViewById(R.id.etNetPay);

        btnCompute = findViewById(R.id.btnCompute);
        btnClear = findViewById(R.id.btnClear);
        btnExit = findViewById(R.id.btnExit);

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computePay();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void computePay() {
        String name = etAgentName.getText().toString().trim();
        String expStr = etExperience.getText().toString().trim();
        String salaryStr = etSalary.getText().toString().trim();
        String salesStr = etSales.getText().toString().trim();

        if (name.isEmpty() || expStr.isEmpty() || salaryStr.isEmpty() || salesStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int experience = Integer.parseInt(expStr);
            double salary = Double.parseDouble(salaryStr);
            double sales = Double.parseDouble(salesStr);

            double rate;
            if (sales >= 300000) {
                if (experience > 5) {
                    rate = 0.06;
                } else {
                    rate = 0.04;
                }
            } else {
                if (experience > 5) {
                    rate = 0.05;
                } else {
                    rate = 0.03;
                }
            }

            double commission = sales * rate;
            double netPay = salary + commission;

            etCommissionRate.setText((int)(rate * 100) + "%");
            etTotalCommission.setText(df.format(commission));
            etNetPay.setText(df.format(netPay));

            Toast.makeText(this, "Payroll computed for " + name, Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid numeric input", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etAgentName.setText("");
        etExperience.setText("");
        etSalary.setText("");
        etSales.setText("");
        etCommissionRate.setText("0%");
        etTotalCommission.setText("0.00");
        etNetPay.setText("0.00");
        etAgentName.requestFocus();
        Toast.makeText(this, "Fields cleared", Toast.LENGTH_SHORT).show();
    }
}
