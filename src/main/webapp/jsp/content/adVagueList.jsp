<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="text-align" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
		<script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/common.js"></script>
		<script type="text/javascript" src="${basePath}/js/content/adList.js"></script>
	</head>

	<style>
			#query{
					background: gainsboro ;width: 50px; height: 25px;margin-left: 50px;
			}
			#add{
				background: gainsboro;  width: 50px; height: 25px;margin-left: 50px;
			}
	</style>

	<body style="background: #e1e9eb;">
		<form action="${basePath}/ad/search" id="mainForm" method="post">
			<input type="hidden" id="id" name="id"/>
			<input type="hidden" id="message" value="${pageCode.msg}"/>
			<input type="hidden" id="basePath" value="${basePath}"/>
			<input type="hidden" name="page.currentPage" id="currentPage" value="1"/>
			<div class="right">
				<div class="current">当前位置：<a href="#">内容管理</a> &gt; 广告管理</div>
				<div class="rightCont">
					<p class="g_title fix">广告列表</p>
					<table class="tab1">
						<tbody>
							<tr>
									<td align="right" width="80">标题：</td>
									<td>
										<input name="title" id="title" value="" class="allInput" type="text"/>
									</td>
									<td >
										<input  id="query" value="查询" type="button"/>
									</td>
								<td >
									<input  id="add" value="增加" type="button" onclick="location.href='${basePath}/ad/addInit'"/>
								</td>
									<td style="text-align:text-align: right;" width="150">
										<t:auth url="/ad/remove">
											<input class="tabSub" value="删除" onclick="location.href='${basePath}/ad/remove'" type="button"/>
										</t:auth>
									</td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<tbody>
								<tr>
								    <th>序号</th>
								    <th>标题</th>
								    <th>链接地址</th>
								    <th>操作</th>
								</tr>
								<c:forEach items="${list}" var="item" varStatus="s">
									<tr>
										<td>${s.index + 1}</td>
										<td>${item.title}</td>
										<td>${item.link}</td>
										<td>
											<tr:auth url="/ad/modifyInit">
												<a href="javascript:void(0);" onclick="modifyInit('${item.id}')">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
											</tr:auth>
											<tr:auth url="/ad/remove">
												<a href="javascript:void(0);" onclick="remove('${item.id}')">删除</a>
											</tr:auth>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<!-- 分页 -->

						<t:page jsMethodName="search" page="${searchParam.page}"></t:page>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>