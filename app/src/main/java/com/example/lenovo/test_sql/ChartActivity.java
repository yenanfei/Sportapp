package com.example.lenovo.test_sql;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChartActivity extends AppCompatActivity {

    private ColumnChartView mColumnChartView;

    /*========== 数据相关 ==========*/
    private ColumnChartData mColumnChartData;               //柱状图数据
//    public String[] xValues = new String[]{"语文", "数学", "英语", "音乐", "科学", "体育"};
//
//    private LineChartView lineChart;
//    private TextView testview;
//    String[] date = {"5-23","5-22","6-22","5-23","5-22","2-22","5-22","4-22","9-22","10-22","11-22","12-22","1-22","6-22","5-23","5-22","2-22","5-22","4-22","9-22","10-22","11-22","12-22","4-22","9-22","10-22","11-22","zxc"};//X轴的标注
//    int[] score= {74,22,18,79,20,74,20,74,42,90,74,42,90,50,42,90,33,10,74,22,18,79,20,74,22,18,79,20};//图表的数据

    public final static String[] xValues = new String[]{"100m", "200m", "300m", "400m", "500m"};
    public final static float[] yValues = new float[]{25, 22, 27, 30, 33};
    private String str;
    private LineChartView lineChart;
    private TextView testview;
    String[] date = {"100m/步数","200m/步数","300m/步数","400m/步数","500m/步数"};//X轴的标注
    int[] bupin= {44,236,124,173,223};//图表的数据

    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
//            weathertest=(TextView)findViewById(R.id.weathertext);
//            System.out.println("这是主线程");
//            weathertest.setText((String)msg.obj);
        }
    };
    Response response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示另一个activity传过来的数据
        /*setContentView(R.layout.test);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String str=bundle.getString("time");
        System.out.print(str);
        testview=(TextView)findViewById(R.id.test);
        testview.setText(str);*/

        //显示图表

        setContentView(R.layout.activity_column);


        getAxisXLables();//获取折线图x轴的标注
        getAxisPoints();//获取折线图坐标点

        initView1();//折线图
        initView2();//柱状图


    }
    private void initView1() {
        lineChart = (LineChartView)findViewById(R.id.chart2);
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(false);//曲线是否平滑
        //	    line.setStrokeWidth(3);//线条的粗细，默认是3
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        //		line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示
        //	    axisX.setTextColor(Color.WHITE);  //设置字体颜色
        axisX.setTextColor(Color.parseColor("#D6D6D9"));//灰色

        //	    axisX.setName("未来几天的天气");  //表格名称
        axisX.setTextSize(11);//设置字体大小
        axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //	    data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线


        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(11);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边
        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
        lineChart.setMaxZoom((float) 3);//缩放比例
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 尼玛搞的老子好辛苦！！！见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         * 下面几句可以设置X轴数据的显示个数（x轴0-7个数据），当数据点个数小于（29）的时候，缩小到极致hellochart默认的是所有显示。当数据点个数大于（29）的时候，
         * 若不设置axisX.setMaxLabelChars(int count)这句话,则会自动适配X轴所能显示的尽量合适的数据个数。
         * 若设置axisX.setMaxLabelChars(int count)这句话,
         * 33个数据点测试，若 axisX.setMaxLabelChars(10);里面的10大于v.right= 7; 里面的7，则
         刚开始X轴显示7条数据，然后缩放的时候X轴的个数会保证大于7小于10
         若小于v.right= 7;中的7,反正我感觉是这两句都好像失效了的样子 - -!
         * 并且Y轴是根据数据的大小自动设置Y轴上限
         * 若这儿不设置 v.right= 7; 这句话，则图表刚开始就会尽可能的显示所有数据，交互性太差
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right= 7;
        lineChart.setCurrentViewport(v);
    }

    private void initView2() {
        mColumnChartView = (ColumnChartView) findViewById(R.id.chart);
        //mColumnChartView.setOnValueTouchListener(new ValueTouchListener());

         /*========== 柱状图数据填充 ==========*/
        List<Column> columnList = new ArrayList<>(); //柱子列 表
        List<SubcolumnValue> subcolumnValueList;     //子柱列表（即一个柱子，因为一个柱子可分为多个子柱）
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < xValues.length; ++i) {
            subcolumnValueList = new ArrayList<>();
            subcolumnValueList.add(new SubcolumnValue(yValues[i], ChartUtils.pickColor()));
            Column column = new Column(subcolumnValueList);
            column.setHasLabels(true);                    //设置列标签
            //            column.setHasLabelsOnlyForSelected(true);       //只有当点击时才显示列标签
            columnList.add(column);
            //设置坐标值
            axisValues.add(new AxisValue(i).setLabel(xValues[i]));
        }

        mColumnChartData = new ColumnChartData(columnList);               //设置数据



       /*===== 坐标轴相关设置 =====*/
        Axis axisX = new Axis(axisValues); //将自定义x轴显示值传入构造函数
        Axis axisY = new Axis().setHasLines(true); //setHasLines是设置线条
        axisX.setName("距离");    //设置横轴名称
        axisY.setName("速度");    //设置竖轴名称
        mColumnChartData.setAxisXBottom(axisX); //设置横轴
        mColumnChartData.setAxisYLeft(axisY);   //设置竖轴

        //以上所有设置的数据、坐标配置都已存放到mColumnChartData中，接下来给mColumnChartView设置这些配置
        mColumnChartView.setColumnChartData(mColumnChartData);




        /*===== 设置竖轴最大值 =====*/
        //法一：
        Viewport v = mColumnChartView.getMaximumViewport();
        v.top = 103;
        mColumnChartView.setCurrentViewport(v);
         /*法二：
         Viewport v = mColumnChartView.getCurrentViewport();
         v.top = 100;
         mColumnChartView.setMaximumViewport(v);
         mColumnChartView.setCurrentViewport(v);*/
    }
    /**
     * 折线图X 轴的显示
     */
    private void getAxisXLables(){
        for (int i = 0; i < date.length; i++)
        {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }
    /*
       折线图的每个点的显示
      */
    private void getAxisPoints(){
        for (int i = 0; i < bupin.length; i++)
        {
            mPointValues.add(new PointValue(i, bupin[i]));
        }
    }



/*     private class ValueTouchListener implements ColumnChartOnValueSelectListener {


        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            Toast.makeText(ChartActivity.this, xValues[columnIndex]+"成绩 : " +
                    (int)value.getValue(),Toast.LENGTH_SHORT).show();
        }

       @Override
        public void onValueDeselected() {
        }

    }*/
}


