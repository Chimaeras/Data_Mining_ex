# -*- coding: utf-8 -*-
# @Time    : 2020/11/9 15:39
# @Author  : Joker
# @Site    : 
# @File    : 1.py
# @Software: PyCharm


import matplotlib.pyplot as plt
import numpy
import numpy as np


def show(data):
    # cmap = plt.cm.get_cmap('Reds')  # 更多颜色: http://matplotlib.org/examples/color/colormaps_reference.html
    # plt.imshow(confusion_mat, cmap=cmap)
    plt.imshow(data)
    # 图片名字
    plt.title('Confusion matrix')
    # 自动生成渐变色条
    plt.colorbar()
    # 生成对应长度的list
    range = np.arange(len(data))
    # 横轴长度以及标注
    plt.xticks(range, '0')
    # 纵轴长度以及标注
    plt.yticks(range, '0')
    # 纵轴
    plt.ylabel('Row')
    # 横轴
    plt.xlabel('Col')
    # 展示
    plt.show()


if __name__ == '__main__':
    # 读取关系矩阵，并存为数组
    my_data = numpy.loadtxt('学生关系矩阵.txt')
    # 展示混淆矩阵
    show(my_data)
