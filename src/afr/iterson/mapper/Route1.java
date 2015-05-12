package afr.iterson.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class Route1 extends Route
{

private static String DEFAULTURL = "http://i59.tinypic.com/10i6hw2.jpg";	
	
//pins for the polyline drawing the actual route	
	private final static  LatLng[] locationsPolyline =
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
			new LatLng(52.014032, 4.707395),//vrouweveststeeg / regentesseplantsoen
			new LatLng(52.013694, 4.706836),
			new LatLng(52.013558, 4.706612),
			new LatLng(52.013429, 4.706326),
			new LatLng(52.013342, 4.706144),
			new LatLng(52.013165, 4.705825),
			new LatLng(52.013075, 4.705722),
			new LatLng(52.012845, 4.705311),
			new LatLng(52.012727, 4.705493),
			new LatLng(52.012437, 4.705827), //janseniushof / nieuwe haven
			new LatLng(52.012738, 4.706409),
			new LatLng(52.012579, 4.706601),
			new LatLng(52.012371, 4.706852),
			new LatLng(52.012109, 4.707176),
			new LatLng(52.011967, 4.707353),
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
	private final static MarkerObject m1 = new MarkerObject(0, new LatLng(52.011521, 4.710407), R.drawable.number1,
			R.drawable.number1green, "http://i59.tinypic.com/10i6hw2.jpg", R.string.infotext1);
	
	private final static MarkerObject m2 = new MarkerObject(1, new LatLng(52.011242, 4.709597), R.drawable.number2,
			R.drawable.number2green, "http://i62.tinypic.com/tapr89.jpg", R.string.infotext2);
	private final static MarkerObject m3 = new MarkerObject(2, new LatLng(52.011946, 4.708837), R.drawable.number3,
			R.drawable.number3green, "http://i57.tinypic.com/2nulxrp.jpg", R.string.infotext3);
	private final static MarkerObject m4 = new MarkerObject(3, new LatLng(52.012520, 4.708825), R.drawable.number4,
			R.drawable.number4green, "http://i62.tinypic.com/ftlnas.jpg", R.string.infotext4);
	private final static MarkerObject m5 = new MarkerObject(4, new LatLng(52.013552, 4.708080), R.drawable.number5,
			R.drawable.number5green, "http://i59.tinypic.com/10rqt7s.jpg", R.string.infotext5);
	private final static MarkerObject m6 = new MarkerObject(5, new LatLng(52.012845, 4.705311), R.drawable.number6,
			R.drawable.number6green, "http://i62.tinypic.com/2m4syhd.jpg", R.string.infotext6);
	private final static MarkerObject m7 = new MarkerObject(6, new LatLng(52.011804, 4.707599), R.drawable.number7,
			R.drawable.number7green, "http://i61.tinypic.com/xqhow3.jpg", R.string.infotext7);
	private final static MarkerObject m8 = new MarkerObject(7, new LatLng(52.011202, 4.707768), R.drawable.number8,
			R.drawable.number8green, "http://i59.tinypic.com/15f4qa0.jpg", R.string.infotext8);
	private final static MarkerObject m85 = new MarkerObject(8, new LatLng(52.010536, 4.707591), R.drawable.number9,
			R.drawable.number9green, "http://i59.tinypic.com/20kuk3n.jpg", R.string.infotext85);
	
	private final static MarkerObject m87 = new MarkerObject(9, new LatLng(52.010294, 4.708476), R.drawable.number10,
			R.drawable.number10green, "http://i58.tinypic.com/mkvqxe.jpg", R.string.infotext85);
	private final static MarkerObject m9 = new MarkerObject(10, new LatLng(52.009833, 4.709316), R.drawable.number11,
			R.drawable.number11green, "http://i58.tinypic.com/3023xy0.jpg", R.string.infotext9);
	
	
	private final static MarkerObject m10 = new MarkerObject(11, new LatLng(52.010381, 4.710108), R.drawable.number12,
			R.drawable.number12green, "http://i61.tinypic.com/14vjuid.jpg", R.string.infotext10);
	public MarkerObject[] markerobjects = { m1, m2, m3, m4, m5, m6, m7, m8, m85, m87, m9, m10 };
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

	// calculates distance in kilometers between points of a give polyline, in this case the route
	public static double calculateDistance()
	{
		PolylineOptions routedrawing = new PolylineOptions();
		for(LatLng l : locationsPolyline)
		{
			routedrawing.add(l);
		}
		
		float totalDistance = 0;

		for (int i = 1; i < routedrawing.getPoints().size(); i++)
		{
			Location currLocation = new Location("this");
			currLocation.setLatitude(routedrawing.getPoints().get(i).latitude);
			currLocation.setLongitude(routedrawing.getPoints().get(i).longitude);

			Location lastLocation = new Location("this");
			currLocation.setLatitude(routedrawing.getPoints().get(i - 1).latitude);
			currLocation.setLongitude(routedrawing.getPoints().get(i - 1).longitude);
			totalDistance += lastLocation.distanceTo(currLocation);
		}
		return round(totalDistance/1E8,1);
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
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
