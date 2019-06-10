
package com.yanbing.manage.managecontrol.tcp.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lilinfeng
 * @date 2014年2月23日
 * @version 1.0
 */
@Data
public class SubscribeReq implements Serializable {

    /**
     * 默认的序列号ID
     */
    private static final long serialVersionUID = 1L;

    private int subReqID;

    private String userName;

    private String productName;

    private String phoneNumber;

    private String address;

}
