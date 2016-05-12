/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.jbake.projecttype;

import java.beans.PropertyChangeListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
public class JBakeProject implements Project {

    private final FileObject projectDir;
    private final ProjectState state;
    private Lookup lkp;

    JBakeProject(FileObject dir, ProjectState state) {
        this.projectDir = dir;
        this.state = state;
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    @Override
    public Lookup getLookup() {
        if (lkp == null) {
            lkp = Lookups.fixed(new Object[]{ // register your features here
                this,
                new Info(),
                new JBakeProjectLogicalView(this),
            });
        }
        return lkp;
    }

    private final class Info implements ProjectInformation {

        @StaticResource()
        public static final String CUSTOMER_ICON = "org/netbeans/jbake/projecttype/jbake_logo.png";

        @Override
        public Icon getIcon() {
            return new ImageIcon(ImageUtilities.loadImage(CUSTOMER_ICON));
        }

        @Override
        public String getName() {
            return getProjectDirectory().getName();
        }

        @Override
        public String getDisplayName() {
            return getName();
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public Project getProject() {
            return JBakeProject.this;
        }

       
    }

}
