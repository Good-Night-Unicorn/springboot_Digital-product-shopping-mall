package com.dao;

import com.entity.DiscussshumachanpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussshumachanpinVO;
import com.entity.view.DiscussshumachanpinView;


/**
 * 数码产品评论表
 * 
 * @author 
 * @email 
 * @date 2025-03-03 16:49:10
 */
public interface DiscussshumachanpinDao extends BaseMapper<DiscussshumachanpinEntity> {
	
	List<DiscussshumachanpinVO> selectListVO(@Param("ew") Wrapper<DiscussshumachanpinEntity> wrapper);
	
	DiscussshumachanpinVO selectVO(@Param("ew") Wrapper<DiscussshumachanpinEntity> wrapper);
	
	List<DiscussshumachanpinView> selectListView(@Param("ew") Wrapper<DiscussshumachanpinEntity> wrapper);

	List<DiscussshumachanpinView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussshumachanpinEntity> wrapper);

	
	DiscussshumachanpinView selectView(@Param("ew") Wrapper<DiscussshumachanpinEntity> wrapper);
	

}
