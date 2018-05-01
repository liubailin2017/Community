package com.sto.asportclient.data.util;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;
/**
 * 路径定义,这样定义主要方便用于转化为json
 * @author liubailin
 */
public class MyPath {
    /**
     * 单个点坐标
     */
    public static class PositionPoint {
        private double longitude;   //经度
        private double latitude;    //纬度
        private float t; //与上一点相隔时间 单位： s 如果第一个点，t = 0;

        public PositionPoint(double longitude, double latitude) {
            this(longitude,latitude,0);
        }

        public PositionPoint(double longitude, double latitude, float t) {
            this.longitude = longitude;
            this.latitude = latitude;
            this.t = t;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public float getT() {
            return t;
        }

        public void setT(float t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return "PositionPoint{" +
                    "longitude=" + longitude +
                    ", latitude=" + latitude +
                    ", t=" + t +
                    '}';
        }
    }

    private Date date; //开始时间
	private List<PositionPoint> path;


    public MyPath(Date date, List<PositionPoint> path) {
        this.date = date;
        this.path = path;
    }

    public List<PositionPoint> getPath() {
		return path;
	}

    public void setPath(@NonNull  List<PositionPoint> path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MyPath{" +
                "date=" + date +
                ", path=" + path +
                '}';
    }
}