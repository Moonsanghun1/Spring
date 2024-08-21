<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <!-- 1단계: ECharts 설치하기 -->
    <script src="https://cdn.jsdelivr.net/npm/echarts@5.4.3/dist/echarts.min.js"></script>
    <!-- Bootstrap 4 + jquery 라이브러리 등록 - CDN 방식 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- icon 라이브러리 등록 - Font Awesome4 / google -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
    <!-- 2단계: DOM 컨테이너 준비하기: 너비(width), 높이(height) 설정하기 -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <!-- 3단계: 초기화 및 차트 옵션 설정하기 -->
    <script type="text/javascript">


    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom, 'dark');
    var option;

    const upColor = '#00da3c';
    const downColor = '#ec0000';
    function splitData(rawData) {
      let categoryData = [];
      let values = [];
      let volumes = [];
      for (let i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].splice(0, 1)[0]);
        values.push(rawData[i]);
        volumes.push([i, rawData[i][4], rawData[i][0] > rawData[i][1] ? 1 : -1]);
      }
      return {
        categoryData: categoryData,
        values: values,
        volumes: volumes
      };
    }
    function calculateMA(dayCount, data) {
      var result = [];
      for (var i = 0, len = data.values.length; i < len; i++) {
        if (i < dayCount) {
          result.push('-');
          continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
          sum += data.values[i - j][1];
        }
        result.push(+(sum / dayCount).toFixed(3));
      }
      return result;
    }
    $.get('/resources/js/stock-DJI.json', function (rawData) {
      var data = splitData(rawData);
      myChart.setOption(
        (option = {
          animation: false,
          legend: {
            bottom: 10,
            left: 'center',
            data: ['Dow-Jones index', 'MA5', 'MA10', 'MA20', 'MA30']
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross'
            },
            borderWidth: 1,
            borderColor: '#ccc',
            padding: 10,
            textStyle: {
              color: '#000'
            },
            position: function (pos, params, el, elRect, size) {
              const obj = {
                top: 10
              };
              obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
              return obj;
            }
            // extraCssText: 'width: 170px'
          },
          axisPointer: {
            link: [
              {
                xAxisIndex: 'all'
              }
            ],
            label: {
              backgroundColor: '#777'
            }
          },
          toolbox: {
            feature: {
              dataZoom: {
                yAxisIndex: false
              },
              brush: {
                type: ['lineX', 'clear']
              }
            }
          },
          brush: {
            xAxisIndex: 'all',
            brushLink: 'all',
            outOfBrush: {
              colorAlpha: 0.1
            }
          },
          visualMap: {
            show: false,
            seriesIndex: 5,
            dimension: 2,
            pieces: [
              {
                value: 1,
                color: downColor
              },
              {
                value: -1,
                color: upColor
              }
            ]
          },
          grid: [
            {
              left: '10%',
              right: '8%',
              height: '50%'
            },
            {
              left: '10%',
              right: '8%',
              top: '63%',
              height: '16%'
            }
          ],
          xAxis: [
            {
              type: 'category',
              data: data.categoryData,
              boundaryGap: false,
              axisLine: { onZero: false },
              splitLine: { show: false },
              min: 'dataMin',
              max: 'dataMax',
              axisPointer: {
                z: 100
              }
            },
            {
              type: 'category',
              gridIndex: 1,
              data: data.categoryData,
              boundaryGap: false,
              axisLine: { onZero: false },
              axisTick: { show: false },
              splitLine: { show: false },
              axisLabel: { show: false },
              min: 'dataMin',
              max: 'dataMax'
            }
          ],
          yAxis: [
            {
              scale: true,
              splitArea: {
                show: true
              }
            },
            {
              scale: true,
              gridIndex: 1,
              splitNumber: 2,
              axisLabel: { show: false },
              axisLine: { show: false },
              axisTick: { show: false },
              splitLine: { show: false }
            }
          ],
          dataZoom: [
            {
              type: 'inside',
              xAxisIndex: [0, 1],
              start: 98,
              end: 100
            },
            {
              show: true,
              xAxisIndex: [0, 1],
              type: 'slider',
              top: '85%',
              start: 98,
              end: 100
            }
          ],
          series: [
            {
              name: 'Dow-Jones index',
              type: 'candlestick',
              data: data.values,
              itemStyle: {
                color: upColor,
                color0: downColor,
                borderColor: undefined,
                borderColor0: undefined
              }
            },
            {
              name: 'MA5',
              type: 'line',
              data: calculateMA(5, data),
              smooth: true,
              lineStyle: {
                opacity: 0.5
              }
            },
            {
              name: 'MA10',
              type: 'line',
              data: calculateMA(10, data),
              smooth: true,
              lineStyle: {
                opacity: 0.5
              }
            },
            {
              name: 'MA20',
              type: 'line',
              data: calculateMA(20, data),
              smooth: true,
              lineStyle: {
                opacity: 0.5
              }
            },
            {
              name: 'MA30',
              type: 'line',
              data: calculateMA(30, data),
              smooth: true,
              lineStyle: {
                opacity: 0.5
              }
            },
            {
              name: 'Volume',
              type: 'bar',
              xAxisIndex: 1,
              yAxisIndex: 1,
              data: data.volumes
            }
          ]
        }),
        true
      );
      myChart.dispatchAction({
        type: 'brush',
        areas: [
          {
            brushType: 'lineX',
            coordRange: ['2016-06-02', '2016-06-20'],
            xAxisIndex: 0
          }
        ]
      });
    });

    option && myChart.setOption(option);

    </script>
</body>
</html>	