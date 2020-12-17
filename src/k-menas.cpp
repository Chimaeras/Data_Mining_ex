#include<iostream>
#include <string>
#include <vector>
#include <stdlib.h>
#include <algorithm>
#include <cmath>
#include <fstream>  //文件流库函数

using namespace std;

//m为行，n为列
static int m = 100;
static int n = 10;
//c为中心的数量
static int c = 14;


//计算两点之间的距离
double distance(double a[] ,double b[]) {
    double sum = 0;
    for (int i = 0; i < n; i++) {
        sum += pow((a[i] - b[i]), 2);
    }
    return pow(sum, 0.5);
}


int main() {

    double** data = new double* [m];
    for (int i = 0; i < m; i++) {
        data[i] = new double[n];
    }

    //数据文件地址
    string path = "d:\\java_project\\data_mining_ex_1\\z-score.txt";
    //打开文件
    ifstream in(path);
    //读txt数据
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            in >> data[i][j];
        }
    }
    //关闭文件
    in.close();

    /*double data[20][3] =
    {   {3.45,7.08} ,
        {1.76,7.24} ,
        {4.29,9.55} ,
        {3.35,6.65} ,
        {3.17,6.41} ,
        {3.68,5.99} ,
        {2.11,4.08} ,
        {2.58,7.10} ,
        {3.45,7.88} ,
        {6.17,5.40} ,
        {4.20,6.46} ,
        {5.87,3.87} ,
        {5.47,2.21} ,
        {5.97,3.62} ,
        {6.24,3.06} ,
        {6.89,2.41} ,
        {5.38,2.32} ,
        {5.13,2.73} ,
        {7.26,4.19} ,
        {6.32,3.62} ,
    };*/


    //定义类别二维数组，存储每个数据的类别
    vector<double> category(m);

    //count为聚类中心个数
    srand(int(time(0)));
    //中心数组，存储初始生成的聚类中心
    double** point = new double* [c];
    for (int i = 0; i < c; i++) {
        point[i] = new double[n];
    }
    //随机从原数组中挑选count个点成为聚类中心
    for (int i = 0; i < c; i++) {
        int a = rand() % m;
        for (int j = 0; j < n; j++) {
            point[i][j] = data[a][j];
        }
    }

    //随机为初始数组分配类别
    for (int i = 0; i < m; i++) {
        category[i] = rand() % c;
    }
    

    //same存储迭代过程中聚类中心变化的次数
    int same = 0;
    //time存储迭代的次数
    int time = 0;

    //终止条件为聚类中心不发生变化或迭代次数超过20次
    while(same!=c && time<20)
    {
        //输出每次迭代后的中心
        cout << "第" << time + 1 << "次迭代的中心: " << endl;
        for (int i = 0; i < c; i++) {
            cout << "中心" << (i + 1) << ":(";
            for (int j = 0; j < n; j++) {
                if (j == n - 1) {
                    cout << point[i][j];
                }
                else
                {
                    cout << point[i][j] << ",";
                }
            }
            cout << ")" << endl;
        }
        cout << " \n";

        //初始化本次迭代过程中的same
        same = 0;

        //计算每个点到k中心的距离
        //为他们划分类
        for (int i = 0; i < m; i++) {       
            //初始化min
            double min = distance(data[i], point[0]);
            for (int k = 1; k < c; k++)
            {
                //如果有别的聚类中心到该点的距离小于min
                //更新min
                //并将该点划分为新的类
                if (distance(data[i], point[k]) < min) {
                    min = distance(data[i], point[k]);
                    category[i] = k;
                }               
            }
        }

        //调整k中心
        for (int k = 0; k < c; k++) {
            //记录上一次的k中心
            double* res_k = new double[n];
            for (int i = 0; i < n; i++) {
                res_k[i] = point[k][i];
            }
            //存储属于该类的点的总和
            double *sum=new double [n];
            for (int i = 0; i < n; i++) {
                sum[i] = 0;
            }
            //存储属于该类的点的个数
            int num = 0;
            for (int i = 0; i < m; i++) {
                if (category[i] == k) {
                    for (int j = 0; j < n; j++) {
                        //如果点属于该类，则记录
                        {
                            sum[j] += data[i][j];
                        }
                    }
                    num++;
                }                                            
            }
            //k中心迭代为该类的均值中心
            for (int i = 0; i < n; i++) {
                point[k][i] = sum[i] / num;
            }
            //如果迭代后的中心与迭代前相等
            //则same++
            int flag = 0;
            for (int i = 0; i < n; i++) {
                if (res_k[i] != point[k][i]) {
                    flag = 1;
                }
            }
            if (flag==0) {
                same++;
            }
        }
        time++;
    }
    
    //输出分类矩阵
    cout << "\n" << "迭代后的分类矩阵（总共100行，转换成10*10）:" << "\n";
    for (int i = 0; i < m; i++) {
        cout << category[i] << " ";
        if (i % 10 == 9) {
            cout << "\n";
        } 
    }

    double wss = 0;
    //遍历所有点
    for (int i = 0; i < m; i++) {
        //遍历所有类别
        for (int j = 0; j < c; j++) {
            //如果点属于该类别
            if (category[i] == j) {
                //计算wss
                wss += pow(distance(data[i], point[j]), 2);
            }
        }
    }

    cout << "聚类数量为：" << c << endl;
    cout << "WSS距离为：" << wss << endl;

    ofstream write;
    //以覆盖写入的方式打开.txt文件，没有的话就创建该文件。
    write.open("point.txt", std::ios::out);
    if (!write.is_open())
        return 0;
    
    
    //将源数据点写入txt中
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            write << data[i][j] << " ";
        }
        write << endl;
    }

    //将最后更新的中心点写入文件中
    for (int i = 0; i < c; i++) {
        for (int j = 0; j < n; j++) {
            write << point[i][j] << " ";
        }
        write << endl;
    }
    write << endl;

    //关闭文件
    write.close();

    //将分类矩阵写入文件
    write.open("category.txt", std::ios::out);
    if (!write.is_open())
        return 0;

    //将分类矩阵写入文件中
    for (int i = 0; i < m; i++) {
        write << category[i] << " " << endl;
    }

    //关闭文件
    write.close();

    return 0;
}
