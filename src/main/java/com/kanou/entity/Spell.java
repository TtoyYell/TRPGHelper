package com.kanou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String nameEng;

    /** 法术中文名 */
    private String nameCha;

    /** 类型 */
    private String type;

    /** 施法时间 */
    private String costTime;

    /** 施法距离 */
    private String distance;

    /** 法术成分 */
    private String components;

    /** 持续时间 */
    private String duration;

    /** 描述 */
    private String description;

    /** 环阶 */
    private String ringNum;
}
