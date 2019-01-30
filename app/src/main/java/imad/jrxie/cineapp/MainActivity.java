package imad.jrxie.cineapp;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class MainActivity extends Activity
{
    private TextView tv1;//item.xml里的TextView：Textviewname
    private TextView tv2;//item.xml里的TextView：Textviewage
    private Button bt;//activity_main.xml里的Button
    private ListView lv;//activity_main.xml里的ListView
    private BaseAdapter adapter;//要实现的类
    private List<User> userList = new ArrayList<User>();//实体类
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt = (Button) findViewById(R.id.Button);
        lv = (ListView) findViewById(R.id.listView1);

        //模拟数据库
        for (int i = 0; i < 5; i++)
        {
            User ue = new User();//给实体类赋值
            ue.setName("小米"+i);
            ue.setAge("18");
            userList.add(ue);
        }

        /*  bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });*/

        adapter = new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                // TODO Auto-generated method stub
                return userList.size();//数目
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view;

                if (convertView==null) {
                    //因为getView()返回的对象，adapter会自动赋给ListView
                    view = inflater.inflate(R.layout.item_main, null);
                }else{
                    view=convertView;
                    Log.i("info","有缓存，不需要重新生成"+position);
                }
                tv1 = (TextView) view.findViewById(R.id.Textviewname);//找到Textviewname
                tv1.setText(userList.get(position).getName());//设置参数

                tv2 = (TextView) view.findViewById(R.id.Textviewage);//找到Textviewage
                tv2.setText(userList.get(position).getAge());//设置参数
                return view;
            }
            @Override
            public long getItemId(int position) {//取在列表中与指定索引对应的行id
                return 0;
            }
            @Override
            public Object getItem(int position) {//获取数据集中与指定索引对应的数据项
                return null;
            }
        };
        lv.setAdapter(adapter);

        /*
        //获取当前ListView点击的行数，并且得到该数据
        //获取当前ListView点击的行数，并且得到该数据
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv1 = (TextView) view.findViewById(R.id.Textviewname);//找到Textviewname
                String str = tv1.getText().toString();//得到数据
                Toast.makeText(MainActivity.this, "" + str, Toast.LENGTH_SHORT).show();//显示数据
                Intent it = new Intent(MainActivity.this, list0.class); //
                Bundle b = new Bundle();
                b.putString("we",str);  //string
                // b.putSerializable("dd",str);
                // it.putExtra("str_1",str);
                it.putExtras(b);
                startActivity(it);


            }
        });
        */
    }


}