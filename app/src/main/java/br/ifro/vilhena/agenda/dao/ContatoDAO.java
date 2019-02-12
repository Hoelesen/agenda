package br.ifro.vilhena.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.ifro.vilhena.agenda.model.Contato;

public class ContatoDAO extends SQLiteOpenHelper {


    public ContatoDAO(Context context) {
        super(context, "Agenda de contao", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table contatos (id integer primary key, nome text not null, email text, endereco text, telefone text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void inserir(Contato contato){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", contato.getNome());
        dados.put("email", contato.getEmail());
        dados.put("endereco", contato.getEndetreco());
        dados.put("telefone", contato.getTelefone());
        db.insert("contatos", null,dados);
    }
    public List<Contato> listar(){
        String sql = "select * from contatos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Contato> lista = new ArrayList<Contato>();

        while (c.moveToNext()){

            Contato contato = new Contato();
            contato.setId(c.getInt(c.getColumnIndex("id")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
            contato.setEndetreco(c.getString(c.getColumnIndex("endereco")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            lista.add(contato);
        }
        return lista;

    }

}

