/*
 *  eXist Open Source Native XML Database
 *  Copyright (C) 2001-2012 The eXist Project
 *  http://exist-db.org
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *  $Id$
 */
package org.exist.client.security;

import org.exist.client.DialogCompleteWithResponse;
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.exist.client.HighlightedTableCellRenderer;
import org.exist.security.EXistSchemaType;
import org.exist.security.Group;
import org.exist.security.internal.aider.GroupAider;
import org.exist.xmldb.UserManagementService;
import org.xmldb.api.base.XMLDBException;

/**
 *
 * @author Adam Retter <adam.retter@googlemail.com>
 */
public class GroupDialog extends javax.swing.JFrame {

    private static final long serialVersionUID = 2291775874309563932L;

    private final Pattern PTN_GROUPNAME = Pattern.compile("[a-zA-Z0-9\\-\\._]{3,}");
    
    private final UserManagementService userManagementService;
    private final String currentUser;
    
    private DefaultTableModel groupMembersTableModel = null;
    
    /**
     * Creates new form GroupDialog
     */
    public GroupDialog(final UserManagementService userManagementService, final String currentUser) {
        this.userManagementService = userManagementService;
        this.currentUser = currentUser;
        initComponents();
        tblGroupMembers.setDefaultRenderer(Object.class, new HighlightedTableCellRenderer());
        
        addSelfAsManager();
    }

    public UserManagementService getUserManagementService() {
        return userManagementService;
    }
    
    protected final DefaultTableModel getGroupMembersTableModel() {
        if(groupMembersTableModel == null) {
            groupMembersTableModel = new ReadOnlyDefaultTableModel(null, new String[] {
                "Username", "Group Manager"
            }){
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if(columnIndex == 1) {
                        return Boolean.class;
                    } else {
                        return super.getColumnClass(columnIndex);
                    }
                }
                
            };
        }
        
        return groupMembersTableModel;
    }
    
    protected void addSelfAsManager() {
        getGroupMembersTableModel().addRow(new Object[]{
            currentUser,
            Boolean.TRUE
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmGroupMembers = new javax.swing.JPopupMenu();
        miAddGroupMember = new javax.swing.JMenuItem();
        miCbGroupMemberManager = new javax.swing.JCheckBoxMenuItem();
        miRemoveGroupMember = new javax.swing.JMenuItem();
        lblGroupName = new javax.swing.JLabel();
        txtGroupName = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGroupMembers = new javax.swing.JTable();
        lblGroupMembers = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnCreate = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnAddMember = new javax.swing.JButton();

        miAddGroupMember.setText("Add Group Member...");
        miAddGroupMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAddGroupMemberActionPerformed(evt);
            }
        });
        pmGroupMembers.add(miAddGroupMember);

        miCbGroupMemberManager.setSelected(true);
        miCbGroupMemberManager.setText("Group Manager");
        miCbGroupMemberManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCbGroupMemberManagerActionPerformed(evt);
            }
        });
        pmGroupMembers.add(miCbGroupMemberManager);

        miRemoveGroupMember.setText("Remove Group Member");
        miRemoveGroupMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRemoveGroupMemberActionPerformed(evt);
            }
        });
        pmGroupMembers.add(miRemoveGroupMember);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Group");
        setPreferredSize(new java.awt.Dimension(446, 385));

        lblGroupName.setText("Group name:");

        txtGroupName.setInputVerifier(getGroupNameInputVerifier());

        lblDescription.setText("Description:");

        tblGroupMembers.setModel(getGroupMembersTableModel());
        tblGroupMembers.setAutoCreateRowSorter(true);
        tblGroupMembers.setComponentPopupMenu(pmGroupMembers);
        tblGroupMembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGroupMembersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGroupMembers);

        lblGroupMembers.setText("Group Members:");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnAddMember.setText("Add Group Member...");
        btnAddMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMemberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblGroupName)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDescription)
                                .addGap(21, 21, 21)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(txtGroupName))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblGroupMembers)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreate)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddMember)
                .addContainerGap(251, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGroupName)
                    .addComponent(txtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGroupMembers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnAddMember)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose)
                    .addComponent(btnCreate))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private InputVerifier getGroupNameInputVerifier() {
        return new RegExpInputVerifier(PTN_GROUPNAME);
    }
    
    private boolean isValidGroupName() {
        if(PTN_GROUPNAME.matcher(txtGroupName.getText()).matches()) {
            return true;
        }
        
        JOptionPane.showMessageDialog(this, "Group Name must be at least 3 characters (" + PTN_GROUPNAME.toString() + ")");
        return false;
    }
    
    private boolean isValidGroupDetails() {
        return isValidGroupName();
    }
    
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if(!isValidGroupDetails()) {
            return;
        }
        
        //create the user
        createGroup();
        
        //close the dialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnCreateActionPerformed

    protected void createGroup() {
        
        //1 - create the group
        Group group = null;
        try {
            final GroupAider groupAider = new GroupAider(txtGroupName.getText());
            groupAider.setMetadataValue(EXistSchemaType.DESCRIPTION, txtDescription.getText());
            getUserManagementService().addGroup(groupAider);
            
            //get the created group
            group = getUserManagementService().getGroup(txtGroupName.getText());
        } catch(final XMLDBException xmldbe) {
            JOptionPane.showMessageDialog(this, "Could not create group '" + txtGroupName.getText() + "': " + xmldbe.getMessage(), "Create Group Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //2 - add the users to the group and set managers
        for(int i = 0; i < getGroupMembersTableModel().getRowCount(); i++) {
            final String member = (String)getGroupMembersTableModel().getValueAt(i, 0);

            try {
                getUserManagementService().addAccountToGroup(member, group.getName());
                
                final boolean isManager = (Boolean)getGroupMembersTableModel().getValueAt(i, 1);
                if(isManager) {
                    getUserManagementService().addGroupManager(member, group.getName());
                }
            } catch(final XMLDBException xmldbe) {
                JOptionPane.showMessageDialog(this, "Could not add user '" + member + "' to group '" + group.getName() + "': " + xmldbe.getMessage(), "Create Group Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        setVisible(false);
        dispose();
    }
    
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    protected boolean canModifyGroupMembers() {
        return true;
    }
    
    protected boolean canModifySelectedGroupMember() {
        return tblGroupMembers.getSelectedRow() > -1;
    }
    
    private void tblGroupMembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGroupMembersMouseClicked
                
        miCbGroupMemberManager.setEnabled(canModifySelectedGroupMember());
        miCbGroupMemberManager.setSelected(isSelectedMemberManager());
        
        miRemoveGroupMember.setEnabled(canModifySelectedGroupMember());
    }//GEN-LAST:event_tblGroupMembersMouseClicked

    private void miCbGroupMemberManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCbGroupMemberManagerActionPerformed
        getGroupMembersTableModel().setValueAt(!isSelectedMemberManager(), tblGroupMembers.getSelectedRow(), 1);
    }//GEN-LAST:event_miCbGroupMemberManagerActionPerformed

    private void miRemoveGroupMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRemoveGroupMemberActionPerformed
        final int row = tblGroupMembers.getSelectedRow();
        getGroupMembersTableModel().removeRow(row);
    }//GEN-LAST:event_miRemoveGroupMemberActionPerformed

    private void btnAddMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMemberActionPerformed
        showFindUserForm();
    }//GEN-LAST:event_btnAddMemberActionPerformed

    private void miAddGroupMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddGroupMemberActionPerformed
        showFindUserForm();
    }//GEN-LAST:event_miAddGroupMemberActionPerformed

    protected String getSelectedMember() {
        final int row = tblGroupMembers.getSelectedRow();
        return (String)getGroupMembersTableModel().getValueAt(row, 0);
    }
    
    protected boolean isSelectedMemberManager() {
        final int row = tblGroupMembers.getSelectedRow();
        return (Boolean)getGroupMembersTableModel().getValueAt(row, 1);
    }
    
    private void showFindUserForm() {
        final DialogCompleteWithResponse<String> callback = new DialogCompleteWithResponse<String>() {
            @Override
            public void complete(final String username) {
               if(!groupMembersContains(username)) {
                   getGroupMembersTableModel().addRow(new Object[]{
                       username,
                       false
                   });
               }
            }
        };
        
        try {
            final FindUserForm findUserForm = new FindUserForm(getUserManagementService());
            findUserForm.addDialogCompleteWithResponseCallback(callback);
            findUserForm.setTitle("Add User to Group...");
            findUserForm.setVisible(true);
        } catch(final XMLDBException xmldbe) {
            JOptionPane.showMessageDialog(this, "Could not retrieve list of users: " + xmldbe.getMessage(), "Add Member Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    private boolean groupMembersContains(final String username) {
        for(int i = 0; i < getGroupMembersTableModel().getRowCount(); i++) {
          final String member = (String)getGroupMembersTableModel().getValueAt(i, 0);
          if(member.equals(username)) {
              return true;
          }
        }
        return false;
    }
    
    protected String getCurrentUser() {
        return currentUser;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnAddMember;
    private javax.swing.JButton btnClose;
    protected javax.swing.JButton btnCreate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblGroupMembers;
    private javax.swing.JLabel lblGroupName;
    protected javax.swing.JMenuItem miAddGroupMember;
    private javax.swing.JCheckBoxMenuItem miCbGroupMemberManager;
    private javax.swing.JMenuItem miRemoveGroupMember;
    private javax.swing.JPopupMenu pmGroupMembers;
    protected javax.swing.JTable tblGroupMembers;
    protected javax.swing.JTextField txtDescription;
    protected javax.swing.JTextField txtGroupName;
    // End of variables declaration//GEN-END:variables
}
