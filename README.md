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
##### ID Name City Gender Height C1 ... C10 Constitution
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



