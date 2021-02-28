package org.howcompany.mytown;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("r1")
    @Expose
    private Integer r1;
    @SerializedName("r2")
    @Expose
    private Integer r2;
    @SerializedName("r3")
    @Expose
    private Integer r3;
    @SerializedName("r4")
    @Expose
    private Integer r4;
    @SerializedName("r5")
    @Expose
    private Integer r5;
    @SerializedName("r6")
    @Expose
    private Integer r6;
    @SerializedName("r7")
    @Expose
    private Integer r7;
    @SerializedName("r8")
    @Expose
    private Integer r8;
    @SerializedName("lp")
    @Expose
    private Integer lp;
    @SerializedName("city_code")
    @Expose
    private String city;
    @SerializedName("shop_code")
    @Expose
    private String shop;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code+"";
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getR1() {
        return r1;
    }

    public void setR1(Integer r1) {
        this.r1 = r1;
    }

    public Integer getR2() {
        return r2;
    }

    public void setR2(Integer r2) {
        this.r2 = r2;
    }

    public Integer getR3() {
        return r3;
    }

    public void setR3(Integer r3) {
        this.r3 = r3;
    }

    public Integer getR4() {
        return r4;
    }

    public void setR4(Integer r4) {
        this.r4 = r4;
    }

    public Integer getR5() {
        return r5;
    }

    public void setR5(Integer r5) {
        this.r5 = r5;
    }

    public Integer getR6() {
        return r6;
    }

    public void setR6(Integer r6) {
        this.r6 = r6;
    }

    public Integer getR7() {
        return r7;
    }

    public void setR7(Integer r7) {
        this.r7 = r7;
    }

    public Integer getR8() {
        return r8;
    }

    public void setR8(Integer r8) {
        this.r8 = r8;
    }

    public Integer getLp() {
        return lp;
    }

    public void setLp(Integer lp) {
        this.lp = lp;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCity() {
        return city;
    }

    public String getShop() {
        return shop;
    }
}