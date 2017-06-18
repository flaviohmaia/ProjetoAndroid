package com.example.flavi.projetoandroid;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.flavi.projetoandroid.Entity.Cliente;

public class MainActivity extends AppCompatActivity
                          implements ClienteListFragment.AoClicarNoCliente,
                                     SearchView.OnQueryTextListener,
                                     MenuItemCompat.OnActionExpandListener,
                                     ClienteDialogFragment.AoSalvarCliente{

    private FragmentManager fragmentManager;
    private ClienteListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        listFragment = (ClienteListFragment) fragmentManager.findFragmentById(R.id.fragmentLista);
    }

    @Override
    public void clicouNoCliente(Cliente cliente) {
        if(isTablet()){
            ClienteDetalheFragment fragment = ClienteDetalheFragment.novaInstancia(cliente);

            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.detalhe, fragment, ClienteDetalheFragment.TAG_DETALHE);
            ft.commit();
        }else{
            Intent intent = new Intent(this, ClienteDetalheActivity.class);
            intent.putExtra(ClienteDetalheActivity.EXTRA_CLIENTE, cliente);
            startActivity(intent);
        }
    }

    private boolean isTablet(){
        return findViewById(R.id.detalhe) != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_projeto, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.hint_busca));
        MenuItemCompat.setOnActionExpandListener(search, this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_info:
                break;

            case R.id.action_new:
                ClienteDialogFragment clienteDialogFragment = ClienteDialogFragment.newInstance(null);
                clienteDialogFragment.abrir(getSupportFragmentManager());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        listFragment.limparBusca();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String string) {
        listFragment.buscar(string);
        return false;
    }


    @Override
    public void salvouCliente(Cliente cliente) {
        listFragment.adicionar(cliente);
    }
}
