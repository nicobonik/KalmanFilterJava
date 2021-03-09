package org.vulcanrobotics;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        XYChart chart = new XYChartBuilder().build();
        chart.setTitle("kalman filter");

        KalmanFilter filter = new KalmanFilter(0.6, 0.001, 0.25, 0.2);

        int n = 0;
        ArrayList<Double> inputList = new ArrayList<Double>();
        ArrayList<Double> outputList = new ArrayList<>();
        ArrayList<Integer> nList = new ArrayList<>();
        ArrayList<Double> errorList = new ArrayList<>();

        while(n < 1000) {
           double rand = 0.5;
           inputList.add(rand);
           filter.run(rand);
           outputList.add(filter.getEstimate());
           errorList.add((0.5) - filter.getEstimate());
           nList.add(n);

            n++;
        }

        chart.addSeries("input", nList, inputList);
        chart.addSeries("output", nList, outputList);

        XYChart errChart = new XYChartBuilder().build();

        errChart.addSeries("error", nList, errorList);

//        chart.getStyler().setYAxisMax(2.0);
//        chart.getStyler().setYAxisMin(-2.0);

        new SwingWrapper<>(chart).displayChart();
        new SwingWrapper<>(errChart).displayChart();


    }

}
