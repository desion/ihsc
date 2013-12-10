//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Country implements Serializable {
    private Integer id;
    private String name;
    private String shortName;
    private String nameEn;
    private String shortNameEn;
    private Integer exclusiveKey;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the nameEn
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * @param nameEn the nameEn to set
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * @return the shortNameEn
     */
    public String getShortNameEn() {
        return shortNameEn;
    }

    /**
     * @param shortNameEn the shortNameEn to set
     */
    public void setShortNameEn(String shortNameEn) {
        this.shortNameEn = shortNameEn;
    }

    /**
     * @return the exclusiveKey
     */
    public Integer getExclusiveKey() {
        return exclusiveKey;
    }

    /**
     * @param exclusiveKey the exclusiveKey to set
     */
    public void setExclusiveKey(Integer exclusiveKey) {
        this.exclusiveKey = exclusiveKey;
    }
}
