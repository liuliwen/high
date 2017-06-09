package com.high.utils;

import com.high.entity.Location;

public class LocationUtils {

	private final static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	
	public static double getDisBetweenTwoPoint(Location location1, Location location2) {
		return getDisBetweenTwoPoint(location1.getLongitude(), location1.getLatitude(), location2.getLongitude(), location2.getLatitude());// 单位米
	}
	/**
	 * 根据两个位置的经纬度坐标，计算两点间距离，单位为米
	 * 
	 * @param location1
	 * @param location2
	 * @return
	 */
	public static double getDisBetweenTwoPoint(double lon1,double lat1,double lon2, double lat2){
		double radLat1 = rad(lat1);
	    double radLat2 = rad(lat2);
	    double a = radLat1 - radLat2;
	    double b = rad(lon1) - rad(lon2);
	    double s = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))); 
	    s = s * EARTH_RADIUS;    
	    return s;//单位米
	}
}
