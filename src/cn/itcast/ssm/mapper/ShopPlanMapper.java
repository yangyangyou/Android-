package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.po.Asset;
import cn.itcast.ssm.po.ChangeTemPriceVo;
import cn.itcast.ssm.po.CipinNew;
import cn.itcast.ssm.po.CipinType;
import cn.itcast.ssm.po.GetMaterial;
import cn.itcast.ssm.po.MoldReady;
import cn.itcast.ssm.po.MoldReadyFindVo;
import cn.itcast.ssm.po.MonthPlan;
import cn.itcast.ssm.po.OperatorVo;
import cn.itcast.ssm.po.Person;
import cn.itcast.ssm.po.ProcessTransition;
import cn.itcast.ssm.po.ProcessTransitionVo;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ShopDelivery;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.Task_New;
import cn.itcast.ssm.po.TrackCard;
import cn.itcast.ssm.po.WorkCardNew;

public interface ShopPlanMapper {
	
	public List<String> findProcess(String material_no, String shop_name) throws Exception;
	public List<Person> findOperator(String shop_name) throws Exception;
	public List<Integer> findPlanId(String material_no,String batch_no,String plan_no,String shop_name) throws Exception;
	public List<Task_New> findTask(int plan_id,String process_name) throws Exception;
	public List<ProductionPlan> findProductionPlan(String plan_no,String material_no) throws Exception;
	public void insertNewShopPlan(ShopPlan shopPlan) throws Exception;
	public void insertTask(Task_New task) throws Exception;
	public void updateShopPlan(int plan_id) throws Exception;
	public List<TrackCard> findCardId(String plan_no,String material_no,String batch_no) throws Exception;
	public List<WorkCardNew> findWorkCard(int card_id,String shop_name,String process_name,String asset,String operator) throws Exception;
	public List<CipinNew> findCipin(int track_id) throws Exception;
	public List<CipinType> findCipinType(String shop_name,String cipin_type) throws Exception;
	public void insertWorKCard(WorkCardNew workCardNew) throws Exception;
	public void insertTemPriceWorKCard(WorkCardNew workCardNew) throws Exception;
	public void insertCipin(CipinNew cipinNew) throws Exception;
	public void updateWorkCard(WorkCardNew workCardNew) throws Exception;
	public void updateTemPriceWorkCard(WorkCardNew workCardNew) throws Exception;
	public void updateCipin(CipinNew cipinNew) throws Exception;
	public void deleteCipin(int track_id) throws Exception;
	public List<CipinType> findLiaofeiType() throws Exception;
	public List<ProductionPlan> findNewProductionPlan(String shop_name) throws Exception;
	public List<ShopDelivery> findShopDelivery(int plan_id) throws Exception;
	public List<ProductionPlan> findPushProductionPlan() throws Exception;
	public void updatePushProductionPlan(int plan_id) throws Exception;
	public List<String> findRealProcess(int plan_id) throws Exception;
	public List<String> findRealProcessOneWorker(int plan_id,String operator) throws Exception;
	public String findPlanNum(int plan_id) throws Exception;
	public String findPrice(String material_no,String shop_name,String process_name) throws Exception;
	public List<Asset> findAssetName(String asset_no) throws Exception;
	public List<WorkCardNew> findWorkCardTogether(OperatorVo operator) throws Exception;
	public String findAssetMold(String asset) throws Exception;
	public List<WorkCardNew> findTemPrice(String material_no,String plan_no,String batch_no,String shop_name) throws Exception;
	public List<WorkCardNew> findAndUpdateTemPrice(String material_no,String plan_no,String batch_no,String shop_name) throws Exception;
	public void updateTemPrice(ChangeTemPriceVo changeTemPriceVo) throws Exception;
	public String findNeedMold(String material_no,String shop_name,String process_name,String client_material_no) throws Exception;
	public void insertNeedMold(MoldReady moldReady) throws Exception;
	public List<MoldReadyFindVo> findNoReadyMold() throws Exception;
	public void updateReadyMold(int id) throws Exception;
	public List<GetMaterial> findNoPushGetMaterial() throws Exception;
	public void updateGetMaterialPush(int get_material_id) throws Exception;
	public List<GetMaterial> findNoGetGetMaterial() throws Exception;
	public void updateMaterialGet(int get_material_id) throws Exception;
	public void insertProcessTransition(ProcessTransition processTransition) throws Exception;
	public void updateProcessTransition(ProcessTransition processTransition) throws Exception;
	public List<String> findProcessAcceptor(ProcessTransition processTransition) throws Exception;
	public List<String> findProcessAcceptorTogether(ProcessTransitionVo processTransitionVo) throws Exception;
	
	public List<MonthPlan> findMonthPlan()throws Exception;
}
