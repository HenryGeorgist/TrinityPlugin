/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.rma.factories.NewObjectFactory;
import com.rma.factories.AbstractNewObjectFactory;
import com.rma.io.FileManagerImpl;
import com.rma.io.RmaFile;
import com.rma.model.Project;
import com.rma.ui.GenericNewObjectPanel;
import javax.swing.JComponent;
/**
 *
 * @author WatPowerUser
 */
public class DurationAlternativeFactory extends AbstractNewObjectFactory  implements NewObjectFactory{
    private DurationPlugin _plugin;
    public DurationAlternativeFactory(DurationPlugin plugin){
        super(DurationPluginI18n.getI18n(DurationPluginMessages.Bundle_Name));
        _plugin = plugin;
    }
    @Override
    public JComponent createNewObjectPanel() {
        GenericNewObjectPanel panel = new GenericNewObjectPanel();
        panel.setFileComponentsVisible(false);
        Project p = Project.getCurrentProject();
        panel.setName("");
        panel.setDescription("");
        panel.setExistingNamesList(_plugin.getAlternativeList());
        panel.setDirectory(p.getProjectDirectory() + RmaFile.separator + _plugin.getPluginDirectory());
        return panel;
    }
    @Override
    public Object createObject(JComponent jc) {
        GenericNewObjectPanel panel = (GenericNewObjectPanel) jc;
        DurationAlternative alt = new DurationAlternative();
        alt.setName(panel.getSelectedName());
        alt.setDescription(panel.getSelectedDescription());
        alt.setFile(FileManagerImpl.getFileManager().getFile(panel.getSelectedFile().getPath() + RmaFile.separator + alt.getName() + _plugin.getAltFileExtension()));
        alt.setProject(Project.getCurrentProject());
        _plugin.addAlternative(alt);
        _plugin.editAlternative(alt);
        alt.saveData();
        return alt;
    }
    @Override
    public JComponent createOpenObjectPanel() {
        return null;
    }
    @Override
    public void openObject(JComponent jc) { 
    }

}
