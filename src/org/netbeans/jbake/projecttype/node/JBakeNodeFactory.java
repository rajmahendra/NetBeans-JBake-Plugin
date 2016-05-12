/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.jbake.projecttype.node;

import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

/*
* @author Rajmahendra Hegde <rajmahendra@gmail.com


 */
@NodeFactory.Registration(projectType = "org-jbake-project", position = 10)
public class JBakeNodeFactory implements NodeFactory {

    @Override
    public NodeList createNodes(Project project) {
        try {
            return NodeFactorySupport.fixedNodeList(new AssetNode(project), new ContentNode(project), new TemplatesNode(project), new PropertyNode(project));
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return NodeFactorySupport.fixedNodeList();
    }
}
