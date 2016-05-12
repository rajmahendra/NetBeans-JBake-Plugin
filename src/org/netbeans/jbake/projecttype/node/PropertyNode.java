/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.jbake.projecttype.node;

import java.awt.Image;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */

public class PropertyNode extends FilterNode {

    @StaticResource
    private static final String IMAGE = "org/netbeans/jbake/projecttype/jbake_logo.png";

    public PropertyNode(Project proj) throws DataObjectNotFoundException {
        super(DataObject.find(proj.getProjectDirectory().getFileObject("jbake.properties")).getNodeDelegate());
    }

    @Override
    public String getDisplayName() {
        return "JBake Config";
    }
    
    
/*
    @Override
    public Image getIcon(int type) {
        DataFolder root = DataFolder.findFolder(FileUtil.getConfigRoot());
        Image original = root.getNodeDelegate().getIcon(type);
        return ImageUtilities.mergeImages(original, ImageUtilities.loadImage(IMAGE), 7, 7);
    }

    @Override
    public Image getOpenedIcon(int type) {
        DataFolder root = DataFolder.findFolder(FileUtil.getConfigRoot());
        Image original = root.getNodeDelegate().getIcon(type);
        return ImageUtilities.mergeImages(original, ImageUtilities.loadImage(IMAGE), 7, 7);
    }
*/
}
