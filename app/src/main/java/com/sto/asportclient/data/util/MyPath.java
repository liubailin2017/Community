package com.sto.asportclient.data.util;
import java.util.List;
/**
 * 路径定义
 * @author liubailin
 */
public class MyPath {
	
	public static class PositionPoint { 
		private double x;
		private double y;
		private float t;
		
		public PositionPoint() {
			super();
		}
		public PositionPoint(double x, double y, float t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}

		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		public float getT() {
			return t;
		}
		public void setT(float t) {
			this.t = t;
		}
		@Override
		public String toString() {
			return "PositionPoint [x=" + x + ", y=" + y + ", t=" + t + "]";
		} 
		
	}
	
	private List<PositionPoint> path;
	
	public List<PositionPoint> getPath() {
		return path;
	}

	public void setPath(List<PositionPoint> path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "MyPath [path=" + path + "]";
	}
	
}