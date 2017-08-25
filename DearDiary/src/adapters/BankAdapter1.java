package adapters;

import java.util.ArrayList;

import com.example.deardiary.BankInfo1;
import com.example.deardiary.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BankAdapter1 extends ArrayAdapter<String>{
	int layout;
	BankInfo1 context;
	ArrayList<String> b_u;

	public BankAdapter1(BankInfo1 context, int layout,	ArrayList<String> b_u) {
		super(context, layout, b_u);
			this.b_u = b_u;
			this.layout = layout;
			this.context = context;
	}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			convertView = inflater.inflate(layout, parent, false);
			TextView t1 = (TextView) convertView.findViewById(R.id.textView1);
			t1.setText(b_u.get(position));
			



			MyTag2 bank_mytag = new MyTag2();
			bank_mytag.s1 = b_u.get(position);

					convertView.setTag(bank_mytag);
			return convertView;	
			}
}
class MyTag2{
	String s1;
}
