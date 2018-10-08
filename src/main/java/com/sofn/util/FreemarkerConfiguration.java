package com.sofn.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerConfiguration {

	private FreemarkerConfiguration() {
	}
	
	private static Configuration cfg;
	private static String encoding = "UTF-8";
	
	@SuppressWarnings("deprecation")
	public static Configuration getFreemarkerConfiguration(){
		if(cfg == null){
			 //指定版本号
			cfg = new Configuration(Configuration.VERSION_2_3_22);
			cfg.setDefaultEncoding(encoding);
			cfg.setTemplateUpdateDelay(0);
		}
		return cfg;
	}
	
	public static void writeTemplateFile(Configuration cfg,String templatePath, String templateName,String filePath,Map<String, Object> dataMap) throws IOException{
		File file=new File(filePath);
		//设置模板目录
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        
        //从设置的目录中获得模板
        Template template = cfg.getTemplate(templateName);
        try {
        Writer writer=new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
     
			template.process(dataMap, writer);
			writer.flush();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
        
	}

}
