package com.algorithm.service.impl;

import java.math.BigDecimal;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algorithm.repository.ConfigRep;
import com.algorithm.service.ConfigService;
import com.algorithm.util.SysProp;

/**
 * @class: ConfigServiceImpl
 * @description: 
 * @author Liuzhen
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Autowired
	private ConfigRep configRep;
	
	@Override
	public BigDecimal getRatio() {
		return configRep.getRatio();
	}
	
	@Override
	public void setRatio(BigDecimal ratio, ServletContext sc) {
		configRep.setRatio(ratio);
		sc.setAttribute(SysProp.SCORE_RATIO, ratio);
	}
}
