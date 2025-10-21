package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ShumachanpinDao;
import com.entity.ShumachanpinEntity;
import com.service.ShumachanpinService;
import com.entity.vo.ShumachanpinVO;
import com.entity.view.ShumachanpinView;

@Service("shumachanpinService")
public class ShumachanpinServiceImpl extends ServiceImpl<ShumachanpinDao, ShumachanpinEntity> implements ShumachanpinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShumachanpinEntity> page = this.selectPage(
                new Query<ShumachanpinEntity>(params).getPage(),
                new EntityWrapper<ShumachanpinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShumachanpinEntity> wrapper) {
		  Page<ShumachanpinView> page =new Query<ShumachanpinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ShumachanpinVO> selectListVO(Wrapper<ShumachanpinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShumachanpinVO selectVO(Wrapper<ShumachanpinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShumachanpinView> selectListView(Wrapper<ShumachanpinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShumachanpinView selectView(Wrapper<ShumachanpinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
