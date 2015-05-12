package afr.iterson.mapper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class StartupFragment extends Fragment
{
	private String TAG = "afr.iterson.StartupFragment";
	DisplayImageOptions options;
	private  int PADDING; 
	private Context context;
	private MainActivity main;
	private LinearLayout ll_startupscreen;
	private TextView textview1, textview2;
	ImageView imageview3, imageview4;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity)
	{
		main = (MainActivity) activity;
		super.onAttach(activity);
		context = main.getApplicationContext();
		PADDING = getResources().getDimensionPixelSize(R.dimen.startscreen_infowindow_margin);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.startupscreen, container, false);
		populateLayout(view);
		return view;
	}

	private void populateLayout(View view)
	{

		ll_startupscreen = (LinearLayout) view.findViewById(R.id.linearlayout_startupscreen);
		
		textview1 = (TextView) view.findViewById(R.id.textview1);
		textview2 = (TextView) view.findViewById(R.id.textview2);
		imageview3 = (ImageView) view.findViewById(R.id.imageview3);
		imageview4 = (ImageView) view.findViewById(R.id.imageview4);
		
		textview1.setText(getResources().getString(R.string.startpage_route1)
				+ getResources().getString(R.string.startpage_distance) 
				+ Route1.calculateDistance() 
				+ " km \n" 
				+ getResources().getString(R.string.startpage_route1_subtitle));
		
		textview2.setText(getResources().getString(R.string.startpage_route2)
				+ getResources().getString(R.string.startpage_distance) 
				+ Route2.calculateDistance() 
				+ " km \n" 
				+ getResources().getString(R.string.startpage_route2_subtitle));

		
		
		
		
		textview1.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ll_startupscreen.setVisibility(View.INVISIBLE);
				//Fragment f = (PopupFragment) main.fragment;
				//main.addFragment(f, main.ROUTE1);
				main.CURRENT_ROUTE = main.ROUTE1;
				main.STARTSCREEN_VISIBLE = false;
				main.setupMapIfNeeded();;
			}
		});

		textview2.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ll_startupscreen.setVisibility(View.INVISIBLE);
//				Fragment f = (PopupFragment) main.fragment;
//				main.addFragment(f, main.ROUTE2);
				main.CURRENT_ROUTE = main.ROUTE2;
				main.STARTSCREEN_VISIBLE = false;
				main.setupMapIfNeeded();
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.startpage_infowindow, null, false), main.mDisplayWidth - PADDING,
						main.mDisplayHeight-PADDING, true);
				pw.showAtLocation(v.getRootView(), Gravity.CENTER, 0, 0);
				View mypopupview = pw.getContentView();
				TextView tv1 = (TextView)mypopupview.findViewById(R.id.textview_startpage);
				Spanned sp = Html.fromHtml(getString(R.string.startpage_infotext));
				tv1.setMovementMethod(LinkMovementMethod.getInstance());
				tv1.setText((sp));
				tv1.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						pw.dismiss();
					}
				});
			}
		});
	}
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		main.swapWidthAndHeight();
		int orient = getResources().getConfiguration().orientation;
		switch (orient)

		{
		case Configuration.ORIENTATION_LANDSCAPE:
			textview1.setBackgroundDrawable(getResources().getDrawable(R.drawable.startscreen_background_land1));
			textview2.setBackgroundDrawable(getResources().getDrawable(R.drawable.startscreen_background_land2));
			break;

		case Configuration.ORIENTATION_PORTRAIT:
			textview1.setBackgroundDrawable(getResources().getDrawable(R.drawable.startscreen_background_port1));
			textview2.setBackgroundDrawable(getResources().getDrawable(R.drawable.startscreen_background_port2));
			break;
		}
	}

}
