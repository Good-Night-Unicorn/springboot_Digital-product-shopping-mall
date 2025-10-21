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
import com.entity.OrdersEntity;
import com.service.OrdersService;
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.CuxiaohuodongEntity;
import com.entity.view.CuxiaohuodongView;

import com.service.CuxiaohuodongService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 促销活动
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 16:49:08
 */
@RestController
@RequestMapping("/cuxiaohuodong")
public class CuxiaohuodongController {
    @Autowired
    private CuxiaohuodongService cuxiaohuodongService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CuxiaohuodongEntity cuxiaohuodong,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("shangjia")) {
			cuxiaohuodong.setDianpumingcheng((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CuxiaohuodongEntity> ew = new EntityWrapper<CuxiaohuodongEntity>();
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);


        //查询结果
		PageUtils page = cuxiaohuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cuxiaohuodong), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CuxiaohuodongEntity cuxiaohuodong, 
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CuxiaohuodongEntity> ew = new EntityWrapper<CuxiaohuodongEntity>();
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);

        //查询结果
		PageUtils page = cuxiaohuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cuxiaohuodong), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CuxiaohuodongEntity cuxiaohuodong){
       	EntityWrapper<CuxiaohuodongEntity> ew = new EntityWrapper<CuxiaohuodongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( cuxiaohuodong, "cuxiaohuodong")); 
        return R.ok().put("data", cuxiaohuodongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CuxiaohuodongEntity cuxiaohuodong){
        EntityWrapper< CuxiaohuodongEntity> ew = new EntityWrapper< CuxiaohuodongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( cuxiaohuodong, "cuxiaohuodong")); 
		CuxiaohuodongView cuxiaohuodongView =  cuxiaohuodongService.selectView(ew);
		return R.ok("查询促销活动成功").put("data", cuxiaohuodongView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CuxiaohuodongEntity cuxiaohuodong = cuxiaohuodongService.selectById(id);
        if(null==cuxiaohuodong.getClicknum()){
            cuxiaohuodong.setClicknum(0);
        }
		cuxiaohuodong.setClicknum(cuxiaohuodong.getClicknum()+1);
		cuxiaohuodongService.updateById(cuxiaohuodong);
        cuxiaohuodong = cuxiaohuodongService.selectView(new EntityWrapper<CuxiaohuodongEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(cuxiaohuodong,deSens);
        return R.ok().put("data", cuxiaohuodong);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CuxiaohuodongEntity cuxiaohuodong = cuxiaohuodongService.selectById(id);
        if(null==cuxiaohuodong.getClicknum()){
            cuxiaohuodong.setClicknum(0);
        }
		cuxiaohuodong.setClicknum(cuxiaohuodong.getClicknum()+1);
		cuxiaohuodongService.updateById(cuxiaohuodong);
        cuxiaohuodong = cuxiaohuodongService.selectView(new EntityWrapper<CuxiaohuodongEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(cuxiaohuodong,deSens);
        return R.ok().put("data", cuxiaohuodong);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        CuxiaohuodongEntity cuxiaohuodong = cuxiaohuodongService.selectById(id);
        if(type.equals("1")) {
        	cuxiaohuodong.setThumbsupnum(cuxiaohuodong.getThumbsupnum()+1);
        } else {
        	cuxiaohuodong.setCrazilynum(cuxiaohuodong.getCrazilynum()+1);
        }
        cuxiaohuodongService.updateById(cuxiaohuodong);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CuxiaohuodongEntity cuxiaohuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(cuxiaohuodong);
        cuxiaohuodongService.insert(cuxiaohuodong);
        return R.ok().put("data",cuxiaohuodong.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CuxiaohuodongEntity cuxiaohuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(cuxiaohuodong);
        cuxiaohuodongService.insert(cuxiaohuodong);
        return R.ok().put("data",cuxiaohuodong.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CuxiaohuodongEntity cuxiaohuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(cuxiaohuodong);
        //全部更新
        cuxiaohuodongService.updateById(cuxiaohuodong);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        cuxiaohuodongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,CuxiaohuodongEntity cuxiaohuodong, HttpServletRequest request,String pre){
        EntityWrapper<CuxiaohuodongEntity> ew = new EntityWrapper<CuxiaohuodongEntity>();
        ew.eq("onshelves","1");
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
		params.put("sort", "clicknum");
        params.put("order", "desc");

		PageUtils page = cuxiaohuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cuxiaohuodong), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（基于用户的协同算法）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,CuxiaohuodongEntity cuxiaohuodong, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        // 查询订单数据
        List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>());
        Map<String, Map<String, Double>> ratings = new HashMap<>();
        if(orders!=null && orders.size()>0) {
            for(OrdersEntity o : orders) {
                Map<String, Double> userRatings = null;
                if(ratings.containsKey(o.getUserid().toString())) {
                    userRatings = ratings.get(o.getUserid().toString());
                } else {
                    userRatings = new HashMap<>();
                    ratings.put(o.getUserid().toString(), userRatings);
                }
                if(userRatings.containsKey(o.getGoodid().toString())) {
                    userRatings.put(o.getGoodid().toString(), userRatings.get(o.getGoodid().toString())+1.0);
                } else {
                    userRatings.put(o.getGoodid().toString(), 1.0);
                }

            }
        }
        // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings);

        // 为指定用户推荐物品
        String targetUser = userId;
        int numRecommendations = limit;
        List<String> recommendations = filter.recommendItems(targetUser, numRecommendations);

        // 输出推荐结果
        System.out.println("Recommendations for " + targetUser + ":");
        for (String item : recommendations) {
            System.out.println(item);
        }

        EntityWrapper<CuxiaohuodongEntity> ew = new EntityWrapper<CuxiaohuodongEntity>();
        ew.in("id", recommendations);
        ew.eq("onshelves","1");
        if(recommendations!=null && recommendations.size()>0 && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+String.join(",", recommendations)+")");
        }

        // 根据协同结果查询结果并返回
        PageUtils page = cuxiaohuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cuxiaohuodong), params), params));
        List<CuxiaohuodongEntity> pageList = (List<CuxiaohuodongEntity>)page.getList();
        if(recommendations!=null && recommendations.size()>0 && pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<CuxiaohuodongEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(cuxiaohuodongService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }






}
