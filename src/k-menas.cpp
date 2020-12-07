#include<iostream>
#include <string>
#include <vector>
#include <stdlib.h>
#include <algorithm>
#include <cmath>
#include <fstream>  //文件流库函数

using namespace std;

//全局定义数据地规模大小
//m为行，n为列
static int m = 100;
static int n = 10;

int main() {

    //定义数据二维数组，存储文件中的数据
    //规模为[m][n]
    vector<vector<double>> array(m);
    for (int i = 0; i < m; i++)
    {
        array[i].resize(n);
    }
    //定义类别二维数组，存储每个数据的类别
    vector<vector<double>> category(m);
    for (int i = 0; i < m; i++)
    {
        category[i].resize(n);
    }

    //数据文件地址
    string path = "D:\\Java_project\\Data_Mining_ex_1\\z-score.txt";
    //打开文件
    ifstream in(path);
    //读txt数据
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            in >> array[i][j];
        }
    }
    //关闭文件
    in.close();
  
    //count为聚类中心个数
    int count = 3;
    srand(int(time(0)));
    //中心数组，存储初始生成的聚类中心
    vector<double> point(count);
    //随机从原数组中挑选count个点成为聚类中心
    for (int i = 0; i < count; i++) {
        int a = rand() % m;
        int b = rand() % n;
        point[i] = array[a][b];
    }

    //随机为初始数组分配类别
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            category[i][j] = rand() % count;
        }
    }

    //same存储迭代过程中聚类中心变化的次数
    int same = 0;
    //time存储迭代的次数
    int time = 0;

    //终止条件为聚类中心不发生变化或迭代次数超过20次
    while(same!=count && time<20)
    {
        //输出每次迭代后的中心
        cout << "第" << time + 1 << "次迭代的中心: ";
        for (int i = 0; i < count; i++) {
            cout << point[i] << " ";
        }
        cout << " \n";

        //初始化本次迭代过程中的same
        same = 0;

        //计算每个点到k中心的距离
        //为他们划分类
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //初始化min
                double min = fabs(array[i][j] - point[0]);
                for (int k = 1; k < count; k++)
                {
                    //如果有别的聚类中心到该点的距离小于min
                    //更新min
                    //并将该点划分为新的类
                    if (fabs(array[i][j] - point[k]) < min) {
                        min = fabs(array[i][j] - point[k]);
                        category[i][j] = k;
                    }
                }
            }
        }

        //调整k中心
        for (int k = 0; k < count; k++) {
            //记录上一次的k中心
            double res_k = point[k];
            //存储属于该类的点的总和
            double sum = 0;
            //存储属于该类的点的个数
            int num = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (category[i][j] == k) {
                        //如果点属于该类，则记录
                        sum += array[i][j];
                        num++;
                    }
                }
            }
            //k中心迭代为该类的均值中心
            point[k] = sum / num;
            //如果迭代后的中心与迭代前相等
            //则same++
            if (res_k == point[k]) {
                same++;
            }
        }
        time++;
    }
    
    cout << "\n" << "迭代后的分类矩阵:" << "\n";
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cout << category[i][j]<<" ";
        }
        cout << "\n";
    }

    return 0;
}