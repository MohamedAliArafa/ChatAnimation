package com.example.mohamedarafa.chatanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        TextView tv = (TextView) findViewById(R.id.tv_add);
        final Adapter adapter = new Adapter();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add("what?", adapter.getItemCount());
                rv.scrollToPosition(adapter.getItemCount()-1);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fad_in_out);
                view.startAnimation(animation);
            }
        });
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new SlideInUpAnimator());
        rv.setAdapter(adapter);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

        private ArrayList<String> list = new ArrayList<>();

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title;

            MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.textView);
            }
        }

        Adapter() {
            list.add("Hello");
            notifyItemInserted(0);
            list.add("Hi!");
            notifyItemInserted(1);
            list.add("How Are You");
            notifyItemInserted(2);
            list.add("Good U");
            notifyItemInserted(3);
            list.add("Fine!!");
            notifyItemInserted(4);
        }

        public void add(String text, int position) {
            list.add(position, text);
            notifyItemInserted(position);
        }

        public Adapter(ArrayList<String> list) {
            this.list = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            if (viewType == 1) {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_message_me, parent, false);
            } else {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_message_you, parent, false);
            }
            return new MyViewHolder(itemView);
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? 1 : 2;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.title.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
//            return list.size();
        }
    }
}
