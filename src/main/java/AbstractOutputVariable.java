/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hec2.model.DataLocation;
import java.util.ArrayList;
import org.jdom.Attribute;
import org.jdom.Element;

/**
 *
 * @author Q0HECWPL
 */
public abstract class AbstractOutputVariable implements IOutputVariable{
    protected DataLocation _dataLocation;
    protected ArrayList<String> _parameterNames;
    protected ArrayList<Double> _parameterValues;
    @Override
    public Element writeToXML() {
        int counter = 0;
        Element el = new Element("OutputVariable");//should this be class type?
        for(Double d : getParameterValues()){
            el.setAttribute(getParameters().get(counter), Double.toString(d));
            counter ++;
        }
        return el;
    }

    @Override
    public IOutputVariable readFromXML(Element ele, DataLocation dl) {
        _dataLocation = dl;
        _parameterNames = new ArrayList();
        _parameterValues = new ArrayList();
        for(Object a : ele.getAttributes()){
            Attribute aa = (Attribute)a;
            _parameterNames.add(aa.getName());
            _parameterValues.add(Double.parseDouble(aa.getValue()));
        } 
        //why do i have it returning an IOutputVariable but not mark it as static? what was I thinking?
        return null;
    }
    
}
