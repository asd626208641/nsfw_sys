<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";       //这句代码的作用到底是什么？  jsp里面的bathPath和html里面的bathPath是不一样的，html里面的bathPath是Head里面的，所以这句代码的作用是什么，我也不是很清楚。
	
	//获取当前年份
	Calendar cal = Calendar.getInstance();
	int curYear = cal.get(Calendar.YEAR); //当前年份
	request.setAttribute("curYear", curYear);
	List yearList = new ArrayList();
	for (int i = curYear; i >= curYear - 4; i--) {
		yearList.add(i);
	}
	request.setAttribute("yearList", yearList);
%>

<!DOCTYPE HTML>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>年度投诉统计图</title>
</head>
<script type="text/javascript"
	src="${basePath }js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript"
	src="${basePath }js/fusioncharts/fusioncharts.charts.js"></script>
<script type="text/javascript"
	src="${basePath }js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
<script type="text/javascript">
//加载dom元素后，执行统计方法

$(document).ready(doAnnualStatistic());

	function doAnnualStatistic() {
		//1.获取当前年份
		var year = $("#year option:selected").val();
		if (year == "" || year == undefined) {
			year = "${curYear}";//默认当前月份
		}
		//2.根据年份统计
		$.ajax({
			url:"${basePath }/nsfw/complain_getAnnualStatisticData.action",
			data : {"year":year},
			type : "post",
			dataType : "json",
			success : function(data) {
				if (data != null && data != "" && data != undefined) {
					var revenueChart = new FusionCharts({
						"type" : "line",
						"renderAt" : "chartContainer",
						"width" : "800",
						"height" : "500",
						"dataFormat" : "json",
						"dataSource" : {
							"chart" : {
								"caption" : year+"年度投诉数统计图",
								"xAxisName" : "月 份 ",
								"yAxisName" : "投  诉  数",
								"theme" : "fint"
							},
							"data" : data.chartData
						}
					});
					revenueChart.render();
				} else {
					alert("统计投诉数目失败");
				}
			},
			error : function() {
				alert("统计投诉数目失败");
			}
		});
	}
</script>

<body>
	<br>
	<s:select id="year" list="#request.yearList"
		onchange="doAnnualStatistic()"></s:select>
	<br>
	<div id="chartContainer"></div>
</body>
</html>
