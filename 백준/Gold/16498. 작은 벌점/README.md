# [Gold V] 작은 벌점 - 16498 

[문제 링크](https://www.acmicpc.net/problem/16498) 

### 성능 요약

메모리: 15360 KB, 시간: 236 ms

### 분류

브루트포스 알고리즘, 정렬, 이분 탐색

### 제출 일자

2025년 6월 8일 13:35:54

### 문제 설명

<p>세 명이 한 팀이 되어 정수를 조합하는 게임이 있다. 이 게임에서 각 팀의 각 플레이어는 정수가 하나씩 적혀있는 숫자 카드를 한 장 이상 받는다. 각 플레이어는 가지고 있는 숫자 카드 중 한 장을 선택해 책상에 내려 놓는다. 이렇게 되면 책상에 총 3장의 카드가 놓이게 되며, 이 때 보이는 수의 최댓값과 최솟값의 차이가 벌점이 된다. 이를 식으로 표현하면 다음과 같다.</p>

<p style="text-align: center;">| max(a,b,c) – min(a,b,c) |</p>

<p>여기서 a, b, c는 각각 플레이어가 선택하여 내려놓은 카드의 숫자 값이다. </p>

<p>세 명의 플레이어에게 주어진 숫자 카드가 주어졌을 때, 만들 수 있는 가장 작은 벌점을 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 첫 번째 플레이어가 받은 숫자 카드의 개수 A, 두 번째 플레이어가 받은 숫자 카드의 개수 B, 세 번째 플레이어가 받은 숫자 카드의 개수 C가 주어진다. (1 ≤ A, B, C ≤ 1,000)</p>

<p>둘째 줄에 첫 번째 플레이어가 받은 숫자 카드에 적힌 수, 셋째 줄에 두 번째 플레이어가 받은 숫자 카드에 적힌 수, 넷째 줄에 세 번째 플레이어가 받은 숫자 카드에 적힌 수가 주어진다.</p>

<p>숫자 카드에 적힌 수는 절댓값이 100,000,000보다 작거나 같은 정수이다..</p>

### 출력 

 <p>세 플레이어가 만들 수 있는 가장 작은 벌점을 출력한다.</p>

