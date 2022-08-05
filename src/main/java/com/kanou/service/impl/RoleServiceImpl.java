package com.kanou.service.impl;

import com.kanou.service.RoleService;
import com.kanou.util.DiceUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/8/4 14:58
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * 随机获取一组属性
     * @param seed 随机种子
     * */
    @Override
    public Map randomRole(Long seed) throws Exception {

        Map<String, String> resMap = new LinkedHashMap<>();

        Random random = new Random(seed);
        resMap.put("力量", DiceUtil.nD6mul5(random, 3));
        resMap.put("敏捷", DiceUtil.nD6mul5(random, 3));
        resMap.put("体质", DiceUtil.nD6mul5(random, 3));
        resMap.put("外貌", DiceUtil.nD6mul5(random, 3));
        resMap.put("意志", DiceUtil.nD6mul5(random, 3));

        resMap.put("体型", DiceUtil.nD6mul5Plus(random, 2, 30));
        resMap.put("教育", DiceUtil.nD6mul5Plus(random, 2, 30));
        resMap.put("智力", DiceUtil.nD6mul5Plus(random, 2, 30));
        return resMap;
    }
}
