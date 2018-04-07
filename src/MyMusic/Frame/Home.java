package MyMusic.Frame;

import MyMusic.Control.Audio;
import MyMusic.Control.Retrieve;
import MyMusic.Control.Session;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Enigma
 */
public class Home extends javax.swing.JFrame implements ActionListener {

    private SignIn in;
    private Audio music;
    private Session session;
    private Info info;
    private DefaultTableModel table;
    private DefaultTableCellRenderer render;
    private TableColumn tablecolumn;
    private ArrayList<Audio> songs;

    private AudioStream audios;

    public Home() {
        initComponents();
        in = new SignIn(this, rootPaneCheckingEnabled);
        clearContent();
        Login();
    }

    public Home(boolean empty) {
        initComponents();
    }

    public final void setIcon() {
        try {
            Cover.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/004-avatar.png")).getScaledInstance(Cover.getWidth() - 16, Cover.getHeight() - 16, java.awt.Image.SCALE_SMOOTH)));
            btnPlay.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/001-play-button.png")).getScaledInstance(btnPlay.getHeight(), btnPlay.getHeight(), java.awt.Image.SCALE_SMOOTH)));
            btnInfo.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/006-chat.png")).getScaledInstance(btnInfo.getHeight(), btnInfo.getHeight(), java.awt.Image.SCALE_SMOOTH)));
            this.setIconImage(ImageIO.read(getClass().getResource("/MyMusic/Img/002-music-player-4.png")).getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public final void clearContent() {
        setIcon();
        this.setTitle("MyMusic App");
        Title.setText("Title");
        Artist.setText("Artist");
        Album.setText("Album");
        Genre.setText("Genre");
        Year.setText("Year");
    }

    public final void Login() {
        while (session == null) {
            in.setLocationRelativeTo(this);
            in.setVisible(true);
            session = in.getSession();
        }
        this.setTitle("MyMusic App | " + session.getName());
        showPlaylist();
        MenuLogin.setEnabled(false);
        MenuLogout.setEnabled(true);
    }

    public void showPlaylist() {
        songs = new ArrayList<>();
        Retrieve retrieve = new Retrieve();
        songs = retrieve.getList(session, true);
        showMusic();
    }

    public void showMusic() {
        table = TableModel();
        render = RenderTable();
        table.addColumn("#");
        table.addColumn("Music");
        for (int i = 0; i < songs.size(); i++) {
            Audio audio = songs.get(i);
            table.addRow(new Object[]{(i + 1), audio.getTitle()});
        }
        List.setModel(table);
        List.getColumnModel().getColumn(0).setCellRenderer(render);
        tablecolumn = ColumnTable(0, 40, List);
        List.getTableHeader().setResizingColumn(tablecolumn);
    }

    public TableColumn ColumnTable(int column, int width, JTable Tab) {
        TableColumn Table = Tab.getColumnModel().getColumn(column);
        Table.setWidth(width);
        return Table;
    }

    public DefaultTableModel TableModel() {
        DefaultTableModel Model = new DefaultTableModel();
        return Model;
    }

    public DefaultTableCellRenderer RenderTable() {
        DefaultTableCellRenderer Render = new DefaultTableCellRenderer();
        Render.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        return Render;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        Cover = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        List = new javax.swing.JTable();
        Title = new javax.swing.JLabel();
        Artist = new javax.swing.JLabel();
        Album = new javax.swing.JLabel();
        Genre = new javax.swing.JLabel();
        Year = new javax.swing.JLabel();
        panelControl = new javax.swing.JPanel();
        btnPlay = new javax.swing.JLabel();
        btnInfo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mainmenu = new javax.swing.JMenu();
        MenuLogin = new javax.swing.JMenuItem();
        MenuLogout = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MenuExit = new javax.swing.JMenuItem();
        menuFile = new javax.swing.JMenu();
        MenuAdd = new javax.swing.JMenuItem();
        MenuEdit = new javax.swing.JMenuItem();
        menuTools = new javax.swing.JMenu();
        MenuSearch = new javax.swing.JMenuItem();
        menuStore = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyMusic App");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        Cover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cover.setFocusable(false);
        Cover.setRequestFocusEnabled(false);

        List.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "#", "Songs"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        List.setRowHeight(30);
        List.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        List.setShowHorizontalLines(false);
        List.setShowVerticalLines(false);
        List.getTableHeader().setResizingAllowed(false);
        List.getTableHeader().setReorderingAllowed(false);
        List.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(List);

        Title.setText("Title");

        Artist.setText("Artist");

        Album.setText("Album");

        Genre.setText("Genre");

        Year.setText("Year");

        panelControl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelControl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPlay.setText("Play");
        btnPlay.setToolTipText("Play music");
        btnPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
        });
        panelControl.add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 70, 40));

        btnInfo.setText("Info");
        btnInfo.setToolTipText("Show any information relatated to that song");
        btnInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInfoMouseClicked(evt);
            }
        });
        panelControl.add(btnInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 70, 40));

        mainmenu.setText("Menu");

        MenuLogin.setText("Login");
        MenuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLoginActionPerformed(evt);
            }
        });
        mainmenu.add(MenuLogin);

        MenuLogout.setText("Logout");
        MenuLogout.setEnabled(false);
        MenuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLogoutActionPerformed(evt);
            }
        });
        mainmenu.add(MenuLogout);
        mainmenu.add(jSeparator1);

        MenuExit.setText("Close");
        MenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuExitActionPerformed(evt);
            }
        });
        mainmenu.add(MenuExit);

        jMenuBar1.add(mainmenu);

        menuFile.setText("File");

        MenuAdd.setText("Add");
        MenuAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAddActionPerformed(evt);
            }
        });
        menuFile.add(MenuAdd);

        MenuEdit.setText("Edit");
        MenuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuEditActionPerformed(evt);
            }
        });
        menuFile.add(MenuEdit);

        jMenuBar1.add(menuFile);

        menuTools.setText("Tools");

        MenuSearch.setText("Search");
        MenuSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSearchActionPerformed(evt);
            }
        });
        menuTools.add(MenuSearch);

        jMenuBar1.add(menuTools);

        menuStore.setText("Store");
        menuStore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuStoreMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuStore);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Cover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Title)
                    .addComponent(Artist)
                    .addComponent(Album)
                    .addComponent(Genre)
                    .addComponent(Year)
                    .addComponent(panelControl, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cover, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Title)
                        .addGap(12, 12, 12)
                        .addComponent(Artist)
                        .addGap(12, 12, 12)
                        .addComponent(Album)
                        .addGap(12, 12, 12)
                        .addComponent(Genre)
                        .addGap(12, 12, 12)
                        .addComponent(Year)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAddActionPerformed
        Login();
        Add add = new Add(this, rootPaneCheckingEnabled, session);
        add.setLocationRelativeTo(this);
        add.setVisible(true);
        showPlaylist();
    }//GEN-LAST:event_MenuAddActionPerformed

    private void MenuSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSearchActionPerformed
        Login();
        Search search = new Search(this, rootPaneCheckingEnabled, session);
        search.setLocationRelativeTo(this);
        search.setVisible(true);
        showPlaylist();
    }//GEN-LAST:event_MenuSearchActionPerformed

    private void MenuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLoginActionPerformed
        Login();
    }//GEN-LAST:event_MenuLoginActionPerformed

    private void MenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_MenuExitActionPerformed

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseClicked
        if (music != null) {
            audios = music.audioStream(audios);
            AudioPlayer.player.start(audios);
        } else {
            JOptionPane.showMessageDialog(this, "Select music from your library first", "Media Library error", 0);
        }
    }//GEN-LAST:event_btnPlayMouseClicked

    private void MenuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLogoutActionPerformed
        if (music != null && audios != null) {
            music.stopStream(audios);
            audios = null;
        }
        music = null;
        session = in.destroySession();
        clearContent();
        MenuLogin.setEnabled(true);
        MenuLogout.setEnabled(false);
        table = TableModel();
        render = RenderTable();
        table.addColumn("#");
        table.addColumn("Music");
        List.setModel(table);
        List.getColumnModel().getColumn(0).setCellRenderer(render);
        tablecolumn = ColumnTable(0, 40, List);
        List.getTableHeader().setResizingColumn(tablecolumn);
    }//GEN-LAST:event_MenuLogoutActionPerformed

    private void ListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListMouseClicked
        music = songs.get(List.getSelectedRow());
        try {
            Cover.setText(null);
            if (music.getArtwork() != null) {
                Cover.setIcon(new ImageIcon(music.getArtwork().getScaledInstance(Cover.getWidth(), Cover.getHeight(), Image.SCALE_SMOOTH)));
            } else {
                Cover.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/004-avatar.png")).getScaledInstance(Cover.getWidth() - 16, Cover.getHeight() - 16, java.awt.Image.SCALE_SMOOTH)));
            }
            Title.setText(music.getTitle());
            Album.setText(music.getAlbum());
            Artist.setText(music.getArtist());
            Genre.setText(music.getGenre());
            Year.setText(Integer.toString(music.getYear()));
            this.setIconImage(music.getArtwork().getScaledInstance(128, 128, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "ERROR", 0);
        }
    }//GEN-LAST:event_ListMouseClicked

    private void menuStoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuStoreMouseClicked
        Login();
        Store store = new Store(session);
        store.show();
        showPlaylist();
    }//GEN-LAST:event_menuStoreMouseClicked

    private void btnInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfoMouseClicked
        if (music != null) {
            info = new Info(this, rootPaneCheckingEnabled, music);
            info.setLocationRelativeTo(this);
            info.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Select music from your library first", "Media Library error", 0);
        }
    }//GEN-LAST:event_btnInfoMouseClicked

    private void MenuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEditActionPerformed
        Login();
        Editor edit = new Editor(this, rootPaneCheckingEnabled, session);
        edit.setLocationRelativeTo(this);
        edit.setVisible(true);
        showPlaylist();
    }//GEN-LAST:event_MenuEditActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        showPlaylist();
    }//GEN-LAST:event_formWindowGainedFocus

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Album;
    private javax.swing.JLabel Artist;
    private javax.swing.JLabel Cover;
    private javax.swing.JLabel Genre;
    private javax.swing.JTable List;
    private javax.swing.JMenuItem MenuAdd;
    private javax.swing.JMenuItem MenuEdit;
    private javax.swing.JMenuItem MenuExit;
    private javax.swing.JMenuItem MenuLogin;
    private javax.swing.JMenuItem MenuLogout;
    private javax.swing.JMenuItem MenuSearch;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Year;
    private javax.swing.JLabel btnInfo;
    private javax.swing.JLabel btnPlay;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu mainmenu;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuStore;
    private javax.swing.JMenu menuTools;
    private javax.swing.JPanel panelControl;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
