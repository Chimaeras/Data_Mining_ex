## Data_Mining_ex(数据挖掘实验)

#### 组长：吴富乐 
#### 组员：吴富乐

---
### 实验一《多源数据集成、清洗和统计》
### 实验题目： 广州大学某班有同学100人，现要从两个数据源汇总学生数据。第一个数据源在数据库中，第二个数据源在txt文件中，两个数据源课程存在缺失、冗余和不一致性，请用C/C++/Java程序实现对两个数据源的一致性合并以及每个学生样本的数值量化。

---

#### 数据库表字段要求：
* ID(int)
* 姓名(string)
* 家乡(string)(Beijing/Guangzhou/Shenzhen/Shanghai)
* 性别(string)(boy/girl)
* 身高(float)(单位为cm)
* 课程1成绩(float)
* ...
* 课程9成绩(float)
* 体能测试成绩(string)(bad/gengeral/good/excellent)
* (课程1-课程5为百分制，课程6-课程9为十分制)

#### txt文件数据要求：
* ID(string)(6位学号)
* 姓名(string)
* 家乡(string)(Beijing/Guangzhou/Shenzhen/Shanghai)
* 性别(string)(male/female)
* 身高(float)(单位为m)
* 课程1成绩(string)
* ...
* 课程9成绩(string)
* 体能测试成绩(string)(差/一般/良好/优秀)
* (课程1-课程5为百分制，课程6-课程9为十分制)

---

#### 示例：

* 数据库表

  | ID   | Name | City | Gender | Height | C1   | C2   | ... | C9 | Constitution |
  | ---- | ---- | ---- | ------ | ------ | ---- | ---- | ---- | ---- | ---- |
  | 1 | Sun | Beijing | boy | 160 | 87 | 92 | ... | 5 | good |
  | 2 | Zhu | ShenZhen | girl | 177 | 66 | 86 | ... | 8 | excellent |
  | ... | ... | ... | ... | ... | ... | ... | ... | ... | ... |

* Student.txt文件:
##### ID Name City Gender Height C1 ... C9 Constitution
##### 202001 Sun Beijing male 180 87 ... 9 good
##### 202003 Tang Hanghai male 156 91 ... 10 general
##### ... ... ... .. ... .. ... ... ...

---

题目要求：
#### 1. 学生中家乡在Beijing的所有课程的平均成绩。
#### 2.	学生中家乡在广州，课程1在80分以上，且课程9在9分以上的男同学的数量。(备注：该处做了修正，课程10数据为空，更改为课程9)
#### 3.	比较广州和上海两地女生的平均体能测试成绩，哪个地区的更强些？
#### 4.	学习成绩和体能测试成绩，两者的相关性是多少？（九门课的成绩分别与体能成绩计算相关性）

---

#### 均值公式：
![](http://latex.codecogs.com/svg.latex?\overline{x}=\frac{1}{n}\sum_{i=1}^{n}x_{i})

#### 协方差公式：
  ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E5%8D%8F%E6%96%B9%E5%B7%AE%E5%85%AC%E5%BC%8F.png)

#### z-score规范化公式：
  ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/z-score%E8%A7%84%E8%8C%83%E5%8C%96%E5%85%AC%E5%BC%8F.png)

#### 相关性公式：
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E7%9B%B8%E5%85%B3%E6%80%A7%E5%85%AC%E5%BC%8F.png)

* A=[a1,a2,a3,...,an],B=[b1,b2,b3,...,bn]
* mean(A)代表A中元素的平均值
* std是标准差，即对协方差的开平方。
* 点乘的定义：
```math
A*B=\sum_{i}^{}a_{i}*b_{i}
```

---

#### 语言：Java、Markdown、sql
#### 环境：IDEA、Mysql
#### 调用函数库：（java画图库）

