package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.Check;
import cn.itcast.ssm.po.CheckRecord;

public interface CheckNowHistoryService {
    public List<String> findGongxu(Check check) throws Exception;
    public List<String> findSheBei(Check check) throws Exception;
    public List<String> findWorker(Check check) throws Exception;
    public List<String> findCheck(Check check) throws Exception;
    public List<CheckRecord> findCheckMuch(Check check) throws Exception;
    public CheckRecord findCheckOne(int check_id) throws Exception;
}
