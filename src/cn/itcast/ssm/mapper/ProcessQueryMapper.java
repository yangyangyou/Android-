package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.po.ShopPlanVo;
import cn.itcast.ssm.po.Task;

public interface ProcessQueryMapper {
   public int findPlanId(ShopPlanVo shopPlanVo) throws Exception;
   public List<Task> findProcess(int id) throws Exception;
}
