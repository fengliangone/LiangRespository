<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">

    <link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/crm/ECharts/echarts.min.js"></script>

</head>

<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 700px;height:500px;margin: 0 auto;margin-top: 50px"></div>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    /*
    * 统计处在交易不同阶段的交易数量
    * 01资质审查    20
    * 02需求分析    10
    * 03价值建议    1000
    * ...
    * 07成交        50000
    * */
    $.ajax({
        url : '/crm/workbench/chart/transaction/queryTransactionEcharts',
        type : 'get',
        dataType : 'json',
        success : function(data){
            console.log(data);
            option = {
                title: {
                    text: 'CRM交易阶段统计',
                    subtext: '真实数据',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: data.dataList
                },
                series: [
                    {
                        name: '交易比例',
                        type: 'pie',
                        radius: '60%',
                        center: ['50%', '60%'],
                        data: data.dataMap.transactions,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

    });

</script>

</body>
</html>