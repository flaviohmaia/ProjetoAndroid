package com.example.flavi.projetoandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flavi.projetoandroid.Entity.Cliente;

public class ClienteDetalheFragment extends Fragment{

    public  static final String TAG_DETALHE = "tagDetalhe";
    public  static final String EXTRA_CLIENTE = "cliente";

    TextView txtNome;
    TextView txtApelido;
    TextView txtTelefone;
    TextView txtDescricao;

    Cliente cliente;

    public static ClienteDetalheFragment novaInstancia(Cliente cliente){
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_CLIENTE, cliente);

        ClienteDetalheFragment fragment = new ClienteDetalheFragment();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        cliente = (Cliente) getArguments().getSerializable(EXTRA_CLIENTE);
        setHasOptionsMenu(true);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.fragment_detalhe_cliente, container, false);

        txtNome = (TextView) layout.findViewById(R.id.txtNome);
        txtApelido = (TextView) layout.findViewById(R.id.txtApelido);
        txtTelefone = (TextView) layout.findViewById(R.id.txtTelefone);
        txtDescricao = (TextView) layout.findViewById(R.id.txtDescricao);

        if(cliente != null){
            txtNome.setText(cliente.getNome());
            txtApelido.setText(cliente.getApelido());
            txtTelefone.setText(cliente.getTelefone());
            txtDescricao.setText(cliente.getDescricao());
        }

        return  layout;
    }

}
