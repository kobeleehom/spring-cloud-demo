package com.dongxie.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class reqFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(reqFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        logger.info("the request url is {} ,--to method--> {}", request.getRequestURL().toString(),request.getMethod());
        String token = request.getParameter("token");
        if(token == null) {
            logger.warn("request with no token,please check!");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                currentContext.getResponse().getWriter().write("request with no token,please check!");
            }catch (IOException ioe){
                logger.warn("request has error, {}", ioe);
            }
        }
        return null;
    }
}
