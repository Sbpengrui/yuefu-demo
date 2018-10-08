package com.sofn.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sofn.util.FreemarkerConfiguration;
import com.sofn.util.HttpCode;
import com.sofn.util.ImageUtil;

import freemarker.template.Configuration;

@Controller
@RequestMapping("/base")
public class BaseController {

	private static final String TEMPLATES_PATH = "/templates"; // 模板文件路径

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, dateEditor);

	}

	public Object getAttribute(String attributeName) {
		return this.getRequest().getAttribute(attributeName);
	}

	public void setAttribute(String attributeName, Object object) {
		this.getRequest().setAttribute(attributeName, object);
	}

	public Object getSessionAttrbute(String attributeName) {
		return this.getRequest().getSession(true).getAttribute(attributeName);
	}

	public void setSessionAttrbute(String attributeName, Object object) {
		this.getRequest().getSession(true).setAttribute(attributeName, object);
	}

	public HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) ra).getRequest();
	}

	public HttpServletResponse getResponse() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) ra).getResponse();
	}

	public HttpSession getSession() {
		return this.getRequest().getSession(true);
	}

	public String getParameter(String paraName) {
		return this.getRequest().getParameter(paraName);
	}

	/**
	 * 获取表单格式数据(或url拼接参数)
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getParameterMap() {
		return this.getRequest().getParameterMap();
	}

	public String getHeader(String headerName) {
		return this.getRequest().getHeader(headerName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getHeaderMap() {
		Enumeration headerNames = this.getRequest().getHeaderNames();
		Map headerMap = new HashMap<>();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String headerValue = getRequest().getHeader(headerName);
			headerMap.put(headerName, headerValue);
		}
		return headerMap;
	}

	public String getIpAddress() {
		String ip = this.getRequest().getRemoteAddr();
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	/**
	 * 获取服务器ip地址
	 * 
	 * @return
	 */
	public String getServerIpAddress() {
		InetAddress address;
		String serverIpAddress = null;
		try {
			address = InetAddress.getLocalHost(); // 获取的是本地的IP地址
													// //PC-20140317PXKX/192.168.0.121
			serverIpAddress = address.getHostAddress();// 192.168.0.121
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return serverIpAddress;
	}

	/**
	 * 允许跨域访问
	 */
	public void allowCrossDomainAccess() {
		HttpServletResponse servletResponse = getResponse();
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET");
		servletResponse
				.setHeader("Access-Control-Allow-Headers:x-requested-with",
						"content-type");
	}

	public ModelAndView setModelAndWriteHtml(ModelAndView mav,
			Map<String, Object> dataMap) throws IOException {
		// 获得模板文件路径
		String templatePath = this.getClass().getClassLoader()
				.getResource(TEMPLATES_PATH).getPath();
		// 静态页面名称
		String templateName = mav.getViewName() + ".ftl";
		// 模板静态页面路径
		String filePath = getSession().getServletContext().getRealPath(
				"/" + mav.getViewName() + ".html");

		Configuration cfg = FreemarkerConfiguration
				.getFreemarkerConfiguration();

		FreemarkerConfiguration.writeTemplateFile(cfg, templatePath,
				templateName, filePath, dataMap);
		return mav;
	}

	@RequestMapping(value = "/getPicStream", method = RequestMethod.GET)
	// @GetMapping("/getPicStream")
	public void valicode(HttpServletRequest req, HttpSession session,
			HttpServletResponse res) throws IOException {

		Object[] objs = ImageUtil.createImage();
		session.setAttribute("valicode", objs[0]);
		BufferedImage image = (BufferedImage) objs[1];

		res.setContentType("image/png");
		OutputStream os = res.getOutputStream();
		ImageIO.write(image, "png", os);
		os.close();
	}

	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
		return setSuccessModelMap(modelMap, null);
	}

	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap,
			Object data) {

		return setModelMap(modelMap, HttpCode.OK, data);
	}

	protected ResponseEntity<ModelMap> setFailModelMap(ModelMap modelMap,
			Object data) {
		return setModelMap(modelMap, HttpCode.BAD_REQUEST, data);
	}

	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap,
			HttpCode code) {
		return setModelMap(modelMap, code, null);
	}

	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap,
			HttpCode code, Object data) {
		if (modelMap == null) {
			modelMap = new ModelMap();
		}
		modelMap.remove("void");
		if (data != null) {
			modelMap.put("data", data);
		} else if (!modelMap.containsKey("data")) {
			modelMap.put("data", null);
		}
		modelMap.put("httpCode", code.value());
		modelMap.put("msg", code.msg());
		modelMap.put("timestamp", System.currentTimeMillis());
		return ResponseEntity.ok(modelMap);
	}
	
	protected ModelAndView setModelAndView(String viewName){
		ModelAndView mv = new ModelAndView(viewName);
		return mv;
	}

}