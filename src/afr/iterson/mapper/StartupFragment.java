package afr.iterson.mapper;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import afr.iterson.mapper.PopupFragment.DataTransfer;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class StartupFragment extends Fragment
{
private String TAG = "afr.iterson.StartupFragment";
DisplayImageOptions options;
private int screenwidth;
private int screenheight;
private int screenorientation;
private Context context;
private MainActivity main;
private LinearLayout ll_startupscreen;
@Override
public void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
}



@Override
public void onAttach(Activity activity)
{
	main = (MainActivity)activity;
	super.onAttach(activity);
}



@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
{
	View view = inflater.inflate(R.layout.startupscreen, container, false);
	ll_startupscreen =(LinearLayout) view.findViewById(R.id.linearlayout_startupscreen);
	TextView textview1 = (TextView)view.findViewById(R.id.textview1);
	textview1.setOnClickListener(new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			ll_startupscreen.setVisibility(View.INVISIBLE);
			Fragment f = (PopupFragment)main.fragment;
			main.addFragment(f,main.ROUTE1);
		}
	});
	
	TextView textview2 = (TextView)view.findViewById(R.id.textview2);
	textview2.setOnClickListener(new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			ll_startupscreen.setVisibility(View.INVISIBLE);
			Fragment f = (PopupFragment)main.fragment;
			main.addFragment(f,main.ROUTE2);
		}
	});
	
	return view;
}


	
	
}
