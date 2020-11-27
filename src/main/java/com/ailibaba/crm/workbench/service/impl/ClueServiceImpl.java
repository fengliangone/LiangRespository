package com.ailibaba.crm.workbench.service.impl;

import com.ailibaba.crm.base.constants.CrmExceptionEnum;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.settings.bean.User;
import com.ailibaba.crm.settings.mapper.UserMapper;
import com.ailibaba.crm.utils.DateTimeUtil;
import com.ailibaba.crm.utils.UUIDUtil;
import com.ailibaba.crm.workbench.bean.*;
import com.ailibaba.crm.workbench.mappers.*;
import com.ailibaba.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.service.impl
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/22 20:28
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("clueService")
public class ClueServiceImpl implements ClueService {

    @Autowired
    public ClueMapper clueMapper;

    @Autowired
    public ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionHistoryMapper transactionHistoryMapper;

    @Autowired
    private TransactionRemarkMapper transactionRemarkMapper;

    @Override
    public void saveClue(Clue clue) throws CrmException {
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());

        int count=clueMapper.insertSelective(clue);

        if (count==0){
            throw new CrmException(CrmExceptionEnum.CLUE_SAVE);
        }

    }

    @Override
    public Clue queryClueDetailById(String id) {
//        查询线索信息
        Clue clue=clueMapper.selectByPrimaryKey(id);
        //根据activity中的owner查询对用的用户
        User user = userMapper.selectByPrimaryKey(clue.getOwner());
//        设置所有者转化为name
        clue.setOwner(user.getName());

//        查询线索下对应的备注信息
        Example example=new Example(ClueRemark.class);
        example.createCriteria().andEqualTo("clueId",clue.getId());
        List<ClueRemark> clueRemarks=clueRemarkMapper.selectByExample(example);

        //查询线索对应的活动信息
        //思路，先查询关联中间表，得到对应市场活动的id集合,在根据集合中的id号查询对应市场活动的信息
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(clue.getId());
        List<ClueActivityRelation> clueActivityRelations =
                clueActivityRelationMapper.select(clueActivityRelation);

        List<Activity> activities = new ArrayList<>();
        for (ClueActivityRelation activityRelation : clueActivityRelations) {
            //取出每个对应的activityId，查询市场活动表对应的数据
            Activity activity = activityMapper.selectByPrimaryKey(activityRelation.getActivityId());

            activities.add(activity);

        }
        //把查询出来的市场活动集合设置到clue中
        clue.setActivities(activities);

//        线索下对应的备注信息放入clue
        clue.setClueRemarks(clueRemarks);
        return clue;
    }

    @Override
    public void updateClueRemark(ClueRemark clueRemark) throws CrmException {
        clueRemark.setEditFlag("1");
        clueRemark.setEditTime(DateTimeUtil.getSysTime());
        int count=clueRemarkMapper.updateByPrimaryKeySelective(clueRemark);
        if (count==0){
            throw new CrmException(CrmExceptionEnum.CLUE_REMARK_UPDATE);
        }

    }

    @Override
    public void saveClueRemark(ClueRemark clueRemark) throws CrmException {
        clueRemark.setCreateTime(DateTimeUtil.getSysTime());
        clueRemark.setId(UUIDUtil.getUUID());
        clueRemark.setEditFlag("0");
        int count=clueRemarkMapper.insertSelective(clueRemark);
        if(count==0){
            throw  new CrmException(CrmExceptionEnum.CLUE_REMARK_SAVE);
        }

    }

    @Override
    public void deleteBind(ClueActivityRelation clueActivityRelation) throws CrmException {
        int count =  clueActivityRelationMapper.delete(clueActivityRelation);
        if(count == 0){
            throw new CrmException(CrmExceptionEnum.CLUE_ACTIVITY_UNBIND);
        }
    }

    @Override
    public void deleteManyBind(String clueId, String activityIds) throws CrmException {

        String[] activityid=activityIds.split(",");
        for (String s : activityid) {
            ClueActivityRelation clueActivityRelation=new ClueActivityRelation();
            clueActivityRelation.setClueId(clueId);
            clueActivityRelation.setActivityId(s);
            int count=clueActivityRelationMapper.delete(clueActivityRelation);
            if(count==0){
                throw new CrmException(CrmExceptionEnum.CLUE_ACTIVITY_UNBIND);

            }
        }

    }

    @Override
    public List<Activity> queryActivityExculdeNow(String clueId, String activityName) {

//        查询当前clue关联的市场活动
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(clueId);
        List<ClueActivityRelation> clueActivityRelations=clueActivityRelationMapper.select(clueActivityRelation);
        List<String> activityids=new ArrayList<>();
        for (ClueActivityRelation activityRelation : clueActivityRelations) {
            activityids.add(activityRelation.getActivityId());
        }
        Example example=new Example(Activity.class);

//        判断是否存在模糊查询，加入条件
        if(activityName != null && activityName != ""){
            example.createCriteria().andLike("name","%" + activityName + "%")
                    .andNotIn("id",activityids);
        }else {
            example.createCriteria().andNotIn("id",activityids);
        }

        List<Activity> activities=activityMapper.selectByExample(example);
        //查询用户表，将用户姓名放置到activity的owner
        for (Activity activity : activities) {
            User user = userMapper.selectByPrimaryKey(activity.getOwner());
            activity.setOwner(user.getName());
        }

        return activities;
    }

    @Override
    public void saveBind(String clueId,String activityIds) throws CrmException {
        String[] aids = activityIds.split(",");
        for (String aid : aids) {
            ClueActivityRelation clueActivityRelation=new ClueActivityRelation();
            clueActivityRelation.setId(UUIDUtil.getUUID());
            clueActivityRelation.setActivityId(aid);
            clueActivityRelation.setClueId(clueId);
            int count=clueActivityRelationMapper.insertSelective(clueActivityRelation);
            if (count==0){
                throw new CrmException(CrmExceptionEnum.CLUE_ACTIVITY_insert_Clue);
            }
        }

    }

    @Override
    public List<Activity> queryClueActivity(String clueId) {
        ClueActivityRelation clueActivityRelation=new ClueActivityRelation();
        clueActivityRelation.setClueId(clueId);
        List<Activity> activities = new ArrayList<>();
        List<ClueActivityRelation> clueActivityRelations =clueActivityRelationMapper.select(clueActivityRelation);
        for (ClueActivityRelation activityRelation : clueActivityRelations) {
            Activity activity=activityMapper.selectByPrimaryKey(activityRelation.getActivityId());
            User user = userMapper.selectByPrimaryKey(activity.getOwner());
            activity.setOwner(user.getName());
            activities.add(activity);
        }
        return activities;
    }


    @Override
    public List<Clue> listClue() {

        List<Clue> list=clueMapper.selectAll();

        return list;
    }

    @Override
    public void saveConvert(Transaction transaction,String id,String username,String isCreateTranaction) throws CrmException {
//        线索信息
        Clue clue=clueMapper.selectByPrimaryKey(id);
//        1、判断客户是否已经存在，不存在即创建
        Example example=new Example(Customer.class);
        example.createCriteria().andEqualTo("name",clue.getCompany());
        int count=customerMapper.selectCountByExample(example);

        Customer customer=null;
        if (count==0){
//            创建新客户
            customer=new Customer();
            //从线索中取出客户信息
            customer.setId(UUIDUtil.getUUID());
            customer.setAddress(clue.getAddress());
            customer.setContactSummary(clue.getContactSummary());
            //谁点击了转换按钮，谁就是创建人
            customer.setCreateBy(username);
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setName(clue.getCompany());
            customer.setNextContactTime(clue.getNextContactTime());
            customer.setPhone(clue.getPhone());
            customer.setWebsite(clue.getWebsite());

            customer.setOwner(clue.getOwner());
            //保存客户信息
            int result = customerMapper.insertSelective(customer);
            if (result==0){
                throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
            }
        }else {
//            更新客户信息
        }

//        2、将线索中的联系人信息提取出来保存在联系人表中
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setAppellation(clue.getAppellation());
        contacts.setCreateBy(username);
        contacts.setCreateTime(DateTimeUtil.getSysTime());
        contacts.setCustomerId(customer.getId());
        contacts.setEmail(clue.getEmail());
        contacts.setFullname(clue.getFullname());
        contacts.setJob(clue.getJob());
        contacts.setMphone(clue.getMphone());
        contacts.setOwner(clue.getOwner());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setSource(clue.getSource());

        count = contactsMapper.insertSelective(contacts);
        if(count == 0){
            throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
        }

        //3、线索中的备注信息取出来保存在客户备注和联系人备注中
//              根据线索id查询当前线索的所有备注
        example = new Example(ClueRemark.class);
        example.createCriteria().andEqualTo("clueId",clue.getId());
        List<ClueRemark> clueRemarks = clueRemarkMapper.selectByExample(example);
        for (ClueRemark clueRemark : clueRemarks) {
            //客户备注信息
            CustomerRemark customerRemark = new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setNoteContent(clueRemark.getNoteContent());
            customerRemark.setCreateBy(username);
            customerRemark.setCreateTime(clueRemark.getCreateTime());
            customerRemark.setCustomerId(customer.getId());
            count = customerRemarkMapper.insertSelective(customerRemark);
            if(count == 0){
                throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
            }

            //联系人备注信息
            ContactsRemark contactsRemark = new ContactsRemark();
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setNoteContent(clueRemark.getNoteContent());
            contactsRemark.setCreateBy(username);
            contactsRemark.setCreateTime(clueRemark.getCreateTime());
            contactsRemark.setContactsId(contacts.getId());
            count = contactsRemarkMapper.insertSelective(contactsRemark);
            if(count == 0){
                throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
            }
        }
//        4、将"线索和市场活动的关系"转换到"联系人和市场活动的关系中"
//                先将线索对应的市场活动id取出来，1：n
        example = new Example(ClueActivityRelation.class);
        example.createCriteria().andEqualTo("clueId",clue.getId());
        List<ClueActivityRelation> clueActivityRelations = clueActivityRelationMapper.selectByExample(example);
//                将联系人id和市场活动id放入关联表中
        for (ClueActivityRelation clueActivityRelation : clueActivityRelations) {
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setContactsId(contacts.getId());
            contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
            count = contactsActivityRelationMapper.insertSelective(contactsActivityRelation);
            if(count == 0){
                throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
            }
        }
//        5、如果发生交易，创建交易记录
        if ("1".equals(isCreateTranaction)){
//            发生交易
            transaction.setId(UUIDUtil.getUUID());
            transaction.setCustomerId(customer.getId());
            transaction.setContactsId(contacts.getId());
            transaction.setCreateBy(username);
            transaction.setCreateTime(clue.getCreateTime());
            transaction.setOwner(clue.getOwner());
            transaction.setSource(clue.getSource());
            count =transactionMapper.insertSelective(transaction);
            if(count == 0){
                throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
            }
//            创建交易备注和交易历史
//            交易历史  1：n;
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setId(UUIDUtil.getUUID());
            transactionHistory.setCreateBy(username);
            transactionHistory.setCreateTime(transaction.getCreateTime());
            transactionHistory.setExpectedDate(transaction.getExpectedDate());
            transactionHistory.setMoney(transaction.getMoney());
            transactionHistory.setStage(transaction.getStage());
            transactionHistory.setTranId(transaction.getId());
            count = transactionHistoryMapper.insertSelective(transactionHistory);
            if(count == 0){
                throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
            }
//            交易备注
            TransactionRemark transactionRemark = new TransactionRemark();
            transactionRemark.setId(UUIDUtil.getUUID());
            transactionRemark.setCreateBy(transaction.getCreateBy());
            transactionRemark.setCreateTime(transaction.getCreateTime());
            transactionRemark.setTranId(transaction.getId());
            transactionRemark.setEditFlag("0");
            count = transactionRemarkMapper.insertSelective(transactionRemark);
            if(count == 0){
                throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
            }
        }
//        6、删除线索备注信息
        example = new Example(ClueRemark.class);
        example.createCriteria().andEqualTo("clueId",clue.getId());
        count = clueRemarkMapper.deleteByExample(example);
        if(count == 0){
            throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
        }
        //7、删除线索和市场活动的关联关系
        example = new Example(ClueActivityRelation.class);
        example.createCriteria().andEqualTo("clueId",clue.getId());
        count = clueActivityRelationMapper.deleteByExample(example);
        if(count == 0){
            throw new CrmException(CrmExceptionEnum.CLUE_CONVERT);
        }

        //8、删除线索
        clueMapper.deleteByPrimaryKey(clue.getId());

    }

    @Override
    public List<Activity> queryActivityIncludeNow(String clueId, String activityName) {
        //查询出该线索下的关联的市场活动id
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(clueId);
        List<ClueActivityRelation> clueActivityRelations = clueActivityRelationMapper.select(clueActivityRelation);
        //将clueActivityRelations集合中每个ClueActivityRelation中的activityId号取出来放入到集合中
        List<String> activityIds = new ArrayList<>();
        for (ClueActivityRelation activityRelation : clueActivityRelations) {
            activityIds.add(activityRelation.getActivityId());
        }

        //查询集合中的所有市场活动
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        if(activityName != null && activityName != ""){
            //有查询条件
            criteria.andLike("name","%" + activityName + "%")
                    .andIn("id",activityIds);
        }else{
            //查询条件为空，查询所有
            criteria.andIn("id",activityIds);
        }
        List<Activity> activities = activityMapper.selectByExample(example);
        //查询用户表，将用户姓名放置到activity的owner
        for (Activity activity : activities) {
            User user = userMapper.selectByPrimaryKey(activity.getOwner());

            activity.setOwner(user.getName());
        }
        return activities;
    }
}
