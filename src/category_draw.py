# -*- coding: utf-8 -*-
# @Time    : 2020/12/13 11:04
# @Author  : JOker
# @Site    : 
# @File    : draw.py
# @Software: PyCharm
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties

m = 20  # 行
n = 2  # 列
c = 3  # 分类数量

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

    # 存储颜色
    color = ['red', 'blue', 'pink', 'yellow', 'green']
    # 绘制源数据点
    # 不同类别的点放在不同数组中
    x = [[] for i in range(c)]
    y = [[] for i in range(c)]
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
    plt.scatter(point_x, point_y, color='black', label="中心点")

    # for i in range(c):
    #     plt.contour(point_x[i], point_y[i])
    plt.legend()
    plt.show()
