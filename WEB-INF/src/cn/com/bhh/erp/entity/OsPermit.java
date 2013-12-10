//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/08/20
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;

/**
 * operation type info.
 * @author xiangzq
 * @version 2.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class OsPermit implements Serializable {
    private Integer id;
    private String name;

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
}
