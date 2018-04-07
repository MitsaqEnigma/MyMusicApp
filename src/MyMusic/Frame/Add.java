package MyMusic.Frame;

import MyMusic.Control.Audio;
import MyMusic.Control.OpenConnection;
import MyMusic.Control.Session;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class Add extends javax.swing.JDialog {

    static ArrayList<Audio> list;
    private ImageIcon imgi;
    private DefaultTableModel table;
    private TableColumn tablecolumn;
    private DefaultTableCellRenderer tablerender;

    Audio music;

    private final Connection C;
    private Session session;
    private Statement S;
    private PreparedStatement PS;
    private ResultSet RS;

    public Add(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        C = OpenConnection.getConnection();
        setBtnIco();
    }

    public Add(java.awt.Frame parent, boolean modal, Session session) {
        super(parent, modal);
        initComponents();
        C = OpenConnection.getConnection();
        setBtnIco();
        list = new ArrayList<>();
        this.session = session;
    }

    public final void setBtnIco() {
        try {
            btnSave.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/009-diskette.png")).getScaledInstance(btnSave.getWidth(), btnSave.getHeight(), Image.SCALE_SMOOTH)));
            btnAdd.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/005-plus.png")).getScaledInstance(btnSave.getWidth(), btnSave.getHeight(), Image.SCALE_SMOOTH)));
            btnRemove.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/003-minus.png")).getScaledInstance(btnSave.getWidth(), btnSave.getHeight(), Image.SCALE_SMOOTH)));
            Cover.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/004-avatar.png")).getScaledInstance(Cover.getWidth() - 32, Cover.getHeight() - 32, Image.SCALE_SMOOTH)));
            this.setIconImage(ImageIO.read(getClass().getResource("/MyMusic/Img/004-music-player-2.png")).getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void showList() {
        table = TableModel();
        tablerender = RenderTable();
        table.addColumn("#");
        table.addColumn("Title");
        for (int i = 0; i < list.size(); i++) {
            music = list.get(i);
            table.addRow(new Object[]{music.getTrack(), music.getTitle()});
        }
        tblMusic.setModel(table);
        tblMusic.getColumnModel().getColumn(0).setCellRenderer(tablerender);
        tablecolumn = ColumnTable(0, 40, tblMusic);
        tblMusic.getTableHeader().setResizingColumn(tablecolumn);
        music = null;
    }

    public final int getID() {
        int id = 0;
        try {
            S = C.createStatement();
            String sql = "select umusicid from offline_songs order by umusicid asc";
            RS = S.executeQuery(sql);
            while (RS.next()) {
                id = Integer.parseInt(RS.getString("umusicid")) + 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", 0);
        }
        return id;
    }

    public void savePre() throws SQLException {
        PS = null;
        int i;
        for (i = 0; i < list.size(); i++) {
            music = list.get(i);
            try {
                String sql = "insert into offline_songs values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PS = C.prepareStatement(sql);
                PS.setInt(1, getID());
                PS.setString(2, session.getUsername());
                PS.setString(3, session.getUsername());
                PS.setString(4, music.getPath());
                PS.setString(5, music.getTitle());
                PS.setString(6, music.getArtist());
                PS.setString(7, music.getAlbum());
                PS.setString(8, music.getGenre());
                PS.setString(9, music.getComposer());
                PS.setInt(10, music.getTrack());
                PS.setInt(11, music.getYear());
                PS.setString(12, music.getLabel());
                PS.setBinaryStream(13, music.getImageOnBinary(), music.getImageSize());
                PS.setString(14, music.getDescription());
                PS.setString(15, music.getDescription());
                PS.executeUpdate();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.toString(), "ERROR", 0);
            } finally {
                PS.close();
                music = null;
            }
        }
        JOptionPane.showMessageDialog(this, Integer.toString(i) + " music has been saved", "Information", 1);
        list = new ArrayList<>();
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

        btnAdd = new javax.swing.JLabel();
        btnSave = new javax.swing.JLabel();
        Cover = new javax.swing.JLabel();
        lblAlbum = new java.awt.Label();
        lblArtist = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMusic = new javax.swing.JTable();
        btnRemove = new javax.swing.JLabel();
        lblGenre = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add your own music");
        setResizable(false);

        btnAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdd.setToolTipText("Add music and information");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setPreferredSize(new java.awt.Dimension(20, 20));
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnSave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSave.setToolTipText("Save your recent playlist");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setPreferredSize(new java.awt.Dimension(64, 20));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });

        Cover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cover.setFocusable(false);
        Cover.setRequestFocusEnabled(false);

        lblAlbum.setText("Album Title");

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
        btnRemove.setToolTipText("Remove song from playlist");
        btnRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemove.setPreferredSize(new java.awt.Dimension(20, 20));
        btnRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoveMouseClicked(evt);
            }
        });

        lblGenre.setText("Genre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(368, 368, 368))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cover, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArtist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(28, 28, 28)
                        .addComponent(lblAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblArtist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        Metafile metafile = new Metafile(new Home(rootPaneCheckingEnabled), rootPaneCheckingEnabled);
        metafile.isInsert(true);
        metafile.setLocationRelativeTo(new Home(rootPaneCheckingEnabled));
        metafile.setVisible(true);
        showList();
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        try {
            savePre();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered

    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited

    }//GEN-LAST:event_btnSaveMouseExited

    private void tblMusicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMusicMouseClicked
        music = list.get(tblMusic.getSelectedRow());
        try {
            imgi = new ImageIcon(music.getArtwork().getScaledInstance(Cover.getWidth(), Cover.getHeight(), java.awt.Image.SCALE_SMOOTH));
            Cover.setIcon(imgi);
            lblAlbum.setText(music.getAlbum());
            lblArtist.setText(music.getArtist());
            lblGenre.setText(music.getGenre());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", 0);
        }
    }//GEN-LAST:event_tblMusicMouseClicked

    private void btnRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveMouseClicked
        try {
            Cover.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/002-avatar-1.png")).getScaledInstance(Cover.getWidth() - 32, Cover.getHeight() - 32, Image.SCALE_SMOOTH)));
            lblAlbum.setText("Album Title");
            lblArtist.setText("Album Artist");
            lblGenre.setText("Genre");
            list.remove(music);
            showList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "ERROR", 0);
        } finally {
            music = null;
        }
    }//GEN-LAST:event_btnRemoveMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add dialog = new Add(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btnRemove;
    private javax.swing.JLabel btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label lblAlbum;
    private java.awt.Label lblArtist;
    private java.awt.Label lblGenre;
    private javax.swing.JTable tblMusic;
    // End of variables declaration//GEN-END:variables
}
