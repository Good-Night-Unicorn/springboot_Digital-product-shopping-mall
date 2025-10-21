package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShumachanpinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ShumachanpinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ShumachanpinView;


/**
 * 数码产品
 *
 * @author 
 * @email 
 * @date 2025-03-03 16:49:08
 */
public interface ShumachanpinService extends IService<ShumachanpinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShumachanpinVO> selectListVO(Wrapper<ShumachanpinEntity> wrapper);
   	
   	ShumachanpinVO selectVO(@Param("ew") Wrapper<ShumachanpinEntity> wrapper);
   	
   	List<ShumachanpinView> selectListView(Wrapper<ShumachanpinEntity> wrapper);
   	
   	ShumachanpinView selectView(@Param("ew") Wrapper<ShumachanpinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShumachanpinEntity> wrapper);

   	

}

