package com.example.deardiary;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>{
	Integer layout;
	Friendinfo context;
	ArrayList<String> n;
	ArrayList<String> a;
	ArrayList<String> b;
	ArrayList<String> m;
	ArrayList<String> ani;
	ArrayList<String> ml;
	ArrayList<String> g;

	public CustomAdapter(Friendinfo context,int layout,ArrayList<String> n,ArrayList<String> a,ArrayList<String> b,	ArrayList<String> m,ArrayList<String> ani,ArrayList<String> ml,ArrayList<String> g)
	{
		super(context,layout,n);
		this.n = n;
		this.a = a;
		this.b = b;
		this.m = m;
		this.ani = ani;
		this.ml = ml;
		this.g = g;

		this.layout = layout;
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layout, parent, false);
		TextView t1 = (TextView) convertView.findViewById(R.id.textView1);
		TextView t2 = (TextView) convertView.findViewById(R.id.textView2);
		TextView t3 = (TextView) convertView.findViewById(R.id.textView3);
		TextView t4 = (TextView) convertView.findViewById(R.id.textView4);
		TextView t5 = (TextView) convertView.findViewById(R.id.textView5);
		TextView t6 = (TextView) convertView.findViewById(R.id.textView6);
		//TextView t7 = (TextView) convertView.findViewById(R.id.textView7);


		t1.setText(n.get(position));
		t2.setText(a.get(position));
		t3.setText(b.get(position));
		t4.setText(m.get(position));
		t5.setText(ani.get(position));
		t6.setText(ml.get(position));
		//t7.setText(g.get(position));



		MyTag mytag = new MyTag();
		mytag.s1 = n.get(position);
		mytag.s2 = a.get(position);
		mytag.s3 = b.get(position);
		mytag.s4 = m.get(position);
		mytag.s5 = ani.get(position);
		mytag.s6 = ml.get(position);
		mytag.s7 = g.get(position);

				convertView.setTag(m);
		return convertView;
	}
}
class MyTag{
	String s1,s2,s3,s4,s5,s6,s7;
}
