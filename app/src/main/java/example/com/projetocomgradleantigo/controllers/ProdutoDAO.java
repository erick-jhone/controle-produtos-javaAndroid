package example.com.projetocomgradleantigo.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import example.com.projetocomgradleantigo.models.Produto;

public class ProdutoDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;


    public ProdutoDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarProduto(Produto produto){
        ContentValues cv = new ContentValues();
        cv.put("nome", produto.getNome());
        cv.put("estoque", produto.getEstoque());
        cv.put("valor", produto.getValor());

        try {
            write.insert(DBHelper.TB_PRODUTO, null, cv);
          //  write.close(); // fecha a conexão
        }catch (Exception e){
            Log.i("ERROR", "salvarProduto: Erro ao salvar produto" + e.getMessage());
        }

    }

}
