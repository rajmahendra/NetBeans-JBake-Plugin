/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.jbake.projecttype;

import java.awt.Image;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import javax.swing.Action;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
public class JBakeProjectLogicalView implements LogicalViewProvider {

    private final JBakeProject project;

    @StaticResource()
    public static final String JBAKE_ICON = "org/netbeans/jbake/projecttype/jbake_logo.png";

    public JBakeProjectLogicalView(JBakeProject project) {
        this.project = project;
    }

    @Override
    public Node createLogicalView() {
        try {
            FileObject projectDirectory = project.getProjectDirectory();
            DataFolder projectFolder = DataFolder.findFolder(projectDirectory);
            Node nodeOfProjectFolder = projectFolder.getNodeDelegate();
            return new ProjectNode(nodeOfProjectFolder, project);
        } catch (DataObjectNotFoundException donfe) {
            Exceptions.printStackTrace(donfe);
            return new AbstractNode(Children.LEAF);
        }
    }

    private final class ProjectNode extends FilterNode {

        final JBakeProject project;

        public ProjectNode(Node node, JBakeProject project) throws DataObjectNotFoundException {
            super(node,NodeFactorySupport.createCompositeChildren( project, "Projects/org-jbake-project/Nodes")
                  //  new FilterNode.Children(node)
                    , new ProxyLookup(new Lookup[]{Lookups.singleton(project), node.getLookup()}));
            this.project = project;
        }

        @Override
        public Action[] getActions(boolean arg0) {
            return new Action[]{
                CommonProjectActions.newFileAction(),
                CommonProjectActions.copyProjectAction(),
                CommonProjectActions.deleteProjectAction(),
                CommonProjectActions.renameProjectAction(),
                CommonProjectActions.customizeProjectAction(),
                CommonProjectActions.closeProjectAction()
            };
        }

        @Override
        public Image getIcon(int type) {
            return ImageUtilities.loadImage(JBAKE_ICON);
        }

        @Override
        public Image getOpenedIcon(int type) {
            return getIcon(type);
        }

        @Override
        public String getDisplayName() {
            return project.getProjectDirectory().getName();
        }
    }

    @Override
    public Node findPath(Node root, Object target) {
        return null;
    }
}
