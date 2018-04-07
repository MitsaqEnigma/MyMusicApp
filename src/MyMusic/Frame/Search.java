package MyMusic.Frame;

import MyMusic.Control.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Search extends javax.swing.JDialog {

    private final Connection C;
    private DefaultTableModel table;

    private ArrayList<Audio> list = null;
    private Audio music = null;
    Session session;

    public Search(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        C = OpenConnection.getConnection();
    }

    public Search(java.awt.Frame parent, boolean modal, Session session) {
        super(parent, modal);
        initComponents();
        C = OpenConnection.getConnection();
        this.session = session;
        loadMusic("");
    }

    public void loadMusic(String param) {
        list = new ArrayList<>();
        String sql = "select * from offline_songs o, account a where a.userid=o.userid and "
                + "a.userid='" + session.getUsername() + "' order by o.utrack ";
        try {
            Statement S = C.createStatement();
            if (!param.equalsIgnoreCase("")) {
                sql = "select * from offline_songs o, account a where o.userid=a.userid and a.userid='" + session.getUsername() + "' and "
                        + "(o.utitle like '" + param + "%' or "
                        + "o.ualbum like '" + param + "%' or "
                        + "o.uartist like '" + param + "%') "
                        + "order by o.utrack ";//add some condition
            }
            ResultSet RS = S.executeQuery(sql);
            while (RS.next()) {
                music = new Audio();
                music.setTitle(RS.getString("o.utitle"));
                music.setAlbum(RS.getString("o.ualbum"));
                music.setArtist(RS.getString("o.uartist"));

                list.add(music);
            }
            RS.close();
            S.close();
        } catch (SQLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            music = null;
            showMusic();
        }
    }

    public void showMusic() {
        table = new DefaultTableModel();
        table.addColumn("Title");
        table.addColumn("Album");
        table.addColumn("Artist");
        for (int i = 0; i < list.size(); i++) {
            music = list.get(i);
            table.addRow(new Object[]{music.getTitle(), music.getAlbum(), music.getArtist()});
        }
        tblMusic.setModel(table);
        music = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TextSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMusic = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Search");

        TextSearch.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        TextSearch.setText("type Title, Album, Artist");
        TextSearch.setOpaque(false);
        TextSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TextSearchFocusLost(evt);
            }
        });
        TextSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextSearchMouseClicked(evt);
            }
        });
        TextSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextSearchKeyTyped(evt);
            }
        });

        tblMusic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title", "Album", "Artist"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMusic.setRowHeight(30);
        tblMusic.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblMusic);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextSearchKeyTyped
        loadMusic(TextSearch.getText());
    }//GEN-LAST:event_TextSearchKeyTyped

    private void TextSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextSearchMouseClicked
        TextSearch.selectAll();
    }//GEN-LAST:event_TextSearchMouseClicked

    private void TextSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextSearchFocusLost
        if(TextSearch.getText().equals("")){
            TextSearch.setText("type Title, Album, Artist");
        }
    }//GEN-LAST:event_TextSearchFocusLost

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Search dialog = new Search(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TextSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMusic;
    // End of variables declaration//GEN-END:variables
}
