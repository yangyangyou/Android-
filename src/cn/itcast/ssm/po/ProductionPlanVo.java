package cn.itcast.ssm.po;

import java.util.List;

public class ProductionPlanVo {
	private Integer finish_num;
	private List<ProductionPlan> production_plans;
	public Integer getFinish_num() {
		return finish_num;
	}
	public void setFinish_num(Integer finish_num) {
		this.finish_num = finish_num;
	}
	public List<ProductionPlan> getProduction_plans() {
		return production_plans;
	}
	public void setProduction_plans(List<ProductionPlan> production_plans) {
		this.production_plans = production_plans;
	}
	
}
