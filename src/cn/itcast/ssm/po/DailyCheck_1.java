package cn.itcast.ssm.po;

public class DailyCheck_1 {
    private Integer checkId;

    private String planNo;

    private String clientMaterialNo;

    private String materialNo;

    private String batchNo;

    private String shopName;

    private String processName;

    private String assetName;

    private String assetNo;

    private String assetXh;

    private String operator;

    private Integer state;

    private String errorDate;

    private String errorContent;

    private Integer isRepair;

    private String sendPerson;

    private Integer isRijian;

    private Integer isLatest;
    
    private Integer is_mold;

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo == null ? null : planNo.trim();
    }

    public String getClientMaterialNo() {
        return clientMaterialNo;
    }

    public void setClientMaterialNo(String clientMaterialNo) {
        this.clientMaterialNo = clientMaterialNo == null ? null : clientMaterialNo.trim();
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo == null ? null : materialNo.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo == null ? null : assetNo.trim();
    }

    public String getAssetXh() {
        return assetXh;
    }

    public void setAssetXh(String assetXh) {
        this.assetXh = assetXh == null ? null : assetXh.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate == null ? null : errorDate.trim();
    }

    public String getErrorContent() {
        return errorContent;
    }

    public void setErrorContent(String errorContent) {
        this.errorContent = errorContent == null ? null : errorContent.trim();
    }

    public Integer getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Integer isRepair) {
        this.isRepair = isRepair;
    }

    public String getSendPerson() {
        return sendPerson;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson == null ? null : sendPerson.trim();
    }

    public Integer getIsRijian() {
        return isRijian;
    }

    public void setIsRijian(Integer isRijian) {
        this.isRijian = isRijian;
    }

    public Integer getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(Integer isLatest) {
        this.isLatest = isLatest;
    }

	public Integer getIs_mold() {
		return is_mold;
	}

	public void setIs_mold(Integer is_mold) {
		this.is_mold = is_mold;
	}
}