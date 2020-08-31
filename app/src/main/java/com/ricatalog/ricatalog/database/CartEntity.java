package com.ricatalog.ricatalog.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "cart_products")
    public class CartEntity {
        @PrimaryKey(autoGenerate = true)
        private int no;
        private String id;

        private String title;

        private String code;

        private String imgurl;

        private String qtyinstock;

        private String color;

        private String polish;

        private String qtyorder;

    public void setNo(int no) {
        this.no = no;
    }

    public CartEntity(String id, String title, String code, String imgurl, String qtyinstock, String color, String polish, String qtyorder) {
            this.id=id;
            this.title = title;
            this.code = code;
            this.imgurl = imgurl;
            this.qtyinstock = qtyinstock;
            this.color=color;
            this.polish=polish;
            this.qtyorder=qtyorder;

        }

        public String getId() {
            return id;
        }

    public int getNo() {
        return no;
    }

    public String getTitle() {
            return title;
        }

        public String getCode() {
            return code;
        }

        public String getImgurl() {
            return imgurl;
        }

        public String getQtyinstock() {
            return qtyinstock;
        }

        public String getColor() {
            return color;
        }

        public String getPolish() {
            return polish;
        }

        public String getQtyorder() {
            return qtyorder;
        }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setQtyinstock(String qtyinstock) {
        this.qtyinstock = qtyinstock;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPolish(String polish) {
        this.polish = polish;
    }

    public void setQtyorder(String qtyorder) {
        this.qtyorder = qtyorder;
    }
}
