package br.com.creche.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.creche.fragment.ProfessorAvisosFragment;
import br.com.creche.fragment.ProfessorCalendarioFragment;
import br.com.creche.fragment.ProfessorTurmasFragment;

/**
 * Created by u6390869 on 29/10/2017.
 */

public class TabAdapterProfessor extends FragmentStatePagerAdapter {

    private String[] tituloAbas = {"TURMAS","AVISOS", "CALENDÁRIO"};

    public TabAdapterProfessor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new ProfessorTurmasFragment();
                break;
            case 1:
                fragment = new ProfessorAvisosFragment();
                break;
            case 2:
                fragment = new ProfessorCalendarioFragment();
                break;

        }


        return fragment;

    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAbas[position];
    }
}
