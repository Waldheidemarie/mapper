package afr.iterson.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import afr.iterson.mapper.PopupFragment.DataTransfer;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MainActivity extends LifecycleLoggingActivity implements DataTransfer, OnMapReadyCallback
{
	public static String TAG = "afr.iterson.MainActivity";

	private static final String TAG_POPUPFRAGMENT = "tag_popupfragment";
	private static final String KEY_STARTSCREEN_VISIBLE = "key_startscreen_visible";
	private static final String KEY_CURRENT_ROUTE = "key_current_route";
	private static final String KEY_LATLNG_LAT = "key_current_lattitude";
	private static final String KEY_LATLNG_LNG = "key_current_longitude";
	private static final String KEY_ZOOMLEVEL = "key_zoomlevel";
	private static final String KEY_SELECTED_MARKER = "key_selected_marker";
	private static final String KEY_OLDMARKER = "key_oldmarker";
	private static final String KEY_WIDTH = "key_width";
	private static final String KEY_HEIGHT = "key_height";
	private static final String KEY_BAR = "key_bar";
	private static final String KEY_ORIENT = "key_orient";
	public static int CURRENT_ROUTE;
	public static int ROUTE1 = 1;
	public static int ROUTE2 = 2;
	public static int STARTSCREEN = 3;

	private LatLng ROUTECENTER = new LatLng(52.011718, 4.709201);
	private double ZOOMLEVEL = 17;
	private static int ROUTECOLOR;
	private static float ROUTEDISTANCE;

	private int selectedmarker = 0;
	private int oldselectedmarker = 0;
	public boolean STARTSCREEN_VISIBLE = true;

	private GoogleMap map;
	PolylineOptions routedrawing;
	MarkerOptions markeroptions;
	private HashMap<Integer, Marker> markergroup;
	public MarkerObject[] markerobjects;
	private Route1 route1;
	private Route2 route2;

	public int mDisplayWidth;
	public int mDisplayHeight;
	Fragment fragment;
	Fragment startupFragment;
	private DisplayImageOptions options;
	private int mSubstract;
	private int mInitialOrientation;
	private boolean mFirstload;
	ArrayList<LatLng> locations;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//context = getBaseContext();
		markergroup = new HashMap<Integer, Marker>();
		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty).showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true).displayer(new RoundedVignetteBitmapDisplayer(20, 3))
				.build();
		startupFragment = new StartupFragment();
		fragment = new PopupFragment();
		fragment.setRetainInstance(true);
		if (savedInstanceState != null)
		{
			STARTSCREEN_VISIBLE = savedInstanceState.getBoolean(KEY_STARTSCREEN_VISIBLE);
			ZOOMLEVEL = (int) savedInstanceState.getDouble(KEY_ZOOMLEVEL);
			ROUTECENTER = new LatLng(savedInstanceState.getDouble(KEY_LATLNG_LAT),
					savedInstanceState.getDouble(KEY_LATLNG_LNG));
			if (map == null)
			{
				Log.d(TAG, " Map is null in onCreate...");
				setupMapIfNeeded();
			}
		}
		else
		{
			mFirstload = true;
			setupMapIfNeeded();
		}
	}

	@Override
	public void onBackPressed()
	{
		selectedmarker = 0;
		oldselectedmarker = 0;
		addFragment(startupFragment, STARTSCREEN);
	}

	public void setupMapIfNeeded()
	{
		if (map == null)
		{
			Log.d(TAG, " Map is setup again...");
			SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);
			mapFragment.setRetainInstance(true);
			mapFragment.getMapAsync(this);
		}
		if (map != null)
		{
			Log.d(TAG, " Map isalready setup , goto onMapReady");
			onMapReady(map);
		}

	}

	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		this.map = googleMap;

		if (!STARTSCREEN_VISIBLE)
		{
			addFragment(fragment, CURRENT_ROUTE);
		} else
		{
			addFragment(startupFragment, STARTSCREEN);
		}
		if ((locations != null))
		{
			this.map.clear();
			this.map.setOnMarkerClickListener(new onMarkerClickListener());
			drawRoute();
			addMarkers();
		}
		
	}

	/**
	 * Populating a hashmap <Integer,Marker> this hasmap will be accessed from
	 * the fragment to retrieve texts and images. If there is a current marker
	 * it will be displayed green.
	 */
	private void addMarkers()
	{
		for (MarkerObject m : markerobjects)
		{
			markergroup.put(
					m.getId(),
					map.addMarker(new MarkerOptions().position(m.getLocation()).flat(true)
							.icon(BitmapDescriptorFactory.fromResource(m.getIconresource()))));
		}
	}

	/**
	 * A route will be drawn on the basis of locations and resources defined in
	 * the Route file and loaded as a response to the buttonclick;
	 */
	private void drawRoute()
	{
		routedrawing = new PolylineOptions();
		routedrawing.color(getResources().getColor(ROUTECOLOR));
		routedrawing.width(5);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.setMyLocationEnabled(true);
		map.getUiSettings().setTiltGesturesEnabled(false);
		for (LatLng loc : locations)
		{
			routedrawing.add(loc);
		}
		map.addPolyline(routedrawing);
		map.moveCamera(CameraUpdateFactory.newLatLng(locations.get(0)));
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ROUTECENTER, (float) ZOOMLEVEL);
		map.animateCamera(update);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus)
		{
			Log.i(TAG, "onWindowFocusChanged ");
			// Get the size of the display so this View knows where borders are
			setScreenDims();
			
			// jumps straight to the map on the position where the user closed
			// the application
			if (startupFragment != null && STARTSCREEN_VISIBLE == false)
			{
				FragmentManager fm = getSupportFragmentManager();
				fm.beginTransaction().show(fragment).commit();
			}
			// gives the user a startupscreen
			else
			{
				if (!startupFragment.isAdded())
				{
					FragmentManager fm = getSupportFragmentManager();
					FragmentTransaction transaction = fm.beginTransaction();
					transaction.replace(R.id.map, startupFragment);
					transaction.commit();
				}
			}
		}
	}

	public void setScreenDims()
	{
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativelayout1);
		mInitialOrientation = getResources().getConfiguration().orientation;
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		mSubstract = height - layout.getHeight();
		mDisplayWidth = layout.getWidth();
		mDisplayHeight = layout.getHeight();
	}
	
	
	// making a correction to properly display the foto's in the PopupFragment
	// after a configuration change.
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

	/**
	 * In response to either a buttonclick in the startupscreen or a backpress
	 * from the map-screen fragments will be made visible;
	 */
	public void addFragment(Fragment f, int type)
	{
		Log.i(TAG, "addFragment(), Type " + type);
		if (type == (ROUTE1))
		{
			route1 = new Route1();
			CURRENT_ROUTE = ROUTE1;
			STARTSCREEN_VISIBLE = true;
			if (mFirstload == true)
			{
				ROUTECENTER = Route1.getDEFAULTCENTER();
				ZOOMLEVEL = Route1.getDEFAULTZOOMLEVEL();
				mFirstload = false;
			}
			STARTSCREEN_VISIBLE = false;
			setROUTECOLOR(Route1.getDEFAULTROUTECOLOR());
			markerobjects = route1.getMarkerobjects();
			locations = route1.getLocationsForPolyline();
			//setupMapIfNeeded();
			if (!f.isAdded())
			{
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.replace(R.id.map, f);
				transaction.commit();
			}
		} else if (type == ROUTE2)
		{
			route2 = new Route2();
			CURRENT_ROUTE = ROUTE2;
			if (mFirstload == true)
			{
				ROUTECENTER = Route2.getDEFAULTCENTER();
				ZOOMLEVEL = Route2.getDEFAULTZOOMLEVEL();
				mFirstload = false;
			}
			STARTSCREEN_VISIBLE = false;
			setROUTECOLOR(Route2.getDEFAULTROUTECOLOR());
			markerobjects = route2.getMarkerobjects();
			locations = route2.getLocationsForPolyline();
			//setupMapIfNeeded();
			if (!f.isAdded())
			{
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.replace(R.id.map, f);
				transaction.commit();
			}
		} else if (type == STARTSCREEN)
		{
			STARTSCREEN_VISIBLE = true;
			if (!f.isAdded())
			{
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.replace(R.id.map, f);
				transaction.commit();
			}
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		Log.i(TAG, "In onrestore instance state");
		super.onRestoreInstanceState(savedInstanceState);
		selectedmarker = savedInstanceState.getInt(KEY_SELECTED_MARKER);
		oldselectedmarker = savedInstanceState.getInt(KEY_OLDMARKER);
		CURRENT_ROUTE = savedInstanceState.getInt(KEY_CURRENT_ROUTE);
		mDisplayWidth = savedInstanceState.getInt(KEY_WIDTH);
		mDisplayHeight = savedInstanceState.getInt(KEY_HEIGHT);
		mSubstract = savedInstanceState.getInt(KEY_BAR);
		
		//if saved from landscape
		if(mDisplayWidth>mDisplayHeight)
		{
			mInitialOrientation = 2;
			if(getResources().getConfiguration().orientation != mInitialOrientation)
			{
				swapWidthAndHeight();
			}
		}
		
		//if saved from portrait
		else
		{
			mInitialOrientation = 1;
			if(getResources().getConfiguration().orientation != mInitialOrientation)
			{
				swapWidthAndHeight();
			}
			
		}		
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

	// Layout changes will be handled in the onConfigurationChanged methods of
	// the StartScreen and the Popup Fragment
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		Log.i(TAG, "In onConfigurationChanged: width:" + mDisplayWidth + " height: " + mDisplayHeight);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		Log.i(TAG, "In onSaveInstanceState");
		CameraPosition position = map.getCameraPosition();
		LatLng target = position.target;
		ROUTECENTER = target;
		outState.putDouble(KEY_LATLNG_LAT, target.latitude);
		outState.putDouble(KEY_LATLNG_LNG, target.longitude);
		double zoom = position.zoom;
		ZOOMLEVEL = zoom;
		outState.putDouble(KEY_ZOOMLEVEL, zoom);
		outState.putBoolean(KEY_STARTSCREEN_VISIBLE, STARTSCREEN_VISIBLE);
		outState.putInt(KEY_CURRENT_ROUTE, CURRENT_ROUTE);
		outState.putInt(KEY_SELECTED_MARKER, selectedmarker);
		outState.putInt(KEY_OLDMARKER, oldselectedmarker);
		outState.putInt(KEY_WIDTH, mDisplayWidth);
		outState.putInt(KEY_HEIGHT, mDisplayHeight);
		outState.putInt(KEY_BAR, mSubstract);
		outState.putInt(KEY_ORIENT, mInitialOrientation);

		
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

	// Sets the appropriate marker colors after a press on the next marker arrow
	// in the popupfragment, a markerclick, or after onresume.
	@Override
	public void onDataPass(int oldmarkerid, int markerid)
	{
		if (oldmarkerid != markerid)
		{
			Log.i(TAG, "Old and New : " + oldmarkerid + " ," + markerid);
			oldselectedmarker = oldmarkerid;
			selectedmarker = markerid;
			Marker m = markergroup.get((Integer) markerid);
			m.setIcon(BitmapDescriptorFactory.fromResource(markerobjects[markerid].getIconresource2()));
			Marker mold = markergroup.get((Integer) oldmarkerid);
			mold.setIcon(BitmapDescriptorFactory.fromResource(markerobjects[oldmarkerid].getIconresource()));
		}
	}

	/**
	 * Removing the fragments from the activity after a Pause to prevent major
	 * crashes.
	 */
	@Override
	protected void onPause()
	{
		super.onPause();
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.remove(fragment);
		transaction.remove(startupFragment);
		transaction.commit();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		if (!fragment.isAdded() && !STARTSCREEN_VISIBLE)
		{
			addFragment(fragment, CURRENT_ROUTE);
		} else
		{
			addFragment(startupFragment, STARTSCREEN);
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

	public double getZOOMLEVEL()
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

	public void setRouteDistance(float routedistance)
	{
		ROUTEDISTANCE = routedistance;
	}

	public float getRouteDistance()
	{
		return ROUTEDISTANCE;
	}

}
