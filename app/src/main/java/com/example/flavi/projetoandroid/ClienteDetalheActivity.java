package com.example.flavi.projetoandroid;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.flavi.projetoandroid.Entity.Cliente;

public class ClienteDetalheActivity extends AppCompatActivity {

    public  static final String EXTRA_CLIENTE = "cliente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detalhe);

        if(savedInstanceState == null){
            Intent intent = getIntent();
            Cliente cliente = (Cliente) intent.getSerializableExtra(EXTRA_CLIENTE);

            ClienteDetalheFragment fragment = ClienteDetalheFragment.novaInstancia(cliente);

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.detalhe, fragment, ClienteDetalheFragment.TAG_DETALHE);
            fragmentTransaction.commit();
        }
    }
}
