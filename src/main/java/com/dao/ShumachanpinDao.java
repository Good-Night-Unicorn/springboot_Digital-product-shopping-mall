package com.dao;

import com.entity.ShumachanpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShumachanpinVO;
import com.entity.view.ShumachanpinView;


/**
 * 数码产品
 * 
 * @author 
 * @email 
 * @date 2025-03-03 16:49:08
 */
public interface ShumachanpinDao extends BaseMapper<ShumachanpinEntity> {
	
	List<ShumachanpinVO> selectListVO(@Param("ew") Wrapper<ShumachanpinEntity> wrapper);
	
	ShumachanpinVO selectVO(@Param("ew") Wrapper<ShumachanpinEntity> wrapper);
	
	List<ShumachanpinView> selectListView(@Param("ew") Wrapper<ShumachanpinEntity> wrapper);

	List<ShumachanpinView> selectListView(Pagination page,@Param("ew") Wrapper<ShumachanpinEntity> wrapper);

	
	ShumachanpinView selectView(@Param("ew") Wrapper<ShumachanpinEntity> wrapper);
	

}
