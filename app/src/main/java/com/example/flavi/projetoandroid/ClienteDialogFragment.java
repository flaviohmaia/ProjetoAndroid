package com.example.flavi.projetoandroid;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.flavi.projetoandroid.Entity.Cliente;

public class ClienteDialogFragment extends DialogFragment implements TextView.OnEditorActionListener{

    private static final String DIALOG_TAG = "editDialog";
    private static final String EXTRA_CLIENTE = "cliente";

    private TextView txtId;
    private EditText txtNome;
    private EditText txtApelido;
    private EditText txtCategoria;
    private EditText txtSubcategoria;
    private EditText txtEndereco;
    private EditText txtTelefone;
    private EditText txtDescricao;
    private Cliente cliente;

    public static ClienteDialogFragment newInstance(Cliente cliente){
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_CLIENTE, cliente);

        ClienteDialogFragment dialog = new ClienteDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        cliente = (Cliente) getArguments().getSerializable(EXTRA_CLIENTE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View layoout = inflater.inflate(R.layout.fragment_dialog_cliente, container, false);

        txtNome = (EditText) layoout.findViewById(R.id.txtNome);
        txtNome.requestFocus();

        txtApelido = (EditText) layoout.findViewById(R.id.txtApelido);
        txtCategoria = (EditText) layoout.findViewById(R.id.txtCategoria);
        txtSubcategoria = (EditText) layoout.findViewById(R.id.txtSubcategoria);
        txtEndereco = (EditText) layoout.findViewById(R.id.txtEndereco);
        txtTelefone = (EditText) layoout.findViewById(R.id.txtTelefone);
        txtDescricao = (EditText) layoout.findViewById(R.id.txtDescricao);

        if(cliente != null){
            txtNome.setText(cliente.getNome());
            txtApelido.setText(cliente.getApelido());
            txtCategoria.setText(cliente.getCategoria());
            txtSubcategoria.setText(cliente.getSubCategoria());
            txtEndereco.setText(cliente.getEndereco());
            txtTelefone.setText(cliente.getTelefone());
            txtDescricao.setText(cliente.getDescricao());
        }

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle(R.string.acao_novo);

        return layoout;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(EditorInfo.IME_ACTION_DONE == actionId){
            Activity activity = getActivity();
            if(activity instanceof AoSalvarCliente){
                if(cliente == null){
                    cliente = new Cliente(txtNome.getText().toString(),
                                            txtApelido.getText().toString(),
                                            txtCategoria.getText().toString(),
                                            txtSubcategoria.getText().toString(),
                                            txtEndereco.getText().toString(),
                                            txtTelefone.getText().toString(),
                                            txtDescricao.getText().toString());
                }else{
                    cliente.setNome(txtNome.getText().toString());
                    cliente.setApelido(txtApelido.getText().toString());
                    cliente.setCategoria(txtCategoria.getText().toString());
                    cliente.setSubCategoria(txtSubcategoria.getText().toString());
                    cliente.setEndereco(txtEndereco.getText().toString());
                    cliente.setTelefone(txtTelefone.getText().toString());
                    cliente.setDescricao(txtDescricao.getText().toString());
                }

                AoSalvarCliente listener = (AoSalvarCliente) activity;
                listener.salvouCliente(cliente);
                dismiss();
                return true;
            }
        }
        return false;
    }

    public void abrir(FragmentManager fm){
        if(fm.findFragmentByTag(DIALOG_TAG) == null){
            show(fm, DIALOG_TAG);
        }
    }

    public interface AoSalvarCliente{
        void salvouCliente(Cliente cliente);
    }
}
