package com.dao;

import com.entity.ChargerecordEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChargerecordVO;
import com.entity.view.ChargerecordView;


/**
 * 充值记录表
 * 
 * @author 
 * @email 
 * @date 2025-03-03 16:49:09
 */
public interface ChargerecordDao extends BaseMapper<ChargerecordEntity> {
	
	List<ChargerecordVO> selectListVO(@Param("ew") Wrapper<ChargerecordEntity> wrapper);
	
	ChargerecordVO selectVO(@Param("ew") Wrapper<ChargerecordEntity> wrapper);
	
	List<ChargerecordView> selectListView(@Param("ew") Wrapper<ChargerecordEntity> wrapper);

	List<ChargerecordView> selectListView(Pagination page,@Param("ew") Wrapper<ChargerecordEntity> wrapper);

	
	ChargerecordView selectView(@Param("ew") Wrapper<ChargerecordEntity> wrapper);
	

}
