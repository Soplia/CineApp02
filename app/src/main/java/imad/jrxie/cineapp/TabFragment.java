package imad.jrxie.cineapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jrxie on 2019/2/26.
 */

public class TabFragment extends Fragment
{
    private String mTitle;

    public void setTitle(String title) {
        this.mTitle = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_tablayout, null);
    }
}