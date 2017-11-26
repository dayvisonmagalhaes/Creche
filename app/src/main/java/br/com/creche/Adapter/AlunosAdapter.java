package br.com.creche.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.creche.modelo.Aluno;

/**
 * Created by u6390869 on 26/11/2017.
 */

public class AlunosAdapter extends ArrayAdapter<Aluno> {

    private ArrayList<Aluno> alunos;
    private Context context;
    public AlunosAdapter(@NonNull Context c, @NonNull ArrayList<Aluno> objects) {
        super(c, 0,objects);
        this.alunos = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if (alunos != null){

            LayoutInflater inflater = context.getSystemService()

        }

        return view;

    }
}
