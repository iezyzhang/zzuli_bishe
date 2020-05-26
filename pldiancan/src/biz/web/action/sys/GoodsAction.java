package biz.web.action.sys;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.Constant;
import util.FieldUtil;
import util.MessageUtil;
import util.Page;
import util.SearchParamBean;
import util.StringUtil;
import biz.entity.Goods;
import biz.entity.GoodsType;
import biz.entity.main.SimpleUser;
import biz.web.service.impl.BizService;

import com.opensymphony.xwork2.ModelDriven;
import common.action.struts.BaseAction;

@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class GoodsAction extends BaseAction implements ModelDriven<Goods> {
	@Autowired
	private BizService service;

	private long uid;
	private Goods bean = new Goods();

	@Action(value = "toUpload", results = { @Result(name = "add2", location = "/WEB-INF/jsp/select/uploadFile.jsp") })
	public String toUpload() {
		return "add2";
	}

	@Action(value = "add2Goods", results = { @Result(name = "add2", location = "/WEB-INF/jsp/sys/addGoods.jsp") })
	public String add2() {
		List<?> list = service.findAll(GoodsType.class);
		putRequestValue("list", list);
		return "add2";
	}

	@Action(value = "getGoods", results = { @Result(name = "getOne", location = "/WEB-INF/jsp/sys/modifyGoods.jsp") })
	public String get() {
		try {
			List<?> list = service.findAll(GoodsType.class);
			putRequestValue("list", list);
			Goods temp = (Goods) service.get(Goods.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteGoods")
	public String delete() {
		try {
			service.delete(Goods.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "操作成功.", "mainquery");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "操作失败");
			return ERROR;
		}
	}

	@Action(value = "updateGoods")
	public String update() {
		try {
			String img = getHttpServletRequest().getParameter("attachment.attachmentPath");
			if (StringUtils.isNotBlank(img)) {
				bean.setImgpath(img);
			}
			service.update(bean);
			MessageUtil.addRelMessage(getHttpServletRequest(), "操作成功.", "baseAdd");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "操作失败");
			return ERROR;
		}
	}

	@Action(value = "addGoods")
	public String add() {
		try {
			String img = getHttpServletRequest().getParameter("attachment.attachmentPath");
			bean.setImgpath(img);
			bean.setId(new Date().getTime());
			service.add(bean);
			MessageUtil.addMessage(getHttpServletRequest(), "操作成功.");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "操作失败");
			return ERROR;
		}
	}

	@Action(value = "queryGoods", results = { @Result(name = "queryList", location = "/WEB-INF/jsp/sys/listGoods.jsp") })
	public String query() {
		try {
			int pageNum = 0;
			String t = getHttpServletRequest().getParameter("pageNum");
			if (StringUtil.notEmpty(t)) {
				pageNum = Integer.valueOf(t);
			}
			Page p = (Page) getHttpSession().getAttribute(Constant.SESSION_PAGE);
			if (pageNum == 0 || p == null) {
				p = new Page();
				p.setCurrentPageNumber(1);
				p.setTotalNumber(0l);
				p.setItemsPerPage(Constant.SESSION_PAGE_NUMBER);

				// 字段名称集合
				LinkedList<String> parmnames = new LinkedList<String>();
				// 字段值集合
				LinkedList<Object> parmvalues = new LinkedList<Object>();
				// 页面参数集合
				Set parm = getHttpServletRequest().getParameterMap().entrySet();
				for (Object o : parm) {
					Entry<String, Object> e = (Entry<String, Object>) o;
					String name = e.getKey();// 页面字段名称
					if (name.startsWith("s_")) {
						String fieldValue = getHttpServletRequest().getParameter(name);// 页面字段值
						if (StringUtil.notEmpty(fieldValue)) {
							name = name.substring(2, name.length());// 实体字段名称
							parmnames.add(name);
							parmvalues.add(FieldUtil.format(Goods.class, name, fieldValue));
						}
					}
				}

				SearchParamBean sbean = new SearchParamBean();
				sbean.setParmnames(parmnames);
				sbean.setParmvalues(parmvalues);

				p.setConditonObject(sbean);
			} else {
				p.setCurrentPageNumber(pageNum);
			}
			Page page = null;
			page = service.find(p, Goods.class);

			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public Goods getModel() {
		return bean;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
