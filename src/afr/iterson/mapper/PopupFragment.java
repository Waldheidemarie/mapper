package afr.iterson.mapper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;

public class PopupFragment extends Fragment
{
	private String TAG = "afr.iterson.PopupFragment";
	DisplayImageOptions options;
	public int markerid;
	public DataTransfer datatransfer;
	public ImageView image;
	public ScrollView scrollview;
	//private ScrollView scrollview;
	public TextView tv1;
	private int screenwidth;
	private int screenheight;
	private int screenorientation;
	private Context context;
	private MainActivity main;
	private String htmlstring = "<b>this is <h1>supposed</h1> to be bold text</b><p>and a new paragraph</p>";

	public interface DataTransfer
	{
		public void onDataPass(int oldmarkerid, int newmarkerid);
	}

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		datatransfer = (DataTransfer) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty).showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true).displayer(new RoundedVignetteBitmapDisplayer(20, 3))
				.build();

		context = getActivity().getApplicationContext();
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screenorientation = getActivity().getResources().getConfiguration().orientation;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		main = (MainActivity) getActivity();
		markerid = main.getSelectedMarker();
		screenwidth = main.getmDisplayWidth();
		screenheight = main.getmDisplayHeight();
		View view = inflater.inflate(R.layout.popupwindow, container, false);
		populateLayout(view);
		image.setVisibility(View.INVISIBLE);
		scrollview.setVisibility(View.INVISIBLE);
		main.fragmentview = view;
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onViewCreated(View v, Bundle savedInstanceState)
	{
		super.onViewCreated(v, savedInstanceState);

	}

	// The behavior of the buttonbar below, the main image and the infotext. The
	// method retrieves the current selected marker
	// and fetches the appropriate resources.

	public void populateLayout(View view)
	{
		MarkerObject m = ((MainActivity) getActivity()).markerobjects[main.getSelectedMarker()];
		ImageView buttonshowpicture = (ImageView)view.findViewById(R.id.buttonshowpicture);
		changeColor(buttonshowpicture);
		buttonshowpicture.setOnClickListener(new ButtonClickListener(2));

		
		String helper1 = getResources().getString(m.getDescriptiontext());
		scrollview = (ScrollView) view.findViewById(R.id.scrollview1);
		scrollview.setOnClickListener(new ButtonClickListener(6));
		
		Spanned sp = Html.fromHtml(getString(m.getDescriptiontext()));
		tv1 = (TextView) view.findViewById(R.id.textView1);
		tv1.setMovementMethod(LinkMovementMethod.getInstance());
		tv1.setOnClickListener(new ButtonClickListener(6));
		tv1.setText((sp));
		
		ImageView buttonshowtext = (ImageView) view.findViewById(R.id.buttonshowtext);
		buttonshowtext.setOnClickListener(new ButtonClickListener(3));
		changeColor(buttonshowtext);
		final ImageView buttonprevious = (ImageView) view.findViewById(R.id.buttonpreviousitem);
		buttonprevious.setOnClickListener(new ButtonClickListener(1));
		changeColor(buttonprevious);

		final ImageView buttonnext = (ImageView) view.findViewById(R.id.buttonnextitem);
		buttonnext.setOnClickListener(new ButtonClickListener(4));
		changeColor(buttonnext);
		
		image = (ImageView) view.findViewById(R.id.imageview1);
		image.setOnClickListener(new ButtonClickListener(5));
		image.setVisibility(View.VISIBLE);
		setImageSize(image, scrollview);
		String helper2 = m.getImageurl();
		ImageLoader.getInstance().displayImage(helper2, image, options);

	}

	private class ButtonClickListener implements View.OnClickListener
	{
		private int buttonid;
		public ButtonClickListener(int buttonid)
		{
			this.buttonid = buttonid;
		}
		
		@Override
		public void onClick(View v)
		{
			switch (buttonid)
			{
			case 1:
			{
				Log.d(TAG, "buttonprevious");
				markerid = main.getSelectedMarker();
				int oldmarkerid = markerid;
				markerid--;
				if (markerid < 0)
				{
					markerid = ((MainActivity) getActivity()).markerobjects.length - 1;
				}
				passData(oldmarkerid, markerid);
				MarkerObject m = ((MainActivity) getActivity()).markerobjects[markerid];
				String text = getResources().getString(m.getDescriptiontext());
				String url = m.getImageurl();
				Spanned sp = Html.fromHtml(getString(m.getDescriptiontext()));
				tv1.setText((sp));
				ImageLoader.getInstance().displayImage(url, image, options);

				break;
			}
			case 2:
			{
				Log.d(TAG, "buttontogglepicture");
				if (image.getVisibility() == View.INVISIBLE)
				{
					image.setVisibility(View.VISIBLE);
					MarkerObject m = ((MainActivity) getActivity()).markerobjects[main.getSelectedMarker()];
					String url = m.getImageurl();
					Spanned sp = Html.fromHtml(getString(m.getDescriptiontext()));
					tv1.setText((sp));
					ImageLoader.getInstance().displayImage(url, image, options);

				} else
				{
					image.setVisibility(View.INVISIBLE);
				}

				break;
			}
			case 3:
			{
				Log.d(TAG, "buttonshowtext");
				if (scrollview.getVisibility() == View.INVISIBLE)
				{
					scrollview.setVisibility(View.VISIBLE);
					MarkerObject m = ((MainActivity) getActivity()).markerobjects[main.getSelectedMarker()];
					Spanned sp = Html.fromHtml(getString(m.getDescriptiontext()));
					tv1.setText((sp));
					String url = m.getImageurl();
					ImageLoader.getInstance().displayImage(url, image, options);
				} else
				{
					scrollview.setVisibility(View.INVISIBLE);
				}

				break;
			}
			case 4:
			{
				Log.d(TAG, "buttonshownext");
				markerid = main.getSelectedMarker();
				int oldmarkerid = markerid;
				markerid++;
				if (markerid > ((MainActivity) getActivity()).markerobjects.length - 1)
				{
					markerid = 0;
				}
				passData(oldmarkerid, markerid);
				MarkerObject m = ((MainActivity) getActivity()).markerobjects[markerid];
				String url = m.getImageurl();
				Spanned sp = Html.fromHtml(getString(m.getDescriptiontext()));
				tv1.setText((sp));
				ImageLoader.getInstance().displayImage(url, image, options);
				break;
			}
			case 5:
			{
				Log.d(TAG, "buttonmainimage");
				image.setVisibility(View.INVISIBLE);
				break;
			}
			case 6:
			{
				Log.d(TAG, "buttontextview");
				scrollview.setVisibility(View.INVISIBLE);
				break;
			}

			}

		}
	}

	public void changeColor(ImageView im)
	{
		im.getDrawable().setColorFilter(getResources().getColor(R.color.purple1), Mode.SRC_ATOP);
	}

	public void passData(int oldmarkerid, int newmarkerid)
	{
		datatransfer.onDataPass(oldmarkerid, newmarkerid);
	}

	public void setImageSize(ImageView imageview, ScrollView sv)
	{
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		// screenwidth = main.mDisplayWidth;
		// screenheight = main.mDisplayHeight;
		screenorientation = getActivity().getResources().getConfiguration().orientation;
		switch (screenorientation)
		{
		case Configuration.ORIENTATION_LANDSCAPE:
		{
			imageview.requestLayout();
			int size = (screenwidth / 5) * 3 - 40;
			imageview.getLayoutParams().width = (size);
			imageview.getLayoutParams().height = ((size) / 4) * 3;
			LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			llp.gravity = Gravity.RIGHT;
			sv.requestLayout();
			sv.getLayoutParams().width = (screenwidth / 5) * 2 - 50;
			sv.setPadding(20, 20, 20, 20);
			sv.setLayoutParams(llp);
			break;
		}

		case Configuration.ORIENTATION_PORTRAIT:
		{
			imageview.requestLayout();
			int size = screenwidth - 40;
			imageview.getLayoutParams().width = size;
			imageview.getLayoutParams().height = (size / 4) * 3;
			break;
		}

		default:
		{

		}

		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		main.swapWidthAndHeight();
		screenwidth = main.getmDisplayWidth();
		screenheight = main.getmDisplayHeight();
		LinearLayout linearlayout = (LinearLayout) getView().findViewById(R.id.linearlayout1);
		linearlayout.removeAllViews();
		linearlayout.addView(View.inflate(context, R.layout.popupwindow, null));
		populateLayout(linearlayout);
	}

	@Override
	public void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "on destroy");
	}

	@Override
	public void onDestroyView()
	{
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.d(TAG, "on destroyview");
	}

	@Override
	public void onDetach()
	{
		// TODO Auto-generated method stub
		super.onDetach();
		Log.d(TAG, "on detach");
	}

	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "on pause");
	}

	@Override
	public void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "on stop");
	}

}
