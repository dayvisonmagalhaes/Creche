package br.com.creche.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import br.com.creche.modelo.AlunoPresenca;
import testedelayout.cursoandroid.com.creche.R;

/**
 * Created by u6390869 on 10/01/2018.
 * AlunosPresencaAdapter
 */

public class AlunosPresencaAdapter extends ArrayAdapter<AlunoPresenca> {

    private static DecimalFormat REAL_FORMAT = new DecimalFormat("0.###");
    private ArrayList<AlunoPresenca> alunos;
    private Context context;
    public AlunosPresencaAdapter(@NonNull Context c, @NonNull ArrayList<AlunoPresenca> objects) {
        super(c, 0,objects);
        this.alunos = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (alunos != null){

            //Inicializa objeto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta view a partir do xmll
            view = inflater.inflate(R.layout.linha_aluno, parent, false);

            //Vincula os campos
            TextView nomeAluno = (TextView) view.findViewById(R.id.tv_nome);
            TextView matriculaAluno = (TextView) view.findViewById(R.id.tv_matricula);

            //obtem valor de cada aluno e preenche os campos
            AlunoPresenca aluno = alunos.get(position);

            nomeAluno.setText(aluno.getNome());
            matriculaAluno.setText(REAL_FORMAT.format(aluno.getMatricula()));


        }

        return view;

    }
}
