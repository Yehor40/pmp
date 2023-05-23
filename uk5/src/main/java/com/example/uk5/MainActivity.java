package com.example.uk5;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements MainActivityInter {

    private EditText editTextHeight;
    private Button buttonSave;
    private ChartView chartView;
    private List<Integer> heightPoints;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_main, container, false);
//
//        editTextHeight = view.findViewById(R.id.editTextHeight);
//        buttonSave = view.findViewById(R.id.buttonSave);
//        chartView = view.findViewById(R.id.chartContainer);

        heightPoints = new ArrayList<>();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveHeightEntry();
            }
        });

       //return view;
       return null;
    }

    private void saveHeightEntry() {
        String heightText = editTextHeight.getText().toString().trim();
        if (!heightText.isEmpty()) {
            int height = Integer.parseInt(heightText);
            heightPoints.add(height);
            updateChart();
            editTextHeight.setText("");
        }
    }

    private void updateChart() {
        chartView.setChartData(heightPoints);
    }

    public static class ChartView extends View {
        private List<Integer> chartData;

        public ChartView(@NonNull Context context) {
            super(context);
            chartData = new ArrayList<>();
        }

        public void setChartData(List<Integer> data) {
            chartData = data;
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (chartData.size() > 0) {
                int width = getWidth();
                int height = getHeight();
                int pointCount = chartData.size();
                int stepSize = width / (pointCount - 1);

                Paint linePaint = new Paint();
                linePaint.setColor(Color.BLUE);
                linePaint.setStrokeWidth(5);

                Paint textPaint = new Paint();
                textPaint.setColor(Color.BLACK);
                textPaint.setTextSize(30);

                int previousX = 0;
                int previousY = height - chartData.get(0);

                for (int i = 1; i < pointCount; i++) {
                    int x = i * stepSize;
                    int y = height - chartData.get(i);

                    canvas.drawLine(previousX, previousY, x, y, linePaint);
                    canvas.drawText(chartData.get(i).toString(), x, y, textPaint);

                    previousX = x;
                    previousY = y;
                }
            }
        }
    }
}
