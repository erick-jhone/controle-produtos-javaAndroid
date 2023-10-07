package example.com.projetocomgradleantigo.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import example.com.projetocomgradleantigo.R;
import example.com.projetocomgradleantigo.models.Produto;

public class FormProdutoActivity extends AppCompatActivity {

    private EditText edit_produto;
    private EditText edit_quantidade;
    private EditText edit_valor;

    private ImageButton ib_voltar;

    private ProdutoDAO produtoDAO;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);

        edit_produto = findViewById(R.id.edit_produto);
        edit_quantidade = findViewById(R.id.edit_quantidade);
        edit_valor = findViewById(R.id.edit_valor);
        ib_voltar = findViewById(R.id.ib_voltar);

        produtoDAO = new ProdutoDAO(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            produto = (Produto) bundle.getSerializable("produto");

            editProduto();
        }



    }

    public void editProduto(){
        edit_produto.setText(produto.getNome());
        edit_quantidade.setText(String.valueOf(produto.getEstoque()));
        edit_valor.setText(String.valueOf(produto.getValor()));
    }


    public void salvarProduto(View view){

        String nome = edit_produto.getText().toString();
        String quantidade = edit_quantidade.getText().toString();
        String valor = edit_valor.getText().toString();
        // validação nome
        if(!nome.isEmpty()){
            // validação quantidade
            if(!quantidade.isEmpty()){
                int qtd = Integer.parseInt(quantidade);
                if(qtd >= 1){
                    if(!valor.isEmpty()){
                        double valorProduto = Double.parseDouble(valor);

                        if(valorProduto > 0 ){
                            if(produto == null) produto = new Produto();
                            produto.setNome(nome);
                            produto.setEstoque(qtd);
                            produto.setValor(valorProduto);

                            if(produto.getId() != 0){
                                produtoDAO.atualizaProduto(produto);
                            } else {
                                produtoDAO.salvarProduto(produto);
                            }

                            finish();

                        }
                    } else {


                    }


                } else {
                    edit_quantidade.requestFocus();
                    edit_quantidade.setError("Informe uma quantidade válida");
                }


            }  else {
                edit_quantidade.requestFocus();
                edit_quantidade.setError("Informe uma quantidade válida");
            }


        }  else {
            edit_produto.requestFocus();
            edit_produto.setError("Informe o nome do produto");
        }










    }


}