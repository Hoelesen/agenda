package br.ifro.vilhena.agenda;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

import br.ifro.vilhena.agenda.dao.ContatoDAO;
import br.ifro.vilhena.agenda.model.Contato;

public class ListarContatosActivity extends AppCompatActivity {

   private ListView listarContatosListView;
   private FloatingActionButton button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        listarContatosListView = findViewById(R.id.listar_contatos_listview);
        button = findViewById(R.id.listar_contatos_btn);


    //criando dados para testar
//     String [] contatos = {"maria da Silva"," josé de Oliveira","Tereza Costa", "Hoelesen França","Joaquim da Silva","Patricia Moreira","Jurema constantina"};



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarContatosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listarContatosListView);


        listarContatosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Contato contato = (Contato) listarContatosListView.getItemAtPosition(posicao);
                Intent intent = new Intent(ListarContatosActivity.this, FormularioActivity.class);
                intent.putExtra("contato", contato);
                startActivity(intent);
            }
        });
        }


    private void carregarLista() {
        ContatoDAO contatoDAO = new ContatoDAO(this);
        List<Contato> contatos = contatoDAO.listar();


        //Criando adapter para enviar os dados ao listview
        ArrayAdapter<Contato> adapter =
                new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        //listview utiliza o adapter
        this.listarContatosListView.setAdapter(adapter);
    }



    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem menuDeletar = menu.add("Deletar");

        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                AdapterView.AdapterContextMenuInfo menu = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Contato contato = (Contato) listarContatosListView.getItemAtPosition(menu.position);

             ContatoDAO contatoDAO =    new ContatoDAO(ListarContatosActivity.this);
                contatoDAO.deletar(contato);
                carregarLista();


                return false;
            }
        });
    }
}
