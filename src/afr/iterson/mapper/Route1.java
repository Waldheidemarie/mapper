package afr.iterson.mapper;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

public class Route1 extends Route
{

private static String DEFAULTURL = "http://i59.tinypic.com/10i6hw2.jpg";	
	
//pins for the polyline drawing the actual route	
	private final  LatLng[] locationsPolyline =
			{ 
			new LatLng(52.011493, 4.710369),
			new LatLng(52.011496, 4.710044),
			new LatLng(52.011192, 4.710075),
			new LatLng(52.011282, 4.709018),
			new LatLng(52.011976, 4.708983),
			new LatLng(52.012320, 4.708897),
			new LatLng(52.012373, 4.708854),
			new LatLng(52.012423, 4.708802),
			new LatLng(52.012615, 4.708632),
			new LatLng(52.012902, 4.708372),
			new LatLng(52.013164, 4.708213),
			new LatLng(52.013336, 4.708112),
			new LatLng(52.013544, 4.707958),
			new LatLng(52.013698, 4.707788),
			new LatLng(52.013907, 4.707543),
			new LatLng(52.014032, 4.707395),
			new LatLng(52.013828, 4.707056),
			new LatLng(52.013562, 4.706615),
			new LatLng(52.013323, 4.706969),
			new LatLng(52.013170, 4.707203),
			new LatLng(52.012946, 4.706765),//klooster/nieuwe haven
			new LatLng(52.012487, 4.707381),
			new LatLng(52.012302, 4.706939),
			new LatLng(52.012056, 4.707242),
			new LatLng(52.011844, 4.707509), //lange dwarsstraat / turfmarkt
			new LatLng(52.011768, 4.707582),
			new LatLng(52.011637, 4.707732),
			new LatLng(52.011356, 4.708072),
			new LatLng(52.011220, 4.707779),
			new LatLng(52.010841, 4.708281),//looierspoort/lange groenendaal
			new LatLng(52.010500, 4.707641),//lange groenendaal/lombardsteeg
			new LatLng(52.010284, 4.707932),
			new LatLng(52.010105, 4.708158),
			new LatLng(52.010285, 4.708490),//achter de vismarkt/vissteeg
			new LatLng(52.009891, 4.709141),
			new LatLng(52.010125, 4.709551),
			new LatLng(52.010404, 4.710005),
			new LatLng(52.010514, 4.710166),
			new LatLng(52.010943, 4.710118),
			}; 
	
//pins for interesting point along the route	
	private final static MarkerObject m1 = new MarkerObject(0, new LatLng(52.011545, 4.710527), R.drawable.number1,
			R.drawable.number1green, DEFAULTURL, R.string.infotext1);
	private final static MarkerObject m2 = new MarkerObject(1, new LatLng(52.011290, 4.709551), R.drawable.number2,
			R.drawable.number2green, DEFAULTURL, R.string.infotext2);
	private final static MarkerObject m3 = new MarkerObject(2, new LatLng(52.011070, 4.708527), R.drawable.number3,
			R.drawable.number3green, DEFAULTURL, R.string.infotext3);
	private final static MarkerObject m4 = new MarkerObject(3, new LatLng(52.010822, 4.708310), R.drawable.number4,
			R.drawable.number4green, DEFAULTURL, R.string.infotext4);
	private final static MarkerObject m5 = new MarkerObject(4, new LatLng(52.011326, 4.707886), R.drawable.number5,
			R.drawable.number5green, DEFAULTURL, R.string.infotext5);
	private final static MarkerObject m6 = new MarkerObject(5, new LatLng(52.011787, 4.707567), R.drawable.number6,
			R.drawable.number6green, DEFAULTURL, R.string.infotext6);
	private final static MarkerObject m7 = new MarkerObject(6, new LatLng(52.012105, 4.708080), R.drawable.number7,
			R.drawable.number7green, DEFAULTURL, R.string.infotext7);
	private final static MarkerObject m8 = new MarkerObject(7, new LatLng(52.012719, 4.709286), R.drawable.number8,
			R.drawable.number8green, DEFAULTURL, R.string.infotext8);
	private final static MarkerObject m9 = new MarkerObject(8, new LatLng(52.012537, 4.710107), R.drawable.number9,
			R.drawable.number9green, DEFAULTURL, R.string.infotext9);
	private final static MarkerObject m10 = new MarkerObject(9, new LatLng(52.012264, 4.710251), R.drawable.number10,
			R.drawable.number10green, DEFAULTURL, R.string.infotext10);
	private final static MarkerObject m11 = new MarkerObject(10, new LatLng(52.012332, 4.710883), R.drawable.number11,
			R.drawable.number11green, DEFAULTURL, R.string.infotext11);

	public MarkerObject[] markerobjects = { m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11 };
	public ArrayList<LatLng> locationsForPolyline;
	
	public static int DEFAULTZOOMLEVEL = 16;
	public static LatLng DEFAULTCENTER = new LatLng(52.012388, 4.709125);
	public static int DEFAULTROUTECOLOR = R.color.CadetBlue;


	
	
	public Route1()
	{
		locationsForPolyline = new ArrayList<LatLng>();
		for(LatLng l : locationsPolyline)
		{
			locationsForPolyline.add(l);
		}
	}

	public MarkerObject[] getMarkerobjects()
	{
		return markerobjects;
	}

	public ArrayList<LatLng> getLocationsForPolyline()
	{
		return locationsForPolyline;
	}

	public static int getDEFAULTZOOMLEVEL()
	{
		return DEFAULTZOOMLEVEL;
	}

	public static LatLng getDEFAULTCENTER()
	{
		return DEFAULTCENTER;
	}

	public static int getDEFAULTROUTECOLOR()
	{
		return DEFAULTROUTECOLOR;
	}

	
		
	
}
