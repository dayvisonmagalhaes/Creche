package br.com.creche.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import br.com.creche.modelo.Aluno;
import testedelayout.cursoandroid.com.creche.R;

/**
 * Created by u6390869 on 14/01/2018.
 */

public class AlunosChamadaAdapter extends ArrayAdapter<Aluno>{

    private static DecimalFormat REAL_FORMAT = new DecimalFormat("0.###");
    private ArrayList<Aluno> alunos;
    private Context context;
    public AlunosChamadaAdapter(@NonNull Context c, @NonNull ArrayList<Aluno> objects) {
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

            //Monta view a partir do xml

            //view = inflater.inflate(R.layout.linha_aluno, parent, false);

            //TextView nomeAluno = (TextView) view.findViewById(R.id.tv_nome);
            //TextView matriculaAluno = (TextView) view.findViewById(R.id.tv_matricula);

            view = inflater.inflate(R.layout.linha_aluno_presenca, parent, false);

            //CheckBox ck_status = (CheckBox) view.findViewById(R.id.rowCheckBox);
            TextView nomeAluno = (TextView) view.findViewById(R.id.tv_nome_chamada);
            TextView matriculaAluno = (TextView) view.findViewById(R.id.tv_matricula_chamada);

            Aluno aluno = alunos.get(position);

            nomeAluno.setText(aluno.getNome());
            matriculaAluno.setText(REAL_FORMAT.format(aluno.getMatricula()));


        }

        return view;

    }
}
