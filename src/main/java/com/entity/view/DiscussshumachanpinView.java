package com.entity.view;

import com.entity.DiscussshumachanpinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 数码产品评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-03 16:49:10
 */
@TableName("discussshumachanpin")
public class DiscussshumachanpinView  extends DiscussshumachanpinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussshumachanpinView(){
	}
 
 	public DiscussshumachanpinView(DiscussshumachanpinEntity discussshumachanpinEntity){
 	try {
			BeanUtils.copyProperties(this, discussshumachanpinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
