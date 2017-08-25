package com.example.deardiary;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter1 extends ArrayAdapter<String>{
	Integer layout;
	Friendinfo context;
	ArrayList<String> n;
	
	public CustomAdapter1(Friendinfo context,int layout,ArrayList<String> n)
	{
		super(context,layout,n);
		this.n = n;

		this.layout = layout;
		this.context = context;
	}


@Override
public View getView(int position, View convertView, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	convertView = inflater.inflate(layout, parent, false);
	TextView t1 = (TextView) convertView.findViewById(R.id.textView1);
	t1.setText(n.get(position));
	



	MyTag1 mytag1 = new MyTag1();
	mytag1.s1 = n.get(position);

			convertView.setTag(mytag1);
	return convertView;
	}
}
class MyTag1{
	String s1;
}

