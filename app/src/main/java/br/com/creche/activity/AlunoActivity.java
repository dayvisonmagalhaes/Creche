package br.com.creche.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.creche.AdapterTab.TabAdapterAlunos;
import br.com.creche.helper.SlidingTabLayout;
import testedelayout.cursoandroid.com.creche.R;

public class AlunoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private  String nomeTurma;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        toolbar = (Toolbar) findViewById(R.id.toolbarprofessor);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            nomeTurma = extras.getString("nomeTurma");
        }

        //Configurar toobar
        toolbar.setTitle("Alunos do " + nomeTurma);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);

        setSupportActionBar(toolbar);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_pagina);

        //Configurar Slidin Tabs
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));


        //Configurar o Adapter
        TabAdapterAlunos tabAdapterAlunos = new TabAdapterAlunos(getSupportFragmentManager());

        viewPager.setAdapter(tabAdapterAlunos);


        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_sair:
                deslogarUsuario();
                return true;
            //case R.id.item_configuracoes:
            //  return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void deslogarUsuario() {
        Intent intent = new Intent(AlunoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
