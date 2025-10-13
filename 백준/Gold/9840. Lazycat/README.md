# [Gold III] Lazycat - 9840 

[문제 링크](https://www.acmicpc.net/problem/9840) 

### 성능 요약

메모리: 20140 KB, 시간: 160 ms

### 분류

너비 우선 탐색, 그래프 이론, 그래프 탐색, 외판원 순회 문제

### 제출 일자

2025년 10월 13일 12:43:07

### 문제 설명

<p>The map of a house is given by an <i>n</i> x <i>n</i> grid as shown below (for a 4 x 4 case):</p>

<table class="table table-bordered" style="width: 20%;">
	<tbody>
		<tr>
			<td style="text-align: center;">B</td>
			<td style="text-align: center;"> </td>
			<td style="text-align: center;">F</td>
			<td style="text-align: center;"> </td>
		</tr>
		<tr>
			<td style="text-align: center;">X</td>
			<td style="text-align: center;">X</td>
			<td style="text-align: center;">F</td>
			<td style="text-align: center;"> </td>
		</tr>
		<tr>
			<td style="text-align: center;">F</td>
			<td style="text-align: center;">X</td>
			<td style="text-align: center;">X</td>
			<td style="text-align: center;">F</td>
		</tr>
		<tr>
			<td style="text-align: center;">S</td>
			<td style="text-align: center;"> </td>
			<td style="text-align: center;"> </td>
			<td style="text-align: center;"> </td>
		</tr>
	</tbody>
</table>

<p>The walls of the house are marked with 'X', food items are marked by 'F', and a single bed is marked by a 'B'. The cat begins at position 'S' (which can be anywhere within the grid and not just at the bottom left hand corner). The objective of this question is to find the smallest number of steps needed to reach the bed from the starting position 'S', while visiting all food items. The cat can only step up, down, left and right, and cannot enter squares marked with 'X'.</p>

### 입력 

 <p>Your program will read from standard input the following data. The first line specifies the grid size <i>n</i>, where 2 ≤ <i>n</i> ≤ 30. Each of the following <i>n</i> lines contains <i>n</i> characters, each of which characterizes the corresponding cell of the grid. The bed is marked with a 'B', the food items with 'F', the walls with 'X' and the empty squares with '0' (the digit zero). All letters are uppercase only. You can assume that there are at least one and at most 10 occurrences of 'F'.</p>

### 출력 

 <p>Your program must write one line to the standard output, containing the smallest number of steps required for reaching all food items and then go to bed. If there is no way for the cat to reach all food items and the bed, the output line contains the number -1 instead. The line is terminated by a newline character.</p>

