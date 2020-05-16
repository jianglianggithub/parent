package com.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht123
 * @since 2019-11-14
 */
@TableName("b_bind_jd_account_info")
public class BBindJdAccountInfo extends Model<BBindJdAccountInfo> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @TableField("buyer_id")
    private Long buyerId;
    @TableField("buyer_account")
    private String buyerAccount;
    @TableField("is_high_quality")
    private Integer isHighQuality;
    @TableField("jd_account")
    private String jdAccount;
    private String name;
    private String phone;
    private Integer sex;
    private Integer age;
    private String province;
    private String city;
    private String district;
    private String address;
    @TableField("account_lev")
    private Integer accountLev;
    @TableField("is_plus")
    private Integer isPlus;
    @TableField("jd_white_url")
    private String jdWhiteUrl;
    @TableField("jd_credit_url")
    private String jdCreditUrl;
    @TableField("lev_url")
    private String levUrl;
    @TableField("real_name_url")
    private String realNameUrl;
    @TableField("bind_time")
    private Date bindTime;
    @TableField("is_sex_type")
    private Integer isSexType;
    @TableField("address_url")
    private String addressUrl;
    @TableField("audit_state")
    private Integer auditState;
    @TableField("audit_time")
    private Date auditTime;
    @TableField("audit_name")
    private String auditName;
    @TableField("is_black")
    private Integer isBlack;
    private String note;
    @TableField("is_real_name")
    private Integer isRealName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public Integer getIsHighQuality() {
        return isHighQuality;
    }

    public void setIsHighQuality(Integer isHighQuality) {
        this.isHighQuality = isHighQuality;
    }

    public String getJdAccount() {
        return jdAccount;
    }

    public void setJdAccount(String jdAccount) {
        this.jdAccount = jdAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAccountLev() {
        return accountLev;
    }

    public void setAccountLev(Integer accountLev) {
        this.accountLev = accountLev;
    }

    public Integer getIsPlus() {
        return isPlus;
    }

    public void setIsPlus(Integer isPlus) {
        this.isPlus = isPlus;
    }

    public String getJdWhiteUrl() {
        return jdWhiteUrl;
    }

    public void setJdWhiteUrl(String jdWhiteUrl) {
        this.jdWhiteUrl = jdWhiteUrl;
    }

    public String getJdCreditUrl() {
        return jdCreditUrl;
    }

    public void setJdCreditUrl(String jdCreditUrl) {
        this.jdCreditUrl = jdCreditUrl;
    }

    public String getLevUrl() {
        return levUrl;
    }

    public void setLevUrl(String levUrl) {
        this.levUrl = levUrl;
    }

    public String getRealNameUrl() {
        return realNameUrl;
    }

    public void setRealNameUrl(String realNameUrl) {
        this.realNameUrl = realNameUrl;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Integer getIsSexType() {
        return isSexType;
    }

    public void setIsSexType(Integer isSexType) {
        this.isSexType = isSexType;
    }

    public String getAddressUrl() {
        return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public Integer getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(Integer isBlack) {
        this.isBlack = isBlack;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(Integer isRealName) {
        this.isRealName = isRealName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BBindJdAccountInfo{" +
        ", id=" + id +
        ", buyerId=" + buyerId +
        ", buyerAccount=" + buyerAccount +
        ", isHighQuality=" + isHighQuality +
        ", jdAccount=" + jdAccount +
        ", name=" + name +
        ", phone=" + phone +
        ", sex=" + sex +
        ", age=" + age +
        ", province=" + province +
        ", city=" + city +
        ", district=" + district +
        ", address=" + address +
        ", accountLev=" + accountLev +
        ", isPlus=" + isPlus +
        ", jdWhiteUrl=" + jdWhiteUrl +
        ", jdCreditUrl=" + jdCreditUrl +
        ", levUrl=" + levUrl +
        ", realNameUrl=" + realNameUrl +
        ", bindTime=" + bindTime +
        ", isSexType=" + isSexType +
        ", addressUrl=" + addressUrl +
        ", auditState=" + auditState +
        ", auditTime=" + auditTime +
        ", auditName=" + auditName +
        ", isBlack=" + isBlack +
        ", note=" + note +
        ", isRealName=" + isRealName +
        "}";
    }
}
