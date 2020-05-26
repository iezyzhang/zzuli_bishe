package common.filter;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import biz.web.service.impl.BizService;

@WebListener
public class WL implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		BizService service = (BizService) wac.getBean("bizService");

		List list = service.queryByHQL("from Link");
		event.getServletContext().setAttribute("APPLICATION_FLINK", list);

	}

}
