<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">

<link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<style type="text/css">
.mystage{
	font-size: 20px;
	vertical-align: middle;
	cursor: pointer;
}
.closingDate{
	font-size : 15px;
	cursor: pointer;
	vertical-align: middle;
}
</style>
	
<script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">

	//默认情况下取消和保存按钮是隐藏的
	var cancelAndSaveBtnDefault = true;
	
	$(function(){
		$("#remark").focus(function(){
			if(cancelAndSaveBtnDefault){
				//设置remarkDiv的高度为130px
				$("#remarkDiv").css("height","130px");
				//显示
				$("#cancelAndSaveBtn").show("2000");
				cancelAndSaveBtnDefault = false;
			}
		});
		
		$("#cancelBtn").click(function(){
			//显示
			$("#cancelAndSaveBtn").hide();
			//设置remarkDiv的高度为130px
			$("#remarkDiv").css("height","90px");
			cancelAndSaveBtnDefault = true;
		});

        $('#father').on("mouseover",".remarkDiv",function () {
            $(this).children("div").children("div").show();
        });
        $('#father').on("mouseout",".remarkDiv",function () {
            $(this).children("div").children("div").hide();
        });
        $('#father').on("mouseover",".myHref",function () {
            $(this).children("span").css("color","red");
        });
        $('#father').on("mouseout",".myHref",function () {
            $(this).children("span").css("color","#E6E6E6");
        });


        $(".mystage").popover({
            trigger:'manual',
            placement : 'bottom',
            html: 'true',
            animation: false
        }).on("mouseenter", function () {
            var _this = this;
            $(this).popover("show");
            $(this).siblings(".popover").on("mouseleave", function () {
                $(_this).popover('hide');
            });
        }).on("mouseleave", function () {
            var _this = this;
            setTimeout(function () {
                if (!$(".popover:hover").length) {
                    $(_this).popover("hide")
                }
            }, 100);
        });

	});
	
	
	
</script>

</head>
<body>
	
	<!-- 返回按钮 -->
	<div style="position: relative; top: 35px; left: 10px;">
		<a href="javascript:void(0);" onclick="window.history.back();"><span class="glyphicon glyphicon-arrow-left" style="font-size: 20px; color: #DDDDDD"></span></a>
	</div>
	
	<!-- 大标题 -->
	<div style="position: relative; left: 40px; top: -30px;">
		<div class="page-header">
            <h3>${transaction.name} <small>￥${transaction.money}</small></h3>
		</div>
		<div style="position: relative; height: 50px; width: 250px;  top: -72px; left: 700px;">
			<button type="button" class="btn btn-default" onclick="window.location.href='edit.jsp';"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
			<button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>

	<!-- 阶段状态 -->
	<div id="tranStageDiv" style="position: relative; left: 40px; top: -50px;">
		<%--阶段&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
		<%--<span class="glyphicon glyphicon-ok-circle mystage" data-toggle="popover" data-placement="bottom" data-content="资质审查" style="color: #90F790;"></span>--%>
		<%---------------%>
		<%--<span class="glyphicon glyphicon-ok-circle mystage" data-toggle="popover" data-placement="bottom" data-content="需求分析" style="color: #90F790;"></span>--%>
		<%---------------%>
		<%--<span class="glyphicon glyphicon-ok-circle mystage" data-toggle="popover" data-placement="bottom" data-content="价值建议" style="color: #90F790;"></span>--%>
		<%---------------%>
		<%--<span class="glyphicon glyphicon-ok-circle mystage" data-toggle="popover" data-placement="bottom" data-content="确定决策者" style="color: #90F790;"></span>--%>
		<%---------------%>
		<%--<span class="glyphicon glyphicon-map-marker mystage" data-toggle="popover" data-placement="bottom" data-content="提案/报价" style="color: #90F790;"></span>--%>
		<%---------------%>
		<%--<span class="glyphicon glyphicon-record mystage" data-toggle="popover" data-placement="bottom" data-content="谈判/复审"></span>--%>
		<%---------------%>
		<%--<span class="glyphicon glyphicon-record mystage" data-toggle="popover" data-placement="bottom" data-content="成交"></span>--%>
		<%---------------%>
		<%--<span class="glyphicon glyphicon-record mystage" data-toggle="popover" data-placement="bottom" data-content="丢失的线索"></span>--%>
		<%---------------%>
		<%--<span class="glyphicon glyphicon-record mystage" data-toggle="popover" data-placement="bottom" data-content="因竞争丢失关闭"></span>--%>
		<%---------------%>
		<%--<span class="closingDate">2010-10-10</span>--%>
	</div>
	
	<!-- 详细信息 -->
	<div style="position: relative; top: 0px;">
        <div style="position: relative; left: 40px; height: 30px;">
            <div style="width: 300px; color: gray;">所有者</div>
            <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${transaction.owner}</b></div>
            <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">金额</div>
            <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${transaction.money}</b></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 10px;">
            <div style="width: 300px; color: gray;">名称</div>
            <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${transaction.name}</b></div>
            <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">预计成交日期</div>
            <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${transaction.expectedDate}</b></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 20px;">
            <div style="width: 300px; color: gray;">客户名称</div>
            <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${transaction.customerId}</b></div>
            <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">阶段</div>
            <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${transaction.stage}</b></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 30px;">
            <div style="width: 300px; color: gray;">类型</div>
            <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${transaction.type}</b></div>
            <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">可能性</div>
            <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${transaction.possibility.get(0)}</b></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 40px;">
            <div style="width: 300px; color: gray;">来源</div>
            <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${transaction.source}</b></div>
            <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">市场活动源</div>
            <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${transaction.activityId}</b></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 50px;">
            <div style="width: 300px; color: gray;">联系人名称</div>
            <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${transaction.contactsId}</b></div>
            <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 60px;">
            <div style="width: 300px; color: gray;">创建者</div>
            <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${transaction.createBy}&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">${transaction.createTime}</small></div>
            <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 70px;">
            <div style="width: 300px; color: gray;">修改者</div>
            <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>修改者&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">2017-01-19 10:10:10</small></div>
            <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 80px;">
            <div style="width: 300px; color: gray;">描述</div>
            <div style="width: 630px;position: relative; left: 200px; top: -20px;">
                <b>
                    ${transaction.description}
                </b>
            </div>
            <div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 90px;">
            <div style="width: 300px; color: gray;">联系纪要</div>
            <div style="width: 630px;position: relative; left: 200px; top: -20px;">
                <b>
                    ${transaction.contactSummary}
                </b>
            </div>
            <div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
        </div>
        <div style="position: relative; left: 40px; height: 30px; top: 100px;">
            <div style="width: 300px; color: gray;">下次联系时间</div>
            <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${transaction.nextContactTime}</b></div>
            <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px;"></div>
        </div>
    </div>
	
	<!-- 备注 -->
	<div style="position: relative; top: 100px; left: 40px;">
		<div class="page-header">
			<h4>备注</h4>
		</div>
		
		<!-- 备注 -->
        <c:forEach items="${transaction.transactionRemarks}" var="transactionRemark">
            <div class="remarkDiv" style="height: 60px;">
                <img title="zhangsan" src="/crm/image/user-thumbnail.png" style="width: 30px; height:30px;">
                <div style="position: relative; top: -40px; left: 40px;" >
                    <h5>${transactionRemark.noteContent}</h5>
                    <font color="gray">交易</font> <font color="gray">-</font> <b>${transaction.name}</b> <small style="color: gray;"> ${transaction.expectedDate} ${transaction.createBy}</small>
                    <div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
                        <a class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #E6E6E6;"></span></a>
                    </div>
                </div>
            </div>
        </c:forEach>
		
		<div id="remarkDiv" style="background-color: #E6E6E6; width: 870px; height: 90px;">
			<form role="form" style="position: relative;top: 10px; left: 10px;">
				<textarea id="remark" class="form-control" style="width: 850px; resize : none;" rows="2"  placeholder="添加备注..."></textarea>
				<p id="cancelAndSaveBtn" style="position: relative;left: 737px; top: 10px; display: none;">
					<button id="cancelBtn" type="button" class="btn btn-default">取消</button>
					<button type="button" class="btn btn-primary">保存</button>
				</p>
			</form>
		</div>
	</div>
	
	<!-- 阶段历史 -->
	<div>
		<div style="position: relative; top: 100px; left: 40px;">
			<div class="page-header">
				<h4>阶段历史</h4>
			</div>
			<div style="position: relative;top: 0px;">
				<table id="activityTable" class="table table-hover" style="width: 900px;">
					<thead>
						<tr style="color: #B3B3B3;">
							<td>阶段</td>
							<td>金额</td>
							<td>可能性</td>
							<td>预计成交日期</td>
							<td>创建时间</td>
							<td>创建人</td>
						</tr>
					</thead>
					<tbody id="transactionHistoryBody">
                    <c:forEach items="${transaction.transactionHistories}" var="transactionHistory">
                        <tr>
                            <td>${transactionHistory.stage}</td>
                            <td>${transactionHistory.money}</td>
                            <td>${transaction.possibility.get(0)}</td>
                            <td>${transactionHistory.expectedDate}</td>
                            <td>${transactionHistory.createTime}</td>
                            <td>${transactionHistory.createBy}</td>
                        </tr>
                    </c:forEach>
					</tbody>
				</table>
			</div>
			
		</div>
	</div>
	
	<div style="height: 200px;"></div>
	<script>
        //跳转到交易详情页面，发送异步请求，查询当前的交易状态
        $.ajax({
            url : '/crm/workbench/transaction/stageList',
            data : {
                'tranId' : '${transaction.id}'
            },
            type : 'get',
            dataType : 'json',
            success : function(data){
                var content = "阶段&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                for(var i = 0 ; i < data.length ;i++){
                    var stageMap = data[i];
                    if("绿圈" == stageMap.type){
                        //绿圈
                        content += "<span index="+stageMap.index+"  class=\"glyphicon glyphicon-ok-circle mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+" style=\"color: #90F790;\"></span>"
                        content += "-----------";
                    }else if("锚点" == stageMap.type){
                        //锚点
                        content += "<span index="+stageMap.index+" class=\"glyphicon glyphicon-map-marker mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+" style=\"color: #90F790;\"></span>"
                        content += "-----------";
                    }else if("黑圈" == stageMap.type){
                        //黑圈
                        content += "<span index="+stageMap.index+" class=\"glyphicon glyphicon-record mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+"></span>"
                        content += "-----------";
                    }else if("红叉" == stageMap.type){
                        //红叉
                        content += "<span index="+stageMap.index+" class=\"glyphicon glyphicon-remove mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+" style=\"color: red;\"></span>"
                        content += "-----------";
                    }else if("黑叉" == stageMap.type){
                        //黑叉
                        content += "<span index="+stageMap.index+" class=\"glyphicon glyphicon-remove mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+"></span>"
                        content += "-----------";
                    }
                }
                content += "-----------"+new Date().toLocaleDateString();
                $('#tranStageDiv').html(content);
                //阶段提示框
                $(".mystage").popover({
                    trigger:'manual',
                    placement : 'bottom',
                    html: 'true',
                    animation: false
                }).on("mouseenter", function () {
                    var _this = this;
                    $(this).popover("show");
                    $(this).siblings(".popover").on("mouseleave", function () {
                        $(_this).popover('hide');
                    });
                }).on("mouseleave", function () {
                    var _this = this;
                    setTimeout(function () {
                        if (!$(".popover:hover").length) {
                            $(_this).popover("hide")
                        }
                    }, 100);
                });
            }
        });

        //将每个span的单击事件委托给父元素
        //参数1:事件类型  参数2:委托对象  参数3:回调函数(触发事件执行的函数)
        /*
            点击阶段图标，向后台发送异步请求，给后台发送每个阶段的索引下标，查询出对应的阶段信息
         */
        $('#tranStageDiv').on('click','span',function () {
            //$(this):每个span
            var $this = $(this);
            $.ajax({
                url : '/crm/workbench/transaction/stageList',
                data : {
                    'index' : $(this).attr('index'),
                    'tranId' : '${transaction.id}'
                },
                type : 'get',
                dataType : 'json',
                success : function(data){
                    $('#tranStageDiv').html("");
                    var content = "阶段&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                    var possibility;
                    for(var i = 0 ; i < data.length ;i++){
                        var stageMap = data[i];
                        //修改当前页面的交易阶段内容和交易的可能性
                        if($this.attr('index') == i){
                            var text = stageMap.text;
                            possibility = stageMap.possibility;
                            $('#stage').html(text);
                            $('#possibility').html(possibility);
                        }

                        if("绿圈" == stageMap.type){
                            //绿圈
                            content += "<span index="+stageMap.index+"  class=\"glyphicon glyphicon-ok-circle mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+" style=\"color: #90F790;\"></span>"
                            content += "-----------";
                        }else if("锚点" == stageMap.type){
                            //锚点
                            content += "<span index="+stageMap.index+" class=\"glyphicon glyphicon-map-marker mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+" style=\"color: #90F790;\"></span>"
                            content += "-----------";
                        }else if("黑圈" == stageMap.type){
                            //黑圈
                            content += "<span index="+stageMap.index+" class=\"glyphicon glyphicon-record mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+"></span>"
                            content += "-----------";
                        }else if("红叉" == stageMap.type){
                            //红叉
                            content += "<span index="+stageMap.index+" class=\"glyphicon glyphicon-remove mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+" style=\"color: red;\"></span>"
                            content += "-----------";
                        }else if("黑叉" == stageMap.type){
                            //黑叉
                            content += "<span index="+stageMap.index+" class=\"glyphicon glyphicon-remove mystage\" data-toggle=\"popover\" data-placement=\"bottom\" data-content="+stageMap.text+"></span>"
                            content += "-----------";
                        }
                    }
                    content += "-----------"+new Date().toLocaleDateString();
                    $('#tranStageDiv').html(content);

                    // 交易历史添加
                    var transactionHistory = data[data.length -1]. transactionHistory;
                    $('#transactionHistoryBody').append("<tr>\n" +
                        "\t\t\t\t\t\t\t\t<td>"+transactionHistory.stage+"</td>\n" +
                        "\t\t\t\t\t\t\t\t<td>"+transactionHistory.money+"</td>\n" +
                        "\t\t\t\t\t\t\t\t<td>"+possibility+"</td>\n" +
                        "\t\t\t\t\t\t\t\t<td>"+transactionHistory.expectedDate+"</td>\n" +
                        "\t\t\t\t\t\t\t\t<td>"+transactionHistory.createTime+"</td>\n" +
                        "\t\t\t\t\t\t\t\t<td>"+transactionHistory.createBy+"</td>\n" +
                        "\t\t\t\t\t\t\t</tr>");




                    //阶段提示框
                    $(".mystage").popover({
                        trigger:'manual',
                        placement : 'bottom',
                        html: 'true',
                        animation: false
                    }).on("mouseenter", function () {
                        var _this = this;
                        $(this).popover("show");
                        $(this).siblings(".popover").on("mouseleave", function () {
                            $(_this).popover('hide');
                        });
                    }).on("mouseleave", function () {
                        var _this = this;
                        setTimeout(function () {
                            if (!$(".popover:hover").length) {
                                $(_this).popover("hide")
                            }
                        }, 100);
                    });
                }
            });

        });




    </script>
</body>
</html>