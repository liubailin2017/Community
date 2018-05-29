package com.sto.asportclient.data.util.bean;

import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comms {

    private List<CommsBean> comms;

    /**
     * 获取对动态的回复
     * @return
     */
    public ArrayList<CommsBean> removeCommsForDyns() {
        ArrayList<CommsBean> commsBeans = new ArrayList<>();
        for(int i = 0; i< comms.size(); i++) {
            if(comms.get(i).getCom_commnet_id() == -1) {
                commsBeans.add(comms.get(i));
                comms.remove(i);
                i--;
            }
        }
        return  commsBeans;
    }

    /**
     * 获取对回复的回复
     * @param id 回复id
     * @return
     */
    ArrayList<CommsBean> tmp = new ArrayList<>();
    public void clear() {
        tmp.clear();
    }
    public ArrayList<CommsBean> removeCommsForComms(CommsBean forComms) {

        ArrayList<CommsBean> commsBeans  = new ArrayList<>();
        /**
         * 递归遍历
         */
        for(int i = 0; i< comms.size();i++) {
            CommsBean t = comms.get(i);
            t.setForComms_nickName(forComms.getStu_nickName());
            t.setForComms_nmb(forComms.getStu_nmb());
            if(t.getCom_commnet_id() == forComms.getComment().getComment_id()) {
                tmp.add(t);
                comms.remove(i);
                i--;
                removeCommsForComms(t);
            }
        }
        return tmp;
    }

    public List<CommsBean> getComms() {
        return comms;
    }

    public void setComms(List<CommsBean> comms) {
        this.comms = comms;
    }

    public static class CommsBean {

        private int stu_nmb;
        private String stu_nickName;
        private String forComms_nickName;
        private int forComms_nmb;
        private int dyn;
        private CommentBean comment;
        private int com_commnet_id;


        public String getForComms_nickName() {
            return forComms_nickName;
        }

        public void setForComms_nickName(String forComms_nickName) {
            this.forComms_nickName = forComms_nickName;
        }

        public int getForComms_nmb() {
            return forComms_nmb;
        }

        public void setForComms_nmb(int forComms_nmb) {
            this.forComms_nmb = forComms_nmb;
        }

        public String getStu_nickName() {
            return stu_nickName;
        }

        public void setStu_nickName(String stu_nickName) {
            this.stu_nickName = stu_nickName;
        }

        public int getStu_nmb() {
            return stu_nmb;
        }

        public void setStu_nmb(int stu_nmb) {
            this.stu_nmb = stu_nmb;
        }

        public int getDyn() {
            return dyn;
        }

        public void setDyn(int dyn) {
            this.dyn = dyn;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public int getCom_commnet_id() {
            return com_commnet_id;
        }

        public void setCom_commnet_id(int com_commnet_id) {
            this.com_commnet_id = com_commnet_id;
        }

        public static class CommentBean {

            private Date date;
            private int comment_id;
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

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
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
