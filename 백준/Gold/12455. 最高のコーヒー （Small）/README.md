# [Gold III] 最高のコーヒー (Small) - 12455 

[문제 링크](https://www.acmicpc.net/problem/12455) 

### 성능 요약

메모리: 14824 KB, 시간: 132 ms

### 분류

그리디 알고리즘

### 제출 일자

2025년 4월 6일 21:14:18

### 문제 설명

<p>ヘインの一日は朝のコーヒーを飲むことから始まります。</p>

<p>彼の手元には <strong>N</strong> 種類のコーヒーがあります。<strong>i</strong> 番目の種類のコーヒーは <strong>c<sub>i</sub></strong> 杯分残っていて、今日から数えて <strong>t<sub>i</sub></strong> 日目に消費期限を迎えます。彼は <strong>i</strong> 番目（1 ≤ <strong>i</strong> ≤ <strong>N</strong>）の種類のコーヒーを一杯飲むごとに <strong>s<sub>i</sub></strong>の満足度が得られます。消費期限の切れたコーヒーを飲むことはできません（ちょうど <strong>t<sub>i</sub></strong> 日目であればそのコーヒーは飲むことができます）。たとえば <strong>t<sub>i</sub></strong> = 1 であれば、今日中に飲んでしまうか、そのコーヒーをあきらめるかのどちらかにしなければなりません。</p>

<p>彼はコーヒーを一日に一杯だけ、朝だけにしか飲まないことにしています。もし手元に飲めるコーヒーがない日は、満足度を得ることはできません。</p>

<p>これらのコーヒーを飲むことで、今日から始めて <strong>K</strong> 日目までに彼は合計して最大でどれだけの満足度を得られるでしょうか。</p>

### 입력 

 <p>入力の最初の行はテストケースの個数 <strong>T</strong> です。そのあとに <strong>T</strong> 個のテストケースが続きます。 それぞれのテストケースは 1 つのスペースで区切られた 2 つの正の整数が含まれる行から始まります。 最初の整数はコーヒーの種類数 <strong>N</strong> を表し、次の整数は日数 <strong>K</strong> を表します。 そのあとにそれぞれの種類のコーヒーの残数、消費期限、満足度を表す以下の形式の行が <strong>N</strong> 行続きます。</p>

<pre>c<sub>i</sub> t<sub>i</sub> s<sub>i</sub></pre>

<h3>制約</h3>

<ul>
	<li>1 ≤ <strong>T</strong> ≤ 100</li>
	<li>1 ≤ <strong>c<sub>i</sub></strong> ≤ K</li>
	<li>1 ≤ <strong>t<sub>i</sub></strong> ≤ K</li>
	<li>1 ≤ <strong>s<sub>i</sub></strong> ≤ 1000</li>
	<li>1 ≤ <strong>N</strong> ≤ 8</li>
	<li>1 ≤ <strong>K</strong> ≤ 8</li>
</ul>

### 출력 

 <p>各テストケースごとに、</p>

<pre>Case #X: Y
</pre>

<p>と一行出力してください。ここで <strong>X</strong> は 1 から始まるテストケースの番号、<strong>Y</strong> はヘインの満足度の合計の最大値です。</p>

