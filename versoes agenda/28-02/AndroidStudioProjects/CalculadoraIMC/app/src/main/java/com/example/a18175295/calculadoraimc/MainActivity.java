package com.example.a18175295.calculadoraimc;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a18175295.calculadoraimc.R;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText txtPeso;
    private EditText txtAltura;
    private ImageButton btCalcular;
    private ImageButton btLimpar;

    private TextView viewImc;
    private TextView viewResultado;
    private TextView viewResumo;

    private RelativeLayout cardResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        private TextInputLayout layoutTxtPeso;
        private TextInputLayout layoutTxtAltura;

        txtPeso = findViewById(R.id.txt_peso);
        txtAltura = findViewById(R.id.txt_altura);
        btCalcular = findViewById(R.id.btn_calcular);
        btLimpar = findViewById(R.id.btn_limpar);

        viewImc = findViewById(R.id.view_imc);
        viewResultado = findViewById(R.id.view_resultado);
        viewResumo = findViewById(R.id.view_resumo);

        cardResultado = findViewById(R.id.card_resultado);

        cardResultado.setVisibility(View.INVISIBLE);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularImc();
            }
        });
    }

    private void calcularImc(){

        DecimalFormat df = new DecimalFormat(".##");

        double peso = Double.parseDouble(txtPeso.getText().toString());
        double altura = Double.parseDouble(txtAltura.getText().toString());

        double imc = peso / Math.pow(altura, 2);

        viewImc.setText(df.format(imc));

        if(imc < 15){
            //viewResultado.setText(getResources().getText(R.string.muito_abaixo_peso_titulo).toString());
           // viewResumo.setText(getResources().getText(R.string.abaixo_peso_1).toString());
        }else if(imc <18.5){
            //viewResultado.setText(getResources().getText(R.string.abaixo_peso_1_titulo));
            //viewResumo.setText(getResources().getText(R.string.abaixo_peso_1).toString());
        }



        cardResultado.setVisibility(View.VISIBLE);
    }

    private boolean validar() {
        boolean validado = true;
        if(txtPeso.getText().toString().isEmpty()){
            layoutTxtPeso.setErrorEnabled(true);
            layoutTxtPeso.setError("Por favor digite o seu peso");
            validado = false;
        }else{
            layoutTxtPeso.setErrorEnabled(false);
        }

        if(txtAltura.getText().toString().isEmpty()){
            layoutTxtAltura.setErrorEnabled(true);
            layoutTxtAltura.setError("Por favor digite a sua altura");
            validado = false;

        }else{
            layoutTxtAltura.setErrorEnabled(false);
        }
        return validado;
    }

}
