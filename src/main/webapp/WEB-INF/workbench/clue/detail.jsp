<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">

<link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
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

        //参数1:事件对象,参数2:被代理的元素的选择器,参数3:事件触发的函数
		$('#father').on('mouseover','.remarkDiv',function () {
            $(this).children("div").children("div").show();
        });

        $('#father').on('mouseout','.remarkDiv',function () {
            $(this).children("div").children("div").hide();
        });

        $('#father').on('mouseover','.myHref',function () {
            $(this).children("span").css("color","red");
        });

        $('#father').on('mouseout','.myHref',function () {
            $(this).children("span").css("color","#E6E6E6");
        });


	});
	
</script>

</head>
<body>

	<!-- 关联市场活动的模态窗口 -->
	<div class="modal fade" id="bundModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">关联市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input type="text" class="form-control" style="width: 300px;" id="activityName" placeholder="请输入市场活动名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td><input type="checkbox"/></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="clueActivityBody">
							<%--<tr>
								<td><input type="checkbox"/></td>
								<td>发传单</td>
								<td>2020-10-10</td>
								<td>2020-10-20</td>
								<td>zhangsan</td>
							</tr>--%>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button"  onclick="saveBindFun()" class="btn btn-primary" data-dismiss="modal">关联</button>
				</div>
			</div>
		</div>
	</div>

    <!-- 修改线索的模态窗口 -->
    <div class="modal fade" id="editClueModal" role="dialog">
        <div class="modal-dialog" role="document" style="width: 90%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改线索</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">

                        <div class="form-group">
                            <label for="edit-clueOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <select class="form-control" id="edit-clueOwner">
                                    <option>zhangsan</option>
                                    <option>lisi</option>
                                    <option>wangwu</option>
                                </select>
                            </div>
                            <label for="edit-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-company" value="动力节点">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="edit-call" class="col-sm-2 control-label">称呼</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <select class="form-control" id="edit-call">
                                    <option></option>
                                    <option selected>先生</option>
                                    <option>夫人</option>
                                    <option>女士</option>
                                    <option>博士</option>
                                    <option>教授</option>
                                </select>
                            </div>
                            <label for="edit-surname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-surname" value="李四">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="edit-job" class="col-sm-2 control-label">职位</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-job" value="CTO">
                            </div>
                            <label for="edit-email" class="col-sm-2 control-label">邮箱</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-email" value="lisi@bjpowernode.com">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="edit-phone" class="col-sm-2 control-label">公司座机</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-phone" value="010-84846003">
                            </div>
                            <label for="edit-website" class="col-sm-2 control-label">公司网站</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-website" value="http://www.bjpowernode.com">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="edit-mphone" class="col-sm-2 control-label">手机</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-mphone" value="12345678901">
                            </div>
                            <label for="edit-status" class="col-sm-2 control-label">线索状态</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <select class="form-control" id="edit-status">
                                    <option></option>
                                    <option>试图联系</option>
                                    <option>将来联系</option>
                                    <option selected>已联系</option>
                                    <option>虚假线索</option>
                                    <option>丢失线索</option>
                                    <option>未联系</option>
                                    <option>需要条件</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="edit-source" class="col-sm-2 control-label">线索来源</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <select class="form-control" id="edit-source">
                                    <option></option>
                                    <option selected>广告</option>
                                    <option>推销电话</option>
                                    <option>员工介绍</option>
                                    <option>外部介绍</option>
                                    <option>在线商场</option>
                                    <option>合作伙伴</option>
                                    <option>公开媒介</option>
                                    <option>销售邮件</option>
                                    <option>合作伙伴研讨会</option>
                                    <option>内部研讨会</option>
                                    <option>交易会</option>
                                    <option>web下载</option>
                                    <option>web调研</option>
                                    <option>聊天</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="edit-describe" class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-10" style="width: 81%;">
                                <textarea class="form-control" rows="3" id="edit-describe">这是一条线索的描述信息</textarea>
                            </div>
                        </div>

                        <div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

                        <div style="position: relative;top: 15px;">
                            <div class="form-group">
                                <label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="3" id="edit-contactSummary">这个线索即将被转换</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="edit-nextContactTime" value="2017-05-01">
                                </div>
                            </div>
                        </div>

                        <div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

                        <div style="position: relative;top: 20px;">
                            <div class="form-group">
                                <label for="edit-address" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="edit-address">北京大兴区大族企业湾</textarea>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
                </div>
            </div>
        </div>
    </div>

	<!-- 返回按钮 -->
	<div style="position: relative; top: 35px; left: 10px;">
		<a href="javascript:void(0);" onclick="window.history.back();"><span class="glyphicon glyphicon-arrow-left" style="font-size: 20px; color: #DDDDDD"></span></a>
	</div>
	
	<!-- 大标题 -->
	<div style="position: relative; left: 40px; top: -30px;">
		<div class="page-header">
			<h3>${clue.fullname} <small>${clue.company}</small></h3>
		</div>
		<div style="position: relative; height: 50px; width: 500px;  top: -72px; left: 700px;">
			<a type="button" class="btn btn-default"
			   href="/crm/workbench/clue/toConvertView?id=${clue.id}">
				<span class="glyphicon glyphicon-retweet"></span> 转换</a>
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#editClueModal"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
			<button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	
	<!-- 详细信息 -->
	<div style="position: relative; top: -70px;">
		<div style="position: relative; left: 40px; height: 30px;">
			<div style="width: 300px; color: gray;">名称</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.fullname}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">所有者</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.owner}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 10px;">
			<div style="width: 300px; color: gray;">公司</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.company}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">职位</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.job}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 20px;">
			<div style="width: 300px; color: gray;">邮箱</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.email}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">公司座机</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.phone}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 30px;">
			<div style="width: 300px; color: gray;">公司网站</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.website}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">手机</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.mphone}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 40px;">
			<div style="width: 300px; color: gray;">线索状态</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.state}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">线索来源</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.source}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 50px;">
			<div style="width: 300px; color: gray;">创建者</div>
			<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${clue.createBy}&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">${clue.createTime}</small></div>
			<div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 60px;">
			<div style="width: 300px; color: gray;">修改者</div>
			<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${clue.editBy}&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">${clue.editTime}</small></div>
			<div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 70px;">
			<div style="width: 300px; color: gray;">描述</div>
			<div style="width: 630px;position: relative; left: 200px; top: -20px;">
				<b>
					${clue.description}
				</b>
			</div>
			<div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 80px;">
			<div style="width: 300px; color: gray;">联系纪要</div>
			<div style="width: 630px;position: relative; left: 200px; top: -20px;">
				<b>
					${clue.contactSummary}
				</b>
			</div>
			<div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 90px;">
			<div style="width: 300px; color: gray;">下次联系时间</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.nextContactTime}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px; "></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 100px;">
			<div style="width: 300px; color: gray;">详细地址</div>
			<div style="width: 630px;position: relative; left: 200px; top: -20px;">
				<b>
					${clue.address}
				</b>
			</div>
			<div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
	</div>
	
	<!-- 备注 -->
	<div id="father" style="position: relative; top: 40px; left: 40px;">
		<div class="page-header">
			<h4>备注</h4>
		</div>
		
		<!-- 备注 -->
		<c:forEach items="${clue.clueRemarks}" var="clueRemark">
			<div class="remarkDiv" style="height: 60px;">
				<img title="zhangsan" src="/crm/image/user-thumbnail.png" style="width: 30px; height:30px;">
				<div style="position: relative; top: -40px; left: 40px;" >
					<h5 id="clueNoteContentH5${clueRemark.id}">${clueRemark.noteContent}</h5>
					<font color="gray">线索</font> <font color="gray">-</font> <b>${clue.fullname}-${clue.company}</b> <small style="color: gray;"> ${clueRemark.createTime} ${clueRemark.createBy}</small>
					<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
						<a class="myHref" onclick="updateClue($('#clueNoteContentH5${clueRemark.id}').html(),'${clueRemark.id}')" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>
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
					<button type="button" id="saveClueRemarkBtn" class="btn btn-primary">添加</button>
				</p>
			</form>
		</div>
	</div>
	
	<!-- 市场活动 -->
	<div>
		<div style="position: relative; top: 60px; left: 40px;">
			<div class="page-header">
				<h4>市场活动</h4>
			</div>
			<div style="position: relative;top: 0px;">
				<table class="table table-hover" style="width: 900px;">
					<thead>
						<tr style="color: #B3B3B3;">
                            <td><input type="checkbox" /> </td>
							<td>名称</td>
							<td>开始日期</td>
							<td>结束日期</td>
							<td>所有者</td>
                            <td>
                                <button class="btn btn-danger" onclick="deleteManyBind()">解除多个绑定</button>
                            </td>
						</tr>
					</thead>
					<tbody id="clueActivities">
					<c:forEach items="${clue.activities}" var="activity">
						<tr>
                            <td><input type="checkbox" class="son" id="tr${activity.id}" value="${activity.id}" /> </td>
							<td>${activity.name}</td>
							<td>${activity.startDate}</td>
							<td>${activity.endDate}</td>
							<td>${activity.owner}</td>
							<td><a onclick="unbind('${clue.id}','${activity.id}',$(this))" href="javascript:void(0);"   style="text-decoration: none;"><span class="glyphicon glyphicon-remove"></span>解除关联</a></td>
						</tr>

					</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div>
				<a href="javascript:void(0);" data-toggle="modal" data-target="#bundModal" style="text-decoration: none;"><span class="glyphicon glyphicon-plus"></span>关联市场活动</a>
			</div>
		</div>
	</div>
	
	
	<div style="height: 200px;"></div>

    <!-- 修改线索备注的模态窗口 -->
    <div class="modal fade" id="updateClueModal" role="dialog">
        <%-- 备注的id --%>
        <input type="hidden" id="clueRemarkId">
        <div class="modal-dialog" role="document" style="width: 40%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel1">修改备注</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="edit-describe" class="col-sm-2 control-label">内容</label>
                            <div class="col-sm-10" style="width: 81%;">
                                <textarea class="form-control" rows="3" id="clueNoteContent"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="updateClueRemarkBtn">更新</button>
                </div>
            </div>
        </div>
    </div>
<script>
    // 修改备注按钮，弹出修改线索备注的模块
    function updateClue(noteContent,id) {
        //将页面遍历出来的备注内容设置到模态窗口中的备注文本域中
        $('#clueNoteContent').val($("#clueNoteContentH5"+id).html());
        //将备注的主键放在模态窗口的隐藏域中
        $('#clueRemarkId').val(id);
        $('#updateClueModal').modal('show');
    }
    //点击更新备注按钮，异步提交信息
    $('#updateClueRemarkBtn').click(function () {
        $.ajax({
            url : '/crm/workbench/clue/updateClueRemark',
            data : {
                'id' : $('#clueRemarkId').val(),
                'noteContent' : $('#clueNoteContent').val()
            },
            type : 'get',
            dataType : 'json',
            success : function(data){
                alert(data.mess);
                $('#updateClueModal').modal('hide');
                //将用户在模态窗口中输入的修改内容替换原先遍历出来对应的备注记录
                //alert($('#clueNoteContent').val());
                $("#clueNoteContentH5"+$('#clueRemarkId').val()).html($('#clueNoteContent').val());
                //$("#clueNoteContentH5"+id01).html($('#clueNoteContent').val());
            }
        });
    });

    // 点击线索的备注添加按钮
    $('#saveClueRemarkBtn').click(function () {
        $.ajax({
            url : '/crm/workbench/clue/saveClueRemark',
            data : {
                'clueId' : '${clue.id}',
                'noteContent' : $('#remark').val()
            },
            type : 'post',
            dataType : 'json',
            success : function(data){
                alert(data.mess);
                //将文本域内容情况

                //在页面使用js把添加的备注内容动态拼接到备注的div中
                $('#remarkDiv').before("<div class=\"remarkDiv\" style=\"height: 60px;\">\n" +
                    "<img title=\"zhangsan\" src=\"/crm//image/user-thumbnail.png\" style=\"width: 30px; height:30px;\">\n" +
                    "<div style=\"position: relative; top: -40px; left: 40px;\" >\n" +
                    "<h5 id=\"clueNoteContentH5${clueRemark.id}\">"+$('#remark').val()+"</h5>\n" +
                    "<font color=\"gray\">线索</font> <font color=\"gray\">-</font> <b>${clue.fullname}-${clue.company}</b> <small style=\"color: gray;\"> ${clue.createTime} ${clue.createBy}</small>\n" +
                    "<div style=\"position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;\">\n" +
                    "<a class='myHref' onclick=\"updateClue($('#clueNoteContentH5${clueRemark.id}').html(),'${clueRemark.id}')\" href=\"javascript:void(0);\"><span class='glyphicon glyphicon-edit' style=\"font-size: 20px; color: #E6E6E6;\"></span></a>\n" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                    "<a class='myHref' href=\"javascript:void(0);\"><span class='glyphicon glyphicon-remove' style=\"font-size: 20px; color: #E6E6E6;\"></span></a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>");
                /*
                * 因为js只会被加载一次，页面元素都可以解析到js，但是添加备注是在加载js之后才生成的，找不到
                * 只被解析一次的原先的js
                * 1、在动态添加备注dom元素的时候再次绑定原先js事件即可
                * 2、前提:事件的冒泡
                *
                * 把事件委托给其父元素[不是动态生成的父元素]，如果其直接父元素也是动态生成，还要继续往上级
                * 找，直到找到不是动态生成的父元素即可，此时把动态生成的dom元素的事件委托给找到的父元素进行
                * 管理
                *
                * */

                $('#remark').val("");

            }
        });
    });

    //接触绑定（市场活动和线索之间）
    function unbind(clueId,activityId,$this ){
        $.ajax({
            url : '/crm/workbench/clue/deleteBind',
            data : {
                'clueId' :clueId,
                'activityId':activityId
            },
            type : 'get',
            dataType : 'json',
            success : function(data){
                if(data.sucess){
                    alert(data.mess);
                    //删除解除绑定的dom元素(页面的tr)
                    $this.parent().parent().remove();
                }
            }
        });

    };
    //解除多个关联
    function deleteManyBind() {
        //获取勾中的记录
        var activityIds = [];
        $('.son').each(function () {
            if($(this).prop('checked')){
                activityIds.push($(this).val());
            }
        });
        $.ajax({
            url : '/crm/workbench/clue/deleteManyBind',
            data : {
                'clueId' :'${clue.id}',
                'activityIds':activityIds.join()
            },
            type : 'get',
            dataType : 'json',
            success : function(data){
                if(data.sucess){
                    alert(data.mess);
                    for(var i = 0 ; i < activityIds.length;i++){

                        //删除解除绑定的dom元素(页面的tr)
                        $('#tr'+activityIds[i]).parent().parent().remove();
                    }
                }
            }
        });
    }
    //弹出关联市场活动模态窗口，在输入框中输入内容后，触发键盘事件
    //注意:对于模态窗口，如果按下回车键，模态窗口会默认刷新当前页面
    $('#activityName').keypress(function (event) {
        //按下回车键发送异步请求
        if(event.keyCode == 13){
            $.ajax({
                url : '/crm/workbench/clue/queryActivityExculdeNow',
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
                            "\t\t\t\t\t\t\t\t<td><input type=\"checkbox\" class='son1' value="+data[i].id+" /></td>\n" +
                            "\t\t\t\t\t\t\t\t<td>"+data[i].name+"</td>\n" +
                            "\t\t\t\t\t\t\t\t<td>"+data[i].startDate+"</td>\n" +
                            "\t\t\t\t\t\t\t\t<td>"+data[i].endDate+"</td>\n" +
                            "\t\t\t\t\t\t\t\t<td>"+data[i].owner+"</td>\n" +
                            "\t\t\t\t\t\t\t</tr>");
                    }
                }
            });

        }
        return false;
    });
//  关联市场活动和线索
    function saveBindFun() {
        //市场活动ids
        var activityIds = [];
        $('.son1').each(function () {
            if($(this).prop('checked')){
                activityIds.push($(this).val());
            }
        });
        $.ajax({
            url : '/crm/workbench/clue/saveBind',
            data : {
                'clueId' :'${clue.id}',
                'activityIds':activityIds.join()
            },
            type : 'get',
            dataType : 'json',
            success : function(data){
                if(data.sucess){
                    alert(data.mess);
                    //隐藏模态窗口
                    $('#bundModal').modal('hide');
                    //异步查询当前线索关联的所有市场活动
                    $.ajax({
                        url : '/crm/workbench/clue/queryClueActivity',
                        data : {
                            'clueId' :'${clue.id}'
                        },
                        type : 'get',
                        dataType : 'json',
                        success : function(data){
                            //动态拼接当前线索的市场活动
                            $('#clueActivities').html("");
                            for(var i = 0 ; i < data.length; i++){
                                $('#clueActivities').
                                append("<tr>\n" +
                                    "\t\t\t\t\t\t\t\t\t<td><input type=\"checkbox\" class=\"son\" id=\"tr"+data[i].id+"\" value=\""+data[i].id+"\" /> </td>\n" +
                                    "\t\t\t\t\t\t\t\t\t<td>"+data[i].name+"</td>\n" +
                                    "\t\t\t\t\t\t\t\t\t<td>"+data[i].startDate+"</td>\n" +
                                    "\t\t\t\t\t\t\t\t\t<td>"+data[i].endDate+"</td>\n" +
                                    "\t\t\t\t\t\t\t\t\t<td>"+data[i].owner+"</td>\n" +
                                    "\t\t\t\t\t\t\t\t\t<td><a onclick=\"unbind('${clue.id}',"+data[i].id+","+$(this)+")\" href=\"javascript:void(0)\"  style=\"text-decoration: none;\"><span class=\"glyphicon glyphicon-remove\"></span>解除关联</a></td>\n" +
                                    "\t\t\t\t\t\t\t</tr>");
                            }
                        }
                    });
                }
            }
        });
    }
</script>
</body>
</html>