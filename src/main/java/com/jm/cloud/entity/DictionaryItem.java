package com.jm.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字典类型条目表
 * </p>
 *
 * @author caozhenhao
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictionaryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableField("SORT")
    private Integer sort;

    /**
     * 字典内容
     */
    @TableField("TEXT")
    private String text;

    /**
     * 值
     */
    @TableField("VALUE")
    private Integer value;

    /**
     * 类型ID
     */
    @TableField("TYPE_ID")
    private String typeId;


}
