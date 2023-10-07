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
import example.com.projetocomgradleantigo.controllers.ProdutoDAO;
import example.com.projetocomgradleantigo.models.Produto;
public class MainActivity extends AppCompatActivity implements AdapterProduto.OnClick {
    private SwipeableRecyclerView rvProdutos;
    private List<Produto> produtoList = new ArrayList<>();
    private AdapterProduto adapterProduto;
    private ImageButton ibAdd;
    private ImageButton ibVerMais;

    private ProdutoDAO produtoDAO;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        produtoDAO = new ProdutoDAO(this);

        produtoList = produtoDAO.getListProdutos();



        ibAdd = findViewById(R.id.ib_add);
        ibVerMais = findViewById(R.id.ib_ver_mais);

        rvProdutos = findViewById(R.id.rvProdutos);

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

        produtoList.clear();
        produtoList = produtoDAO.getListProdutos();

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
                Produto produto = produtoList.get(position);
                produtoDAO.delelaProduto(produto);
                produtoList.remove(produto);
                adapterProduto.notifyItemRemoved(position);

            }
        });


    }

    @Override
    public void onClickListener(Produto produto) {
        Intent intent = new Intent(this, FormProdutoActivity.class);
        intent.putExtra("produto", produto);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        configRecyclerView();
    }
}