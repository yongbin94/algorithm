# [Gold II] 거울냥이는 죽어서 거울을 남긴다 - 16226 

[문제 링크](https://www.acmicpc.net/problem/16226) 

### 성능 요약

메모리: 115528 KB, 시간: 848 ms

### 분류

자료 구조, 구현

### 제출 일자

2025년 4월 25일 13:36:03

### 문제 설명

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/7481b1cf-7952-4099-ae73-d8dbfc677c4f/-/preview/" style="height: 150px; width: 143px; margin-left: 50px; margin-right: 50px;"><img alt="" src="https://upload.acmicpc.net/f36b5d00-6698-4980-a87d-e9ad3aa12ffa/-/preview/" style="height: 150px; width: 314px; margin-left: 50px; margin-right: 50px;"></p>

<p>격자판으로 이루어진 디디몬 어드벤쳐의 어느 섬. 그 곳에는 거울냥이들이 모여 살고 있다. 거울냥이들의 생태계를 조사하던 디디는 충격적인 사실을 알게 되었다. 거울냥이들은 닿는 생명체를 전부 녹여버리는 빔을 상하좌우 네 방향으로 발사한다. 그 빔은 다른 거울냥이들을 관통하며, 어디서 발사하든 격자판의 끝에 도달한다. 거울냥이들은 동료로부터 자신의 몸을 보호하기 위해 꼬리에 거울을 달고 있도록 진화했는데, 그 거울은 항상 거울냥이의 하단 칸에 위치한다. 거울은 빔이 통과하지 않게 막으며 빔에 의해 녹지 않는다. 그리고 요즘은 무광이 대세이므로, 거울은 빔을 반사하지 않는다. 거울냥이는 죽어서 거울을 남기기 때문에 거울냥이가 녹아 없어져도 거울은 있던 자리에 그대로 남는다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/4eb7b47f-4cf1-415b-948e-ee3cd12a1640/-/preview/" style="height: 242px; width: 200px; margin-left: 100px; margin-right: 100px;"><img alt="" src="https://upload.acmicpc.net/3d2aa64e-c678-4ee7-a8e6-5745e7acb61e/-/preview/" style="height: 247px; width: 200px; margin-left: 100px; margin-right: 100px;"></p>

<p>디디는 거울냥이들이 빔을 쏘는 순서가 정해져 있다는 것을 알아냈다. 하지만 이미 누군가의 빔에 의해 사라진 거울냥이는 자신의 차례가 오더라도 빔을 쏘지 못한다. 살아 있는 거울냥이들이 모두 빔을 한 번씩 순서대로 쐈을 때, 마지막까지 살아남은 거울냥이의 마릿수를 구하는 프로그램을 작성하라.</p>

### 입력 

 <p>첫째 줄에 거울냥이의 마릿수를 나타내는 정수 <em>N</em>(1 ≤ <em>N</em> ≤ 2 x 10<sup>5</sup>)이 주어진다.</p>

<p>둘째 줄부터 <em>N</em>개의 줄에는 빔을 쏘는 순서대로 거울냥이의 격자상의 위치 <em>R<sub>i</sub></em>, <em>C<sub>i</sub></em>(1 ≤ <em>R<sub>i</sub></em>, <em>C<sub>i</sub></em> ≤ 10<sup>5</sup>)가 공백으로 구분되어 주어진다. 거울냥이와 거울냥이, 거울냥이와 거울의 위치는 중복되지 않게 주어지는 것이 보장된다.</p>

### 출력 

 <p>마지막까지 살아남은 거울냥이의 마릿수를 출력하시오.</p>

