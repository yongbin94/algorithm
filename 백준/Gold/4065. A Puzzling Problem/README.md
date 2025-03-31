# [Gold V] A Puzzling Problem - 4065 

[문제 링크](https://www.acmicpc.net/problem/4065) 

### 성능 요약

메모리: 63860 KB, 시간: 480 ms

### 분류

브루트포스 알고리즘, 그래프 이론, 그래프 탐색

### 제출 일자

2025년 3월 31일 22:42:46

### 문제 설명

<p>Anna Graham is a puzzle maker who prides herself in the quality and complexity of her work. She makes puzzles of all kinds - crosswords, logic problems, acrostics, and word search puzzles, to name but a few. For each puzzle, she has developed a set of rules which she constrains herself to follow. For word search puzzles, she insists not only that all the words be connected to one another (as in most word search puzzles), but also that removing any word from the word list will not cause one or more words to become disconnected from the rest. (Two words are connected if they contain a common letter in the grid.) The example word search puzzle on the left satisfies this condition, but the one on the right does not (removing the word Pascal from the word list disconnects Java from the other two words).</p>

<p><img alt="" src="https://www.acmicpc.net/upload/images2/puzzle.png" style="height:160px; width:452px"></p>

<p>Your job is to write a program that checks to see if Anna’s word search problems are up to snuff.</p>

### 입력 

 <p>Input will consist of multiple test cases. The first line of each test case contains 3 integers n m l, where n and m are the number of rows and columns in the puzzle and l is the number of words. Following this are n lines containing m uppercase characters each (the puzzle) followed by l lines containing one word each (the word list, in mixed case). Each word in the word list will appear in the puzzle exactly once. There will be no more than 100 rows and 100 columns in the puzzle and no more than 100 words to search for. There will be no spaces in the input words</p>

### 출력 

 <p>For each problem instance, output the word Yes or No depending on whether the puzzle satisfies Anna’s constraints.</p>

