package org.imooc.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Business extends BaseBean {
    
    private int id;
    private String imgFileName;
    private String title;
    private String subtitle;
    private Double price;
    private Integer distance;
    private Integer number;
    private String desc;
    private String city;
    private String category;
    private int starTotalNum;
    private int commentTotalNum;
    
    private Dic cityDic;
    private Dic categoryDic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStarTotalNum() {
        return starTotalNum;
    }

    public void setStarTotalNum(int starTotalNum) {
        this.starTotalNum = starTotalNum;
    }

    public int getCommentTotalNum() {
        return commentTotalNum;
    }

    public void setCommentTotalNum(int commentTotalNum) {
        this.commentTotalNum = commentTotalNum;
    }

    public Dic getCityDic() {
        return cityDic;
    }

    public void setCityDic(Dic cityDic) {
        this.cityDic = cityDic;
    }

    public Dic getCategoryDic() {
        return categoryDic;
    }

    public void setCategoryDic(Dic categoryDic) {
        this.categoryDic = categoryDic;
    }
}