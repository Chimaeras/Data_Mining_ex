# -*- coding: utf-8 -*-
# @Time    : 2020/12/13 11:04
# @Author  : Joker
# @Site    : 
# @File    : draw.py
# @Software: PyCharm

import numpy as np
import matplotlib.pyplot as plt

m = 20  # 行
n = 2  # 列
c = 5  # 分类数量
test_point = [2, 6]  # 测试点数据

if __name__ == '__main__':
    # 文件地址
    path = "C:/Users/99259/source/repos/k-means/k-means/point.txt"
    # 文件对象
    file = []
    # 源点数组
    data = np.zeros((m + c, n))
    # 读取数组文件
    for line in open(path, "r"):
        # 取出换行符
        line = line.strip()
        file.append(line)
    # 将文件数据存储进数组中
    for i in range(m + c):
        for j in range(n):
            data[i][j] = float(file[i].split(' ')[j])

    # 同上面操作一样，不过处理的是分类数组
    cate_path = "C:/Users/99259/source/repos/k-means/k-means/category.txt"
    cate_file = []
    cate = np.zeros(m)
    for line in open(cate_path, 'r'):
        # 取出换行符
        line = line.strip()
        cate_file.append(line)
    for i in range(m):
        cate[i] = int(cate_file[i])

    # 解决中文乱码
    plt.rcParams['font.sans-serif'] = ['SimHei']
    plt.rcParams['axes.unicode_minus'] = False
    plt.title('数据点分布')

    # 存储颜色数组
    color = ['red', 'blue', 'pink', 'yellow', 'green', 'purple']

    # 绘制源数据点
    # 不同类别的点放在不同数组中
    x = [[] for i in range(c)]  # x轴数据
    y = [[] for i in range(c)]  # y轴数据
    for i in range(m):
        for j in range(c):
            if cate[i] == j:
                x[j].append(data[i][0])
                y[j].append(data[i][1])

    # 分别绘制不同类别的点
    for i in range(c):
        plt.scatter(x[i], y[i], color=color[i], label=("类别%d" % (i + 1)))

    # 绘制中心点
    point_x = []
    point_y = []
    for i in range(c):
        point_x.append([data[m + i][0]])
        point_y.append([data[m + i][1]])
    plt.scatter(point_x, point_y, color='black', marker='*', label="中心点")

    # 存储不同类的半径
    radius = np.zeros(c)
    # 遍历类别
    for i in range(c):
        # 记录x轴和y轴的最大值r
        # 如果是一个点属于一类 就令其半径为0.2
        max_dis = 0.2
        # 遍历点
        for j in range(len(x[i])):
            dis_x = x[i][j] - point_x[i]
            dis_y = y[i][j] - point_y[i]
            # 计算欧式距离
            dis = np.sqrt(pow(dis_x, 2) + pow(dis_y, 2))
            # 更新最大半径
            if dis > max_dis:
                max_dis = dis
        # 最大值最为该类的类半径
        radius[i] = max_dis

    # 分别绘制不同类的类半径
    for i in range(c):
        # 定义圆心和半径
        x = point_x[i][0]
        y = point_y[i][0]
        r = radius[i]

        # 点的横坐标为a
        a = np.arange(x - r, x + r, 0.0001)
        # 点的纵坐标为b
        b = np.sqrt(pow(r, 2) - pow((a - x), 2))
        # 绘制上半部分
        plt.plot(a, y + b, color=color[i], linestyle='-')
        # 绘制下半部分
        plt.plot(a, y - b, color=color[i], linestyle='-')

    # t暂时存储（2，6）的类别
    # 可以在开头改变测试点的坐标
    t = 0
    # 设置一个很大的值
    d = 100
    # 遍历类别
    for i in range(c):
        # 计算测试点到每个中心的距离
        dis = np.sqrt(pow((test_point[0] - point_x[i][0]), 2) + pow((test_point[1] - point_y[i][0]), 2))
        # 寻找最小的距离
        if dis < d:
            d = dis
            t = i
    # 绘制测试点
    plt.scatter(test_point[0], test_point[1], c=color[t], marker='x', label='(2,6)')

    plt.legend()
    # 保存图片
    plt.savefig(r'C:/Users/99259/source/repos/k-means/k-means/show.png', dpi=300)
    plt.show()
