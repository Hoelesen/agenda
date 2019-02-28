package br.ifro.vilhena.agenda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import br.ifro.vilhena.agenda.dao.ContatoDAO;
import br.ifro.vilhena.agenda.model.Contato;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 457;
    private TextInputEditText formularioNome;
    private TextInputEditText formularioEmail;
    private TextInputEditText formularioEndreco;
    private TextInputEditText formularioTelefone;
    private Button formularioBtn;
    private FloatingActionButton formularioBtnFoto;
    private ImageView formularioFoto;
    private Contato contato;
    private String caminhoFoto;


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
        formularioBtnFoto = findViewById(R.id.formulario_btn_foto);
        formularioFoto = findViewById(R.id.formulario_foto);


        formularioBtnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             caminhoFoto = getExternalFilesDir(null)+"/foto" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                startActivityForResult( intent, CODIGO_CAMERA);



            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("contato")){
            contato = (Contato) intent.getSerializableExtra("contato");
        }else{
            contato = new Contato();
        }
        // Carregando os dados para o formulario
        if (contato != null){
            formularioNome.setText(contato.getNome());
            formularioEndreco.setText(contato.getEndetreco());
            formularioEmail.setText(contato.getEmail());
            formularioTelefone.setText(contato.getTelefone());

            carregarImagem(contato.getCaminhoFoto());


        }


        formularioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Criar objeto

                contato.setNome(formularioNome.getText().toString());
                contato.setEmail(formularioEmail.getText().toString());
                contato.setEndetreco(formularioEndreco.getText().toString());
                contato.setTelefone(formularioTelefone.getText().toString());
                contato.setCaminhoFoto((String) formularioBtnFoto.getTag());


                //Inserir no banco de dados
                ContatoDAO contatoDAO = new ContatoDAO( FormularioActivity.this);
               if (contato.getId() == 0 )
                   contatoDAO.inserir(contato);
              else
                  contatoDAO.alterar(contato);
                contatoDAO.close();



                Toast.makeText(FormularioActivity.this, "Contato salvo com sucesso", Toast.LENGTH_LONG).show();

                finish();


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK){

            if (requestCode == CODIGO_CAMERA){

                carregarImagem((caminhoFoto));
            }

        }


    }

    private void carregarImagem(String caminhoFoto) {
        Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);

        if (bitmap != null) {


            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap,400,400,true);
            formularioFoto.setImageBitmap(bitmap);
            formularioFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            formularioFoto.setTag(caminhoFoto);




        }


    }
}
