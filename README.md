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
#### （Tips）由于java没有比较好的绘制图形库，所以这里采用python中的plt库完成对混淆矩阵的可视化。详细代码请见Chimaeras/Data_Mining_ex/src/draw.py。
#### 可视化混淆矩阵：

![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E5%8F%AF%E8%A7%86%E5%8C%96%E6%B7%B7%E6%B7%86%E7%9F%A9%E9%98%B5.png
)

* 5.	根据相关矩阵，找到距离每个样本最近的三个样本，得到100x3的矩阵（每一行为对应三个样本的ID）输出到txt文件中，以\t,\n间隔。
#### 根据相关矩阵，将样本ID写到txt文件中，文件名为：学生样本.txt。详情请见Chimaeras/Data_Mining_ex/学生样本.txt。


---
## 实验三《k-means聚类算法》
### 实验题目： 用C++实现k-means聚类算法，
* 1. 对实验二中的z-score归一化的成绩数据进行测试，观察聚类为2类，3类，4类，5类的结果，观察得出什么结论？
* 2. 由老师给出测试数据，进行测试，并画出可视化出散点图，类中心，类半径，并分析聚为几类合适。

样例数据(x,y)数据对：

x | y
---|---
3.45 | 7.08
1.76 | 7.24
4.29 | 9.55
3.35 | 6.65
3.17 | 6.41
3.68 | 5.99
2.11 | 4.08
2.58 | 7.10
3.45 | 7.88
6.17 | 5.40
4.20 | 6.46
5.87 | 3.87
5.47 | 2.21
5.97 | 3.62
6.24 | 3.06
6.89 | 2.41
5.38 | 2.32
5.13 | 2.73
7.26 | 4.19
6.32 | 3.62

找到聚类中心后，判断（2，6）是属于哪一类？

#### 注意
除文件读取外，不能使用C++基础库以外的API和库函数。

#### 算法思想：根据样本之间的距离远近，将总样本分为几个聚类。

#### 算法流程：
* 1.选取一个k值，即选择将样本分成几类。
* 2.初始化k个随机质心
* 3.遍历所有样本，对于每一个样本，分别计算该样本与k个质心的距离，将该样本点划分为距离其本身最近的质心所属的类。
* 4.遍历所有样本，对于属于i类样本点（0<i<k）重新计算i类点的中心。
* 5.当所有质心都没有发生变化或者到达最大迭代次数后，终止循环。
* 6.输出划分后的类簇

#### 优点：
* 原理比较简单，实现也比较容易，收敛速度快。
* 聚类效果较优。
* 算法通俗易懂，可解释度比较强。
* 需要调整的参数只有簇数k。

#### 缺点：
* K值的选取不好把握，不同的k值对于结果的影响较大
* 采用迭代方法，得到的结果只是局部最优。多次生成同样个数的k个质心，结果可能不一样
* 对噪音和异常数据比较的敏感

#### 算法代码：
* 详情请见Chimaeras/Data_Mining_ex/src/k-means.cpp文件
* 通过调整c改变聚类中心的个数
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E8%81%9A%E7%B1%BB%E4%B8%AA%E6%95%B0.png)

* 通过改变path改变文件地址
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E6%96%87%E4%BB%B6%E5%9C%B0%E5%9D%80.png)

#### 结果展示：
* (聚类：2类)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/100_%E4%BA%8C%E7%B1%BB.png)
---
* (聚类：3类)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/100_%E4%B8%89%E7%B1%BB.png)
---
* (聚类：4类)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/100_%E5%9B%9B%E7%B1%BB.png)
---
* (聚类：5类)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/100_%E4%BA%94%E7%B1%BB.png)
---

### 如何判断k值是否合适
##### 常用方法为elbow method，x轴为聚类的数量，y轴为WSS（within cluster sum of squares）也就是各个点到cluster中心的距离的平方的和。

##### 当k=1时，表示全部点聚合为一类，然后wss就表示wss就是每个点到中心点的距离的总和
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/wss%E8%A7%A3%E9%87%8A_1.jpg)

##### 当k=2、3、...时，wss就表示不同类别的点分别到其类中心的距离的总和
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/wss%E8%A7%A3%E9%87%8A_2.jpg)

##### 于是可以绘制出类别数与wss的折现图，通过观察其转折部分，可以判断k值是否合适。
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/wss%E8%82%98%E5%AD%90%E5%9B%BE.jpg)

#### 通过给出的分类截图可以看出，随着类数的增加，wss距离不断缩小，于是除了题目中要求的2-5类，我还添加了6-13类时的wss距离，以绘制类别-wss折线图。

#### 以下给出绘制的x、y轴数据（绘制代码十分简单，故不放上来）
##### x = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
##### y = y = [990, 950.1, 884.895, 806.211, 760.121, 725.274, 670.831, 650.543, 645.611, 610.432, 584.23, 557.352, 549.938,543.846]
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/wss%E6%8A%98%E7%BA%BF%E5%9B%BE.png)
### 可以看见，在图中两个红框中的k值是比较适合的。
---

### 小题2：由老师给出测试数据，进行测试，并画出可视化出散点图，类中心，类半径，并分析聚为几类合适。
#### 方法和原理与小题1类似，只是将数据变成给定的测试数据，同时绘制出散点图等。

#### k-means.cpp使用方法：
* 在题目1时，将给定测试数据注释掉，剩下读取文件代码部分，以获取正确的数据，同时要修改m、n、c。
* 在题目2时，将读取文件代码部分注释掉，剩下指定数据，以获取正确的数据，同时要修改m、n、c。

#### 使用步骤：
* 1、确定k值，在k-menas.cpp中修改k值后运行cpp文件
* 2、在draw.py中修改k值，进行绘制（绘制代码详见categroy_draw.py文件）
---
#### 结果展示：
##### 图例说明：不同类别的点用不同的颜色表示，其中测试数据（2，6）使用x符号表示，其颜色代表其类别
* (聚类：2类)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/20_2%E7%B1%BB.png)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/20_2%E7%B1%BB_%E5%8F%AF%E8%A7%86%E5%8C%96.png)
---
* (聚类：3类)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/20_3%E7%B1%BB.png)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/20_3%E7%B1%BB_%E5%8F%AF%E8%A7%86%E5%8C%96.png)
---
* (聚类：4类)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/20_4%E7%B1%BB.png)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/20_4%E7%B1%BB_%E5%8F%AF%E8%A7%86%E5%8C%96.png)
---
* (聚类：5类)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/20_5%E7%B1%BB.png)
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/20_5%E7%B1%BB_%E5%8F%AF%E8%A7%86%E5%8C%96.png)
---
#### 分析聚类k值的合理性：根据k值不同情况下，聚类样本的wss值总和可以发现，在红色标出的两个拐点处发生转折，说明其k值较为合理。
给出测绘数据wss图的数据：
* x = [1, 2, 3, 4, 5, 6, 7, 8, 9]
* y = [138.98, 36.6251, 30.4537, 23.5686, 16.1588, 9.38101, 8.78766, 6.67928, 6.8736]
* ![Image text](https://github.com/Chimaeras/Data_Mining_ex/blob/master/img/%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE_wss.png)

---
### 问题补充说明：
#### 1、在运行cpp文件，进行聚类迭代时，有时候会出现失败状况（具体表现为：数值出现-nan）。原因是初始生成的聚类中心较为不合理，导致在迭代的过程中，会出现所有聚类样本都不属于该类的状况。解决办法为重新运行，直至重新生成合理k中心。
#### 2、category_deaw.py文件的运行无问题，但是有可能会出现警告。原因在于绘制类圆圈的时候，绘制方法为根据圆点和半径生成一串密集的x、y轴数据点，不断绘制这些数据点以在视觉上形成圆。绘制点的数据精度可能会超出数据的精度范围，所以发生警告。但由于其不影响图片的绘制，且不属于k-menas算法的核心内容，故不予理会。
#### 3、图片中绘制的圆是椭圆形状，这是由于x轴和y轴的值域不相等导致的。

---

## 实验四 《逻辑二分回归》
### 题目
#### 学习sigmoid函数和逻辑回归算法。将实验三.2中的样例数据用聚类的结果打标签{0，1}，并用逻辑回归模型拟合。
* 1. 学习并画出sigmoid函数
* 2. 设计梯度下降算法，实现逻辑回归模型的学习过程。
* 3. 根据给定数据（实验三.2），用梯度下降算法进行数据拟合，并用学习好的模型对(2,6)分类。

---

### 实验准备:
* 本实验运用到了实验三中保存的点数据文件（point.txt）和分类数据文件(cate.txt)
* 本实验的前提是将测试点数据分为两类。

---

### 实验原理：
#### （1）逻辑回归二分类假设：
* 将测试数据点（二维）的数据映射到{0，1}，我们运用函数h_θ（x）：![Image text]()，
其中θ是m维得回归参数：![Image text]()，g为sigmoid激活函数：![Image text]()。
#### （2）θ参数估计：
* 训练：通过已知分类（或标签）的数据，求得一个模型（或分离器），然后使用这个模型对未知标签的数据打上标签（或者对其进行分类）。
* 参数估计：使用样本（即已知分类的数据），进行一系列的估算，得到θ。
#### （3）设置代价函数：
* 对于实际为1的点，若分类为0，则通过代价函数为其添加附加代价，使得函数往代价小的方向迭代。
#### （4）迭代学习θ参数，直到参数θ收敛：
* 梯度下降学习法：关于求解函数的最优解（极大值和极小值），在数学中我们一般会对函数求导，然后让导数等于0，获得方程，然后通过解方程直接得到结果。但是在机器学习中，我们的函数常常是多维高阶的，得到导数为0的方程后很难直接求解（有些时候甚至不能求解），所以就需要通过梯度下降法来获得结果。
* 对于每一次迭代后的θ参数，我们可以对当前函数进行求导，以确定下一步更新方向，每一次迭代时，在更新方向上进行更新步长（也可以叫学习率）为α的学习过程。
* 不断重复上述过程，直到参数收敛。

---

### 实验推导：
#### 以下完成对逻辑回归公式的推导。
![Image text]()

---

### 实验结果：
![Image text]()

---

### 实验方法分析：
| 名称 | m | 优点 | 缺点 |
| ---- | ---- | ---- | ---- |
| 批量梯度下降（BGD） | 样本总数 | 考虑所有样本，容易获得全局最优 | 训练速度慢 |
| 随机梯度下降（SGD） | 1 | 训练速度快 | 准确度下降，并不是全局最优 |
| 部分梯度下降（MBGD）  | 样本的一部分 | both | both|

---

### 实验总结：
#### 本实验的难点在于理解逻辑二分回归中的代价函数以及梯度下降学习，其中包含等式的推导与转换等过程，但只要理解了实验原理之后，程序的编写就较为容易了。
