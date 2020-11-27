<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">

    <link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>


    <link href="/crm/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <script type="text/javascript">
        $(function(){
            $("#isCreateTransaction").click(function(){
                if(this.checked){
                    $("#create-transaction2").show(200);
                }else{
                    $("#create-transaction2").hide(200);
                }
            });
        });
    </script>

</head>
<body>

<!-- 搜索市场活动的模态窗口 -->
<div class="modal fade" id="searchActivityModal" role="dialog" >
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">搜索市场活动</h4>
            </div>
            <div class="modal-body">
                <div class="btn-group" style="position: relative; top: 18%; left: 8px;">
                    <form class="form-inline" role="form">
                        <div class="form-group has-feedback">
                            <input type="text" class="form-control" id="activityName" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </form>
                </div>
                <table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
                    <thead>
                    <tr style="color: #B3B3B3;">
                        <td></td>
                        <td>名称</td>
                        <td>开始日期</td>
                        <td>结束日期</td>
                        <td>所有者</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody id="clueActivityBody">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="selectActivity()" class="btn btn-primary">确定</button>
            </div>
        </div>

    </div>

</div>

<div id="title" class="page-header" style="position: relative; left: 20px;">
    <h4>转换线索 <small>${clue.fullname}${clue.appellation}-${clue.company}</small></h4>
</div>
<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
    新建客户：${clue.company}
</div>
<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
    新建联系人：${clue.fullname}${clue.appellation}
</div>
<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
    <input type="checkbox" id="isCreateTransaction" />
    为客户创建交易
</div>
<div id="create-transaction2" style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;" >

    <form id="tranForm" method="post">
        <input type="hidden" name="clueId" value="${clue.id}">
        <div class="form-group" style="width: 400px; position: relative; left: 20px;">
            <label for="amountOfMoney">金额</label>
            <input type="text" name="money" class="form-control" id="amountOfMoney">
        </div>
        <div class="form-group" style="width: 400px;position: relative; left: 20px;">
            <label for="tradeName">交易名称</label>
            <input type="text" name="name" class="form-control" id="tradeName" />
        </div>
        <div class="form-group" style="width: 400px;position: relative; left: 20px;">
            <label for="expectedClosingDate">预计成交日期</label>
            <input type="text" name="expectedDate" class="form-control" id="expectedClosingDate">
        </div>
        <div class="form-group" style="width: 400px;position: relative; left: 20px;">
            <label for="stage">阶段</label>
            <select id="stage" name="stage" class="form-control">
                <option></option>
                <option>资质审查</option>
                <option>需求分析</option>
                <option>价值建议</option>
                <option>确定决策者</option>
                <option>提案/报价</option>
                <option>谈判/复审</option>
                <option>成交</option>
                <option>丢失的线索</option>
                <option>因竞争丢失关闭</option>
            </select>
        </div>
        <div class="form-group" style="width: 400px;position: relative; left: 20px;">
            <label>市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" data-toggle="modal" data-target="#searchActivityModal" style="text-decoration: none;"><span class="glyphicon glyphicon-search"></span></a></label>
            <input type="text"  class="form-control" id="activityText" placeholder="点击上面搜索" readonly>
            <input name="activityId" type="hidden" id="activityId">
        </div>
    </form>

</div>

<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
    记录的所有者：<br>
    <b>${clue.owner}</b>
</div>
<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
    <input class="btn btn-primary" type="button" onclick="convert()" value="转换">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input class="btn btn-default" type="button" value="取消">
</div>
</body>
</html>

<script>
    function convert() {
        //勾中是否创建交易
        if($('#isCreateTransaction').prop('checked')){
            //创建交易
            $('#tranForm').prop('action','/crm/workbench/clue/convert?isCreateTransaction=1');
            $('#tranForm').submit();
        }else{
            location.href = "/crm/workbench/clue/convert?isCreateTransaction=0&clueId=${clue.id}";
        }

    }

    $('#activityName').keypress(function (event) {
        //按下回车键发送异步请求
        if(event.keyCode == 13){
            $.ajax({
                url : '/crm/workbench/clue/queryActivityIncludeNow',
                data : {
                    'clueId' :'${clue.id}',
                    'activityName' : $(this).val()
                },
                type : 'get',
                dataType : 'json',
                success : function(data){
                    //情况内容
                    $('#clueActivityBody').html("");
                    for (var i = 0; i < data.length; i++) {
                        $('#clueActivityBody').
                        append("<tr>\n" +
                            "\t\t\t\t\t\t\t\t<td><input type=\"radio\" name='aid' class='son1' value="+data[i].id+" /></td>\n" +
                            "\t\t\t\t\t\t\t\t<td>"+data[i].name+"</td>\n" +
                            "\t\t\t\t\t\t\t\t<td>"+data[i].startDate+"</td>\n" +
                            "\t\t\t\t\t\t\t\t<td>"+data[i].endDate+"</td>\n" +
                            "\t\t\t\t\t\t\t\t<td>"+data[i].owner+"</td>\n" +
                            "\t\t\t\t\t\t\t</tr>");
                    }
                }
            });

        }
        //防止按下回车键模态窗关闭
        return false;
    });

    //点击确定按钮，选择市场活动
    function selectActivity() {
        //获取勾中的市场活动
        var activityId = $('.son1:checked').val();
        var activityName = $('.son1:checked').parent().next().text();

        //将选中的市场活动的主键和名字放入到市场活动源下面的input框中
        $('#activityText').val(activityName);
        $('#activityId').val(activityId);
        //关闭模态窗口
        $('#searchActivityModal').modal('hide');
    }

    //预计成交日期插件
    $("#expectedClosingDate").datetimepicker({
        language:  "zh-CN",
        format: "yyyy-mm-dd",//显示格式
        minView: "month",//设置只显示到月份
        initialDate: new Date(),//初始化当前日期
        autoclose: true,//选中自动关闭
        todayBtn: true, //显示今日按钮
        clearBtn : true,
        pickerPosition: "bottom-left"
    });
</script>