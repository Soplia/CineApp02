package imad.jrxie.cineapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewpagerFragment extends Fragment
{
    private int mTitle;
    private int mColor;
    private TextView mTextView;
    private LinearLayout mLinear;

    public static ViewpagerFragment newInstance(int title,int color)
    {
        ViewpagerFragment fragment=new ViewpagerFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("title",title);
        bundle.putInt("color",color);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mTitle=getArguments().getInt("title");
        mColor=getArguments().getInt("color"); 
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_layout,container,false);
        mTextView= (TextView) view.findViewById(R.id.fragment_textView);
        mTextView.setText("Page"+(mTitle+1));
        mLinear= (LinearLayout) view.findViewById(R.id.fragment_ll);
        mLinear.setBackgroundResource(mColor);
        return view;
    }

}