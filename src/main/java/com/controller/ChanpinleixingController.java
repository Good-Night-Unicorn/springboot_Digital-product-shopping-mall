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

import com.entity.ChanpinleixingEntity;
import com.entity.view.ChanpinleixingView;

import com.service.ChanpinleixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 产品类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 16:49:08
 */
@RestController
@RequestMapping("/chanpinleixing")
public class ChanpinleixingController {
    @Autowired
    private ChanpinleixingService chanpinleixingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChanpinleixingEntity chanpinleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ChanpinleixingEntity> ew = new EntityWrapper<ChanpinleixingEntity>();


        //查询结果
		PageUtils page = chanpinleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chanpinleixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ChanpinleixingEntity chanpinleixing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ChanpinleixingEntity> ew = new EntityWrapper<ChanpinleixingEntity>();

        //查询结果
		PageUtils page = chanpinleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chanpinleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChanpinleixingEntity chanpinleixing){
       	EntityWrapper<ChanpinleixingEntity> ew = new EntityWrapper<ChanpinleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chanpinleixing, "chanpinleixing")); 
        return R.ok().put("data", chanpinleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChanpinleixingEntity chanpinleixing){
        EntityWrapper< ChanpinleixingEntity> ew = new EntityWrapper< ChanpinleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chanpinleixing, "chanpinleixing")); 
		ChanpinleixingView chanpinleixingView =  chanpinleixingService.selectView(ew);
		return R.ok("查询产品类型成功").put("data", chanpinleixingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChanpinleixingEntity chanpinleixing = chanpinleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chanpinleixing,deSens);
        return R.ok().put("data", chanpinleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChanpinleixingEntity chanpinleixing = chanpinleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chanpinleixing,deSens);
        return R.ok().put("data", chanpinleixing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChanpinleixingEntity chanpinleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chanpinleixing);
        chanpinleixingService.insert(chanpinleixing);
        return R.ok().put("data",chanpinleixing.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChanpinleixingEntity chanpinleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chanpinleixing);
        chanpinleixingService.insert(chanpinleixing);
        return R.ok().put("data",chanpinleixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChanpinleixingEntity chanpinleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chanpinleixing);
        //全部更新
        chanpinleixingService.updateById(chanpinleixing);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chanpinleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
