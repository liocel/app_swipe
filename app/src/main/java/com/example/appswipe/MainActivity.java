package com.example.appswipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv_direcao;
    private TextView tv_Direcao;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_direcao = findViewById(R.id.lv_direcao);
        tv_Direcao= findViewById(R.id.tvDirecal);
        layout = findViewById(R.id.layout);

        carregarProdutos();
        layout.setOnTouchListener( new OnSwipeTouchListener(this){

            @Override
            public void onSwipeRight(){
                super.onSwipeRight();
                monstrarDirecao("direita");
            }
            @Override
            public void onSwipeLeft(){
                super.onSwipeLeft();
                monstrarDirecao("esquerda");
            }
            @Override
            public void onSwipeBottom(){
                super.onSwipeBottom();
                monstrarDirecao("topo");
            }
            @Override
            public void onSwipeTop(){
                super.onSwipeTop();
                monstrarDirecao("base");

            }

            @Override

            public boolean onTouch(View v, MotionEvent event){

                //monstrarDirecao("so tocou");
                return super.onTouch(v,event);
            }
        });
        lv_direcao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Direcao direcaoSelecionada = (Direcao) adapterView.getItemAtPosition( i );
                excluir(direcaoSelecionada);
                return true;
            }
        });
    }

    private void monstrarDirecao(String direcao){
        AlertDialog.Builder  alerta = new AlertDialog.Builder(this);
        alerta.setTitle("direçao do movimento");
        Direcao d =new Direcao();
        switch (direcao) {
            case "direita":
                alerta.setMessage("da direita para a esquerda");
                tv_Direcao.setText("da esquerda para a direita");
                d.setDirecao("direita");
                DirecaoDAO.inserir(this,d);
                break;
            case "esquerda":
                alerta.setMessage("da direta pra esquerda");
                tv_Direcao.setText("da direta pra esquerda");
                d.setDirecao("esquerda");
                DirecaoDAO.inserir(this,d);
                break;
            case "topo":
                alerta.setMessage("de cima pra baixo");
                tv_Direcao.setText("de cima pra baixo");
                d.setDirecao("baixo");
                DirecaoDAO.inserir(this,d);
                break;
            case "base":
                alerta.setMessage("da base pra cima");
                tv_Direcao.setText("da base pra cima");
                d.setDirecao("cima");
                DirecaoDAO.inserir(this,d);
                break;
            default:
                alerta.setMessage("movimento nao indentificado");
                tv_Direcao.setText("movimento nao indentificado");
                break;
        }
        alerta.setPositiveButton("ok",null);
        alerta.show();
        carregarProdutos();
    }
    private void carregarProdutos(){
        List<Direcao> lista = DirecaoDAO.getDirecao( this );
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lv_direcao.setAdapter( adapter );
    }
    protected void onRestart() {
        super.onRestart();
        carregarProdutos();
    }

    private void excluir(final Direcao prod){
        androidx.appcompat.app.AlertDialog.Builder alerta = new androidx.appcompat.app.AlertDialog.Builder(this);
        alerta.setTitle("Excluir Produto");
        alerta.setMessage("Confirma a exclusão do produto " + prod.getDirecao() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DirecaoDAO.excluir(MainActivity.this, prod.getId());
                carregarProdutos();
            }
        });
        alerta.show();
    }

}
