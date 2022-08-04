package com.kanou.service.impl;

import com.kanou.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/8/4 14:58
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Map randomRole(Long seed) throws Exception {

        Map<String, String> resMap = new LinkedHashMap<>();

        Random random = new Random(seed);
        resMap.put("力量", String.valueOf((random.nextInt(6) + 1) * 3 * 5));
        resMap.put("敏捷", String.valueOf((random.nextInt(6) + 1) * 3 * 5));
        resMap.put("体质", String.valueOf((random.nextInt(6) + 1) * 3 * 5));
        resMap.put("外貌", String.valueOf((random.nextInt(6) + 1) * 3 * 5));
        resMap.put("意志", String.valueOf((random.nextInt(6) + 1) * 3 * 5));

        resMap.put("体型", String.valueOf(((random.nextInt(6) + 1) * 2 + 6) * 5));
        resMap.put("教育", String.valueOf(((random.nextInt(6) + 1) * 2 + 6) * 5));
        resMap.put("智力", String.valueOf(((random.nextInt(6) + 1) * 2 + 6) * 5));
        return resMap;
    }
}
