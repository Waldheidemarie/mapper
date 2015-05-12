package afr.iterson.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class Route2 extends Route
{

private static String DEFAULTURL = "http://i59.tinypic.com/10i6hw2.jpg";	
	
//pins for the polyline drawing the actual route	
	private final static  LatLng[] locationsPolyline =
			{ 
				new LatLng(52.011496, 4.710373),
				new LatLng(52.011495, 4.710042),
				new LatLng(52.011192, 4.710074),
				new LatLng(52.011234, 4.709510),
				new LatLng(52.011269, 4.709019), //naaierstraat / korte groenendaal
				new LatLng(52.011800, 4.708991),
				new LatLng(52.011978, 4.708982),
				new LatLng(52.012322, 4.708897 ),//naaiersstraat / turfmarkt
				new LatLng(52.011732, 4.707622),
				new LatLng(52.011318, 4.706813),
				new LatLng(52.010877, 4.705952), // turfmarkt / lage gouwe
				new LatLng(52.011240, 4.705337),
				new LatLng(52.011736, 4.704493),
				new LatLng(52.011548, 4.704207),//pottersplein / hoge gouwe
				new LatLng(52.011344, 4.704582),
				new LatLng(52.011064, 4.705099),
				new LatLng(52.010993, 4.705219),
				new LatLng(52.010824, 4.705554),
				new LatLng(52.010625, 4.705845),
				new LatLng(52.010363, 4.706231),
				new LatLng(52.010016, 4.706740),//hoge gouwe / brug naar de lange groenendaal
				new LatLng(52.010134, 4.706958),
				new LatLng(52.010016, 4.706740),//hoge gouwe / brug naar de lange groenendaal
				new LatLng(52.009715, 4.707192),//hoge gouwe / aaltje bakstraat
				new LatLng(52.009468, 4.706786),
				new LatLng(52.009250, 4.706429),//aaltje bakstraat / raam
				new LatLng(52.009190, 4.706354),//vlamingstraat / raam
				new LatLng(52.008933, 4.706959),
				new LatLng(52.008803, 4.707261),
				new LatLng(52.008524, 4.707876),
				new LatLng(52.008337, 4.708372),
				new LatLng(52.008218, 4.708686),
				new LatLng(52.008146, 4.708903),
				new LatLng(52.008019, 4.709210),
				new LatLng(52.007806, 4.709719 ),// raam / kuiperstraat
				new LatLng(52.007872, 4.709823),
				new LatLng(52.008051, 4.710131),// kuiperstraat / keizerstraat
				new LatLng(52.008236, 4.709919),
				new LatLng(52.008434, 4.709591),
				new LatLng(52.008549, 4.709406),
				new LatLng(52.008786, 4.708874),
				new LatLng(52.008927, 4.708579),
				new LatLng(52.009031, 4.708423),
				new LatLng(52.009371, 4.708207),// keizerstraat /  hoge gouwe
				new LatLng(52.009393, 4.708409),
				new LatLng(52.009419, 4.708576),
				new LatLng(52.009486, 4.708820),
				new LatLng(52.009748, 4.709319),
				new LatLng(52.009844, 4.709505), // hoge gouwe / peperstraat
				new LatLng(52.009735, 4.709706),
				new LatLng(52.009625, 4.709901),
				new LatLng(52.009498, 4.710132),
				new LatLng(52.009359, 4.710385),
				new LatLng(52.009034, 4.710744),
				new LatLng(52.008869, 4.710915),
				new LatLng(52.008696, 4.711062),
				new LatLng(52.008557, 4.711180),//Peperstraat / Kuiperstraat
				new LatLng(52.008242, 4.711463),
				new LatLng(52.007942, 4.711731),
				new LatLng(52.007791, 4.711899),
				new LatLng(52.007687, 4.712025),
				new LatLng(52.007476, 4.712245),
				new LatLng(52.007383, 4.712341),
				new LatLng(52.007190, 4.712482),
				new LatLng(52.007047, 4.712593),//Peperstraat / Veerstal
				new LatLng(52.007083, 4.712919),
				new LatLng(52.007124, 4.713261),
				new LatLng(52.007134, 4.713351), //Veerstal / Westhaven
				new LatLng(52.006983, 4.713386), //Westhaven / Nieuwe Veerstal
				new LatLng(52.007043, 4.713737),
				new LatLng(52.007081, 4.713974), //Nieuwe Veerstal / Oosthaven
				new LatLng(52.007168, 4.713903),
				new LatLng(52.007284, 4.713806), //Oosthaven / Punt
				new LatLng(52.007315, 4.714353),
				new LatLng(52.007339, 4.714689),
				new LatLng(52.007402, 4.714715),
				new LatLng(52.007455, 4.714760),
				new LatLng(52.007490, 4.714878),
				new LatLng(52.007581, 4.714935),//Houtmanplantsoen
				new LatLng(52.007607, 4.714643),
				new LatLng(52.007753, 4.714281),
				new LatLng(52.008059, 4.714145),//Spieringstraat / Vijverstraat				
				new LatLng(52.008278, 4.714035),
				new LatLng(52.008391, 4.713981),
				new LatLng(52.008540, 4.713900),
				new LatLng(52.008844, 4.713733),
				new LatLng(52.009174, 4.713554),// Spieringstraat / Walestraat
				new LatLng(52.009198, 4.713745),
				new LatLng(52.009236, 4.714062),
				new LatLng(52.009276, 4.714401),//Walestraat / Groeneweg
				new LatLng(52.009451, 4.714324),
				new LatLng(52.009501, 4.714302),
				new LatLng(52.009591, 4.714240),
				new LatLng(52.009714, 4.714175),
				new LatLng(52.009900, 4.714092),
				new LatLng(52.010158, 4.714007),
				new LatLng(52.010402, 4.713937),
				new LatLng(52.010563, 4.713897),
				new LatLng(52.010680, 4.713867),
				new LatLng(52.010774, 4.713833),
				new LatLng(52.011048, 4.713764),
				new LatLng(52.011144, 4.713750),//Groeneweg / Wallenbergplantsoen
				new LatLng(52.011100, 4.713358),
				new LatLng(52.011017, 4.712875),//Wallenbergplantsoen / Jeruzalemstraat
				new LatLng(52.010786, 4.712914),
				new LatLng(52.010630, 4.712941),
				new LatLng(52.010536, 4.712978),
				new LatLng(52.010576, 4.712790),
				new LatLng(52.010623, 4.712328),
				new LatLng(52.010525, 4.711926),
				new LatLng(52.010474, 4.711690),
				new LatLng(52.010443, 4.711187),
				new LatLng(52.010488, 4.710795),
				new LatLng(52.010608, 4.710564),
				new LatLng(52.010730, 4.710490),
				new LatLng(52.010823, 4.710499),
				new LatLng(52.010904, 4.710571),
				new LatLng(52.011008, 4.710444),
				new LatLng(52.011114, 4.710320)
				
			}; 
	
//pins for interesting point along the route	
	private final static MarkerObject m1 = new MarkerObject(0, new LatLng(52.011545, 4.710527), R.drawable.number1,//Stadhuis
			R.drawable.number1green, "http://i59.tinypic.com/10i6hw2.jpg", R.string.infotext1);
	private final static MarkerObject m2 = new MarkerObject(1, new LatLng(52.011290, 4.709551), R.drawable.number2,//Korte Groenendaal
			R.drawable.number2green, "http://i62.tinypic.com/tapr89.jpg", R.string.infotext2);
	private final static MarkerObject m3 = new MarkerObject(2, new LatLng(52.011965, 4.708898), R.drawable.number3,//Steenhouwersgildehuisje
			R.drawable.number3green, "http://i57.tinypic.com/2nulxrp.jpg", R.string.infotext3);
	private final static MarkerObject m4 = new MarkerObject(3, new LatLng(52.011467, 4.707139), R.drawable.number4,//Synagoge
			R.drawable.number4green, "http://i60.tinypic.com/2445dlg.jpg", R.string.infotext11);
	private final static MarkerObject m5 = new MarkerObject(4, new LatLng(52.011467, 4.705071), R.drawable.number5,//Pinkstergemeente
			R.drawable.number5green, "http://i58.tinypic.com/120ou3s.jpg", R.string.infotext12);
	private final static MarkerObject m6 = new MarkerObject(5, new LatLng(52.010635, 4.705704), R.drawable.number6,//Christelijk Gereformeerden
			R.drawable.number6green, "http://i59.tinypic.com/6em136.jpg", R.string.infotext13);
	private final static MarkerObject m7 = new MarkerObject(6, new LatLng(52.010134, 4.706439), R.drawable.number7,//Oudkatholieke kerk
			R.drawable.number7green, "http://i57.tinypic.com/ogxrba.jpg", R.string.infotext14);
	private final static MarkerObject m8 = new MarkerObject(7, new LatLng(52.010127, 4.707001), R.drawable.number8,//St Joostkapel
			R.drawable.number8green, "http://i58.tinypic.com/n3s2ac.jpg", R.string.infotext15);
	private final static MarkerObject m9 = new MarkerObject(8, new LatLng(52.008390, 4.708111), R.drawable.number9,//Moskee
			R.drawable.number9green, "http://i61.tinypic.com/2q9cnpd.jpg", R.string.infotext16);
	private final static MarkerObject m10 = new MarkerObject(9, new LatLng(52.007952, 4.709839), R.drawable.number10,//BarabaraKapel
			R.drawable.number10green, "http://i58.tinypic.com/nlvp52.jpg", R.string.infotext17);
	private final static MarkerObject m11 = new MarkerObject(10, new LatLng(52.009303, 4.708153), R.drawable.number11,//Remonstrantse kerk
			R.drawable.number11green, "http://i57.tinypic.com/2e2krax.jpg", R.string.infotext18);
	private final static MarkerObject m12 = new MarkerObject(11, new LatLng(52.009233, 4.708974), R.drawable.number12,//Gouwekerk
			R.drawable.number12green, "http://i58.tinypic.com/10fn779.jpg", R.string.infotext19);
	private final static MarkerObject m13 = new MarkerObject(12, new LatLng(52.009422, 4.710150), R.drawable.number13,//Vergadering der gelovigen
			R.drawable.number13green, "http://i58.tinypic.com/2z7q39l.jpg", R.string.infotext235);
	
	private final static MarkerObject m14 = new MarkerObject(13, new LatLng(52.007525, 4.712134), R.drawable.number14,//armenkerk
			R.drawable.number14green, "http://i59.tinypic.com/2148kfk.jpg", R.string.infotext24);
	private final static MarkerObject m15 = new MarkerObject(14, new LatLng(52.007156, 4.713524), R.drawable.number15,//tolhuis
			R.drawable.number15green, "http://i60.tinypic.com/vhxbg0.jpg", R.string.infotext25);
	private final static MarkerObject m16 = new MarkerObject(15, new LatLng(52.007313, 4.714229), R.drawable.number16,//volmolen
			R.drawable.number16green, "http://i60.tinypic.com/t9gn5u.jpg", R.string.infotext26);
	private final static MarkerObject m17 = new MarkerObject(16, new LatLng(52.007322, 4.714959), R.drawable.number17,//kasteel
			R.drawable.number17green, "http://i62.tinypic.com/w0qqgj.jpg", R.string.infotext27);
	private final static MarkerObject m18 = new MarkerObject(17, new LatLng(52.007729, 4.715173), R.drawable.number18,//park
			R.drawable.number18green, "http://i61.tinypic.com/y0vu0.jpg", R.string.infotext28);
	private final static MarkerObject m19 = new MarkerObject(18, new LatLng(52.008146, 4.714005), R.drawable.number19,//Moskee
			R.drawable.number19green, "http://i59.tinypic.com/xn4zuw.jpg", R.string.infotext23);
	private final static MarkerObject m20 = new MarkerObject(19, new LatLng(52.010293, 4.713781), R.drawable.number20,//Swanenburg
			R.drawable.number20green, "http://i58.tinypic.com/11jxmap.jpg", R.string.infotext29);
	private final static MarkerObject m21 = new MarkerObject(20, new LatLng(52.010538, 4.713941), R.drawable.number21,//Huize Groeneweg
			R.drawable.number21green, "http://i60.tinypic.com/2wmjmrp.jpg", R.string.infotext30);
	private final static MarkerObject m22 = new MarkerObject(21, new LatLng(52.010732, 4.713026), R.drawable.number22,//Jeruzalemkapel
			R.drawable.number22green, "http://i58.tinypic.com/292tjwg.jpg", R.string.infotext22);
	private final static MarkerObject m23 = new MarkerObject(22, new LatLng(52.010091, 4.710931), R.drawable.number23,//Gasthuiskapel
			R.drawable.number23green, "http://i58.tinypic.com/257lqg3.jpg", R.string.infotext20);
	private final static MarkerObject m24 = new MarkerObject(23, new LatLng(52.010648, 4.711025), R.drawable.number24,//St Jan
			R.drawable.number24green, "http://i61.tinypic.com/2qi4jdc.jpg", R.string.infotext21);
	
	
	
	
	
	
	
	
	
	public MarkerObject[] markerobjects = { m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22,  m23, m24 };
	public ArrayList<LatLng> locationsForPolyline;
	
	public static int DEFAULTZOOMLEVEL = 15;
	public static LatLng DEFAULTCENTER = new LatLng(52.010738, 4.709576);
	public static int DEFAULTROUTECOLOR = R.color.BlueViolet;
	
	
	public Route2()
	{
		locationsForPolyline = new ArrayList<LatLng>();
		for(LatLng l : locationsPolyline)
		{
			locationsForPolyline.add(l);
		}
	}

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
	    bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
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
