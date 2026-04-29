package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etN;
    Button btnGenerar;
    TextView tvResultado;

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

        etN = findViewById(R.id.etN);
        btnGenerar = findViewById(R.id.btnGenerar);
        tvResultado = findViewById(R.id.tvResultado);

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = etN.getText().toString().trim();

                if (input.isEmpty()) {
                    etN.setError("no lo dejes vacío >:v");
                    return;
                }

                int n;

                try {
                    n = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    etN.setError("Debe ser un número válido");
                    return;
                }

                if (n <= 0) {
                    etN.setError("El número debe ser mayor que 0");
                    return;
                }

                StringBuilder serie = new StringBuilder();
                double suma = 0.0;

                for (int i = 1; i <= n; i++) {

                    double valor;

                    if (i % 2 != 0) {
                        int k = (i + 1) / 2;
                        valor = (2 * k * k) - (2 * k) + 1;
                    } else {
                        int k = i / 2;
                        valor = -((2 * k * k) + 0.5);
                    }

                    suma += valor;
                    serie.append(valor);

                    if (i < n) {
                        serie.append(", ");
                    }
                }

                tvResultado.setText("Serie:\n" + serie.toString() + "\n\nSuma: " + suma);
            }
        });
    }
}