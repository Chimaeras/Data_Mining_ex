import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;


public class data_mining_1 {

    /*************************************工具类函数*******************************************/

    //将txt中的身高转换为cm
    public static int change_height(String a) {
        //将string转换成float
        float temp = Float.parseFloat(a);
        //将单位转换成cm，即放大100倍
        temp = temp * 100;
        //返回int
        return (int) temp;
    }

    //将txt中的体育成绩转换成英文标准
    public static String change_constitution(String a) {
        //将中文评价标准转换为英文标准
        if (a.equals("优秀")) {
            a = "excellent";
        }
        if (a.equals("良好")) {
            a = "good";
        }
        if (a.equals("一般")) {
            a = "general";
        }
        if (a.equals("差")) {
            a = "bad";
        }
        return a;
    }

    //将txt文件中的male/female转换成boy/girl
    public static String change_gender(String a) {
        if (a.equals("male")) {
            a = "boy";
        }
        if (a.equals("female")) {
            a = "girl";
        }
        return a;
    }

    //量化体育成绩
    //量化方法1：不同等级转换成固定数值
    public static int quanfity_constitution_1(String a) {
        int score = 0;
        if (a.equals("excellent")) {
            score = 95;
        }
        if (a.equals("good")) {
            score = 85;
        }
        if (a.equals("general")) {
            score = 75;
        }
        if (a.equals("bad")) {
            score = 60;
        }
        return score;
    }

    //量化方法2：不同等级转换成不同范围内的随机数值
    public static int quanfity_constitution_2(String a) {
        int score = 0;
        if (a.equals("excellent")) {
            score = ThreadLocalRandom.current().nextInt(90, 100);
        }
        if (a.equals("good")) {
            score = ThreadLocalRandom.current().nextInt(80, 90);
        }
        if (a.equals("general")) {
            score = ThreadLocalRandom.current().nextInt(60, 80);
        }
        if (a.equals("bad")) {
            score = ThreadLocalRandom.current().nextInt(0, 60);
        }
        return score;
    }

    //求均值函数
    //传入一个一维数组，返回均值
    public static double average(int[] list) {
        //总和
        double sum = 0;
        for (int ints : list) {
            sum += ints;
        }
        //求均值
        return sum / list.length;
    }

    //求样本标准差函数
    //传入一个数组，返回样本标准差
    public static double standard(int[] list) {
        double sum = 0;
        double ave = average(list);
        for (int ints : list) {
            //(x-average(X)的平方)
            sum += Math.pow((ints - ave), 2);
        }
        return Math.sqrt(sum / (list.length - 1));
    }

    //求相关性函数
    //传入两个一维数组，返回相关系数
    public static double corelation(int[] list_1, int[] list_2) {
        //统计当前列的平均值和标准差
        double ave_1 = average(list_1);
        double std_1 = standard(list_1);

        //第九列为学生的体育成绩
        double ave_2 = average(list_2);
        double std_2 = standard(list_2);

        //计算相关性
        //cov=累加((x-ace(x))*(y-ave(y)))/n-1
        double cov = 0;
        for (int i = 0; i < list_1.length; i++) {
            for (int j = 0; j < list_2.length; j++) {
                cov += (list_1[i] - ave_1) * (list_2[j] - ave_2);
            }
        }
        //相关系数公式：cov(x,y)/(std(x)*std(y))
        return cov / ((list_1.length - 1) * std_1 * std_2);
    }

    /*************************************数据初始化*******************************************/

    //连接数据库
    public static Connection getConnect() throws SQLException {
        //连接类，初始化一个连接对象
        Connection connection = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取与目标数据库的连接，参数（"jdbc:mysql://localhost/数据库名"，"root"，"数据库密码"；
            connection = DriverManager.getConnection("jdbc:mysql://localhost/data_mining_ex?serverTimezone=UTC",
                    "root", "130766");
        } catch (ClassNotFoundException e) {
            //捕获错误
            e.printStackTrace();
        }
        //返回连接对象
        return connection;
    }

    //对数据库进行查询，读取数据库源的学生数据
    public static void search(Student[] list) throws SQLException {
        //得到与目标数据库连接的对象
        Connection con = getConnect();
        //返回结果类对象 res
        ResultSet res = null;
        //把sql语句发送到数据库，得到预编译类的对象，这句话是选择该student表里的所有数据
        PreparedStatement prepar = con.prepareStatement("select *from student_table");
        res = prepar.executeQuery();

        //将得到的数据库响应的查询结果存放在ResultSet对象中
        while (res.next()) {
            //初始化学生的序列
            //因为数据库中的数据可能存在缺失
            //所以要使list[i]对应学生的id
            //list下标范围为（0-99），所以要id-1
            int i = res.getInt("id") - 1;
            //初始化学生对象
            list[i] = new Student();

            if (list[i].name == null) {
                //进行赋值
                list[i].ID = res.getInt("id") + 202000;
                list[i].name = res.getString("name");
                list[i].city = res.getString("city");
                list[i].gender = res.getString("gender");
                list[i].height = res.getInt("height");
                list[i].c1 = res.getInt("c1");
                list[i].c2 = res.getInt("c2");
                list[i].c3 = res.getInt("c3");
                list[i].c4 = res.getInt("c4");
                list[i].c5 = res.getInt("c5");
                list[i].c6 = res.getInt("c6");
                list[i].c7 = res.getInt("c7");
                list[i].c8 = res.getInt("c8");
                list[i].c9 = res.getInt("c9");
                //体育成绩有可能缺失,如果不缺失，写入数组
                if (res.getString("constitution") != null) {
                    list[i].constitution = res.getString("constitution");
                    //c10为体育成绩转换后的数值成绩
                    //这里采取了量化方法2
                    list[i].c10 = quanfity_constitution_1(list[i].constitution);
                }

            }
        }
    }

    //读取学生数据.txt文件，并写入学生数组
    public static void read(Student[] list) throws IOException {
        //读取类对象br
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\99259\\Desktop\\学生数据.txt"));
        //当前行对象
        String line = br.readLine();
        while (line != null) {

            //读取当前行数据，忽略空格
            String[] numbers = line.split("\\s+");

            //txt文件中可能存在缺失
            //所以要使当前行的数据对应到正确的下标
            //txt文件中ID是以202001开始
            int j = Integer.parseInt(numbers[0]) - 202001;

            //我们先读取数据库的数据，再读取txt的数据
            //对缺失数据进行补充和完善

            list[j].ID = Integer.parseInt(numbers[0]);
            list[j].name = numbers[1];
            list[j].city = numbers[2];
            list[j].gender = change_gender(numbers[3]);
            list[j].height = change_height(numbers[4]);
            list[j].c1 = Integer.parseInt(numbers[5]);
            list[j].c2 = Integer.parseInt(numbers[6]);
            list[j].c3 = Integer.parseInt(numbers[7]);
            list[j].c4 = Integer.parseInt(numbers[8]);
            list[j].c5 = Integer.parseInt(numbers[9]);
            list[j].c6 = Integer.parseInt(numbers[10]);
            list[j].c7 = Integer.parseInt(numbers[11]);
            list[j].c8 = Integer.parseInt(numbers[12]);
            list[j].c9 = Integer.parseInt(numbers[13]);
            if (numbers.length == 16) {
                list[j].constitution = change_constitution(numbers[15]);
                list[j].c10 = quanfity_constitution_1(list[j].constitution);
            }

            //更新line
            line = br.readLine();
        }
        br.close();
    }

    //初始化分数矩阵
    public static void initialize(Student[] list, int[][] score) {
        for (int i = 0; i < 100; i++) {
            score[i][0] = list[i].c1;
            score[i][1] = list[i].c2;
            score[i][2] = list[i].c3;
            score[i][3] = list[i].c4;
            score[i][4] = list[i].c5;
            score[i][5] = list[i].c6 * 10;
            score[i][6] = list[i].c7 * 10;
            score[i][7] = list[i].c8 * 10;
            score[i][8] = list[i].c9 * 10;
            score[i][9] = list[i].c10;
        }
    }

    /*************************************数据展示*******************************************/

    //展示学生数组函数
    public static void show_list(Student[] list) {
        for (int i = 0; i < 100; i++) {
            System.out.print("ID: " + list[i].ID + " ");
            System.out.print("name: " + list[i].name + " ");
            System.out.print("city: " + list[i].city + " ");
            System.out.print("gender: " + list[i].gender + " ");
            System.out.print("height: " + list[i].height + " ");
            System.out.print("c1: " + list[i].c1 + " ");
            System.out.print("c2: " + list[i].c2 + " ");
            System.out.print("c3: " + list[i].c3 + " ");
            System.out.print("c4: " + list[i].c4 + " ");
            System.out.print("c5: " + list[i].c5 + " ");
            System.out.print("c6: " + list[i].c6 + " ");
            System.out.print("c7: " + list[i].c7 + " ");
            System.out.print("c8: " + list[i].c8 + " ");
            System.out.print("c9: " + list[i].c9 + " ");
            System.out.println("constitution: " + list[i].constitution);
        }
    }

    //展示成绩矩阵函数
    public static void show_score(int[][] score) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(score[i][j] + " ");
            }
            System.out.println();
        }
    }

    /************************************实验问题求解********************************************/

    //实验1

    //Q1：学生中家乡在Beijing的所有课程的平均成绩
    public static void ex1_Q1(Student[] list) {
        //定义科目的总成绩
        int sum = 0;
        int count = 0;

        //遍历查找city为北京的同学
        for (int i = 0; i < 100; i++) {
            if (list[i].city.equals("Beijing")) {
                //统计人数+1
                count++;
                //累加每门科目的成绩
                //c1-c5是百分制
                sum += list[i].c1;
                sum += list[i].c2;
                sum += list[i].c3;
                sum += list[i].c4;
                sum += list[i].c5;
                //c6-c10是十分制，将其转换成百分制
                sum += list[i].c6 * 10;
                sum += list[i].c7 * 10;
                sum += list[i].c8 * 10;
                sum += list[i].c9 * 10;
                sum += list[i].c10;
            }
        }
        //平均数=总分数/（课程数目*人数）
        float average = (float) (sum) / (10 * count);
        System.out.println("学生中家乡在Beijing的所有课程的平均成绩：" + average);
    }

    //Q2：学生中家乡在广州，课程1在80分以上，且课程9在9分以上的男同学的数量
    public static void ex1_Q2(Student[] list) {
        //统计人数
        int count = 0;
        for (int i = 0; i < 100; i++) {
            //同时满足四个条件
            if (list[i].city.equals("Guangzhou") && list[i].c1 >= 80 && list[i].c9 >= 9 && list[i].gender.equals("boy")) {
                count++;
            }
        }

        //这里提供一种在sql思想上的优化
        //查询时，尽可能先查询满足条件项较少的列
        //在这里体现为：先查询c9课程分数>=9的同学，因为满足该条件的同学较少
        //接着查找c1课程分数>=80的同学，依次往后推
        //这里由于电脑本身运算速度比较快，且数据规模较小，所以在运行时间上体现不出区别
//        for (int i = 0; i < 100; i++) {
//            if (list[i].c10 >= 9) {
//                if (list[i].c1 >= 80) {
//                    if (list[i].gender.equals("boy")) {
//                        if (list[i].city.equals("Guangzhou")) {
//                            count++;
//                        }
//                    }
//
//                }
//            }
//        }
        System.out.println("学生中家乡在广州，课程1在80分以上，且课程9在9分以上的男同学的数量：" + count);
    }

    //Q3：比较广州和上海两地女生的平均体能测试成绩，哪个地区的更强些？
    public static void ex1_Q3(Student[] list) {
        //统计人数
        int gz_count = 0;
        int sh_count = 0;

        //量化学生的体育成绩的方法：
        //excellent(90-100);good(80-90);general(60-80);bad(0-60)
        //excellent:95;good:85;general:75;bad:60
        //可从初始化读取数据中选择量化方法

        float gz_score = 0;
        float sh_score = 0;


        for (Student student : list) {
            if (student.gender.equals("girl")) {
                if (student.city.equals("Guangzhou")) {
                    gz_count++;
                    gz_score += student.c10;
                }
                if (student.city.equals("Shanghai")) {
                    sh_count++;
                    sh_score += student.c10;
                }
            }
        }

        gz_score = gz_score / gz_count;
        sh_score = sh_score / sh_count;

        System.out.println("广州女同学平均体测成绩：" + gz_score);
        System.out.println("上海女同学平均体测成绩：" + sh_score);
        System.out.println((gz_score > sh_score ? "广州" : "上海") + "地区的女生平均体能更强");
    }

    //Q4: 学习成绩和体能测试成绩，两者的相关性是多少？
    //list为学生数据,index为所求相关性的科目（c1-c9）
    public static void ex1_Q4(int[][] score) {
        //用临时数组将每个学生的课程分数按照行的方式存储
        //temp=[10][100]
        int[][] t = new int[score[0].length][score.length];
        //遍历学生
        for (int i = 0; i < score.length; i++) {
            //遍历科目
            for (int j = 0; j < score[0].length; j++) {
                t[j][i] = score[i][j];
            }
        }
        for (int i = 0; i < score[0].length - 1; i++) {
            System.out.println("成绩" + (i + 1) + "和体育成绩的相关性为" + corelation(t[i], t[9]));
        }
    }


    //实验2

    //Q1: 请以课程1成绩为x轴，体能成绩为y轴，画出散点图。
    public static void ex2_Q1(int[][] score) {

        //初始化对象
        DefaultXYDataset xydataset = new DefaultXYDataset();

        //绘制图表所需的数据集
        double[][] data = new double[2][score.length];

        //记载数据集，x轴为c1的成绩，y轴为体育成绩
        for (int i = 0; i < score.length; i++) {
            //第一门科目的成绩
            data[0][i] = score[i][0];
            //体育成绩
            data[1][i] = score[i][10];
        }

        //addSeries接受三个参数（名称，数据数组，选项数组（可省略））
        xydataset.addSeries("student", data);

        //设置图表（散点图）的样式，例如标题，x轴名称，y轴名称等
        JFreeChart chart = ChartFactory.createScatterPlot("score_relationship", "c1", "constitution",
                xydataset,
                //绘图方向：垂直
                PlotOrientation.VERTICAL,
                //图例
                true,
                //工具提示
                false,
                //是否生成外部网址
                false);

        //初始化图标框架对象（外层样式）参数分别为（名称，图标，是否可以放大缩小）
        ChartFrame frame = new ChartFrame("散点图", chart, true);
        //初始化xy轴样式对象
        XYPlot xyplot = (XYPlot) chart.getPlot();
        //设置背景颜色
        xyplot.setBackgroundPaint(new Color(255, 253, 246));
        //获取坐标轴的轴域
        ValueAxis va = xyplot.getDomainAxis(0);
        // 坐标轴粗细
        va.setAxisLineStroke(new BasicStroke(1.5f));
        // 坐标轴颜色
        va.setAxisLinePaint(new Color(215, 215, 215));
        // 边框粗细
        xyplot.setOutlineStroke(new BasicStroke(1.5f));
        // 坐标轴标题颜色
        va.setLabelPaint(new Color(10, 10, 10));
        // 坐标轴标尺值颜色
        va.setTickLabelPaint(new Color(102, 102, 102));
        //根据组件的size和窗口的布局来确定frame的最佳大小
        frame.pack();
        //窗口可视化
        frame.setVisible(true);
    }

    //Q2：以5分为间隔，画出课程1的成绩直方图。
    public static void ex2_Q2(int[][] score) throws IOException {

        //记录每个分数段的人数
        int[] count_c1 = new int[20];
        for (int[] ints : score) {
            count_c1[(ints[0] - 1) / 5]++;
        }

        //柱状图数据对象
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //添加数据
        for (int i = 0; i < count_c1.length; i++) {
            String string = "<=" + (i + 1) * 5;
            dataset.addValue(count_c1[i], "", string);
        }

        //生成柱状图
        JFreeChart chart = ChartFactory.createBarChart3D(
                "科目1成绩-人数",
                "分数",//X轴的标签
                "人数",//Y轴的标签
                dataset, //图标显示的数据集合
                PlotOrientation.VERTICAL, //图像的显示形式（水平或者垂直）
                false,//是否显示子标题
                false,//是否生成提示的标签
                false); //是否生成URL链接

        /*
         * 处理图形上的乱码
         */

        //处理主标题的乱码
        chart.getTitle().setFont(new Font("黑体", Font.BOLD, 18));

        //获取图表区域对象
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();

        //获取X轴的对象
        CategoryAxis3D categoryAxis3D = (CategoryAxis3D) categoryPlot.getDomainAxis();

        //获取Y轴的对象
        NumberAxis3D numberAxis3D = (NumberAxis3D) categoryPlot.getRangeAxis();

        //处理X轴上的乱码
        categoryAxis3D.setTickLabelFont(new Font("黑体", Font.BOLD, 8));

        //处理X轴外的乱码
        categoryAxis3D.setLabelFont(new Font("黑体", Font.BOLD, 10));

        //处理Y轴上的乱码
        numberAxis3D.setTickLabelFont(new Font("黑体", Font.BOLD, 10));

        //处理Y轴外的乱码
        numberAxis3D.setLabelFont(new Font("黑体", Font.BOLD, 10));

        //自定义Y轴上显示的刻度，间隔为1
        numberAxis3D.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(1);
        numberAxis3D.setTickUnit(unit);

        //获取绘图区域对象
        BarRenderer3D barRenderer3D = (BarRenderer3D) categoryPlot.getRenderer();

        //设置柱形图的宽度
        barRenderer3D.setMaximumBarWidth(0.03);

        //在图形上显示数字
        barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barRenderer3D.setBaseItemLabelsVisible(true);
        barRenderer3D.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 10));

        //存储绘制的直方图
        File file = new File("e:\\成绩直方图.png");
        try {
            ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Q3：对每门成绩进行z-score归一化，得到归一化的数据矩阵。
    //公式：(x-average(x))/总体标准差
    public static void ex2_Q3(int[][] score) {

        //定义一个double的返回数组，长度大小和score一样
        double[][] res = new double[score.length][score[0].length];

        //用临时数组将每个学生的课程分数按照行的方式存储
        //temp=[10][100]
        int[][] temp = new int[score[0].length][score.length];
        //遍历学生
        for (int i = 0; i < score.length; i++) {
            //遍历科目
            for (int j = 0; j < score[0].length; j++) {
                temp[j][i] = score[i][j];
            }
        }

        //遍历每门科目
        for (int i = 0; i < score[0].length; i++) {
            //遍历每个学生
            for (int j = 0; j < score.length; j++) {
                //z-score规范化：
                //z=(x-x的均值)/总体标准差
                res[j][i] = (score[j][i] - average(temp[i])) / standard(temp[i]);
            }
        }

        //展示归一化后的数据矩阵
        for (double[] re : res) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(re[j] + " ");
            }
            System.out.println();
        }
    }

    //Q4：计算协相关矩阵，并画出混淆矩阵。
    public static void ex2_Q4(int[][] score) {
        double[][] cor_list = new double[score.length][score[0].length];

        //相当于score的转置矩阵 cow[10][100]
        int[][] cow = new int[score[0].length][score.length];
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[0].length; j++) {
                cow[j][i] = score[i][j];
            }
        }

        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[0].length; j++) {
                cor_list[i][j] = corelation(cow[j], score[i]);
            }
        }
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[0].length; j++) {
                System.out.print(cor_list[i][j] + "    ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws SQLException, IOException {

        //初始化对象数组
        Student[] list = new Student[100];
        for (int i = 0; i < list.length; i++) {
            //初始化对象
            list[i] = new Student();
        }
        //从数据库进行写入数据
        search(list);
        //从txt进行写入数据
        read(list);

        //定义成绩矩阵
        int[][] score = new int[list.length][10];
        //初始化成绩矩阵
        initialize(list, score);

        //展示学生数据
        //show_list(list);
        //展示成绩矩阵
//        show_score(score);

        //实验问题求解
//        ex1_Q1(list);
//        System.out.println();
//        ex1_Q2(list);
//        System.out.println();
//        ex1_Q3(list);
//        System.out.println();
        ex1_Q4(score);


//        ex2_Q1(score);
//        ex2_Q2(score);
//        ex2_Q3(score);
//        ex2_Q4(score);

    }
}

