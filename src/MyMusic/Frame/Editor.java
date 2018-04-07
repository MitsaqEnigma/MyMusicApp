package MyMusic.Frame;

import MyMusic.Control.Audio;
import MyMusic.Control.OpenConnection;
import MyMusic.Control.Retrieve;
import MyMusic.Control.Session;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Editor extends javax.swing.JDialog {

    static ArrayList<Audio> playlist;
    private BufferedImage bimage;
    private DefaultTableModel table;
    private TableColumn tablecolumn;
    private DefaultTableCellRenderer tablerender;

    Audio music;

    private Session session;
    private final Connection C;
    private Statement S;
    private ResultSet RS;

    public Editor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBtnIco();
        C = OpenConnection.getConnection();
    }

    public Editor(java.awt.Frame parent, boolean modal, Session session) {
        super(parent, modal);
        initComponents();
        setBtnIco();
        C = OpenConnection.getConnection();
        this.session = session;
        showPlaylist();
    }

    public final void setBtnIco() {
        try {
            btnEdit.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/010-menu.png")).getScaledInstance(btnEdit.getWidth(), btnEdit.getHeight(), Image.SCALE_SMOOTH)));
            btnRemove.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/003-minus.png")).getScaledInstance(btnEdit.getWidth(), btnEdit.getHeight(), Image.SCALE_SMOOTH)));
            Cover.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/004-avatar.png")).getScaledInstance(Cover.getWidth() - 32, Cover.getHeight() - 32, Image.SCALE_SMOOTH)));
            this.setIconImage(ImageIO.read(getClass().getResource("/MyMusic/Img/004-music-player-2.png")).getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.toString();
        }
    }

    public void showPlaylist() {
        playlist = new ArrayList<>();
        Retrieve retrieve = new Retrieve();
        playlist = retrieve.getList(session, false);
        showList();
    }

    public void showList() {

        table = TableModel();
        tablerender = RenderTable();
        table.addColumn("#");
        table.addColumn("Title");
        for (int i = 0; i < playlist.size(); i++) {
            music = playlist.get(i);
            table.addRow(new Object[]{music.getTrack(), music.getTitle()});
        }
        tblMusic.setModel(table);
        tblMusic.getColumnModel().getColumn(0).setCellRenderer(tablerender);
        tablecolumn = ColumnTable(0, 40, tblMusic);
        tblMusic.getTableHeader().setResizingColumn(tablecolumn);
        music = null;
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

        lblArtist = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMusic = new javax.swing.JTable();
        btnRemove = new javax.swing.JLabel();
        lblGenre = new java.awt.Label();
        btnAdd = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        Cover = new javax.swing.JLabel();
        lblAlbum = new java.awt.Label();
        lblYear = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit your library");
        setResizable(false);

        lblArtist.setText("Album Artist");

        tblMusic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null}
            },
            new String [] {
                "#", "Title"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMusic.setRowHeight(30);
        tblMusic.setShowHorizontalLines(false);
        tblMusic.setShowVerticalLines(false);
        tblMusic.getTableHeader().setResizingAllowed(false);
        tblMusic.getTableHeader().setReorderingAllowed(false);
        tblMusic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMusicMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMusic);

        btnRemove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRemove.setToolTipText("Delete music and information related to this");
        btnRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemove.setPreferredSize(new java.awt.Dimension(20, 20));
        btnRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoveMouseClicked(evt);
            }
        });

        lblGenre.setText("Genre");

        btnAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdd.setPreferredSize(new java.awt.Dimension(20, 20));

        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEdit.setToolTipText("Edit music information");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setPreferredSize(new java.awt.Dimension(64, 20));
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });

        Cover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cover.setFocusable(false);
        Cover.setRequestFocusEnabled(false);

        lblAlbum.setText("Album Title");

        lblYear.setText("Year");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(372, 372, 372))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cover, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArtist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cover, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblArtist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblMusicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMusicMouseClicked
        music = playlist.get(tblMusic.getSelectedRow());
        try {
            Cover.setText(null);
            Cover.setIcon(null);
            if (music.getArtwork() != null) {
                Cover.setIcon(new ImageIcon(music.getArtwork().getScaledInstance(Cover.getWidth(), Cover.getHeight(), Image.SCALE_SMOOTH)));
            }
            lblAlbum.setText(music.getAlbum());
            lblArtist.setText(music.getArtist());
            lblGenre.setText(music.getGenre());
            lblYear.setText(Integer.toString(music.getYear()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "ERROR", 0);
        }
    }//GEN-LAST:event_tblMusicMouseClicked

    private void btnRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveMouseClicked
        if (music != null) {
            try {
                S = C.createStatement();
                String sql = "delete from offline_songs where umusicid = '" + music.getID() + "'";
                S.executeUpdate(sql);
                S.close();
                playlist.remove(tblMusic.getSelectedRow());

                lblAlbum.setText("Album Title");
                lblArtist.setText("Album Artist");
                lblGenre.setText("Genre");
                lblYear.setText("Year");
                Cover.setIcon(null);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", 0);
            } finally {
                music = null;
                showPlaylist();
            }
        }
    }//GEN-LAST:event_btnRemoveMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        if (music != null) {
            Metafile metafile = new Metafile(new Home(rootPaneCheckingEnabled), rootPaneCheckingEnabled, music);
            metafile.isInsert(false);
            metafile.setLocationRelativeTo(this);
            metafile.setVisible(rootPaneCheckingEnabled);
            System.out.println("show playlist");
            showPlaylist();
        }
    }//GEN-LAST:event_btnEditMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Editor dialog = new Editor(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Cover;
    private javax.swing.JLabel btnAdd;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label lblAlbum;
    private java.awt.Label lblArtist;
    private java.awt.Label lblGenre;
    private java.awt.Label lblYear;
    private javax.swing.JTable tblMusic;
    // End of variables declaration//GEN-END:variables
}
