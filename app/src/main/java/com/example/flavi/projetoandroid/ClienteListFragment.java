package com.example.flavi.projetoandroid;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.flavi.projetoandroid.Entity.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteListFragment extends ListFragment{
    List<Cliente> listCliente;
    ArrayAdapter<Cliente> adapterCliente;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        limparBusca();
    }


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        listCliente = carregarListaCliente();
    }

    public void onListItemClick(ListView listView, View view, int position, long id){
        super.onListItemClick(listView, view, position, id);

        Activity activity = getActivity();
        if(activity instanceof  AoClicarNoCliente){
            Cliente cliente = (Cliente) listView.getItemAtPosition(position);

            AoClicarNoCliente listener = (AoClicarNoCliente) activity;
            listener.clicouNoCliente(cliente);
        }
    }

    public interface AoClicarNoCliente{
        void clicouNoCliente(Cliente cliente);
    }

    private List<Cliente> carregarListaCliente(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente( "AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG"));
        clientes.add(new Cliente( "aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg"));
        return clientes;
    }

    public void buscar(String string){
        if(string == null || string.trim().equals("")){
            limparBusca();
            return;
        }

        List<Cliente> clientesEncontrados = new ArrayList<Cliente>(listCliente);

        for(int i = clientesEncontrados.size()-1; i > 0; i--){
            Cliente cliente = clientesEncontrados.get(i);
            if(!cliente.getNome().toUpperCase().contains(string.toUpperCase())){
                clientesEncontrados.remove(cliente);
            }
        }

        adapterCliente = new ArrayAdapter<Cliente>(getActivity(), android.R.layout.simple_list_item_1, clientesEncontrados);
        setListAdapter(adapterCliente);
    }

    public void limparBusca(){
        adapterCliente = new ArrayAdapter<Cliente>(getActivity(), android.R.layout.simple_list_item_1, listCliente);
        setListAdapter(adapterCliente);
    }

    public  void adicionar(Cliente cliente){
        listCliente.add(cliente);
        adapterCliente.notifyDataSetChanged();
    }
}
