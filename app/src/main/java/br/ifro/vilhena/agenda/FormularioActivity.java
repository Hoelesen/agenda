package br.ifro.vilhena.agenda;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.ifro.vilhena.agenda.dao.ContatoDAO;
import br.ifro.vilhena.agenda.model.Contato;

public class FormularioActivity extends AppCompatActivity {
   private TextInputEditText formularioNome;
    private TextInputEditText formularioEmail;
    private TextInputEditText formularioEndreco;
    private TextInputEditText formularioTelefone;
    private Button formularioBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        //mapear os componentes do xml
        formularioNome = findViewById(R.id.formulario_nome);
        formularioBtn = findViewById(R.id.formulario_btn);
        formularioEmail = findViewById(R.id.formulario_email);
        formularioEndreco = findViewById(R.id.formulario_endereco);
        formularioTelefone = findViewById(R.id.formulario_telefone);



        formularioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Criar objeto
                Contato contato = new Contato();
                contato.setNome(formularioNome.getText().toString());
                contato.setEmail(formularioEmail.getText().toString());
                contato.setEndetreco(formularioEndreco.getText().toString());
                contato.setTelefone(formularioTelefone.getText().toString());


                //Inserir no banco de dados
                ContatoDAO contatoDAO = new ContatoDAO( FormularioActivity.this);
                contatoDAO.inserir(contato);




                Toast.makeText(FormularioActivity.this, "Contato salvo com sucesso", Toast.LENGTH_LONG).show();

                finish();


            }
        });
    }
}
