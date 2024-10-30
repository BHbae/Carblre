<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp"%>

<h1> 차트 JS </h1>

<canvas id="accidentTypeChart"></canvas>
<canvas id="yearCharts"></canvas>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.0/chart.umd.min.js"></script>
<script>
    <!-- 년도별 사망자수 통계 차트 START -->
    document.addEventListener('DOMContentLoaded', (event) => {
        const deathChartData = JSON.parse('${deathChartData}');
        const label = deathChartData.map(deathToYearCount => deathToYearCount.year);
        const allDeathCount = deathChartData.map(deathToYearCount => deathToYearCount.deathCount);
        const injuredCount = deathChartData.map(deathToYearCount => deathToYearCount.injuredCount);
        const seriousInjuriesCount = deathChartData.map(deathToYearCount => deathToYearCount.seriousInjuriesCount);
        const minorInjuriesCount = deathChartData.map(deathToYearCount => deathToYearCount.minorInjuriesCount);

        const data = {
            labels: label,
            datasets: [
                {
                    label: '사망자 수',
                    data: allDeathCount,
                    borderColor: 'rgba(255, 99, 132, 1)',
                    backgroundColor: 'rgba(255, 99, 132, 0.5)',
                    pointStyle: 'circle',
                    pointRadius: 10,
                    pointHoverRadius: 20
                },{
                    label: '부상자 수',
                    data: injuredCount,
                    borderColor: 'rgb(99,161,255)',
                    backgroundColor: 'rgba(99,161,255, 0.5)',
                    pointStyle: 'circle',
                    pointRadius: 10,
                    pointHoverRadius: 20
                },{
                    label: '중상자 수',
                    data: seriousInjuriesCount,
                    borderColor: 'rgb(128,255,99)',
                    backgroundColor: 'rgba(128,255,99, 0.5)',
                    pointStyle: 'circle',
                    pointRadius: 10,
                    pointHoverRadius: 20
                },{
                    label: '경상자 수',
                    data: minorInjuriesCount,
                    borderColor: 'rgb(255,229,99)',
                    backgroundColor: 'rgba(255,229,99, 0.5)',
                    pointStyle: 'circle',
                    pointRadius: 10,
                    pointHoverRadius: 20
                }

            ]
        };

        const config = {
            type: 'line',
            data: data,
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: '년도별 사망자수 통계'
                    }
                }
            }
        };
        const ct = document.getElementById('yearCharts').getContext('2d');
        const yearCharts = new Chart(ct, config);
    });




    <!-- 년도별 사망자수 통계 차트 END -->
    const accidentChartData = JSON.parse('${accidentChartData}');
    const label = accidentChartData.map(accidentChartData => accidentChartData.asltVtrNm);
    const allDeathCount = accidentChartData.map(accidentChartData => accidentChartData.deathCount);
    const injuredCount = accidentChartData.map(accidentChartData => accidentChartData.injuredCount);
    const seriousInjuriesCount = accidentChartData.map(accidentChartData => accidentChartData.seriousInjuriesCount);
    const minorInjuriesCount = accidentChartData.map(accidentChartData => accidentChartData.minorInjuriesCount);

    const DATA_COUNT = 8;
    const NUMBER_CFG = {count: DATA_COUNT, min: 0, max: 100};

    const labels = Utils.months({count: 8});
    const data = {
        labels: labels,
        datasets: [
            {
                label: 'Dataset 1',
                data: labels.map(() => {
                    return [Utils.rand(0, 100), Utils.rand(0, 100)];
                }),
                backgroundColor: Utils.CHART_COLORS.red,
            },
            {
                label: 'Dataset 2',
                data: labels.map(() => {
                    return [Utils.rand(0, 100), Utils.rand(0, 100)];
                }),
                backgroundColor: Utils.CHART_COLORS.blue,
            },
            {
                label: 'Dataset 2',
                data: labels.map(() => {
                    return [Utils.rand(0, 100), Utils.rand(0, 100)];
                }),
                backgroundColor: Utils.CHART_COLORS.green,
            },
        ]
    };

    const config = {
        type: 'bar',
        data: data,
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: 'Chart.js Floating Bar Chart'
                }
            }
        }
    };

    const ct = document.getElementById('accidentTypeChart').getContext('2d');
    const accidentTypeChart = new Chart(ct, config);
</script>

<%@ include file="../layout/footer.jsp"%>