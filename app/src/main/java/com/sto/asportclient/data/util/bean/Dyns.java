package com.sto.asportclient.data.util.bean;

import java.util.List;

public class Dyns {

    /**
     * dyns : {"bottomPageNo":1,"list":[{"content":"测试","dynId":7,"imgId":7,"nickName":"penguin","stu_nmb":163796,"time":1527560830352,"title":"hello，我测试下我的服务器，：-)"},{"content":"tes3","dynId":6,"imgId":6,"nickName":"penguin","stu_nmb":163796,"time":1527520441396,"title":"test3"},{"content":"test2","dynId":5,"imgId":5,"nickName":"penguin","stu_nmb":163796,"time":1527520436499,"title":"test2"},{"content":"test","dynId":4,"imgId":4,"nickName":"penguin","stu_nmb":163796,"time":1527520431913,"title":"test"},{"content":"test","dynId":3,"imgId":3,"nickName":"penguin","stu_nmb":163796,"time":1527520299314,"title":"test"},{"content":"????????","dynId":2,"imgId":2,"nickName":"penguin","stu_nmb":163796,"time":1527406816954,"title":"??"},{"content":"????????","dynId":1,"imgId":1,"nickName":"penguin","stu_nmb":163796,"time":1527406774804,"title":"??"}],"nextPageNo":1,"pageNo":1,"pageSize":10,"previousPageNo":1,"toPageNo":1,"totalPages":1,"totalRecords":7}
     * schoolId : 0
     * stu_nmb : 163796
     */

    private DynsBean dyns;
    public DynsBean getDyns() {
        return dyns;
    }

    public void setDyns(DynsBean dyns) {
        this.dyns = dyns;
    }


    /**
     * 这个才是实际的动态数据，上一层是一控制的数据。
     */
    public static class DynsBean {


        private int bottomPageNo;
        private int nextPageNo;
        private int pageNo;
        private int pageSize;
        private int previousPageNo;
        private int toPageNo;
        private int totalPages;
        private int totalRecords;
        private List<DynBean> list;

        public DynsBean() {
        }

        public DynsBean(int bottomPageNo, int nextPageNo, int pageNo, int pageSize, int previousPageNo, int toPageNo, int totalPages, int totalRecords, List<DynBean> list) {
            this.bottomPageNo = bottomPageNo;
            this.nextPageNo = nextPageNo;
            this.pageNo = pageNo;
            this.pageSize = pageSize;
            this.previousPageNo = previousPageNo;
            this.toPageNo = toPageNo;
            this.totalPages = totalPages;
            this.totalRecords = totalRecords;
            this.list = list;
        }

        public int getBottomPageNo() {
            return bottomPageNo;
        }

        public void setBottomPageNo(int bottomPageNo) {
            this.bottomPageNo = bottomPageNo;
        }

        public int getNextPageNo() {
            return nextPageNo;
        }

        public void setNextPageNo(int nextPageNo) {
            this.nextPageNo = nextPageNo;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPreviousPageNo() {
            return previousPageNo;
        }

        public void setPreviousPageNo(int previousPageNo) {
            this.previousPageNo = previousPageNo;
        }

        public int getToPageNo() {
            return toPageNo;
        }

        public void setToPageNo(int toPageNo) {
            this.toPageNo = toPageNo;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public List<DynBean> getList() {
            return list;
        }

        public void setList(List<DynBean> list) {
            this.list = list;
        }

        public static class DynBean {
            /**
             * content : 测试
             * dynId : 7
             * imgId : 7
             * nickName : penguin
             * stu_nmb : 163796
             * time : 1527560830352
             * title : hello，我测试下我的服务器，：-)
             */

            private String content;
            private Long dynId;
            private Long imgId;
            private String nickName;
            private Long stu_nmb;
            private long time;
            private String title;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Long getDynId() {
                return dynId;
            }

            public void setDynId(Long dynId) {
                this.dynId = dynId;
            }

            public Long getImgId() {
                return imgId;
            }

            public void setImgId(Long imgId) {
                this.imgId = imgId;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public Long getStu_nmb() {
                return stu_nmb;
            }

            public void setStu_nmb(Long stu_nmb) {
                this.stu_nmb = stu_nmb;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return "DynBean{" +
                        "content='" + content + '\'' +
                        ", dynId=" + dynId +
                        ", imgId=" + imgId +
                        ", nickName='" + nickName + '\'' +
                        ", stu_nmb=" + stu_nmb +
                        ", time=" + time +
                        ", title='" + title + '\'' +
                        '}';
            }
        }


    }
}
