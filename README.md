## Data_Mining_ex(数据挖掘实验)

#### 组长：吴富乐 
#### 组员：吴富乐

---
### 实验一《多源数据集成、清洗和统计》
### 实验题目： 广州大学某班有同学100人，现要从两个数据源汇总学生数据。第一个数据源在数据库中，第二个数据源在txt文件中，两个数据源课程存在缺失、冗余和不一致性，请用C/C++/Java程序实现对两个数据源的一致性合并以及每个学生样本的数值量化。

---

#### 数据不一致性：两个数据源中的数据单位、种类、数据类型存在不一致。

数据源 | 性别 |身高 | 体测成绩
---|---|---|---
数据库 | boy/girl | 单位为cm | (bad/gengeral/good/excellent)
TXT | male/female | 单位为m | (差/一般/良好/优秀)

#### 数据冗余性：两个数据源可能存储了同一个学生的相同信息

#### 数据缺失：某个数据源中可能存在某个学生的某一项信息缺失的情况

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
##### ... ... ... ... ... ... ... ... ...

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
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E7%82%B9%E4%B9%98%E5%85%AC%E5%BC%8F.png)

---

#### 语言：Java、Markdown、sql
#### 环境：IDEA、Mysql
#### 调用函数库：（java画图库）

---

#### 数据解释：
本实验数据通过网站随机生成(http://generatedata.com/?lang=zh#t1)。
* 完整数据：Data_Mining_ex/Data/学生数据-无缺失版本.txt
* 完整数据库表：Data_Mining_ex/Data/学生表.sql
* 实验所用（有缺失）txt数据：Data_Mining_ex/Data/学生数据.txt
* 两个数据源缺失对比参照：Data_Mining_ex/Data/=student_table.csv.（其中黄底数据行为txt文件中缺失数据行，蓝底数据行为数据库中缺失数据行）

---


#### （导入前）学生数据对比（txt VS 数据库）：
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E6%95%B0%E6%8D%AE%E5%AF%B9%E6%AF%941-40.png)
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E6%95%B0%E6%8D%AE%E5%AF%B9%E6%AF%9441-79.png)
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E6%95%B0%E6%8D%AE%E5%AF%B9%E6%AF%9480-100.png)


---

#### （导入后）学生数据展示：
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/1-36.png)
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/37-75.png)
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/75-100.png)

---

#### 程序运行结果截图：
* 问题1：学生中家乡在Beijing的所有课程的平均成绩。
* 问题2：学生中家乡在广州，课程1在80分以上，且课程9在9分以上的男同学的数量。(备注：该处做了修正，课程10数据为空，更改为课程9)
* 问题3：比较广州和上海两地女生的平均体能测试成绩，哪个地区的更强些？
* 问题4：学习成绩和体能测试成绩，两者的相关性是多少？（九门课的成绩分别与体能成绩计算相关性）

![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E5%AE%9E%E9%AA%8C%E4%B8%80%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE.png)

---

## 实验二《数据统计和可视化》
### 题目:基于实验一中清洗后的数据练习统计和视化操作，100个同学（样本），每个同学有11门课程的成绩（10维的向量）；那么构成了一个100x10的数据矩阵。以你擅长的语言C/C++/Java/Python/Matlab，编程计算：
* 1.	请以课程1成绩为x轴，体能成绩为y轴，画出散点图。
#### 这里存在两种体育成绩的量化方法，会导致散点图形状发生变化
##### 方法1：对于不同评级的体育成绩，给予对应的确定分数，例如（excellent=95，good=85，general=75，bad=60），此时散点图为：
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E6%95%A3%E7%82%B9%E5%9B%BE_%E9%87%8F%E5%8C%96_1.png)

##### 方法2：对于不同评级的体育成绩，给予对应范围内的随机分数，例如（excellent=90-100，good=80-90，general=60-80，bad=0-60），此时散点图为：
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E6%95%A3%E7%82%B9%E5%9B%BE_%E9%87%8F%E5%8C%96_2.png)

* 2.	以5分为间隔，画出课程1的成绩直方图。
#### 打印得到的成绩直方图存储地址为："e:\\成绩直方图.png"
#### 展示：
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E6%88%90%E7%BB%A91%E7%9B%B4%E6%96%B9%E5%9B%BE.png)

* 3.	对每门成绩进行z-score归一化，得到归一化的数据矩阵。
#### 归一化矩阵展示：
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E5%BD%92%E4%B8%80%E5%8C%96%E7%9F%A9%E9%98%B5_1.PNG)
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E5%BD%92%E4%B8%80%E5%8C%96%E7%9F%A9%E9%98%B5_2.PNG)
![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E5%BD%92%E4%B8%80%E5%8C%96%E7%9F%A9%E9%98%B5_3.PNG)

* 4.	计算出100x100的相关矩阵，并可视化出混淆矩阵。（为避免歧义，这里“协相关矩阵”进一步细化更正为100x100的相关矩阵，100为学生样本数目，视实际情况而定）
#### 计算得出相关矩阵，并写到txt文件中，文件名为：学生相关矩阵.txt。详情请见Chimaeras/Data_Mining_ex/学生相关矩阵.txt。
#### 可视化混淆矩阵：

![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E5%8F%AF%E8%A7%86%E5%8C%96%E6%B7%B7%E6%B7%86%E7%9F%A9%E9%98%B5.png
)

* 5.	根据相关矩阵，找到距离每个样本最近的三个样本，得到100x3的矩阵（每一行为对应三个样本的ID）输出到txt文件中，以\t,\n间隔。
#### 根据相关矩阵，将样本ID写到txt文件中，文件名为：学生样本.txt。详情请见Chimaeras/Data_Mining_ex/学生样本.txt。
