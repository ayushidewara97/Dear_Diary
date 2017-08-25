package adapters;

import java.util.ArrayList;

import com.example.deardiary.BankInfo1;
import com.example.deardiary.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BankAdapter extends ArrayAdapter<String>{
	Integer layout;
	BankInfo1 context;
	ArrayList<String> b_u;
	ArrayList<String> b_a;
	ArrayList<String> b_n;
	ArrayList<String> b_l;
	public BankAdapter(BankInfo1 context, int layout,ArrayList<String> b_u,ArrayList<String> b_a,ArrayList<String> b_n,ArrayList<String> b_l) {
		super(context, layout,b_u);
		this.b_u = b_u;
		this.b_a = b_a;
		this.b_n = b_n;
		this.b_l = b_l;
		this.layout = layout;
		this.context = context;

	}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			convertView = inflater.inflate(layout, parent, false);
			TextView t1 = (TextView) convertView.findViewById(R.id.textView1);
			t1.setText(b_u.get(position));
			



			MyTag1 mytag1 = new MyTag1();
			mytag1.s1 = b_u.get(position);

					convertView.setTag(mytag1);
			return convertView;	
			}
}
class MyTag1{
	String s1;
}

	
