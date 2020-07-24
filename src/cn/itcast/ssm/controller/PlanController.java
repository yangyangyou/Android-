package cn.itcast.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.ShopPlanVo;
import cn.itcast.ssm.po.Task;
import cn.itcast.ssm.service.PlanService;
@Controller
public class PlanController {
   @Autowired
   private PlanService planService;
   
   @RequestMapping("/queryTask")
   public void queryTask(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   ShopPlanVo shopPlanVo = new ShopPlanVo();
	   ShopPlan shopPlan = new ShopPlan();
	   shopPlan.setBatch_no("19950618");
	   shopPlan.setMaterial_no("200101001");
	   shopPlan.setShop_name("工艺车间");
	   shopPlanVo.setShopPlan(shopPlan);
	   int id = planService.findPlanId(shopPlanVo);
	   List<Task> task_list = planService.findProcess(id);
	   for(int i=0;i<task_list.size();i++) {
	   }
   }
}
