package br.com.creche.AdapterTab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.creche.fragment.AlunosAlunosFragment;
import br.com.creche.fragment.AlunosAtividadesFragment;
import br.com.creche.fragment.AlunosPresencaFragment;


/**
 * Created by u6390869 on 29/10/2017.
 */

public class TabAdapterAlunos extends FragmentStatePagerAdapter {

    private String[] tituloAbas = {"ALUNOS","PRESENÇA", "ATIVIDADES"};

    public TabAdapterAlunos(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new AlunosAlunosFragment();
                break;
            case 1:
                fragment = new AlunosPresencaFragment();
                break;
            case 2:
                fragment = new AlunosAtividadesFragment();
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
