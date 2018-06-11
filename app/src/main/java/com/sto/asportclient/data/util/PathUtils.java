package com.sto.asportclient.data.util;

public class PathUtils {
//
//    public static List<LatLng> myPathToList_Latng(MyPath myPath) {
//        List<LatLng> latLngs = new ArrayList<>();
//        for(MyPath.PositionPoint positionPoint : myPath.getPath()) {
//            LatLng latLng = new LatLng(positionPoint.getLatitude(),positionPoint.getLongitude());
//            latLngs.add(latLng);
//        }
//        return  latLngs;
//    }
//
//    /**
//     * 一条路径的长度
//     * @param latLngs
//     * @return
//     */
//    public static  float getDistance(List<LatLng> latLngs) {
//        float distance = 0;
//        LatLng tmp = null;
//        for(LatLng latLng : latLngs) {
//            if(tmp == null) { // 如果tmp == null就是第一个点
//                tmp = latLng;
//            }else {
//                distance += AMapUtils.calculateLineDistance(tmp, latLng);
//                tmp = latLng;
//            }
//        }
//        return  distance;
//    }
//
//    public static float getMyPathDistance(MyPath myPath) {
//        return  getDistance(myPathToList_Latng(myPath));
//    }
}
