package com.maverick.springboot.app.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PosTiempoTranscurrido extends ZuulFilter{
	
	private static Logger log = LoggerFactory.getLogger(PosTiempoTranscurrido.class);

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Entro a post filter ");
		
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
		
		log.info(String.format("Tiempo transcurrido en segundos %s .seg", tiempoTranscurrido));
		log.info(String.format("Tiempo transcurrido en miliseg %s .mili", tiempoTranscurrido));
		
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
}
