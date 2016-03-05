

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet{
	UnfoldingMap map;
	
	Map<String, Float> LifeExpByCountry;
	
	List<Feature> countries;
	List<Marker> countryMarkers;
	private Map<String, Float>
	LoadLifeExpByCountryFromCSV(String FileName) {
		Map<String, Float> LifeExpMap = new HashMap<String, Float>();
		String[] rows = loadStrings(FileName);
		for (String row : rows) {
			String[] cols = row.split(",");
			if (cols.length == 6 && !cols[5].equals("..")) {
				float value = Float.parseFloat(cols[5]);
				LifeExpMap.put(cols[4], value);
			}
		}
		return LifeExpMap;
	}
	private void shadeCountries() {
		for (Marker marker : countryMarkers) {
			String CountryID = marker.getId();
			if (LifeExpByCountry.containsKey(CountryID)) {
				float LifeExp = LifeExpByCountry.get(CountryID);
				int ColorLevel = (int) map(LifeExp, 40, 90, 10, 255);
				marker.setColor(color(255 - ColorLevel, 100, ColorLevel));
			}
			else {
				marker.setColor(color(150, 150, 150));
			}
		}
	}
	public void setup() {
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		LifeExpByCountry = LoadLifeExpByCountryFromCSV("LifeExpectancyWorldBankModule3.csv");
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	public void draw() {
		map.draw();
	}
}
