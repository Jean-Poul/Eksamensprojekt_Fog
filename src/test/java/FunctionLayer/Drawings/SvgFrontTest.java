package FunctionLayer.Drawings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for SvgFront view
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class SvgFrontTest {

    SvgFront svgFront;

    @Before
    public void setUp() throws Exception {
        svgFront = new SvgFront(1);
    }

    @Test
    public void addCarportFrontView(){
        svgFront.addCarportFront();

        String expectedAddCarportFront = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"x=\"0\" y=\"0\" height=\"400\" width=\"550\" viewBox=\"0,0,600,600\" preserveAspectRatio=\"xMinYMin\"> <defs>\n" +
                "<marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
                "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                "</marker>\n" +
                "<marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
                "<path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                "</marker>\n" +
                "</defs><rect transform=\"translate(100,200)\" x=\"30.000000\" y=\"0.000000\" height=\"255.000000\" width=\"10.000000\" style=\"stroke:#000000; fill: #ffffff\" /><rect transform=\"translate(100,200)\" x=\"420.000000\" y=\"0.000000\" height=\"255.000000\" width=\"10.000000\" style=\"stroke:#000000; fill: #ffffff\" /><line transform=\"translate(100,200)\" x1=\"0.000000\" y1=\"5.000000\" x2=\"230.000000\" y2=\"-90.000000\" style=\"stroke-width:10; stroke:#000000; stroke-linecap:round\"/><line transform=\"translate(100,200)\" x1=\"230.000000\" y1=\"-90.000000\" x2=\"460.000000\" y2=\"5.000000\" style=\"stroke-width:10; stroke:#000000; stroke-linecap:round\"/><rect transform=\"translate(100,200)\" x=\"0.000000\" y=\"0.000000\" height=\"10.000000\" width=\"460.000000\" style=\"stroke:#000000; fill: #000000\" /><line transform=\"translate(100,200)\" x1=\"-30.000000\" y1=\"0.000000\" x2=\"-30.000000\" y2=\"255.000000\" style=\"stroke:#000000;\n" +
                "marker-start: url(#beginArrow);\n" +
                "marker-end: url(#endArrow);\" /><text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"-33.000000\" y=\"127.500000\"> 250 cm</text><line transform=\"translate(100,200)\" x1=\"230.000000\" y1=\"-90.000000\" x2=\"230.000000\" y2=\"0.000000\" style=\"stroke:#000000;\n" +
                "marker-start: url(#beginArrow);\n" +
                "marker-end: url(#endArrow);\" /><text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"90.000000\" y=\"-6.000000\"> 25 °</text><text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"360.000000\" y=\"-6.000000\"> 25 °</text><text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"227.000000\" y=\"-20.000000\"> 104 cm</text><text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"220.000000\" y=\"-57.500000\"> 130 °</text><line transform=\"translate(100,200)\" x1=\"0.000000\" y1=\"-30.000000\" x2=\"225.000000\" y2=\"-120.000000\" style=\"stroke:#000000;\n" +
                "marker-start: url(#beginArrow);\n" +
                "marker-end: url(#endArrow);\" /><text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"112.500000\" y=\"-90.000000\"> 225 cm</text><line transform=\"translate(100,200)\" x1=\"-30.000000\" y1=\"255.000000\" x2=\"480.000000\" y2=\"255.000000\" style=\"stroke:#000000;\" /><line transform=\"translate(100,200)\" x1=\"0.000000\" y1=\"285.000000\" x2=\"460.000000\" y2=\"285.000000\" style=\"stroke:#000000;\n" +
                "marker-start: url(#beginArrow);\n" +
                "marker-end: url(#endArrow);\" /><text transform=\"translate(100,200)\" style=\"text-anchor: middle\" x=\"225.000000\" y=\"280.000000\"> 450 cm</text></svg>";

        assertEquals(svgFront.toString(),expectedAddCarportFront);
    }
}