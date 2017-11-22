package br.com.creche.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.creche.fragment.AvisosFragment;
import br.com.creche.fragment.CalendarioFragment;
import br.com.creche.fragment.TurmasFragment;

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
                fragment = new TurmasFragment();
                break;
            case 1:
                fragment = new AvisosFragment();
                break;
            case 2:
                fragment = new CalendarioFragment();
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
