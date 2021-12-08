/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hec.data.Parameter;
import hec.model.OutputVariable;
import hec2.model.DataLocation;
import hec2.wat.model.tracking.OutputVariableImpl;
import org.jdom.Element;



/**
 *
 * @author Q0HECWPL
 */
public class DurationOutputVariable{
    private Integer _duration;
    private Double _threshold;
    private boolean _durationInDays;
    private DurationComputeTypes _computeType;
    private DataLocation _location;
    private String _DataLocationName;
    private final OutputVariable _outputVariable;
    public DataLocation getLocation(){
        return _location;
    }
    public Integer getDuration(){
        return _duration;
    }
    public Double getThreshold(){
        return _threshold;
    }
    public boolean durationInDays(){
        return _durationInDays;
    }
    public DurationComputeTypes getComputeType(){
        return _computeType;
    }
    public OutputVariable getOutputVariable(){
        return _outputVariable;
    }
    public DurationOutputVariable(DataLocation location, Integer duration, boolean durationInDays, DurationComputeTypes computeType){
        _location = location;
        _threshold = 0.0;
        _duration = duration;
        _durationInDays = durationInDays;
        _computeType = computeType;
        //initailize the output variable
        _outputVariable = new OutputVariableImpl();
        ((OutputVariableImpl)_outputVariable).setName(createName());
        ((OutputVariableImpl)_outputVariable).setDescription(createDescription());
        if(_location.getParameter().equals("Flow")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_FLOW);
        }else if(_location.getParameter().equals("FLOW-IN")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_FLOW);
        }else if(_location.getParameter().equals("Flow-Unreg")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_FLOW);
        }else if(_location.getParameter().equals("FLOW-OUT")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_FLOW);
        }else if(_location.getParameter().equals("ELEV")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_ELEV);
        }else{
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.UNDEF_PARAMETER_ID);
        }
    }
        public DurationOutputVariable(DataLocation location, Double Threshold, DurationComputeTypes computeType){
        _location = location;
        _threshold = Threshold;
        _duration = 1;
        _durationInDays = false;
        _computeType = computeType;
        //initailize the output variable
        _outputVariable = new OutputVariableImpl();
        ((OutputVariableImpl)_outputVariable).setName(createName());
        ((OutputVariableImpl)_outputVariable).setDescription(createDescription());
        if(_location.getParameter().equals("Flow")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_FLOW);
        }else if(_location.getParameter().equals("FLOW-IN")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_FLOW);
        }else if(_location.getParameter().equals("Flow-Unreg")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_FLOW);
        }else if(_location.getParameter().equals("FLOW-OUT")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_FLOW);
        }else if(_location.getParameter().equals("ELEV")){
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.PARAMID_ELEV);
        }else{
            ((OutputVariableImpl)_outputVariable).setParamId(Parameter.UNDEF_PARAMETER_ID);
        }
    }
    public boolean compareToName(String name){
        return createName().equals(name);
    }
    private String createName(){
        String hoursOrDays = "Day";
        if(!durationInDays()){hoursOrDays = "Hour";}
        String computeId = getDuration() + " " + hoursOrDays + " " + getComputeType();
        if(getComputeType()==DurationComputeTypes.TimeStepsOverThreshold){
            computeId = hoursOrDays + "s over threshold " + getThreshold().toString();
        }
        return getLocation().getName() + " - " + getLocation().getParameter() +  " - " + computeId;
    }
    private String createDescription(){
        String hoursOrDays = "Day";
        if(!durationInDays()){hoursOrDays = "Hour";}
        String computeId = getDuration() + " " + hoursOrDays + " " + getComputeType();
        if(getComputeType()==DurationComputeTypes.TimeStepsOverThreshold){
            computeId = hoursOrDays + "s over threshold " + getThreshold().toString();
            return computeId + " " + getLocation().getParameter() + " at " + getLocation().getName();
        }
        return computeId + " for " + getLocation().getName() + " and parameter type: " + getLocation().getParameter();
    }
    public Element writeToXML(){
        Element ele = new Element("DurationOutputVariable");
        if(_computeType==DurationComputeTypes.TimeStepsOverThreshold){
            ele.setAttribute("Threshold", getThreshold().toString());
        }else{
           ele.setAttribute("Duration", getDuration().toString());
           ele.setAttribute("DurationValueRepresentsDays", Boolean.toString(durationInDays()));           
        }

        ele.setAttribute("ComputeType", getComputeType().toString());
        return ele;
    }
    public static DurationOutputVariable readFromElement(Element ele, DataLocation loc){
        DurationComputeTypes computeType = DurationComputeTypes.valueOf(ele.getAttribute("ComputeType").getValue());
        if(computeType==DurationComputeTypes.TimeStepsOverThreshold){
            Double threshold = Double.parseDouble(ele.getAttribute("Threshold").getValue());
            return new DurationOutputVariable(loc,threshold,computeType);
        }else{
            Integer duration = Integer.parseInt(ele.getAttribute("Duration").getValue());
            boolean durationInDays = Boolean.parseBoolean(ele.getAttribute("DurationValueRepresentsDays").getValue());
            return new DurationOutputVariable(loc,duration,durationInDays,computeType);            
        }

    }
}
