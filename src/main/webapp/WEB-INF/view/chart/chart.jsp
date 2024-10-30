<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp"%>

<h1 style="margin-top: 300px "> 차트 JS </h1>
<canvas id="myChart" width="300" height="100"></canvas>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.0/chart.umd.min.js"></script>
<script>
    function countChart(data) {
        const labels = data.map(item => item.asltVtrCd); // 가해자법규위반 유형
        const deathCounts = data.map(item => item.deathCount); // 사망자 수
        const injuredCounts = data.map(item => item.injuredCount); // 부상자 수
        const seriousInjuriesCount = data.map(item => item.seriousInjuriesCount); // 중상자 수
        const minorInjuriesCount = data.map(item => item.minorInjuriesCount); // 경상자 수

        var ctx = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'line', // 차트 타입
            data: {
                labels: labels,
                datasets: [
                    {
                        label: '사망자 수',
                        data: deathCounts, // 사망자 수 데이터
                        backgroundColor: 'rgba(255, 99, 132, 1)',
                        borderColor: 'rgba(255, 177, 193, 1)',
                        borderWidth: 2
                    },
                    {
                        label: '부상자 수',
                        data: injuredCounts, // 부상자 수 데이터
                        backgroundColor: 'rgba(54, 162, 235, 1)',
                        borderColor: 'rgba(154, 208, 245, 1)',
                        borderWidth: 2
                    },
                    {
                        label: '중상자 수',
                        data: seriousInjuriesCount, // 부상자 수 데이터
                        backgroundColor: 'rgba(75, 192, 192, 1)',
                        borderColor: 'rgba(165, 233, 235, 1)',
                        borderWidth: 2
                    },
                    {
                        label: '경상자 수',
                        data: minorInjuriesCount, // 부상자 수 데이터
                        backgroundColor: 'rgba(255, 205, 86, 1)',
                        borderColor: 'rgba(255, 230, 170, 1)',
                        borderWidth: 2
                    }
                ]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    // 데이터 fetch 및 차트 생성
    fetch('/count')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            countChart(data);
        })
        .catch(error => console.error('Error fetching data:', error));

</script>

<%@ include file="../layout/footer.jsp"%>
