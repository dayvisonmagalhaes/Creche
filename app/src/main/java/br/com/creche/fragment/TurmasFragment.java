package br.com.creche.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import testedelayout.cursoandroid.com.creche.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TurmasFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> turmas;


    public TurmasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_turmas, container, false);

        //Monta ListView e adapter
        listView = (ListView) view.findViewById(R.id.lv_turmas);
        adapter = new ArrayAdapter(
                getActivity(),
                R.layout.lista_turma,
                turmas

        );

        return view;
    }

}
