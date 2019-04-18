package com.noideateam.braincode_noideateam;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.opencsv.CSVReader;
import javafx.util.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class CollectionPoints {
    static Vector<CollectionPoint> collectionPoints = new Vector<>();

    public CollectionPoints() throws IOException {
        initializeCollectionPoints();
    }

    public static void initializeCollectionPoints() throws IOException {
        CSVReader values = new CSVReader(new FileReader("pickup_points_small_unique.csv"), ';');
        String[] r;
        while ((r = values.readNext()) != null) {
            if(r[0].equals("Typ")){ continue; }
            CollectionPoint cp = new CollectionPoint(r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7], r[8]);
            collectionPoints.add(cp);
        }
    }

    public static ArrayList<Pair<CollectionPoint, Double>> collectionPointsDistanceToPoint(double x, double y){
        ArrayList<Pair<CollectionPoint, Double>> result = new ArrayList<>();
        LatLng sourcePoint = new LatLng(x, y);
        for (CollectionPoint cp: collectionPoints){
            LatLng targetPoint = new LatLng(cp.getLongitude(), cp.getLatitude());
            double distance = LatLngTool.distance(sourcePoint, targetPoint, LengthUnit.KILOMETER);
            // Pair<CollectionPoint, Double> ret = new Pair<CollectionPoint, Double>(cp, distance);
            result.add(new Pair<>(cp, distance));
        }
        return result;
    }


    public double getDistance(double sourceX, double sourceY, double tagetX, double targetY ){
        LatLng sourcePoint = new LatLng(sourceX, sourceY);
        LatLng targetPoint = new LatLng(tagetX, targetY);

        return LatLngTool.distance(sourcePoint, targetPoint, LengthUnit.KILOMETER);
    }

    public static ArrayList<Pair<CollectionPoint, Double>> collectionPointsInRange(double x, double y, float range){
        ArrayList<Pair<CollectionPoint, Double>> distances = collectionPointsDistanceToPoint(x, y);
        ArrayList<Pair<CollectionPoint, Double>> result = new ArrayList<>();

        for (Pair<CollectionPoint, Double> dist : distances){
            if (dist.getValue()<range){
                result.add(dist);
            }
        }
        return result;
    }




    public Pair<CollectionPoint, Double> getOneClosest(double X, double Y){

        ArrayList<Pair<CollectionPoint, Double>> result= CollectionPoints.collectionPointsInRange(X, Y, 10);
        Collections.sort(result, new Comparator<Pair<CollectionPoint, Double>>() {
            @Override
            public int compare(Pair<CollectionPoint, Double> o1, Pair<CollectionPoint, Double> o2) {

                if (o1.getValue()>o2.getValue()) return 1;
                else if (o1.getValue() == o2.getValue()) return 0;
                else return -1;
            }
        });



        return result.get(0);

    }

}