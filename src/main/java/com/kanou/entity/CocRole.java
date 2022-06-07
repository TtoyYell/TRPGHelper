package com.kanou.entity;

import lombok.Data;

/**
 * COC调查员角色卡
 * @author  Yetianyi
 * @version 1.0
 * @date  2022/5/23 19:47
 */
@Data
public class CocRole {
    /** 角色卡id */
    String id;

    /** 玩家名字 */
    String plName;

    /** 调查员的名字 */
    String pcName;

    /** 调查员年龄 */
    int age;

    /** 调查员职业 */
    String profession;

    /** 调查员性别 */
    String sex;
}
