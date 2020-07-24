package cn.itcast.ssm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;
import cn.itcast.ssm.mapper.RecordMapper;
import cn.itcast.ssm.po.Cipin;
import cn.itcast.ssm.po.Record;
import cn.itcast.ssm.po.Task;
import cn.itcast.ssm.po.WorkCard;
import cn.itcast.ssm.po.WorkCardTrack;
import cn.itcast.ssm.service.RecordService;

public class RecordServiceImpl implements RecordService{

	@Autowired
	private RecordMapper recordMapper;
	
	String produce_date;
	String patch;
	String asset;
	String mold;
	private List<Task> list;	
	
	@Transactional(noRollbackFor=Exception.class)
	public JSONObject getRecord(List<Record> record) throws Exception {
		JSONObject result = new JSONObject();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		produce_date = time.format(new Date());	
		int len = record.size();
		if(len > 0){
			String material_no = record.get(0).getMaterial_no();
			String batch_no = record.get(0).getBatch();
			String shop_name = record.get(0).getWorkshop();
			String process_name = record.get(0).getProcess();
			String assetState = record.get(0).getAssetState();
			String moldState = record.get(0).getMoldState();
			list = new ArrayList<Task>();
			list = recordMapper.findAsset(material_no, batch_no, shop_name, process_name);
			String price = recordMapper.findPrice(material_no, shop_name, process_name);
			if(list!=null){
				try{
					asset = list.get(0).getAsset();
					mold = list.get(0).getMold(); 
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				asset = null;
				mold = null;
			}
			for(int j=0; j<len/4; j++){
				String operator = record.get(4*j).getStaff();
				String hege_num = record.get(4*j).getNum(); //合格品数量
				String cipin_num1 = record.get(4*j+1).getNum();//次品数量
				String total_num = String.valueOf(Integer.parseInt(hege_num)+Integer.parseInt(cipin_num1));
				WorkCard workCard = new WorkCard();
				workCard.setShopName(shop_name);
				workCard.setProcessName(process_name);
				workCard.setOperator(operator);
				workCard.setAsset(asset);
				workCard.setAssetState(assetState);
				workCard.setMold(mold);
				workCard.setMoldState(moldState);
				workCard.setTotalNum(total_num);
				workCard.setHegeNum(hege_num);
				workCard.setTotalCipinNum(cipin_num1);
				workCard.setProduceDate(produce_date);
				workCard.setPrice(price);
				int cardId = recordMapper.getCardID(material_no, batch_no);
				workCard.setCardId(cardId);
				recordMapper.insertTrack(workCard);
				int trackId = workCard.getTrackId();			
				for(int i=0; i<len-2; i++){					
					String cipin_species = record.get(4*j+2+i).getPart();
					String cipin_num = record.get(4*j+2+i).getNum();	
					if(shop_name.equals("焊接工段")){
						if(cipin_species.equals("料废")){							
							recordMapper.insertCipin(trackId, "料废", cipin_species, cipin_num);
						}else {
							recordMapper.insertCipin(trackId, "工废", cipin_species, cipin_num);
						}
					}else if(shop_name.equals("冲压工段")){
						if(cipin_species.equals("工废")){
							recordMapper.insertCipin(trackId, "工废", cipin_species, cipin_num);
						}else{
							recordMapper.insertCipin(trackId, "料废", cipin_species, cipin_num);
						}
					}else if(shop_name.equals("仪表工段")){
						if(cipin_species.equals("高度")){
							recordMapper.insertCipin(trackId, "工废", cipin_species, cipin_num);
						}else{
							recordMapper.insertCipin(trackId, "料废", cipin_species, cipin_num);
						}
					}else if(shop_name.equals("后道工段")){
						if(cipin_species.equals("料废")){							
							recordMapper.insertCipin(trackId, "料废", cipin_species, cipin_num);
						}else {
							recordMapper.insertCipin(trackId, "工废", cipin_species, cipin_num);
						}
					}
				}
			}
			result.put("is_ok", "1");
		}			
		return result;
	}


	public JSONObject getProcess(String material_no, String batch_no, String shop_name) throws Exception {
		JSONObject result = new JSONObject();
		List<Task> gongxu = recordMapper.findProcess(material_no, batch_no, shop_name);  //仅查询不重复工序
		List<Task> operator = recordMapper.findOperator(material_no, batch_no, shop_name);	//查询该车间对应所有工序和人员信息
		if(gongxu.isEmpty()){
			result.put("process","未查询到工序");
			result.put("operator", operator);
		}else {
			result.put("process", gongxu);
			result.put("operator", operator);
		}
		return result;
	}

	public String findWhatRfid(String rfid) throws Exception {
		
		return recordMapper.findWhatRfid(rfid);
	}


	@Override
	public JSONObject getProcessByBatch(String batch_no, String shop_name)
			throws Exception {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		List<Task> gongxu = recordMapper.findProcessByBatch(batch_no, shop_name);//仅查询不重复工序
		
		if(gongxu.isEmpty()){
			result.put("process","未查询到工序");
		}else {
			result.put("process", gongxu);
		}
		return result;
	}
	//更新修改记录
	@Override
	public JSONObject updateRecord(List<Record> record) throws Exception {
		JSONObject result = new JSONObject();	
		int len = record.size();
		if(len > 0){
			String material_no = record.get(0).getMaterial_no();
			String batch_no = record.get(0).getBatch();
			String shop_name = record.get(0).getWorkshop();
			String process_name = record.get(0).getProcess();
			String assetState = record.get(0).getAssetState();
			String moldState = record.get(0).getMoldState();	
			String operator = recordMapper.findName(material_no, batch_no, shop_name, process_name);
			if (operator.contains(",")) {
		         List<String> list1 = Arrays.asList(operator.split(",")); 
		         if(list1.size()>0){
		            for(int j=0; j<list1.size(); j++){	
		            	String ope = list1.get(0);
		            	String ope1 = list1.get(j);
				        List<Cipin> cipin = recordMapper.findCipin(shop_name, process_name, ope);	
				        List<Cipin> cipin1 = recordMapper.findCipin(shop_name, process_name, ope1);
				        int num=cipin.size(); //第一个员工次品种类个数
				        int num1=cipin1.size();
						String hege_num = record.get((num+2)*j).getNum(); //合格品数量
						String cipin_num1 = record.get((num+2)*j+1).getNum();//次品数量
						String total_num = String.valueOf(Integer.parseInt(hege_num)+Integer.parseInt(cipin_num1));				
						WorkCardTrack workCardTrack = new WorkCardTrack();
						workCardTrack.setBatch_no(batch_no);
						workCardTrack.setMaterial_no(material_no);
						workCardTrack.setShopName(shop_name);
						workCardTrack.setProcessName(process_name);
						workCardTrack.setOperator(ope1);						
						workCardTrack.setAssetState(assetState);
						workCardTrack.setMoldState(moldState);
						workCardTrack.setTotalNum(total_num);
						workCardTrack.setHegeNum(hege_num);
						workCardTrack.setTotalCipinNum(cipin_num1);
						recordMapper.updateRecord(workCardTrack);			            	          												
						for(int i=0; i<num1; i++){					
							String cipin_species = record.get((num+2)*j+2+i).getPart();
							String cipin_num = record.get((num+2)*j+2+i).getNum();	
							recordMapper.updateCipin(shop_name, process_name, ope1, cipin_species, cipin_num);
						}						
					}
		          }
				 }else{				 	
			            List<Cipin> cipin = recordMapper.findCipin(shop_name, process_name, operator);	
			            int num=cipin.size(); //次品种类个数
						String hege_num = record.get(0).getNum(); //合格品数量
						String cipin_num1 = record.get(1).getNum();//次品数量
						String total_num = String.valueOf(Integer.parseInt(hege_num)+Integer.parseInt(cipin_num1));				
						WorkCardTrack workCardTrack = new WorkCardTrack();
						workCardTrack.setBatch_no(batch_no);
						workCardTrack.setMaterial_no(material_no);
						workCardTrack.setShopName(shop_name);
						workCardTrack.setProcessName(process_name);
						workCardTrack.setOperator(operator);
						workCardTrack.setAsset(asset);
						workCardTrack.setAssetState(assetState);
						workCardTrack.setMold(mold);
						workCardTrack.setMoldState(moldState);
						workCardTrack.setTotalNum(total_num);
						workCardTrack.setHegeNum(hege_num);
						workCardTrack.setTotalCipinNum(cipin_num1);
						recordMapper.updateRecord(workCardTrack);			            	          												
						for(int i=0; i<num; i++){					
							String cipin_species = record.get(2+i).getPart();
							String cipin_num = record.get(2+i).getNum();	
							recordMapper.updateCipin(shop_name, process_name, operator, cipin_species, cipin_num);
						}
		            }
				result.put("is_ok", "1");
			}			
			return result;		
		}

		//返回跟踪单（最新一条）记录
		@Override
		public JSONObject returnRecord(String material_no, String batch_no,String shop_name, String process) throws Exception {
			JSONObject result = new JSONObject();
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			String operator = recordMapper.findName(material_no, batch_no, shop_name, process);
			if (operator.contains(",")) {
	            List<String> list1 = Arrays.asList(operator.split(","));     
				for(int i=0; i<list1.size(); i++){
					Map<String,String>  map = new HashMap<String,String>();
					String ope = list1.get(i);
					WorkCard work = recordMapper.findRecord(material_no, batch_no, shop_name, process, ope);
					List<Cipin> cipin = recordMapper.findCipin(shop_name, process, ope);
					map.put("operator", ope);
					map.put("asset_state", work.getAssetState());
					map.put("mold_state", work.getMoldState());
					map.put("hege_num", work.getHegeNum());
					map.put("total_cipin_num", work.getTotalCipinNum());
					map.put("cipinSpecNum", String.valueOf(cipin.size()));
					for(int j=0; j<cipin.size(); j++){
						map.put("cipin_species"+j, cipin.get(j).getCipinSpecies());
						map.put("cipin_num"+j, cipin.get(j).getCipinNum());					
					}
					list.add(map);
				}
			}else{
				Map<String,String>map = new HashMap<String,String>();				
				WorkCard work = recordMapper.findRecord(material_no, batch_no, shop_name, process, operator);
				List<Cipin> cipin = recordMapper.findCipin(shop_name, process, operator);
				map.put("operator", operator);
				map.put("asset_state", work.getAssetState());
				map.put("mold_state", work.getMoldState());
				map.put("hege_num", work.getHegeNum());
				map.put("total_cipin_num", work.getTotalCipinNum());
				map.put("cipinSpecNum", String.valueOf(cipin.size()));
				for(int j=0; j<cipin.size(); j++){
					map.put("cipin_species"+j, cipin.get(j).getCipinSpecies());
					map.put("cipin_num"+j, cipin.get(j).getCipinNum());
				}
				list.add(map);
			}
			if(list.size()>0){
				result.put("record", list);
			}
			return result;	
		}
}
