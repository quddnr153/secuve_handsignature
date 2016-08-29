package com.handsignature.secuve.secuvehandsignature;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by quddn on 2016-08-12.
 *
 */
public class SetupActivity extends AppCompatActivity {

    private ListView m_ListView;
    private CustomAdapter m_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        // 커스텀 어댑터 생성
        m_Adapter = new CustomAdapter();

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.setupList);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView에 아이템 추가
        m_Adapter.add("Byungwook Lee");
        m_Adapter.add("Woori Roh");
        m_Adapter.add("Daehan Wyee");
    }

    public class CustomAdapter extends BaseAdapter {

        // 문자열을 보관 할 ArrayList
        private ArrayList<String> m_List;

        // 생성자
        public CustomAdapter() {
            m_List = new ArrayList<String>();
        }

        // 현재 아이템의 수를 리턴
        @Override
        public int getCount() {
            return m_List.size();
        }

        // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
        @Override
        public Object getItem(int position) {
            return m_List.get(position);
        }

        // 아이템 position의 ID 값 리턴
        @Override
        public long getItemId(int position) {
            return position;
        }

        private class CustomHolder {
            TextView userName;
            Button modifyButton;
            Button deleteButton;
        }

        // 출력 될 아이템 관리
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            TextView userName = null;
            Button modifyButton = null;
            Button deleteButton = null;
            CustomHolder holder = null;

            // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
            if (convertView == null) {
                // view가 null일 경우 커스텀 레이아웃을 얻어 옴
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_setup_listview_item, parent, false);

                userName = (TextView) convertView.findViewById(R.id.userName);
                modifyButton = (Button) convertView.findViewById(R.id.modifyButton);
                deleteButton = (Button) convertView.findViewById(R.id.deleteButton);

                // 홀더 생성 및 Tag로 등록
                holder = new CustomHolder();
                holder.userName = userName;
                holder.modifyButton = modifyButton;
                holder.deleteButton = deleteButton;
                convertView.setTag(holder);
            } else {
                holder = (CustomHolder) convertView.getTag();
                userName = holder.userName;
                modifyButton = holder.modifyButton;
                deleteButton = holder.deleteButton;
            }

            // Text 등록
            userName.setText(m_List.get(position));

            // 버튼 이벤트 등록
            modifyButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, m_List.get(pos), Toast.LENGTH_SHORT).show();
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, m_List.get(pos), Toast.LENGTH_SHORT).show();
                }
            });

            // 리스트 아이템을 터치 했을 때 이벤트 발생
            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 클릭 : " + m_List.get(pos), Toast.LENGTH_SHORT).show();
                }
            });

            // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
            convertView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 롱 클릭 : " + m_List.get(pos), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            return convertView;
        }

        // 외부에서 아이템 추가 요청 시 사용
        public void add(String _msg) {
            m_List.add(_msg);
        }

        // 외부에서 아이템 삭제 요청 시 사용
        public void remove(int _position) {
            m_List.remove(_position);
        }
    }

    /*
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mListView = (ListView) findViewById(R.id.mList);

        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        mAdapter.addItem("Byungwook Lee");
        mAdapter.addItem("Woori Roh");
        mAdapter.addItem("Daehan Whee");

    }

    private class ViewHolder {
        public String userName;
        public Button modifyButton;
        public Button deleteButton;
    }

    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<ListData> mListData = new ArrayList<ListData>();

        public ListViewAdapter(Context mContext) {
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            // "listview_item" Layout을 inflate하여 convertView 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_setup_listview_item, parent, false);
            }

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            TextView userNameTextView = (TextView) convertView.findViewById(R.id.userName) ;
            Button modifyButtonView = (Button) convertView.findViewById(R.id.modifyButton) ;
            Button deleteButton = (Button) convertView.findViewById(R.id.deleteButton) ;

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ListData listViewItem = mListData.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            userNameTextView.setText(listViewItem.userName);

            return convertView;
        }

        public void addItem(String userName){
            ListData addInfo = null;
            addInfo = new ListData();
            addInfo.userName = userName;

            mListData.add(addInfo);
        }

        public void remove(int position){
            mListData.remove(position);
            dataChange();
        }

        public void sort(){
            Collections.sort(mListData, ListData.ALPHA_COMPARATOR);
            dataChange();
        }

        public void dataChange(){
            mAdapter.notifyDataSetChanged();
        }
    }
    */
}
