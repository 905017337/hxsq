package com.jm.cloud.entity.Response;

import com.jm.cloud.entity.Phonebook;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Data
public class PhoneTypeResponse implements Serializable {


    private Integer id;
    /**
     * 类型名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private String orderId;

    /**
     * 创建时间
     */
    private Date crateTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private List<Phonebook> phonebookList;

}
