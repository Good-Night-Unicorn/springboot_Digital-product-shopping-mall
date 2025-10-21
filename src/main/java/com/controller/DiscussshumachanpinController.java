package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscussshumachanpinEntity;
import com.entity.view.DiscussshumachanpinView;

import com.service.DiscussshumachanpinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 数码产品评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 16:49:10
 */
@RestController
@RequestMapping("/discussshumachanpin")
public class DiscussshumachanpinController {
    @Autowired
    private DiscussshumachanpinService discussshumachanpinService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussshumachanpinEntity discussshumachanpin,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussshumachanpinEntity> ew = new EntityWrapper<DiscussshumachanpinEntity>();


        //查询结果
		PageUtils page = discussshumachanpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshumachanpin), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussshumachanpinEntity discussshumachanpin, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussshumachanpinEntity> ew = new EntityWrapper<DiscussshumachanpinEntity>();

        //查询结果
		PageUtils page = discussshumachanpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshumachanpin), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussshumachanpinEntity discussshumachanpin){
       	EntityWrapper<DiscussshumachanpinEntity> ew = new EntityWrapper<DiscussshumachanpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussshumachanpin, "discussshumachanpin")); 
        return R.ok().put("data", discussshumachanpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussshumachanpinEntity discussshumachanpin){
        EntityWrapper< DiscussshumachanpinEntity> ew = new EntityWrapper< DiscussshumachanpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussshumachanpin, "discussshumachanpin")); 
		DiscussshumachanpinView discussshumachanpinView =  discussshumachanpinService.selectView(ew);
		return R.ok("查询数码产品评论表成功").put("data", discussshumachanpinView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussshumachanpinEntity discussshumachanpin = discussshumachanpinService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussshumachanpin,deSens);
        return R.ok().put("data", discussshumachanpin);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussshumachanpinEntity discussshumachanpin = discussshumachanpinService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussshumachanpin,deSens);
        return R.ok().put("data", discussshumachanpin);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussshumachanpinEntity discussshumachanpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussshumachanpin);
        discussshumachanpinService.insert(discussshumachanpin);
        return R.ok().put("data",discussshumachanpin.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussshumachanpinEntity discussshumachanpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussshumachanpin);
        discussshumachanpinService.insert(discussshumachanpin);
        return R.ok().put("data",discussshumachanpin.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussshumachanpinEntity discussshumachanpin = discussshumachanpinService.selectOne(new EntityWrapper<DiscussshumachanpinEntity>().eq("", username));
        return R.ok().put("data", discussshumachanpin);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussshumachanpinEntity discussshumachanpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussshumachanpin);
        //全部更新
        discussshumachanpinService.updateById(discussshumachanpin);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussshumachanpinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussshumachanpinEntity discussshumachanpin, HttpServletRequest request,String pre){
        EntityWrapper<DiscussshumachanpinEntity> ew = new EntityWrapper<DiscussshumachanpinEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = discussshumachanpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussshumachanpin), params), params));
        return R.ok().put("data", page);
    }








}
