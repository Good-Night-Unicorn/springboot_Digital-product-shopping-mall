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


import com.dao.DiscussshumachanpinDao;
import com.entity.DiscussshumachanpinEntity;
import com.service.DiscussshumachanpinService;
import com.entity.vo.DiscussshumachanpinVO;
import com.entity.view.DiscussshumachanpinView;

@Service("discussshumachanpinService")
public class DiscussshumachanpinServiceImpl extends ServiceImpl<DiscussshumachanpinDao, DiscussshumachanpinEntity> implements DiscussshumachanpinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussshumachanpinEntity> page = this.selectPage(
                new Query<DiscussshumachanpinEntity>(params).getPage(),
                new EntityWrapper<DiscussshumachanpinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussshumachanpinEntity> wrapper) {
		  Page<DiscussshumachanpinView> page =new Query<DiscussshumachanpinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussshumachanpinVO> selectListVO(Wrapper<DiscussshumachanpinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussshumachanpinVO selectVO(Wrapper<DiscussshumachanpinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussshumachanpinView> selectListView(Wrapper<DiscussshumachanpinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussshumachanpinView selectView(Wrapper<DiscussshumachanpinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
