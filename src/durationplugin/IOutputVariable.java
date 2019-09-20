/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durationplugin;

import hec.io.TimeSeriesContainer;
import hec2.model.DataLocation;
import java.util.List;
import org.jdom.Element;

/**
 *
 * @author Q0HECWPL
 */
public interface IOutputVariable {
    public List<String> getParameters();
    public List<Double> getParameterValues();
    public DataLocation getDataLocation();
    public double Compute(TimeSeriesContainer input);//what if there are many output variables from the same data location (would this require multiple reads of the data?)
    public Element writeToXML();
    public IOutputVariable readFromXML(Element ele, DataLocation dl);
}
