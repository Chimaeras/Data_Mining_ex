#include<iostream>
#include <string>
#include <vector>
#include <stdlib.h>
#include <algorithm>
#include <cmath>
#include <fstream>  //�ļ����⺯��

using namespace std;

//ȫ�ֶ������ݵع�ģ��С
//mΪ�У�nΪ��
static int m = 100;
static int n = 10;

int main() {

    //�������ݶ�ά���飬�洢�ļ��е�����
    //��ģΪ[m][n]
    vector<vector<double>> array(m);
    for (int i = 0; i < m; i++)
    {
        array[i].resize(n);
    }
    //��������ά���飬�洢ÿ�����ݵ����
    vector<vector<double>> category(m);
    for (int i = 0; i < m; i++)
    {
        category[i].resize(n);
    }

    //�����ļ���ַ
    string path = "D:\\Java_project\\Data_Mining_ex_1\\z-score.txt";
    //���ļ�
    ifstream in(path);
    //��txt����
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            in >> array[i][j];
        }
    }
    //�ر��ļ�
    in.close();
  
    //countΪ�������ĸ���
    int count = 3;
    srand(int(time(0)));
    //�������飬�洢��ʼ���ɵľ�������
    vector<double> point(count);
    //�����ԭ��������ѡcount�����Ϊ��������
    for (int i = 0; i < count; i++) {
        int a = rand() % m;
        int b = rand() % n;
        point[i] = array[a][b];
    }

    //���Ϊ��ʼ����������
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            category[i][j] = rand() % count;
        }
    }

    //same�洢���������о������ı仯�Ĵ���
    int same = 0;
    //time�洢�����Ĵ���
    int time = 0;

    //��ֹ����Ϊ�������Ĳ������仯�������������20��
    while(same!=count && time<20)
    {
        //���ÿ�ε����������
        cout << "��" << time + 1 << "�ε���������: ";
        for (int i = 0; i < count; i++) {
            cout << point[i] << " ";
        }
        cout << " \n";

        //��ʼ�����ε��������е�same
        same = 0;

        //����ÿ���㵽k���ĵľ���
        //Ϊ���ǻ�����
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //��ʼ��min
                double min = fabs(array[i][j] - point[0]);
                for (int k = 1; k < count; k++)
                {
                    //����б�ľ������ĵ��õ�ľ���С��min
                    //����min
                    //�����õ㻮��Ϊ�µ���
                    if (fabs(array[i][j] - point[k]) < min) {
                        min = fabs(array[i][j] - point[k]);
                        category[i][j] = k;
                    }
                }
            }
        }

        //����k����
        for (int k = 0; k < count; k++) {
            //��¼��һ�ε�k����
            double res_k = point[k];
            //�洢���ڸ���ĵ���ܺ�
            double sum = 0;
            //�洢���ڸ���ĵ�ĸ���
            int num = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (category[i][j] == k) {
                        //��������ڸ��࣬���¼
                        sum += array[i][j];
                        num++;
                    }
                }
            }
            //k���ĵ���Ϊ����ľ�ֵ����
            point[k] = sum / num;
            //�������������������ǰ���
            //��same++
            if (res_k == point[k]) {
                same++;
            }
        }
        time++;
    }
    
    cout << "\n" << "������ķ������:" << "\n";
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cout << category[i][j]<<" ";
        }
        cout << "\n";
    }

    return 0;
}