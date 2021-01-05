# -*- coding: utf-8 -*-
# @Time    : 2021/1/4 12:18
# @Author  : Joker
# @Site    : 
# @File    : logistic.py
# @Software: PyCharm


import numpy as np
import copy
import matplotlib.pyplot as plt

m = 20  # 行
n = 2  # 列
c = 2  # 分类数量


# 求平均值
def average(array):
    s = 0
    for i in range(len(array)):
        s += array[i]
    return s / len(array)


# sigmoid函数
def sigmoid(x):
    return 1 / (1 + np.exp(-x))


# 参数为（待预测分类的点，θ参数）
# 该函数将多维数据映射到数值上
def h(point, O):
    t = 0
    for i in range(len(point)):
        t += point[i] * O[i]
    return t


# 读取测试点文件
def read_data(filepath):
    # 文件对象
    files = []
    # 源点数组
    array = np.zeros((m + c, n))
    # 读取数组文件
    for lines in open(filepath, "r"):
        # 取出换行符
        lines = lines.strip()
        files.append(lines)
    # 将文件数据存储进数组中
    for i in range(m + c):
        for j in range(n):
            array[i][j] = float(files[i].split(' ')[j])

    return array


# 读取分类点文件
def read_cate(filepath):
    cate_file = []
    array = [0. for _ in range(m)]
    for line in open(filepath, 'r'):
        # 取出换行符
        line = line.strip()
        cate_file.append(line)
    for i in range(m):
        array[i] = int(cate_file[i])

    return array


if __name__ == '__main__':
    # 读取点数据
    path = "C:/Users/99259/source/repos/k-means/k-means/point.txt"
    data = read_data(path)

    # 读取分类数据
    cate_path = "C:/Users/99259/source/repos/k-means/k-means/category.txt"
    cate = read_cate(cate_path)

    # 定义测试数据点
    test_point = [2, 6]

    # 这里用cita表示θ,初始值均为1
    cita = [1, 1]
    # 定义一个学习率α,这里用a表示α
    a = 0.0001
    # 记录迭代次数
    time = 0
    # res记录上一次迭代时的参数θ，用来判断是否收敛
    res = [0, 0]
    # 训练过程，进行参数θ的估计
    while res != cita:
        # 开头时为res赋值
        res = copy.deepcopy(cita)
        # 遍历两个维度
        for k in range(c):
            # 临时变量存储数值
            t = 0
            # 遍历每个点
            for i in range(m):
                # 求累和
                t += (h(data[i], cita) - cate[i]) * data[i][k]
            # 除于训练集数量
            t /= m
            # 乘于学习步长
            t *= a
            # 迭代cita
            cita[k] = cita[k] - t
        # 次数+1
        time += 1

    x = [0 for _ in range(m)]
    y = [0 for _ in range(m)]

    # 存储绘制图片的点数据
    for i in range(m):
        x[i] = h(data[i], cita)
        y[i] = sigmoid(h(data[i], cita))

    # 计算x轴的均值
    ave = average(x)
    # 将x的值偏移到以0为中轴的部分
    for i in range(m):
        x[i] -= ave

    for i in range(m):
        print("点", i, ":", data[i])
        print("类别", cate[i])
        print("x(h):", x[i])
        print("y(sig):", y[i])
        print('\n')

    # 测试点[2,6]的坐标值
    x_test = h(test_point, cita)
    y_test = sigmoid(h(test_point, cita))
    # 偏移测试点[2,6]
    x_test -= ave
    print("次数：", time)
    print("x(h):：", cita)
    print("y(sig):", x_test)
    print(test_point, ":", y_test)
    # 预测类别 位于中轴线右边为1，左边为0
    if x_test > 0:
        print("测试点类别为：1")
    else:
        print("测试点类别为：0")
    print('\n')

    # 不同类别的点用不同颜色画
    color = ['red', 'blue']
    # 绘制测试数据点
    for i in range(m):
        plt.scatter(x[i], y[i], c=color[int(cate[i])])
    # 绘制待分类点
    plt.scatter(x_test, y_test, c='black')
    # 将点排序后进行连接
    x.sort()
    y.sort()
    # 连线
    plt.plot(x, y, linewidth=1)
    # 划分中线
    plt.axvline(x=0, ls='-', c='pink')
    # 展示
    plt.show()
