package com.app.LukandaH.CustomClasses;

public class DistanceCalc {

//    public String distance(double lat1, double long1, double lat2, double long2){
    // calculate longitude difference
//        double longDiff = long1 - long2;
//
//        // calculate distance
//        double distance = Math.sin(deg2rad(lat1))*Math.sin(deg2rad(lat2))
//                + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))
//                *Math.cos(deg2rad(longDiff));
//
//        distance = Math.acos(distance);
//
//        distance = rad2deg(distance);
//
//        //distance in miles
//        distance = distance * 60 * 1.1515;
//
//        //distance in kilometers
//        distance = distance * 1.609344;
//
//        String dist = String.valueOf(distance);
//        return dist.substring(0,3);

//        int d = 6371;
//
//    }
final int R = 6371; // Radious of the earth
    public double haversine(double lat1, double lon1,
                            double lat2, double lon2)
    {
        // distance between latitudes and longitudes
        Double latDistance = deg2rad(lat2-lat1);
        Double lonDistance = deg2rad(lon2-lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;
        return distance;
    }
//    public double distance(double lat1, double lon1, double lat2,
//                           double lon2, double el1, double el2) {
//
//        final double RADIUS = 6371.01; // Radius of the earth
//
//        double distance = Math.cos(Math.toRadians(lat1))
//                * Math.cos(Math.toRadians(lat2))
//                * Math.cos(Math.toRadians((lat2) - (lat1)))
//                + Math.sin(Math.toRadians(lat1))
//                * Math.sin(Math.toRadians(lat1));
//        distance = distance * RADIUS * Math.PI / 180;
//        distance = distance / 1000.0;
//        return distance;
////        double latDistance = Math.toRadians(lat2 - lat1);
////        double lonDistance = Math.toRadians(lon2 - lon1);
////        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
////                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
////                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
////        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
////        double distance = R * c * 1000; // convert to meters
////
////        double height = el1 - el2;
////
////        distance = Math.pow(distance, 2) + Math.pow(height, 2);
//
////        return (Math.sqrt(distance))/10000.0;
//    }

    // convert distance radian to degree
    private double rad2deg(double distance) {
        return (distance * 180.0 / Math.PI);
    }

    // convert degree to radian
    private double deg2rad(double value) {
        return (value * Math.PI / 180.0);
    }
}

