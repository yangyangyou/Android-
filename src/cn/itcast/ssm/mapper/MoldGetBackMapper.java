package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.po.MoldOfAsset;
import cn.itcast.ssm.po.MoldRecord;
import cn.itcast.ssm.po.MoldUpdateVo;
import cn.itcast.ssm.po.MoldVo;
import cn.itcast.ssm.po.Task_New;

public interface MoldGetBackMapper {
   public void GetBackMold(MoldRecord moldRecord) throws Exception;
   public String findMoldName(String mold_no) throws Exception;
   public List<Integer> findPlanId(String batch_no) throws Exception;
   public List<Task_New> findBindingMold(MoldVo moldVo) throws Exception;
   public void updateMold(MoldUpdateVo moldUpdateVo) throws Exception;
   public MoldOfAsset findIsMold(String asset) throws Exception;
   public void updateAssetMold(String mold,String asset) throws Exception;
   public void insertAssetMold(MoldOfAsset moldOfAsset) throws Exception;
   public void deleteAssetMold(String asset) throws Exception;
}
