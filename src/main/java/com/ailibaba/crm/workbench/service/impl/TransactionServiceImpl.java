package com.ailibaba.crm.workbench.service.impl;

import com.ailibaba.crm.base.constants.CrmExceptionEnum;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.settings.bean.User;
import com.ailibaba.crm.settings.mapper.UserMapper;
import com.ailibaba.crm.utils.DateTimeUtil;
import com.ailibaba.crm.utils.UUIDUtil;
import com.ailibaba.crm.workbench.bean.*;
import com.ailibaba.crm.workbench.mappers.*;
import com.ailibaba.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.service.impl
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/26 16:50
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionHistoryMapper transactionHistoryMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    public ActivityMapper activityMapper;
    @Autowired
    public TransactionRemarkMapper transactionRemarkMapper;

    @Override
    public List<String> queryCustomerName(String customerName) {
        Example example=new Example(Customer.class);
        example.createCriteria().andLike("name","%"+customerName+"%");

        List<Customer> customerList=customerMapper.selectByExample(example);

//        存储客户名字
        List<String> list=new ArrayList<>();
        for (Customer customer : customerList) {
            list.add(customer.getName());
        }
        return list;
    }


    @Override
    public void saveTransaction(Transaction transaction,String username,String company) throws CrmException {
//        取出customerId
        Customer customer=new Customer();
        customer.setName(company);
        customer=customerMapper.selectOne(customer);
//        如果客户不存在
        if (customer==null){
            customer=new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setName(company);
            customer.setCreateBy(username);
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setOwner(transaction.getOwner());
            customer.setNextContactTime(transaction.getNextContactTime());
            customer.setDescription(transaction.getDescription());
            int count=customerMapper.insertSelective(customer);
            if (count==0){
                throw new CrmException(CrmExceptionEnum.CUSTOMER_SAVE);
            }
        }
        transaction.setCustomerId(customer.getId());
        //        创建交易
        transaction.setId(UUIDUtil.getUUID());
        transaction.setCreateTime(DateTimeUtil.getSysTime());
        transaction.setCreateBy(username);
        int count=transactionMapper.insertSelective(transaction);
        if (count==0){
            throw new CrmException(CrmExceptionEnum.TRANSACTION_SAVE);
        }
//        创建交易历史
        TransactionHistory transactionHistory=new TransactionHistory();
        transactionHistory.setId(UUIDUtil.getUUID());
        transactionHistory.setTranId(transaction.getId());
        transactionHistory.setCreateBy(username);
        transactionHistory.setCreateTime(DateTimeUtil.getSysTime());
        transactionHistory.setExpectedDate(transaction.getExpectedDate());
        transactionHistory.setMoney(transaction.getMoney());
        transactionHistory.setStage(transaction.getStage());

        count=transactionHistoryMapper.insertSelective(transactionHistory);
        if (count==0){
            throw new CrmException(CrmExceptionEnum.TRANSACTION_HISTORY_SAVE);
        }
    }

    @Override
    public Transaction queryTransactionById(String id, Map<String, String> stage2PossibilityMap) {
         Transaction transaction=transactionMapper.selectByPrimaryKey(id);
        //先查询所有者
        User user = userMapper.selectByPrimaryKey(transaction.getOwner());
        transaction.setOwner(user.getName());

        //查询客户信息
        Customer customer = customerMapper.selectByPrimaryKey(transaction.getCustomerId());
        transaction.setCustomerId(customer.getName());

        //查询当前交易的可能性
        String stage = transaction.getStage();
        String possibility = stage2PossibilityMap.get(stage);
        List<String> possibilities = new ArrayList<>();
        possibilities.add(possibility);
        transaction.setPossibility(possibilities);

        //查询联系人信息
        Contacts contacts = contactsMapper.selectByPrimaryKey(transaction.getContactsId());
        transaction.setContactsId(contacts.getFullname());

        //查询市场活动
        Activity activity = activityMapper.selectByPrimaryKey(transaction.getActivityId());
        transaction.setActivityId(activity.getName());

        //查询交易对应备注信息
        Example example = new Example(TransactionRemark.class);
        example.createCriteria().andEqualTo("tranId",transaction.getId());
        List<TransactionRemark> transactionRemarks = transactionRemarkMapper.selectByExample(example);
        transaction.setTransactionRemarks(transactionRemarks);

        //查询交易历史信息
        example = new Example(TransactionHistory.class);
        example.createCriteria().andEqualTo("tranId",transaction.getId());
        List<TransactionHistory> transactionHistories = transactionHistoryMapper.selectByExample(example);
        transaction.setTransactionHistories(transactionHistories);
        return transaction;
    }

    @Override
    public List<Map<String, ? extends Object>> stageList(String name, Integer location, String tranId, Map<String, String> map) throws CrmException {
        Transaction transaction = null;
        //将阶段map转换成List集合:因为后面需要进行阶段的索引判断
        Set<Map.Entry<String, String>> entries = map.entrySet();
        List<Map.Entry<String,String>> stageList = new ArrayList<>(entries);
//        交易历史
        TransactionHistory transactionHistory = null;
//        没有点击交易阶段图标
        if (location==null){
            transaction=transactionMapper.selectByPrimaryKey(tranId);
        }else {
            //点击了交易阶段,修改交易表中的交易阶段
            String clickStage=stageList.get(location).getKey();
            Transaction tran = new Transaction();
            tran.setEditBy(name);
            tran.setEditTime(DateTimeUtil.getSysTime());
            tran.setId(tranId);
            tran.setStage(clickStage);
            int count=transactionMapper.updateByPrimaryKeySelective(tran);
            if(count==0){
                throw new CrmException(CrmExceptionEnum.TRANSACTION_STAGE_UPDATE);
            }
            //查询更改后的阶段
            transaction = transactionMapper.selectByPrimaryKey(tranId);
            //给当前交易添加交易历史
            transactionHistory = new TransactionHistory();
            transactionHistory.setId(UUIDUtil.getUUID());
            transactionHistory.setCreateBy(transaction.getEditBy());
            transactionHistory.setCreateTime(transaction.getEditTime());
            transactionHistory.setExpectedDate(transaction.getExpectedDate());
            transactionHistory.setMoney(transaction.getMoney());
            transactionHistory.setStage(transaction.getStage());
            transactionHistory.setTranId(transaction.getId());
            count = transactionHistoryMapper.insertSelective(transactionHistory);

            if(count == 0){
                throw new CrmException(CrmExceptionEnum.TRANSACTION_HISTORY_SAVE);
            }
        }
        //当前交易的阶段
        String currentStage = transaction.getStage();
        //获取当前交易的可能性
        String currentPossibility = map.get(currentStage);

        //创建一个返回所有阶段的集合 ? extends Object:泛型上线   ? super String:泛型下线
        List<Map<String,? extends Object>> stages = new ArrayList<>();
        int pointer = 0 ;
        //找到所有阶段当中为0的第一个黑x的坐标
        for(int i = 0 ; i < stageList.size(); i++){
            String possibility = stageList.get(i).getValue();
            if("0".equals(possibility)){
                pointer = i;
                break;
            }
        }

        /*
            为什么要获取当前交易的可能性
         */
        //交易失败了 前7个肯定是黑圈 后2个是x ,一个是黑x一个是红x，但是顺序不知道
        if("0".equals(currentPossibility)){

            //需要遍历所有阶段的可能性
            for(int i = 0; i < stageList.size(); i++){
                //创建一个map，用于存储交易失败的所有阶段
                Map<String,Object> failTranMap = new HashMap<>();
                String stage = stageList.get(i).getKey();
                String possibility = stageList.get(i).getValue();
                //只能意味着能确定后两个x
                if("0".equals(possibility)){
                    if(currentStage.equals(stage)){
                        //确定红x 后面哪个是红x，得根据当前交易的所在阶段来确定 如果在第八阶段，红x在第一个
                        //如果在第九阶段,红x在第二个
                        System.out.println("红x");
                        failTranMap.put("text",stage );
                        failTranMap.put("index",i+"");
                        failTranMap.put("type","红叉");
                        failTranMap.put("possibility",possibility);
                    }else{
                        //黑x
                        System.out.println("黑x");
                        failTranMap.put("text",stage );
                        failTranMap.put("index",i+"");
                        failTranMap.put("type","黑叉");
                        failTranMap.put("possibility",possibility);
                    }
                }else {//前7个黑圈
                    System.out.println("黑圈");
                    failTranMap.put("text",stage );
                    failTranMap.put("index",i+"");
                    failTranMap.put("type","黑圈");
                    failTranMap.put("possibility",possibility);
                }
                stages.add(failTranMap);
            }
        }else{
            //交易进行中或者交易成功
            //判断当前交易阶段的索引位置
            //取出当前交易的阶段，遍历所有阶段
            int index = 0;
            for(int i = 0; i < stageList.size(); i++){
                String stage = stageList.get(i).getKey();
                if(currentStage.equals(stage)){
                    //锚点索引位置
                    index = i;
                    break;
                }
            }

            for(int i = 0; i < stageList.size(); i++){
                Map<String,Object> successTranMap = new HashMap<>();
                String stage = stageList.get(i).getKey();
                String possibility = stageList.get(i).getValue();
                if(i < index){
                    //绿圈
                    System.out.println("绿圈");
                    successTranMap.put("text",stage );
                    successTranMap.put("index",i+"");
                    successTranMap.put("type","绿圈");
                    successTranMap.put("possibility",possibility);
                }else if(i == index){
                    //锚点
                    System.out.println("锚点");
                    successTranMap.put("text",stage );
                    successTranMap.put("index",i+"");
                    successTranMap.put("type","锚点");
                    successTranMap.put("possibility",possibility);
                }else if(index < i && i < pointer){
                    //黑圈
                    System.out.println("黑圈");
                    successTranMap.put("text",stage );
                    successTranMap.put("index",i+"");
                    successTranMap.put("type","黑圈");
                    successTranMap.put("possibility",possibility);
                }else{
                    //黑x
                    System.out.println("黑x");
                    successTranMap.put("text",stage );
                    successTranMap.put("index",i+"");
                    successTranMap.put("type","黑叉");
                    successTranMap.put("possibility",possibility);
                }
                stages.add(successTranMap);
            }

        }
        Map<String,TransactionHistory> transactionHistoryMap = new HashMap<>();
        if(transactionHistory != null){
            transactionHistoryMap.put("transactionHistory",transactionHistory);
        }
        stages.add(transactionHistoryMap);
        return stages;
    }

    @Override
    public TransactionEchartsResultVo queryTransactionEcharts() {
        List<Map<String,String>> mapList=transactionMapper.queryTransactionEcharts();
        Map<String,List<Map<String,String>>> map = new HashMap<>();
        //图表中的series的data
        map.put("transactions",mapList);
        //图表中legend的data
        List<String> names = new ArrayList<>();
        for(Map<String,String> m : mapList){
            String name = m.get("name");
            names.add(name);
        }
        TransactionEchartsResultVo transactionEchartsResultVo=new TransactionEchartsResultVo();
        transactionEchartsResultVo.setDataList(names);
        transactionEchartsResultVo.setDataMap(map);
        return transactionEchartsResultVo;
    }
}
