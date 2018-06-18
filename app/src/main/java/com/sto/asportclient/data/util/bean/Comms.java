package com.sto.asportclient.data.util.bean;

import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comms {

    private List<CommsBean> comms;

    /**
     * 移除对动态的回复
     * @return 对动态的回复
     */
    public ArrayList<CommsBean> removeCommsForDyns() {
        ArrayList<CommsBean> commsBeans = new ArrayList<>();
        for(int i = 0; i< comms.size(); i++) {
            if(comms.get(i).getCom_commnet_id()==-1 ) {
                commsBeans.add(comms.get(i));
                comms.remove(i);
                i--;
            }
        }
        return  commsBeans;
    }

    /**
     * 移除对回复的回复
     * @param id
     * @return 返回对回复的回复
     */
    ArrayList<CommsBean> tmp = new ArrayList<>();

    public ArrayList<CommsBean> back() {
        ArrayList t = tmp;
        tmp = new ArrayList<>();
        return t;
    }

    public int removeCommsForComms(CommsBean forComms,int level) {
        int count = 0;
        Log.i("XXXXX",comms.toString());
        /**
         * 递归遍历
         */
        for(int i = comms.size()-1; i>=0;i--) {
//            if(i > comms.size()-1) i = comms.size()-1;
            CommsBean t = comms.get(i);
            Log.i("msg","+++++++"+t.toString()+"compare"+forComms.toString());
            if(t.getCom_commnet_id().equals(forComms.getComment().getComment_id()) ) {
//                Log.i("XXXXX","XXXXXXXXXXXX");
                t.setForComms_nickName(forComms.getStu_nickName());
                t.setForComms_nmb(forComms.getStu_nmb());
                t.level = level;
                tmp.add(t);
                comms.remove(i);
                count++;
                int n =  removeCommsForComms(t, level+1);
                count += n;
                i-=n;
            }
        }
        return count;
    }

    public List<CommsBean> getComms() {
        return comms;
    }

    public void setComms(List<CommsBean> comms) {
        this.comms = comms;
    }

    public static class CommsBean {

        private Long stu_nmb;
        private String stu_nickName;
        private String forComms_nickName;
        private Long forComms_nmb;
        private Long dyn;
        private CommentBean comment;
        private Long com_commnet_id;
        public int level = 0; //层级

        public String getForComms_nickName() {
            return forComms_nickName;
        }

        public void setForComms_nickName(String forComms_nickName) {
            this.forComms_nickName = forComms_nickName;
        }

        public Long getForComms_nmb() {
            return forComms_nmb;
        }

        public void setForComms_nmb(Long forComms_nmb) {
            this.forComms_nmb = forComms_nmb;
        }

        public String getStu_nickName() {
            return stu_nickName;
        }

        public void setStu_nickName(String stu_nickName) {
            this.stu_nickName = stu_nickName;
        }

        public Long getStu_nmb() {
            return stu_nmb;
        }

        public void setStu_nmb(Long stu_nmb) {
            this.stu_nmb = stu_nmb;
        }

        public Long getDyn() {
            return dyn;
        }

        public void setDyn(Long dyn) {
            this.dyn = dyn;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public Long getCom_commnet_id() {
            return com_commnet_id;
        }

        public void setCom_commnet_id(Long com_commnet_id) {
            this.com_commnet_id = com_commnet_id;
        }

        public static class CommentBean {

            private Date date;
            private Long comment_id;
            private String content;

            public Date getDate() {
                return date;
            }
            public void setDate(Date date) {
                this.date = date;
            }

            public void setDate(long date) {

                this.date = new Date(date);
            }

            public Long getComment_id() {
                return comment_id;
            }

            public void setComment_id(Long comment_id) {
                this.comment_id = comment_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            @Override
            public String toString() {
                return "CommentBean{" +
                        "date=" + date +
                        ", comment_id=" + comment_id +
                        ", content='" + content + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "CommsBean{" +
                    "stu_nmb=" + stu_nmb +
                    ", dyn=" + dyn +
                    ", comment=" + comment +
                    ", com_commnet_id=" + com_commnet_id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Comms{" +
                "comms=" + comms +
                '}';
    }
}
