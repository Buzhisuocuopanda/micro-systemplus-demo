package com.mkst.mini.systemplus.system.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkst.mini.systemplus.common.annotation.Log;
import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.common.enums.BusinessType;
import com.mkst.mini.systemplus.common.utils.ExcelUtil;
import com.mkst.mini.systemplus.framework.web.page.TableDataInfo;
import com.mkst.mini.systemplus.system.domain.Sequence;
import com.mkst.mini.systemplus.system.service.ISequenceService;

/**
 * 序列码 信息操作处理
 * 
 * @author systemplus
 * @date 2019-03-01
 */
@Controller
@RequestMapping("/system/sequence")
public class SequenceController extends BaseController
{
    private String prefix = "system/sequence";
	
	@Autowired
	private ISequenceService sequenceService;
	
	@RequiresPermissions("system:sequence:view")
	@GetMapping()
	public String sequence()
	{
	    return prefix + "/sequence";
	}
	
	/**
	 * 查询序列码列表
	 */
	@RequiresPermissions("system:sequence:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Sequence sequence)
	{
		startPage();
        List<Sequence> list = sequenceService.selectSequenceList(sequence);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出序列码列表
	 */
	@RequiresPermissions("system:sequence:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Sequence sequence)
    {
    	List<Sequence> list = sequenceService.selectSequenceList(sequence);
        ExcelUtil<Sequence> util = new ExcelUtil<Sequence>(Sequence.class);
        return util.exportExcel(list, "sequence");
    }
	
	/**
	 * 新增序列码
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存序列码
	 */
	@RequiresPermissions("system:sequence:add")
	@Log(title = "序列码", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Sequence sequence)
	{		
		return toAjax(sequenceService.insertSequence(sequence));
	}

	/**
	 * 修改序列码
	 */
	@GetMapping("/edit/{nAME}")
	public String edit(@PathVariable("nAME") String nAME, ModelMap mmap)
	{
		Sequence sequence = sequenceService.selectSequenceById(nAME);
		mmap.put("sequence", sequence);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存序列码
	 */
	@RequiresPermissions("system:sequence:edit")
	@Log(title = "序列码", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Sequence sequence)
	{		
		return toAjax(sequenceService.updateSequence(sequence));
	}
	
	/**
	 * 删除序列码
	 */
	@RequiresPermissions("system:sequence:remove")
	@Log(title = "序列码", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sequenceService.deleteSequenceByIds(ids));
	}
	
}
