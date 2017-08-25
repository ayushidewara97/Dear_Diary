package com.example.deardiary;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pass_Frag extends Fragment{
	Button _submit;
	EditText _change_pass;
	DatabaseHelper dh;
	SQLiteDatabase db;
	View v;
	//FragmentManager fm;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 //v= inflater.inflate(R.layout.pass_frag,frame,false);
		v= inflater.inflate(R.layout.pass_frag, container, false);
		 Bundle bundle = this.getArguments();
	        String myValue = bundle.getString("answer");
	       // fm = getFragmentManager();
	        //fm = getActivity().getSupportFragmentManager();
	        _submit = (Button) v.findViewById(R.id.submit_password);
	        _change_pass = (EditText) v.findViewById(R.id.change_pass_et);
	        
	        dh=new DatabaseHelper(getActivity());
			 db = dh.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put("password", _change_pass.getText().toString());

			int ans=db.update("FriendsTable", cv,"answer=?", new String[]{myValue});
			if(ans==1)
			{
				Toast.makeText(v.getContext(), "Password is changed", Toast.LENGTH_SHORT).show();
				
			}
		
			else
			{
				Toast.makeText(v.getContext(), "Password couldn't change", Toast.LENGTH_SHORT).show();
				
			}
	        _submit.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                //fm.beginTransaction().replace(R.id.container,new child1()).commit();

	            }
	        });
	        return v;
	}
}
