package br.ifro.vilhena.agenda.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ifro.vilhena.agenda.R;
import br.ifro.vilhena.agenda.model.Contato;

public class ListaAdapter extends BaseAdapter {
    private List<Contato> contatos;
    private Activity activity;

    public ListaAdapter(List<Contato> contatos, Activity activity) {
        this.contatos = contatos;
        this.activity = activity;
    }

    @Override
    public int getCount() {


        return this.contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contatos.get(position);


    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view   = this.activity.getLayoutInflater().inflate(R.layout.list_adapter, parent, false);
        Contato contato = this.contatos.get(position);

        TextView titulo = view.findViewById(R.id.lista_nome);
        TextView status = view.findViewById(R.id.lista_status);
        ImageView img = view.findViewById(R.id.img_list_adapter_img);

                titulo.setText(contato.getNome());
                status.setText(contato.getTelefone());

        Bitmap bitmap = BitmapFactory.decodeFile(contato.getCaminhoFoto());
        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap,120,75,true);

        img.setImageBitmap(bitmap);
        img.setScaleType(ImageView.ScaleType.FIT_XY);



        return view;
    }
}
