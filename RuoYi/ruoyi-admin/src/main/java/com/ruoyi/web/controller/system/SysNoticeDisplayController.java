package com.ruoyi.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysNoticeService;

@Controller
@RequestMapping("/system/display")
public class SysNoticeDisplayController {
	
	@Autowired
	private ISysNoticeService noticeService;
	
	@PostMapping("/notice")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
		SysNotice notice = new SysNotice();
		List<SysNotice> noticeList = noticeService.selectDisplayNoticeList(notice);
		AjaxResult json = new AjaxResult();
		json.put("code", 0);
		json.put("notice", JSON.toJSONString(noticeList));
        return json;
    }
	
}
