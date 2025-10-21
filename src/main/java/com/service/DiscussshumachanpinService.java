package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussshumachanpinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussshumachanpinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussshumachanpinView;


/**
 * 数码产品评论表
 *
 * @author 
 * @email 
 * @date 2025-03-03 16:49:10
 */
public interface DiscussshumachanpinService extends IService<DiscussshumachanpinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussshumachanpinVO> selectListVO(Wrapper<DiscussshumachanpinEntity> wrapper);
   	
   	DiscussshumachanpinVO selectVO(@Param("ew") Wrapper<DiscussshumachanpinEntity> wrapper);
   	
   	List<DiscussshumachanpinView> selectListView(Wrapper<DiscussshumachanpinEntity> wrapper);
   	
   	DiscussshumachanpinView selectView(@Param("ew") Wrapper<DiscussshumachanpinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussshumachanpinEntity> wrapper);

   	

}

