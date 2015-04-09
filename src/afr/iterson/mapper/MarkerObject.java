package afr.iterson.mapper;

import com.google.android.gms.maps.model.LatLng;

public class MarkerObject
{
	private int id;
	private LatLng location;
	private int iconresource;
	private int iconresource2;
	private String imageurl;
	private int descriptiontextresource;

	public MarkerObject(int id, LatLng location, int iconresource)
	{
		this.id = id;
		this.location = location;
		this.iconresource = iconresource;
		
	}

	public MarkerObject(int id, LatLng location, int iconresource,int iconresource2,String imageurl, int descriptiontextresource)
	{
		this.id = id;
		this.location = location;
		this.iconresource = iconresource;
		this.iconresource2 = iconresource2;
		this.imageurl = imageurl;
		this.descriptiontextresource = descriptiontextresource;
		
	}
	
	
	
	
	public int getIconresource2()
	{
		return iconresource2;
	}

	public void setIconresource2(int iconresource2)
	{
		this.iconresource2 = iconresource2;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public LatLng getLocation()
	{
		return location;
	}

	public void setLocation(LatLng location)
	{
		this.location = location;
	}

	public int getIconresource()
	{
		return iconresource;
	}

	public void setIconresource(int iconresource)
	{
		this.iconresource = iconresource;
	}

	public String getImageurl()
	{
		return imageurl;
	}

	public void setImageurl(String imageurl)
	{
		this.imageurl = imageurl;
	}

	public int getDescriptiontext()
	{
		return descriptiontextresource;
	}

	public void setDescriptiontext(int descriptiontext)
	{
		this.descriptiontextresource = descriptiontext;
	}

	
	
}
