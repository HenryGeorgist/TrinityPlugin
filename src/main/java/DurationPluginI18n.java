/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.rma.util.I18n;
import java.util.ResourceBundle;
/**
 *
 * @author WatPowerUser
 */
public class DurationPluginI18n extends I18n{
    public static final String BUNDLE_NAME = "DurationPluginProperties";
    private static final ResourceBundle SAMPLE_RESOURCE_BUNDLE;
    private ResourceBundle _resourceBundle;
    static
    { 
            SAMPLE_RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    }
    protected DurationPluginI18n(String prefix, String bundleName)
    {
            super(prefix, bundleName);
    }
    public static I18n getI18n(String prefix)
    {
            return new DurationPluginI18n(prefix, BUNDLE_NAME);
    }
}
