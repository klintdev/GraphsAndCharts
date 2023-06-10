package com.michaelgotsopoulos.graphsandcharts

import android.graphics.PointF
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.klintsoft.graphsandcharts.CurveStyle
import com.klintsoft.graphsandcharts.MultiXYGraph
import com.klintsoft.graphsandcharts.XYGraph
import com.klintsoft.graphsandcharts.ui.theme.GraphsAndChartsTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphsAndChartsTheme {
                ChartsAndGraphsExample()
            }
        }
    }
}

val xList = List(10){ (it * 0.041).toFloat() }
val yList = List(10) { ((it * (-2 * it ) + 21.5) ).toFloat() }
val points0 = xList.mapIndexed { index, it -> PointF(it, yList[index])  }


val yList1 = List(10) { (Random.nextInt(200) - 100 ).toFloat() }
val points1 = xList.mapIndexed { index, it -> PointF(it, yList1[index])  }

val yList2 = List(10) { (Random.nextInt(100) - 84 ).toFloat() }
val points2 = xList.mapIndexed { index, it -> PointF(it, yList2[index])  }

val graph = XYGraph(points = points0, pointColor = Color.Cyan, lineColor = Color.Cyan, description = "Blue line")
val graph1 = XYGraph(points = points1, pointColor = Color.Green, lineColor = Color.Green, pointSize = 4.dp, showPoints = false, curveStyle = CurveStyle.BICUBIC)
val graph2 = XYGraph(points = points2, pointColor = Color.Red, lineColor = Color.Red, pointSize = 3.dp, lineWidth = 10f, description = "Red data")

@Composable
fun ChartsAndGraphsExample() {
    Column {
        XYGraph(points0, modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .padding(4.dp),
            title = "XY graph",
            unitsX = "unitsX",
            unitsY = "unitsY",
            showLine = true,
            showPoints = true,
            pointSize = 4.dp,
            scaleTextStyle = MaterialTheme.typography.caption,
            lineWidth = 5f,
            backgroundColor = MaterialTheme.colors.background,
            labelX = "Label of X axis",
            labelY = "Label of Y axis"
        )
        MultiXYGraph(listOf(graph, graph1, graph2), modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .padding(4.dp)
            .border(2.dp, color = Color.Red, shape = RoundedCornerShape(3))
            .clip(RoundedCornerShape(3)),
            backgroundColor = Color.Black,
            scaleTextStyle = MaterialTheme.typography.caption,
            labelTextStyle = MaterialTheme.typography.caption,
            titleStyle = MaterialTheme.typography.caption,
            axisColor = Color.White,
            scaleColor = Color.White,
            title = "Multi XY Graph title",
            unitsX = "unitsX",
            unitsY = "unitsY",
            labelX = "X-axis",
            labelY = "Y-axis",
        )
    }
}
