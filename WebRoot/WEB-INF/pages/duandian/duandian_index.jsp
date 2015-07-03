<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>OneApm</title>
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/layout.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/index.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/view.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${applicationScope.staticPath}skin/css/public.css" type="text/css" media="screen" />
	<script src="${applicationScope.staticPath}skin/js/jquery.min.js"></script>
	<script src="${applicationScope.staticPath}skin/js/guolv.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/timeselect/laydate.js"></script>
</head>
<body>
	<s:include value="/WEB-INF/pages/public/header.jsp" />
	<section id="main" class="column"> 
		<div class="public_main width_3_quarter_customer">
		<div class="header">
			<h3 class="tabs_involved">过滤用户</h3>
		</div> 
			<div style="width:1200px;margin-left:auto;margin-right:auto;">
				<div style="width:1200px;float:left;">
					<div style="float:left;width:60px;" class="go_guolv_ding menu_button biankuang_blue_ding hand">过滤</div>
					<div style="float:left;width:60px;" class="go_from menu_button biankuang_gray hand">来源</div>
				</div>
				<div style="width:1200px;float:left;" class="guolv">
					<div style="margin-top:20px;width:1200px;margin-left:auto;margin-right:auto;">
						<div style="width:120px;float:left;height:30px;">
							语言:
							<select class="hand" id="duandian_agent">
								<option value="0">全部</option>
								<option value="1">java</option>
								<option value="2">php</option>
								<option value="3">nodejs</option>
								<option value="4">python</option>
								<option value="5">dotnet</option>
								<option value="6">ruby</option>
								<option value="7">android</option>
								<option value="8">ios</option>
								<option value="9">browser</option>
								<option value="10">server</option>
							</select>
						</div>
						<div class="hidden duandian_banben" style="width:140px;float:left;height:30px;">
							版本:
							<select class="hand" id="duandian_banben">
							</select>
						</div>
						<div style="width:200px;float:left;height:30px;">
							状态:
							<select class="hand" id="duandian_data">
								<option value="0" selected="selected">全部</option>
								<option value="7">今日有数据</option>
								<option value="8">昨日有数据</option>
								<option value="2">一周内有数据</option>
								<option value="3">二周内有数据</option>
								<option value="4">一个月内有数据</option>
								<option value="9">自定义时间有数据</option>
								<option value="1">有过数据</option>
								<option value="5">添加应用</option>
								<option value="6">下载</option>
							</select>
						</div>
						<div class="hidden duandian_zidingyishijian" style="width:140px;float:left;height:30px;">
							时间:
							<input type="text" id="duandian_zidingyishijian" value="" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" style="width:80px;"/>
						</div>
						<div style="width:140px;float:left;height:20px;line-height:20px;">
							状态:
							<select id="duandian_group_1" class="hand">
								<option value="0">全部</option>
								<s:iterator value="groups">
									<option value="${id}">${name}</option>
								</s:iterator>
							</select>
						</div>
						<div style="width:160px;float:left;height:20px;line-height:20px;" class="duandian_group_2 hidden">
							二级:
							<select id="duandian_group_2" class="hand">
								<option value='0'>全部</option>
							</select>
						</div>
						<div style="width:90px;float:left;height:20px;line-height:20px;">
							<div id="duandian_jinri" style="float:left;width:80px;height:20px;margin:0 0 0 0;" class="menu_button biankuang_gray_ding hand">今日无数据</div>
						</div>
						<!-- <div style="width:75px;float:left;height:20px;line-height:20px;">
							<div id="duandian_guanbi" style="float:left;width:65px;height:20px;margin:0 0 0 0;" class="menu_button biankuang_gray_ding hand">过滤关闭</div>
						</div> -->
						<div style="width:75px;float:left;height:20px;line-height:20px;">
							<div id="duandian_fuze" style="float:left;width:65px;height:20px;margin:0 0 0 0;" class="menu_button biankuang_gray_ding hand">我负责的</div>
						</div>
						<div style="hieght:20px;width:135px;float:left;line-height:30px;">
							<!-- <div style="height:30px;width:40px;float:left;"> -->
								<!-- <input class="hand" id="duandian_chaxun" type="button" style="float:left;" value="查询"/> -->
								<div id="duandian_chaxun" style="float:left;width:65px;height:20px;margin:0 0 0 0;" class="menu_button biankuang_gray_ding hand">查询</div>
							<!-- </div> -->
						</div>
					</div>
					<div style="float:left;width:1200px;height:30px;">
						<div class="duandian_result_msg" style="height:20px;line-hight:20px;font-size:16px;color:red;"></div>
					</div>
				</div>
				<div style="width:1200px;float:left;" class="from hidden">
					<div style="margin-top:20px;width:1200px;margin-left:auto;margin-right:auto;">
						<div style="width:120px;float:left;" class="lable_1" val="1"></div>
						<div style="width:120px;float:left;" class="hidden lable_2" val="2">
						</div>
						<div style="width:120px;float:left;" class="hidden lable_3" val="3">
						</div>
						<div style="width:120px;float:left;" class="hidden lable_4" val="4">
						</div>
						<div style="width:120px;float:left;" class="hidden lable_5" val="5">
						</div>
						<div style="width:120px;float:left;" class="hidden lable_6" val="6">
						</div>
						<div style="width:120px;float:left;" class="hidden lable_7" val="7">
						</div>
						<div style="width:100px;float:left;"><input class="from_chaxun hand" type="button" value="查询"/></div>
					</div>
					<div style="float:left;width:1200px;height:30px;">
						<div class="duandian_result_msg" style="height:20px;line-hight:20px;font-size:16px;color:red;"></div>
					</div>
				</div>
				<div id="duandian_out" style="width:1200px;min-height:400px;float:left;">
					<table class="tablesorter" cellspacing="0"> 
						<thead> 
							<tr> 
								<th style="width:2%"></th>
					  			<th style="width:6%;">ID</th> 
					  			<th style="width:10%;">姓名</th>
					  			<th style="width:14%;">公司</th>
					  			<th style="width:15%;">项目</th>
					  			<th style="width:15%;">语言</th>
					  			<th style="width:7%">Coming</th>
					  			<th style="width:6%;">销售</th>
					  			<th style="width:6%;">售前</th>
					  			<th style="width:6%;">运营</th>
					  			<th style="width:10%">Edit</th>
					  			<th style="width:3%"></th>
							</tr> 
						</thead> 
						<tbody id="duandian_result"> 
						</tbody> 
					</table>
				</div>
			</div>
	</div>
	<div class="clear"></div>
	<div class="spacer"></div>
	<div class="hidden totalPage">${totalPage}</div>
	<div class="hidden nowPage">${nowPage}</div>
	<div class="hidden nowId">${id}</div>
	</section>

	<script src="${applicationScope.staticPath}skin/js/hideshow.js" type="text/javascript"></script>
	<script src="${applicationScope.staticPath}skin/js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/jquery.equalHeight.js"></script>
	<script type="text/javascript" src="${applicationScope.staticPath}skin/js/index.js"></script>
	<script type="text/javascript">
		var father = 0; 
		var lable_list = 1;
		$(document).ready(function() {
			$(".go_from").live('click', function(){
				$(".go_guolv_ding").addClass("go_guolv");
				$(".go_guolv").removeClass("go_guolv_ding");
				$(".go_guolv").removeClass("biankuang_blue_ding");
				$(".go_guolv").addClass("biankuang_gray");
				$(".go_from").addClass("go_from_ding");
				$(".go_from_ding").removeClass("go_from");
				$(".go_from_ding").removeClass("biankuang_gray");
				$(".go_from_ding").addClass("biankuang_blue_ding");
				$(".guolv").addClass("hidden");
				$(".from").removeClass("hidden");
				$.ajax({
					dataType:'json',
					url:'lable_lable.action',
					data:{lableId:0}
				}).done(function(da){
					if(da.status == 1){
						if(da.lables.length > 0){
							var lables = da.lables;
							var html = "<select style='width:100px;' class='hand lable_list lable_list_"+lables[0].grade+"'>"
									+"<option value='0'>所有</option>";
							var i = 0;
							while(lables.length > i){
								html += "<option value='"+lables[i].id+"'>"+lables[i].name+"</option>";
								i++;
							}
							html += "</select>";
							$(".lable_"+lables[0].grade).html(html);
							$(".lable_"+lables[0].grade).removeClass("hidden");
						}
					}else{
						alert(da.msg)
					}
				});
			});
			$(".go_guolv").live('click', function(){
				$(".go_guolv").addClass("go_guolv_ding");
				$(".go_guolv_ding").removeClass("go_guolv");
				$(".go_guolv_ding").removeClass("biankuang_gray");
				$(".go_guolv_ding").addClass("biankuang_blue_ding");
				$(".go_from_ding").addClass("go_from");
				$(".go_from").removeClass("go_from_ding");
				$(".go_from").removeClass("biankuang_blue_ding");
				$(".go_from").addClass("biankuang_gray");
				$(".guolv").removeClass("hidden");
				$(".from").addClass("hidden");
				$()
			});
			$(".from_chaxun").live('click', function(){
				if(father <= 0){
					alert("至少选一个来源分类");
					return;
				}
				$(".from_chaxun").html("查询中");
				$("#duandian_result").html("");
				$(".duandian_result_msg").html("");
				$(".from_chaxun").val("查询中");
				$(".from_chaxun").addClass("from_chaxun_ding");
				$(".from_chaxun_ding").removeClass("from_chaxun");
				$.ajax({
					dataType:'json',
					url:'duandian_chaxun.action',
					data:{fatherId:father}
				}).done(function(da){
					if(da.status == 1){
						var html = "";
						for(var i=0;i<da.infos.length;i++){
							html += "<tr>" 
										+"<td></td>"
										+"<td>"+da.infos[i].userId+"</td>"
										+"<td>"+da.infos[i].name+"</td>"
										+"<td>"+da.infos[i].company+"</td>"
										+"<td>"+da.infos[i].project+"</td>"
										+"<td>"+da.infos[i].language+"</td>"
										+"<td>"+da.infos[i].comming+"</td>"
										+"<td>"+da.infos[i].saleName+"</td>"
										+"<td>"+da.infos[i].preSaleName+"</td>"
										+"<td>"+da.infos[i].supportName+"</td>"
										+"<td><input val1='"+da.infos[i].id+"' class='check_view'  type='image' src='http://manage.oneapm.com/skin/images/icn_view_users.png' title='查看' /></td>"
										+"<td></td>"
										+"</tr>";
						}
						$("#duandian_result").html(html);
						$(".duandian_result_msg").html("总数："+da.infos.length);
						$(".from_chaxun_ding").val("查询");
						$(".from_chaxun_ding").addClass("from_chaxun");
						$(".from_chaxun").removeClass("from_chaxun_ding");
					}else{
						alert(da.msg);
						$(".from_chaxun_ding").html("查询");
						$(".from_chaxun_ding").addClass("from_chaxun");
						$(".from_chaxun").removeClass("from_chaxun_ding");
					}
				});
			});
			$(".lable_list").live("change", function(){
				var lableId = $(this).val();
				var val = Number($(this).parent().attr("val"));
				if(val > 7){
					return;
				}
				for(var i=val+1;i<=7;i++){
					$(".lable_"+i).html("");
					$(".lable_"+i).addClass("hidden");
					lable_list = val;
				}if(lableId <= 0){
					if(val == 1){
						father = 0;
					}else{
						var a = val - 1;
						father = $(".lable_list_"+a).val();
					}
				}
				if(lableId > 0){
					father = lableId;
					$.ajax({
						dataType:'json',
						url:'lable_lable.action',
						data:{lableId:lableId}
					}).done(function(da){
						if(da.status == 1){
							if(da.lables.length > 0){
								var lables = da.lables;
								var html = "<select style='width:100px;' class='hand lable_list lable_list_"+lables[0].grade+"'>"
										+"<option value='0'>所有</option>";
								var i = 0;
								while(lables.length > i){
									html += "<option value='"+lables[i].id+"'>"+lables[i].name+"</option>";
									i++;
								}
								html += "</select>";
								$(".lable_"+lables[0].grade).html(html);
								$(".lable_"+lables[0].grade).removeClass("hidden");
								lable_list = lables[0].grade;
							}
						}else{
							alert(da.msg)
						}
					});
				}else{
					var val = $(this).parent().attr("val");
					if(val > 1){
						val --;
						var lableId = $(".lable_list_"+val).val();
						$.ajax({
							dataType:'json',
							url:'lable_single.action',
							data:{lableId:lableId}
						}).done(function(da){
							if(da.status == 1){
								$("#myText").val(da.lable.from);
							}else{
								alert(da.msg)
							}
						});
					}
				}
				
			});
			
			$("#duandian_group_1").live('click', function(){
				var id = $("#duandian_group_1").val();
				if(id > 0){
					$.ajax({
						dataType:'json',
						url:'group_group.action',
						data:{groupId:id}
					}).done(function(da){
						if(da.status == 1){
							if(da.groups.length > 0){
								var html = "<option value='0'>全部</option>";
								for(var i=0;i<da.groups.length;i++){
									html += "<option value='"+da.groups[i].id+"'>"+da.groups[i].name+"</option>";
								}
								$("#duandian_group_2").html(html);
								$(".duandian_group_2").removeClass("hidden");
							}else{
								$("#duandian_group_2").html("<option value='0'>全部</option>");
								$(".duandian_group_2").addClass("hidden");
							}
						}else{
							alert(alert(da.msg));
						}
					});
				}else{
					$("#duandian_group_2").html("<option value='0'>全部</option>");
					$(".duandian_group_2").addClass("hidden");
				}
			});	
			
		});
	</script>
</body>
</html>