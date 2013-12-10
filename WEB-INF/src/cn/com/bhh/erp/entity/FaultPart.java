//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;

/**
 * fault part info
 * @author liugd
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FaultPart implements Serializable {
    private Integer id;
    private String name;
    private String faultMachineType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the faultMachineType
     */
    public String getFaultMachineType() {
        return faultMachineType;
    }

    /**
     * @param faultMachineType the faultMachineType to set
     */
    public void setFaultMachineType(String faultMachineType) {
        this.faultMachineType = faultMachineType;
    }
    
}
