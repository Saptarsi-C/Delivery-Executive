/**
 * 
 */
package com.example.swiggy.util.general;

import org.springframework.stereotype.Service;

import com.example.swiggy.dto.LocationDto;

/**
 * @author saptarsichaurashy
 *
 */
@Service("distanceService")
public class DistanceService {

    public static final double R = 6372.8; // In kilometers
    
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
    
    public Double getDistance(LocationDto d1, LocationDto d2) {
    	
    		return haversine(d1.getLatitude(), d1.getLongitude(), d2.getLatitude(), d2.getLongitude());
    }
}
