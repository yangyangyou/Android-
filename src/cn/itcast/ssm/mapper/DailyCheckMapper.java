package cn.itcast.ssm.mapper;

import cn.itcast.ssm.po.DailyCheck_1;
import cn.itcast.ssm.po.DailyCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyCheckMapper {
    int countByExample(DailyCheckExample example);

    int deleteByExample(DailyCheckExample example);

    int deleteByPrimaryKey(Integer checkId);

    int insert(DailyCheck_1 record);

    int insertSelective(DailyCheck_1 record);

    List<DailyCheck_1> selectByExample(DailyCheckExample example);

    DailyCheck_1 selectByPrimaryKey(Integer checkId);

    int updateByExampleSelective(@Param("record") DailyCheck_1 record, @Param("example") DailyCheckExample example);

    int updateByExample(@Param("record") DailyCheck_1 record, @Param("example") DailyCheckExample example);

    int updateByPrimaryKeySelective(DailyCheck_1 record);

    int updateByPrimaryKey(DailyCheck_1 record);
}