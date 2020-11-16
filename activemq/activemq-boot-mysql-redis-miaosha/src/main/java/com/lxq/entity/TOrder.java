package com.lxq.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (TOrder)实体类
 *
 * @author makejava
 * @since 2020-11-14 09:48:00
 */
@Data
public class TOrder implements Serializable {
    private static final long serialVersionUID = 449868979264203326L;
    
    private Integer id;
    
    private Integer userid;
    
    private Integer goodsid;
    
    private Date createtime;
}