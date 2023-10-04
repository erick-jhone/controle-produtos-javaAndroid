package example.com.projetocomgradleantigo.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import example.com.projetocomgradleantigo.R;
import example.com.projetocomgradleantigo.models.Produto;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    private List<Produto> produtosList;
    private OnClick onClick;

    public AdapterProduto(List<Produto> produtosList, OnClick onClick) {
        this.produtosList = produtosList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto produto = produtosList.get(position);
        holder.textProduto.setText(produto.getNome());
        holder.textEstoque.setText("Estoque: " + String.valueOf(produto.getEstoque()));
        holder.textValor.setText("R$ "+String.valueOf(produto.getValor()));
        holder.itemView.setOnClickListener(view -> {

            onClick.onClickListener(produto);

        });

    }

    @Override
    public int getItemCount() {
        return produtosList.size();
    }

    public interface OnClick{
        void onClickListener(Produto produto);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textProduto, textEstoque, textValor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textProduto = itemView.findViewById(R.id.text_produto);
            textEstoque = itemView.findViewById(R.id.text_estoque);
            textValor = itemView.findViewById(R.id.text_valor);

        }
    }





}
