//package com.sensors.mystudy.spark;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.SparkContext;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.deploy.SparkApplication;
//
//public class WordCount {
//    public static void main(String[] args) {
//        SparkConf conf = new SparkConf().setAppName("Appliction").setMaster("local[*]");
//        JavaSparkContext sc = new JavaSparkContext(conf);
//         JavaRDD<String> objectJavaRDD = sc.textFile("fff.txt");
////        rdd.foreach(System.out::printf);
//    }
//
//}
