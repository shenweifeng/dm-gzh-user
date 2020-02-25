package com.guozhengood.user.domain.util;

import com.guozhengood.user.domain.BaseModel;
import com.guozhengood.user.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @date 2015-6-21 上午10:20:00
 * 
 * @author swf
 * 
 * @Description 查询条件
 * 
 */
public class QueryParam extends BaseModel {

    private static final long serialVersionUID = 4499355565703257166L;

    private Integer           i1;
    private Integer           i2;
    private Integer           i3;
    private Integer           i4;
    private Integer           i5;

    private String            s1;
    private String            s2;
    private String            s3;
    private String            s4;
    private String            s5;

    private String            s6;
    private String            s7;
    private String            s8;
    private String            s9;
    private String            s10;

    private String            startTime;
    private String            endTime;
    private Integer           from;
    private Integer           size;

    private List<Integer>     groupIds;

    private Date              d1;
    private Date              d2;
    private Date              d3;
    private Date              d4;
    private Date              d5;

    private String            diff1;

    private Integer           count;

    private String            province;
    private String            city;

    private Integer           chainId;

    public QueryParam() {
    }

    public boolean check() {
        return true;
    }

    public boolean checkTime() {
        if (StringUtil.isNotEmpty(startTime, endTime)) {
            return true;
        }
        return false;
    }

    public boolean checkPage() {
        if (from != null && size != null) {
            return true;
        }
        return false;
    }

    public Integer getI1() {
        return i1;
    }

    public void setI1(Integer i1) {
        this.i1 = i1;
    }

    public Integer getI2() {
        return i2;
    }

    public void setI2(Integer i2) {
        this.i2 = i2;
    }

    public Integer getI3() {
        return i3;
    }

    public void setI3(Integer i3) {
        this.i3 = i3;
    }

    public Integer getI4() {
        return i4;
    }

    public void setI4(Integer i4) {
        this.i4 = i4;
    }

    public Integer getI5() {
        return i5;
    }

    public void setI5(Integer i5) {
        this.i5 = i5;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public String getS8() {
        return s8;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }

    public String getS9() {
        return s9;
    }

    public void setS9(String s9) {
        this.s9 = s9;
    }

    public String getS10() {
        return s10;
    }

    public void setS10(String s10) {
        this.s10 = s10;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Integer> groupIds) {
        this.groupIds = groupIds;
    }

    public Date getD1() {
        return d1;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public Date getD2() {
        return d2;
    }

    public void setD2(Date d2) {
        this.d2 = d2;
    }

    public Date getD3() {
        return d3;
    }

    public void setD3(Date d3) {
        this.d3 = d3;
    }

    public Date getD4() {
        return d4;
    }

    public void setD4(Date d4) {
        this.d4 = d4;
    }

    public Date getD5() {
        return d5;
    }

    public void setD5(Date d5) {
        this.d5 = d5;
    }

    public String getDiff1() {
        return diff1;
    }

    public void setDiff1(String diff1) {
        this.diff1 = diff1;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

}
