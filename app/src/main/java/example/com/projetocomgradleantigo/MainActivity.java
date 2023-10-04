package example.com.projetocomgradleantigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

import example.com.projetocomgradleantigo.controllers.AdapterProduto;
import example.com.projetocomgradleantigo.controllers.FormProdutoActivity;
import example.com.projetocomgradleantigo.models.Produto;
public class MainActivity extends AppCompatActivity implements AdapterProduto.OnClick {
    private List<Produto> produtoList = new ArrayList<>();
    private SwipeableRecyclerView rvProdutos;
    private AdapterProduto adapterProduto;
    private ImageButton ibAdd;
    private ImageButton ibVerMais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar);
        ibAdd = findViewById(R.id.ib_add);
        ibVerMais = findViewById(R.id.ib_ver_mais);

        rvProdutos = findViewById(R.id.rvProdutos);
        carregaLista();
        configRecyclerView();
        ouvinteCliques();


    }

    private void ouvinteCliques(){
        ibAdd.setOnClickListener(view -> {
            startActivity(new Intent(this, FormProdutoActivity.class));
        });

        ibVerMais.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(this, ibVerMais);
            popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if(menuItem.getItemId() == R.id.menu_sobre){
                    Toast.makeText(this,"Sobre", Toast.LENGTH_SHORT).show();
                }
                return true;
            });

            popupMenu.show();

        });

    }
    private void configRecyclerView() {
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setHasFixedSize(true);
        adapterProduto = new AdapterProduto(produtoList, this);
        rvProdutos.setAdapter(adapterProduto);
        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
            }

            @Override
            public void onSwipedRight(int position) {
                produtoList.remove(position);
                adapterProduto.notifyItemRemoved(position);

            }
        });


    }

    private void carregaLista() {

        Produto produto1 = new Produto();
        produto1.setNome("Monitor 34 LG");
        produto1.setEstoque(45);
        produto1.setValor(1349.99);
        Produto produto2 = new Produto();
        produto2.setNome("Smartphone Samsung Galaxy S21");
        produto2.setEstoque(30);
        produto2.setValor(999.99);
        Produto produto3 = new Produto();
        produto3.setNome("Notebook Dell XPS 13");
        produto3.setEstoque(20);
        produto3.setValor(1699.99);
        Produto produto4 = new Produto();
        produto4.setNome("Câmera Canon EOS 5D Mark IV");
        produto4.setEstoque(15);
        produto4.setValor(2499.99);
        Produto produto5 = new Produto();
        produto5.setNome("Fone de Ouvido Sony WH-1000XM4");
        produto5.setEstoque(50);
        produto5.setValor(349.99);
        Produto produto6 = new Produto();
        produto6.setNome("Máquina de Lavar Roupa LG Inverter");
        produto6.setEstoque(10);
        produto6.setValor(799.99);
        Produto produto7 = new Produto();
        produto7.setNome("Console de Jogos Xbox Series X");
        produto7.setEstoque(25);
        produto7.setValor(499.99);
        Produto produto8 = new Produto();
        produto8.setNome("Refrigerador Samsung Side-by-Side");
        produto8.setEstoque(12);
        produto8.setValor(1499.99);
        Produto produto9 = new Produto();
        produto9.setNome("Impressora HP LaserJet Pro");
        produto9.setEstoque(18);
        produto9.setValor(249.99);
        Produto produto10 = new Produto();
        produto10.setNome("Bicicleta Trek Dual Sport");
        produto10.setEstoque(35);
        produto10.setValor(699.99);

        produtoList.add(produto1);
        produtoList.add(produto2);
        produtoList.add(produto3);
        produtoList.add(produto4);
        produtoList.add(produto5);
        produtoList.add(produto6);
        produtoList.add(produto7);
        produtoList.add(produto8);
        produtoList.add(produto9);
        produtoList.add(produto10);


    }


    @Override
    public void onClickListener(Produto produto) {
        Toast.makeText(this, produto.getNome(), Toast.LENGTH_SHORT).show();
    }

}