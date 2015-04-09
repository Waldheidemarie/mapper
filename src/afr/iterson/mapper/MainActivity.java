package afr.iterson.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import afr.iterson.mapper.PopupFragment.DataTransfer;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MainActivity extends FragmentActivity implements DataTransfer, OnMapReadyCallback
{
	public static String TAG = "afr.iterson.MainActivity";
	public static int ROUTE1 = 1;
	public static int ROUTE2 = 2;
	public static int STARTSCREEN = 3;

	public MarkerObject[] markerobjects;
	private HashMap<Integer, Marker> markergroup;
	private LatLng ROUTECENTER = new LatLng(52.011718, 4.709201);
	private int ZOOMLEVEL = 17;

	PolylineOptions routedrawing;
	MarkerOptions markeroptions;
	private int selectedmarker = 0;
	private int oldselectedmarker = 0;
	private boolean STARTSCREEN_VISIBLE;

	private static int WIDTH = 10;
	private static int[] colors = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN,
			Color.MAGENTA, Color.RED, Color.WHITE };
	private static int ROUTECOLOR;
	
	
	private PopupWindow pw;
	private GoogleMap map;
	private Route1 route1;
	private Route2 route2;
	private Context context;
	public int mDisplayWidth;
	public int mDisplayHeight;
	private RelativeLayout layout;
	Fragment fragment;
	Fragment startupFragment;
	private DisplayImageOptions options;
	private int mSubstract;
	private int mInitialOrientation;
	public View fragmentview;
	ArrayList<LatLng> locations;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null)
		{
			STARTSCREEN_VISIBLE = savedInstanceState.getBoolean("startscreen_visible");
		}
		else
		{
			STARTSCREEN_VISIBLE = true;
		}
		setContentView(R.layout.activity_main);
		context = getBaseContext();
		markergroup = new HashMap<Integer, Marker>();
		setupMapIfNeeded();
		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty).showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true).displayer(new RoundedVignetteBitmapDisplayer(20, 3))
				.build();
		startupFragment = new StartupFragment();
		fragment = new PopupFragment();
		layout = (RelativeLayout) findViewById(R.id.relativelayout1);
		
		
	}

	@Override
	public void onBackPressed()
	{
		// if we are in viewing mode return to the main screen
		addFragment(startupFragment, STARTSCREEN);
		
		// if we are already on the startscreen clean up stuff
	}

	private void setupMapIfNeeded()
	{
		if (map == null)
		{
			SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);
			mapFragment.getMapAsync(this);
		}
		if (map != null)
		{

		}

	}

	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		this.map = googleMap;
		if ((locations!=null))
		{
			this.map.clear();
			this.map.setOnMarkerClickListener(new onMarkerClickListener());
			drawRoute();
			addMarkers();
		}
	}

	// populating a hashmap <Integer,Marker>
	private void addMarkers()
	{
		for (MarkerObject m : markerobjects)
		{
			markergroup.put(
					m.getId(),
					map.addMarker(new MarkerOptions().position(m.getLocation()).flat(true)
							.icon(BitmapDescriptorFactory.fromResource(m.getIconresource()))));
		}
		markergroup.get(0).setIcon(BitmapDescriptorFactory.fromResource(markerobjects[0].getIconresource2()));
	}

	private void drawRoute()
	{
		routedrawing = new PolylineOptions();
		//routedrawing.color(colors[6]);
		routedrawing.color(getResources().getColor(ROUTECOLOR));
		routedrawing.width(5);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.setMyLocationEnabled(true);
		for (LatLng loc : locations)
		{
			routedrawing.add(loc);
		}
		map.addPolyline(routedrawing);
		map.moveCamera(CameraUpdateFactory.newLatLng(locations.get(0)));
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ROUTECENTER, ZOOMLEVEL);
		map.animateCamera(update);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus)
		{
			// Get the size of the display so this View knows where borders are
			mInitialOrientation = getResources().getConfiguration().orientation;
			Display display = getWindowManager().getDefaultDisplay();
			int height = display.getHeight();
			mSubstract = height - layout.getHeight();
			mDisplayWidth = layout.getWidth();
			mDisplayHeight = layout.getHeight();
			//jumps straight to the map on the position where the user closed the application
			if(startupFragment!=null && STARTSCREEN_VISIBLE == false)
			{
				FragmentManager fm = getSupportFragmentManager();
				fm.beginTransaction()
						.show(fragment)
						.commit();
			}
			//gives the user a startupscreen
			else
			{
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.replace(R.id.map, startupFragment);
				transaction.commit();
			}
		}
	}

	public void swapWidthAndHeight()
	{
		switch (mInitialOrientation)
		{
		case (Configuration.ORIENTATION_PORTRAIT):
		{
			int parking = mDisplayWidth;
			mDisplayWidth = mDisplayHeight + mSubstract;
			mDisplayHeight = parking - mSubstract;
			break;
		}
		case (Configuration.ORIENTATION_LANDSCAPE):
		{
			int parking = mDisplayHeight;
			mDisplayHeight = mDisplayWidth - mSubstract;
			mDisplayWidth = parking + mSubstract;
			break;

		}
		}
	}

	//in response to either a buttonclick in the startupscreen or a backpress from the map-screen
	public void addFragment(Fragment f, int type)
	{
		if (type == (ROUTE1))
		{
			route1 = new Route1();
			ROUTECENTER = Route1.getDEFAULTCENTER(); 
			ZOOMLEVEL = Route1.getDEFAULTZOOMLEVEL();
			STARTSCREEN_VISIBLE = false;
			setROUTECOLOR( Route1.getDEFAULTROUTECOLOR());
			markerobjects = route1.getMarkerobjects();
			locations = route1.getLocationsForPolyline();
			onMapReady(map);
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(R.id.map, f);
			transaction.commit();
		} else if (type == ROUTE2)
		{
			route2 = new Route2();
			ROUTECENTER = Route2.getDEFAULTCENTER(); 
			ZOOMLEVEL = Route2.getDEFAULTZOOMLEVEL();
			STARTSCREEN_VISIBLE = false;
			setROUTECOLOR( Route2.getDEFAULTROUTECOLOR());
			markerobjects = route2.getMarkerobjects();
			locations = route2.getLocationsForPolyline();
			onMapReady(map);
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(R.id.map, f);
			transaction.commit();
		} else if (type == STARTSCREEN)
		{
			STARTSCREEN_VISIBLE = true;
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(R.id.map, f);
			transaction.commit();
		}
	}

	@Override
	protected void onResume()
	{
		Log.i(TAG,"In onresume");
		
		super.onResume();
	}

	
	
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		Log.i(TAG,"In onrestore instance state");

		super.onRestoreInstanceState(savedInstanceState);
	}

	private class onMarkerClickListener implements OnMarkerClickListener
	{

		@Override
		public boolean onMarkerClick(Marker marker)
		{
			// Setting and Changing the colour of the selectedmarker
			oldselectedmarker = selectedmarker;
			for (Entry<Integer, Marker> key : markergroup.entrySet())
			{
				if (key.getValue().getId().equals(marker.getId()))
					selectedmarker = key.getKey().intValue();
			}
			onDataPass(oldselectedmarker, selectedmarker);
			// showing the image and the text as a response to the markerclick
			PopupFragment f = (PopupFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			f.populateLayout(f.getView());

			return true;
		}
	}

	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		Log.i(TAG,"In onConfigurationChanged");

		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		Log.i(TAG,"In onSaveInstanceState");
		outState.putBoolean("startscreen_visible", STARTSCREEN_VISIBLE);
		super.onSaveInstanceState(outState);
	}

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener
	{
		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
		{
			if (loadedImage != null)
			{
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay)
				{
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	
	//sets the appropriate marker colors after a press on the next marker arrow in the popupfragment
	@Override
	public void onDataPass(int oldmarkerid, int markerid)
	{
		if (oldmarkerid != markerid)
		{
			oldselectedmarker = oldmarkerid;
			selectedmarker = markerid;
			Marker m = markergroup.get((Integer) markerid);
			m.setIcon(BitmapDescriptorFactory.fromResource(markerobjects[markerid].getIconresource2()));
			Marker mold = markergroup.get((Integer) oldmarkerid);
			mold.setIcon(BitmapDescriptorFactory.fromResource(markerobjects[oldmarkerid].getIconresource()));
		}
	}

	public int getSelectedMarker()
	{
		return selectedmarker;
	}

	public int getmDisplayWidth()
	{
		return mDisplayWidth;
	}

	public void setmDisplayWidth(int mDisplayWidth)
	{
		this.mDisplayWidth = mDisplayWidth;
	}

	public int getmDisplayHeight()
	{
		return mDisplayHeight;
	}

	public void setmDisplayHeight(int mDisplayHeight)
	{
		this.mDisplayHeight = mDisplayHeight;
	}

	public LatLng getROUTECENTER()
	{
		return ROUTECENTER;
	}

	public void setROUTECENTER(LatLng rOUTECENTER)
	{
		ROUTECENTER = rOUTECENTER;
	}

	public int getZOOMLEVEL()
	{
		return ZOOMLEVEL;
	}

	public void setZOOMLEVEL(int zOOMLEVEL)
	{
		ZOOMLEVEL = zOOMLEVEL;
	}
	public void setROUTECOLOR(int rOUTECOLOR)
	{
		ROUTECOLOR = rOUTECOLOR;
	}

}
