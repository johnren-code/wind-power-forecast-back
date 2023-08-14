package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author lxp
 * @since 2023-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Dictionary对象", description="数据字典")
public class Dictionary extends Model<Dictionary> {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "父ID")
    private Integer parentId;

            @ApiModelProperty(value = "字典类型")
    @TableField("TYPE")
    private String type;

            @ApiModelProperty(value = "显示名")
    private String itemName;

            @ApiModelProperty(value = "存储值")
    private String itemValue;

            @ApiModelProperty(value = "描述说明")
    private String description;

            @ApiModelProperty(value = "扩展JSON")
    private String extdata;

            @ApiModelProperty(value = "排序号")
    private Integer sortId;

            @ApiModelProperty(value = "是否可改")
    private Boolean isEditable;

            @ApiModelProperty(value = "是否可删")
    private Boolean isDeletable;

            @ApiModelProperty(value = "删除标记")
    private Boolean isDeleted;

            @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreated;

            @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
