package com.kanou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 11:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spell {

    private Integer id;

    /** 法术英文名 */
    @NotEmpty
    private String nameEng;

    /** 法术中文名 */
    @NotEmpty
    private String nameCha;

    /** 类型 */
    @NotEmpty
    private String type;

    /** 施法时间 */
    @NotEmpty
    private String costTime;

    /** 施法距离 */
    @NotEmpty
    private String distance;

    /** 法术成分 */
    @NotEmpty
    private String components;

    /** 持续时间 */
    @NotEmpty
    private String duration;

    /** 描述 */
    @NotEmpty
    private String description;

    /** 环阶 */
    @NotEmpty
    private String ringNum;
}
