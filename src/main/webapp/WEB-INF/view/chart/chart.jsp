<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp"%>
<style>


</style>
<h1> 차트 JS </h1>

<canvas id="yearChart"></canvas>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.0/chart.umd.min.js"></script>
<script>
    const ctx = document.getElementById('yearChart').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['과속', '중앙선 침범', '신호위반', '안전거리 미확보', '안전운전 의무 불이행', '교차로 통행방법 위반', '보행자 보호의무 위반', '기타'],
            datasets: [{
                //label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3, 7, 15],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(103, 82, 55, 0.2)',
                    'rgba(23, 102, 25, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(103, 82, 55, 1)',
                    'rgba(23, 102, 25, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                }
            },
            plugins: {
              legend: {
                  position: 'top',
              },
            },
            title: {
                display: true,
                text: '법규위반'
            }
        }
    });


</script>

<%@ include file="../layout/footer.jsp"%>