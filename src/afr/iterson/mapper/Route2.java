package afr.iterson.mapper;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

public class Route2 extends Route
{

//pins for the polyline drawing the actual route	
	private final  LatLng[] locationsPolyline =
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
				new LatLng(52.009944, 4.709651),
				new LatLng(52.010015, 4.709755),
				new LatLng(52.010085, 4.709857),
				new LatLng(52.010260, 4.710109),// hoge gouwe / westhaven
				new LatLng(52.010395, 4.709998),
				new LatLng(52.010511, 4.710163),
				new LatLng(52.010551, 4.710314),
				new LatLng(52.010608, 4.710563), // torenstraat / achter de kerk
				new LatLng(52.010487, 4.710794),
				new LatLng(52.010465, 4.710982),
				new LatLng(52.010442, 4.711179),
				new LatLng(52.010454, 4.711433),
				new LatLng(52.010476, 4.711698),
				new LatLng(52.010515, 4.711887),
				new LatLng(52.010575, 4.712127),
				new LatLng(52.010622, 4.712325),
				new LatLng(52.010586, 4.712714),
				new LatLng(52.010536, 4.712976),// molenwerf / spieringstraat
				new LatLng(52.010341, 4.713053),
				new LatLng(52.010049, 4.713163),
				new LatLng(52.009813, 4.713252),
				new LatLng(52.009636, 4.713334),
				new LatLng(52.009481, 4.713409),
				new LatLng(52.009175, 4.713554), //spieringstraat / lange noodgodstraat
				new LatLng(52.008964, 4.713668),
				new LatLng(52.008656, 4.713836),
				new LatLng(52.008405, 4.713974),
				new LatLng(52.008058, 4.714145),// spieringstraat/ minderbroedersteeg
				new LatLng(52.007942, 4.713769),
				new LatLng(52.007786, 4.713265),
				new LatLng(52.007507, 4.713565),
				new LatLng(52.007281, 4.713811),// oosthaven / punt
				new LatLng(52.007310, 4.714261),
				new LatLng(52.007338, 4.714703),// punt / molen
				new LatLng(52.007447, 4.714728),
				new LatLng(52.007486, 4.714859),
				new LatLng(52.007586, 4.714896),
				new LatLng(52.007514, 4.715341),
				new LatLng(52.007935, 4.715539),
				new LatLng(52.007963, 4.715466),
				new LatLng(52.008166, 4.715404),
				new LatLng(52.008485, 4.715500),
				new LatLng(52.008720, 4.715570),
				new LatLng(52.008887, 4.715484),
				new LatLng(52.009068, 4.715391),
				new LatLng(52.009406, 4.715404),// houtmanplantsoen / doelenstraat
				new LatLng(52.009330, 4.714800),
				new LatLng(52.009278, 4.714399),// doelenstraat / groeneweg
				new LatLng(52.009500, 4.714301),
				new LatLng(52.009581, 4.714246),
				new LatLng(52.009810, 4.714130),
				new LatLng(52.010050, 4.714040),
				new LatLng(52.010291, 4.713965),
				new LatLng(52.010556, 4.713899),
				new LatLng(52.010680, 4.713866),
				new LatLng(52.010775, 4.713829),
				new LatLng(52.010884, 4.713798),
				new LatLng(52.011040, 4.713764),
				new LatLng(52.011285, 4.713740),
				new LatLng(52.011367, 4.713727),
				new LatLng(52.011529, 4.713693),
				new LatLng(52.011609, 4.713672),
				new LatLng(52.011724, 4.713643),
				new LatLng(52.011779, 4.713644),
				new LatLng(52.011842, 4.713670),// groeneweg / lange tiendeweg
				new LatLng(52.011791, 4.713598),
				new LatLng(52.011699, 4.713417),
				new LatLng(52.011636, 4.713240),
				new LatLng(52.011497, 4.712799),//lange tiendeweg / zeugstraat
				new LatLng(52.011742, 4.712551),
				new LatLng(52.011898, 4.712395),
				new LatLng(52.012031, 4.712262),//zeugstraat / stoofsteeg
				new LatLng(52.011700, 4.711578),
				new LatLng(52.011723, 4.711408)
			}; 
	
//pins for interesting point along the route	
	private final static MarkerObject m1 = new MarkerObject(0, new LatLng(52.011545, 4.710527), R.drawable.number1,
			R.drawable.number1green, "http://i59.tinypic.com/10i6hw2.jpg", R.string.infotext1);
	private final static MarkerObject m2 = new MarkerObject(1, new LatLng(52.011290, 4.709551), R.drawable.number2,
			R.drawable.number2green, "http://i62.tinypic.com/tapr89.jpg", R.string.infotext2);
	private final static MarkerObject m3 = new MarkerObject(2, new LatLng(52.011070, 4.708527), R.drawable.number3,
			R.drawable.number3green, "http://i60.tinypic.com/oaskrs.jpg", R.string.infotext3);
	private final static MarkerObject m4 = new MarkerObject(3, new LatLng(52.010822, 4.708310), R.drawable.number4,
			R.drawable.number4green, "http://i61.tinypic.com/a3019h.jpg", R.string.infotext4);
	private final static MarkerObject m5 = new MarkerObject(4, new LatLng(52.011326, 4.707886), R.drawable.number5,
			R.drawable.number5green, "http://i59.tinypic.com/15f4qa0.jpg", R.string.infotext5);
	private final static MarkerObject m6 = new MarkerObject(5, new LatLng(52.011787, 4.707567), R.drawable.number6,
			R.drawable.number6green, "http://i61.tinypic.com/xqhow3.jpg", R.string.infotext6);
	private final static MarkerObject m7 = new MarkerObject(6, new LatLng(52.012105, 4.708080), R.drawable.number7,
			R.drawable.number7green, "http://i61.tinypic.com/2pra44m.jpg", R.string.infotext7);
	private final static MarkerObject m8 = new MarkerObject(7, new LatLng(52.012719, 4.709286), R.drawable.number8,
			R.drawable.number8green, "http://i57.tinypic.com/2jadrmq.jpg", R.string.infotext8);
	private final static MarkerObject m9 = new MarkerObject(8, new LatLng(52.012537, 4.710107), R.drawable.number9,
			R.drawable.number9green, "http://i58.tinypic.com/33p537s.jpg", R.string.infotext9);
	private final static MarkerObject m10 = new MarkerObject(9, new LatLng(52.012264, 4.710251), R.drawable.number10,
			R.drawable.number10green, "http://i60.tinypic.com/nq388x.jpg", R.string.infotext10);
	private final static MarkerObject m11 = new MarkerObject(10, new LatLng(52.012332, 4.710883), R.drawable.number11,
			R.drawable.number11green, "http://i60.tinypic.com/27wx8g9.jpg", R.string.infotext11);

	public MarkerObject[] markerobjects = { m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11 };
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
