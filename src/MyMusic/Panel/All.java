package MyMusic.Panel;

import MyMusic.Control.*;
import MyMusic.Frame.Info;
import MyMusic.Frame.Store;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class All extends javax.swing.JPanel {

    private final Connection C;

    DefaultListModel list;
    AudioStream audios;

    ArrayList<Audio> playlist;
    Session session;
    Audio music;
    Retrieve retrieve;
    Buy buy;
    Info info;

    public All() {
        initComponents();
        C = OpenConnection.getConnection();
        buy = new Buy();
        retrieve = new Retrieve();
        playlist = retrieve.getList();
        showList();
    }

    public All(Session session) {
        initComponents();
        setIcon();
        C = OpenConnection.getConnection();
        this.session = session;
        buy = new Buy();
        retrieve = new Retrieve();
        playlist = retrieve.getList();
        showList();
    }
    
    //experimental
    public All(Session session, String param) {
        initComponents();
        setIcon();
        C = OpenConnection.getConnection();
        this.session = session;
        buy = new Buy();
        retrieve = new Retrieve();
        playlist = retrieve.getList(param);
        showList();
    }

    public final void setIcon() {
        try {
            artwork.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/004-avatar.png")).getScaledInstance(154, 154, java.awt.Image.SCALE_SMOOTH)));
            btnPlay.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/001-play-button.png")).getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
            btnGet.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/003-download.png")).getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
            btnInfo.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/006-itunes.png")).getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void showList() {
        list = new DefaultListModel();
        for (int i = 0; i < playlist.size(); i++) {
            music = playlist.get(i);
            list.add(i, music.getTitle());
        }
        musicList.setModel(list);
        music = null;
    }

    private void setInfo(Audio music) {
        artwork.setIcon(new ImageIcon(music.getArtwork().getScaledInstance(artwork.getWidth(), artwork.getHeight(), java.awt.Image.SCALE_SMOOTH)));
        title.setText(music.getTitle());
        artist.setText(music.getArtist());
        album.setText(music.getAlbum());
        label.setText(music.getLabel());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInfo = new javax.swing.JPanel();
        artwork = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        artist = new javax.swing.JLabel();
        album = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        btnInfo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        musicList = new javax.swing.JList<>();
        btnGet = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();

        panelInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        artwork.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        title.setText("Title");

        artist.setText("Artist");

        album.setText("Album");

        label.setText("Label");

        btnInfo.setText("More Information >");
        btnInfo.setContentAreaFilled(false);
        btnInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInfoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artwork, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(artist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(album, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(153, Short.MAX_VALUE))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInfo))))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(artist)
                .addGap(18, 18, 18)
                .addComponent(album)
                .addGap(18, 18, 18)
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnInfo))
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artwork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setText("Songs");

        musicList.setOpaque(false);
        musicList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                musicListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(musicList);

        btnGet.setText("Get");
        btnGet.setOpaque(false);
        btnGet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetActionPerformed(evt);
            }
        });

        btnPlay.setText("Play");
        btnPlay.setOpaque(false);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel1))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPlay)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void musicListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicListMouseClicked
        music = playlist.get(musicList.getSelectedIndex());
        setInfo(music);
    }//GEN-LAST:event_musicListMouseClicked

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        audios = music.audioStream(audios);
        AudioPlayer.player.start(audios);
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnGetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetActionPerformed
        buy.buyMusic(session, music);
    }//GEN-LAST:event_btnGetActionPerformed

    private void btnInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfoMouseClicked
        info = new Info(new Store(), true, music);
        info.setLocationRelativeTo(this);
        info.setVisible(true);
    }//GEN-LAST:event_btnInfoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel album;
    private javax.swing.JLabel artist;
    private javax.swing.JLabel artwork;
    private javax.swing.JButton btnGet;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnPlay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JList<String> musicList;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
