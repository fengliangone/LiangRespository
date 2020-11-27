<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">

<link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="/crm/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="/crm/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

	<%--分页js和css--%>
	<link href="/crm/jquery/bs_pagination/jquery.bs_pagination.min.css " type="text/css" rel="stylesheet" >
	<script type="text/javascript" src="/crm/jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="/crm/jquery/bs_pagination/en.js"></script>

<script type="text/javascript">

	$(function(){
		
		
		
	});
	
</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" id="createActivity-form" method="post" role="form">
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" name="owner" id="create-marketActivityOwner">

								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" name="name" class="form-control" id="create-marketActivityName">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-startTime">
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-endTime">
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default">关闭</button>
					<button type="button" id="saveActivityBtn" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
                        <input type="hidden" id="activityId" />
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner" >
								  <option></option>
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" >
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startTime" >
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control"  id="edit-endTime" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default">关闭</button>
					<button type="button" id="updateActivityBtn" class="btn btn-primary">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">名称</div>
							<input class="form-control" id="name" type="text">
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">所有者</div>
							<input class="form-control" id="owner" type="text">
						</div>
					</div>


					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">开始日期</div>
							<input class="form-control" type="text" id="startTime" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">结束日期</div>
							<input class="form-control" type="text" id="endTime">
						</div>
					</div>

					<button type="button" onclick="queryActivity()" class="btn btn-default">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" onclick="queryUsers()" class="btn btn-primary" data-toggle="modal" data-target="#createActivityModal"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" id="updateActivity" class="btn btn-default" data-toggle="modal" ><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button"  id="deleteActivity" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="father"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">

					</tbody>
				</table>
			</div>
			<%--分页插件--%>
			<div style="height: 50px; position: relative;top: 15px;">
				<div id="activityPage"  >
				</div>
			</div>

		</div>
		
	</div>
	<script>


        function refresh(data){
            alert(data.mess);
            pageList(1,2);
        }

        // 删除市场活动
        $('#deleteActivity').click(function () {
            if($('.son:checked').length==0 ||$('.son:checked').length >1 ){
                alert("只能选择一条记录");
                return;
            }{
                $.ajax({
                    url: '/crm/workbench/activity/deleteActivity',
                    data:{"id":$('.son:checked').val()},
                    type: 'get',
                    dataType: 'json',
                    success: function (data) {
                        refresh(data);
                    }
                });
            }

        });


		// 点击修改按钮事件
		$('#updateActivity').click(function () {
            if($('.son:checked').length==0 ||$('.son:checked').length >1 ){
                alert("只能选择一条记录");
                return;
            }else {
                $('#editActivityModal').modal('show');
                // 异步查询所有所有者信息
                $.ajax({
                    url: '/crm/workbench/activity/queryUsers',
                    //data:'',
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {

                        for (var i = 0; i < data.length; i++) {
                            $('#edit-marketActivityOwner')
                                .append("<option value=" + data[i].id + ">" + data[i].name + "</option>");

                        }

                    }
                });

                $('#edit-marketActivityOwner').html("");
                // 打开修改市场活动模块框,获取选择的市场活动
                $.ajax({
                    url: '/crm/workbench/activity/queryActivityById',
                    data: {'id': $('.son:checked').val()},
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        //隐藏域
                        $('#activityId').val(data.id);
                        var owner = data[0].owner;
                        $('#edit-marketActivityOwner option').each(function () {
                            if ($(this).val() == owner) {
                                //选中
                                console.log("11");
                                $(this).prop('selected', true);
                            }
                        })
                        $('#edit-marketActivityName').val(data[0].name);
                        $('#edit-startTime').val(data[0].startDate);
                        $('#edit-endTime').val(data[0].endDate);
                        $('#edit-cost').val(data[0].cost);
                        $('#edit-describe').val(data[0].description);
                    }
                });
            }
        });
		// 提交修改表单
		$('#updateActivityBtn').click(function () {
			//异步提交创建市场活动表单
			$.ajax({
				url : '/crm/workbench/activity/updateActivity',
				data : {
					'id' : $('#activityId').val(),
					'owner' : $('#edit-marketActivityOwner').val(),
					'name'  : $('#edit-marketActivityName').val(),
					'startDate' : $('#edit-startTime').val(),
					'endDate'  : $('#edit-endTime').val(),
					'cost' :$('#edit-cost').val(),
					'description' : $('#edit-describe').val()
				},
				type : 'post',
				dataType : 'json',
				success: function(data){
						alert(data.mess);
					/*
                    modal函数:弹窗函数 show:显示 hide:隐藏
                     */
					//隐藏模态窗口
					$('#editActivityModal').modal('hide');
                    //调用pageList刷新页面
					pageList(1,2);
				}
			});
		});

		//提交新建市场活动表单
		$('#saveActivityBtn').click(function () {

			//异步提交创建市场活动表单
			$.ajax({
				url : '/crm/workbench/activity/saveActivity',
				data : {
					'owner' : $('#create-marketActivityOwner').val(),
					'name'  : $('#create-marketActivityName').val(),
					'startDate' : $('#create-startTime').val(),
					'endDate'  : $('#create-endTime').val(),
					'cost' :$('#create-cost').val(),
					'description' : $('#create-describe').val()
				},
				type : 'post',
				dataType : 'json',
				success: function(data){
					alert(data.mess);
					/*
                    modal函数:弹窗函数 show:显示 hide:隐藏
                     */
					//隐藏模态窗口

					$('#createActivityModal').modal('hide');

					//调用pageList刷新页面
					pageList(1,2);

				}
			});
		});
		// 打开新建市场活动模块框,获取所有者
		function queryUsers(){
			// 重置模块窗
			$('#createActivity-form')[0].reset();

			$.ajax({
				url: '/crm/workbench/activity/queryUsers',
				//data:'',
				type:'post',
				dataType:'json',
				success:function(data){
					for (var i=0;i<data.length;i++){
						$('#create-marketActivityOwner')
								.append("<option value="+data[i].id+">"+data[i].name+"</option>");
					}
				}
			});
		};

		//查询事件
		function queryActivity(){
			pageList(1,2);
		}
		// 第一次查询第一页数据
		pageList(1);
		// 刷新页面
		function pageList(page,pageSize) {

			// 跳转到后台时，发送异步请求获取所有市场活动记录,拼接select列表
			$.ajax({
				url: '/crm/workbench/activity/listActivity',
				data:{'page':page,
					'pageSize':pageSize,
					'name':$('#name').val(),
					'owner':$('#owner').val(),
					'startTime':$('#startTime').val(),
					'endTime':$('#endTime').val()},
				type:'get',
				dataType:'json',
				success:function(data){
					//后台传递的总数据，dataList每页总记录
					var dataList=data.dataList;
					// 清空上一次数据
					$('#activityBody').html("");
					for (var i=0;i<dataList.length;i++){
						$('#activityBody').append("<tr class=\"active\">\n" +
								"<td><input type=\"checkbox\" class=\"son\" value="+dataList[i].id+"></td>\n" +
								"<td><a style=\"text-decoration: none; cursor: pointer;\" " +
                                "href=/crm/workbench/activity/queryActivityDetailById?id="+dataList[i].id+">"+dataList[i].name+"</a></td>\n" +
								"<td>"+dataList[i].uname+"</td>\n" +
								"<td>"+dataList[i].startDate+"</td>\n" +
								"<td>"+dataList[i].endDate+"</td>\n" +
								"</tr>")
					}
					// 利用分页插件查询第一页数据
					$("#activityPage").bs_pagination({
						currentPage: data.page, // 页码
						rowsPerPage: data.pageSize, // 每页显示的记录条数
						maxRowsPerPage: 20, // 每页最多显示的记录条数
						totalPages: data.pages, // 总页数
						totalRows: data.count, // 总记录条数
						visiblePageLinks: 3, // 显示几个卡片
						showGoToPage: true,
						showRowsPerPage: true,
						showRowsInfo: true,
						showRowsDefaultInfo: true,
						//回调函数，点击页码触发事件查询数据
						onChangePage : function(event, obj){

							//pageList(obj.rowsPerPage,obj.currentPage);
							// 传入点击页码和选择的每页记录数
							pageList(obj.currentPage,obj.rowsPerPage);
						}
					});
				}
			});
		}
		//条件查询日历插件
		$("#startTime").datetimepicker({
			language:  "zh-CN",
			format: "yyyy-mm-dd",//显示格式
			minView: "hour",//设置只显示到月份
			initialDate: new Date(),//初始化当前日期
			autoclose: true,//选中自动关闭
			todayBtn: true, //显示今日按钮
			clearBtn : true,
			pickerPosition: "bottom-left"
		});

		$("#endTime").datetimepicker({
			language:  "zh-CN",
			format: "yyyy-mm-dd",//显示格式
			minView: "hour",//设置只显示到月份
			initialDate: new Date(),//初始化当前日期
			autoclose: true,//选中自动关闭
			todayBtn: true, //显示今日按钮
			clearBtn : true,
			pickerPosition: "bottom-left"
		});
		//创建市场活动日期,日历插件
		$("#create-startTime").datetimepicker({
			language:  "zh-CN",
			format: "yyyy-mm-dd",//显示格式
			minView: "hour",//设置只显示到月份
			initialDate: new Date(),//初始化当前日期
			autoclose: true,//选中自动关闭
			todayBtn: true, //显示今日按钮
			clearBtn : true,
			pickerPosition: "bottom-left"
		});
		$("#create-endTime").datetimepicker({
			language:  "zh-CN",
			format: "yyyy-mm-dd",//显示格式
			minView: "hour",//设置只显示到月份
			initialDate: new Date(),//初始化当前日期
			autoclose: true,//选中自动关闭
			todayBtn: true, //显示今日按钮
			clearBtn : true,
			pickerPosition: "bottom-left"
		});
		//全选
		$('#father').click(function() {
			$('.son').each(function() {
				$(this).prop('checked', $('#father').prop('checked'))
			});
		});


	</script>
</body>
</html>