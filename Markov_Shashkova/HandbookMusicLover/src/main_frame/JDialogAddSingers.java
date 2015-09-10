/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main_frame;

import java.awt.Color;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import main_compare.DateCompare;
import main_сlasses.Man;
import main_сlasses.MusicIndustryWorker;
import main_сlasses.Voice;

/**
 *
 * @author константин
 */
public class JDialogAddSingers extends javax.swing.JDialog {

/**
 * Creates new form JDialogAddSingers
 */
public JDialogAddSingers(java.awt.Frame parent, boolean modal,MusicIndustryWorker worker) {
    super(parent, modal);
    initComponents();
    this.worker=worker;
    this.getContentPane().setBackground(Color.white);
    this.jComboBoxSex.setBackground(Color.WHITE);
    this.jComboBoxVoice.setBackground(Color.white);
    this.initializeFields();
    this.changingComponents();
}
private void changingComponents()
{
    this.womenVoice.addElement("Сопрано");
    this.womenVoice.addElement("Контральто");
    this.menVoice.addElement("Бас");
    this.menVoice.addElement("Баритон");
    this.menVoice.addElement("Тенор");
}
private void initializeFields()
{
    this.pictureLabel=new ImageIcon("program/3.png");
   this.jLabelSurname.setIcon(this.pictureLabel);
   this.jLabelDate.setIcon(this.pictureLabel);

   this.jLabelName.setIcon(this.pictureLabel);
    this.jPopupMenu1=new JPopupMenu();
    this.womenVoice=new DefaultComboBoxModel();
    this.menVoice=new DefaultComboBoxModel();
}
/**
 * This method is called from within the constructor to initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is always
 * regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextFieldName = new javax.swing.JFormattedTextField();
        jButtonCancel = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        jFormattedTextFieldSurname = new javax.swing.JFormattedTextField();
        jLabelSurname = new javax.swing.JLabel();
        jFormattedTextFieldDate = new javax.swing.JFormattedTextField();
        jLabelDate = new javax.swing.JLabel();
        jComboBoxSex = new javax.swing.JComboBox();
        jComboBoxVoice = new javax.swing.JComboBox();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("main_frame/Bundle"); // NOI18N
        setTitle(bundle.getString("JDialogAddSingers.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(400, 400));
        setModal(true);
        setResizable(false);
        addWindowListener(formListener);
        getContentPane().setLayout(null);

        jFormattedTextFieldName.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)), bundle.getString("JDialogAddSingers.jFormattedTextFieldName.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP)); // NOI18N
        try {
            jFormattedTextFieldName.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("***************")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldName.setAutoscrolls(false);
        jFormattedTextFieldName.addKeyListener(formListener);
        getContentPane().add(jFormattedTextFieldName);
        jFormattedTextFieldName.setBounds(50, 10, 290, 38);

        jButtonCancel.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCancel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButtonCancel.setText(bundle.getString("JDialogAddSingers.jButtonCancel.text")); // NOI18N
        jButtonCancel.setToolTipText(bundle.getString("JDialogAddSingers.jButtonCancel.toolTipText")); // NOI18N
        jButtonCancel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jButtonCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonCancel.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButtonCancel.setFocusable(false);
        jButtonCancel.setName("exit"); // NOI18N
        jButtonCancel.setRolloverEnabled(false);
        jButtonCancel.addMouseListener(formListener);
        jButtonCancel.addActionListener(formListener);
        getContentPane().add(jButtonCancel);
        jButtonCancel.setBounds(240, 330, 130, 40);

        jButtonAdd.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAdd.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButtonAdd.setText(bundle.getString("JDialogAddSingers.jButtonAdd.text")); // NOI18N
        jButtonAdd.setToolTipText(bundle.getString("JDialogAddSingers.jButtonAdd.toolTipText")); // NOI18N
        jButtonAdd.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
        jButtonAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonAdd.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButtonAdd.setFocusable(false);
        jButtonAdd.setName("exit"); // NOI18N
        jButtonAdd.setRolloverEnabled(false);
        jButtonAdd.addMouseListener(formListener);
        jButtonAdd.addActionListener(formListener);
        getContentPane().add(jButtonAdd);
        jButtonAdd.setBounds(30, 330, 130, 40);

        jLabelName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelName.setIcon(new javax.swing.ImageIcon("C:\\Users\\константин\\Desktop\\3.png")); // NOI18N
        jLabelName.setText(bundle.getString("JDialogAddSingers.jLabelName.text")); // NOI18N
        jLabelName.addMouseListener(formListener);
        getContentPane().add(jLabelName);
        jLabelName.setBounds(340, 20, 80, 30);

        jFormattedTextFieldSurname.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)), bundle.getString("JDialogAddSingers.jFormattedTextFieldSurname.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP)); // NOI18N
        try {
            jFormattedTextFieldSurname.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("***************")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldSurname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldSurname.setAutoscrolls(false);
        jFormattedTextFieldSurname.addKeyListener(formListener);
        getContentPane().add(jFormattedTextFieldSurname);
        jFormattedTextFieldSurname.setBounds(50, 60, 290, 38);

        jLabelSurname.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSurname.setIcon(new javax.swing.ImageIcon("C:\\Users\\константин\\Desktop\\3.png")); // NOI18N
        jLabelSurname.setText(bundle.getString("JDialogAddSingers.jLabelSurname.text")); // NOI18N
        jLabelSurname.addMouseListener(formListener);
        getContentPane().add(jLabelSurname);
        jLabelSurname.setBounds(340, 70, 80, 30);

        jFormattedTextFieldDate.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)), bundle.getString("JDialogAddSingers.jFormattedTextFieldDate.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP)); // NOI18N
        try {
            jFormattedTextFieldDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**********")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldDate.setAutoscrolls(false);
        jFormattedTextFieldDate.setInheritsPopupMenu(true);
        jFormattedTextFieldDate.addKeyListener(formListener);
        getContentPane().add(jFormattedTextFieldDate);
        jFormattedTextFieldDate.setBounds(50, 110, 290, 38);

        jLabelDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDate.setIcon(new javax.swing.ImageIcon("C:\\Users\\константин\\Desktop\\3.png")); // NOI18N
        jLabelDate.setText(bundle.getString("JDialogAddSingers.jLabelDate.text")); // NOI18N
        jLabelDate.addMouseListener(formListener);
        getContentPane().add(jLabelDate);
        jLabelDate.setBounds(340, 120, 80, 30);

        jComboBoxSex.setBackground(new java.awt.Color(233, 233, 244));
        jComboBoxSex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Женский", "Мужской" }));
        jComboBoxSex.setToolTipText(bundle.getString("JDialogAddSingers.jComboBoxSex.toolTipText")); // NOI18N
        jComboBoxSex.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)), bundle.getString("JDialogAddSingers.jComboBoxSex.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP)); // NOI18N
        jComboBoxSex.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxSex.addActionListener(formListener);
        getContentPane().add(jComboBoxSex);
        jComboBoxSex.setBounds(50, 160, 290, 50);

        jComboBoxVoice.setBackground(new java.awt.Color(233, 233, 244));
        jComboBoxVoice.setToolTipText(bundle.getString("JDialogAddSingers.jComboBoxVoice.toolTipText")); // NOI18N
        jComboBoxVoice.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)), bundle.getString("JDialogAddSingers.jComboBoxVoice.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP)); // NOI18N
        jComboBoxVoice.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxVoice.addActionListener(formListener);
        getContentPane().add(jComboBoxVoice);
        jComboBoxVoice.setBounds(50, 230, 290, 50);

        pack();
        setLocationRelativeTo(null);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.KeyListener, java.awt.event.MouseListener, java.awt.event.WindowListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButtonCancel) {
                JDialogAddSingers.this.jButtonCancelActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonAdd) {
                JDialogAddSingers.this.jButtonAddActionPerformed(evt);
            }
            else if (evt.getSource() == jComboBoxSex) {
                JDialogAddSingers.this.jComboBoxSexActionPerformed(evt);
            }
            else if (evt.getSource() == jComboBoxVoice) {
                JDialogAddSingers.this.jComboBoxVoiceActionPerformed(evt);
            }
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == jFormattedTextFieldName) {
                JDialogAddSingers.this.jFormattedTextFieldNameKeyReleased(evt);
            }
            else if (evt.getSource() == jFormattedTextFieldSurname) {
                JDialogAddSingers.this.jFormattedTextFieldSurnameKeyReleased(evt);
            }
            else if (evt.getSource() == jFormattedTextFieldDate) {
                JDialogAddSingers.this.jFormattedTextFieldDateKeyReleased(evt);
            }
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jButtonCancel) {
                JDialogAddSingers.this.jButtonCancelMouseEntered(evt);
            }
            else if (evt.getSource() == jButtonAdd) {
                JDialogAddSingers.this.jButtonAddMouseEntered(evt);
            }
            else if (evt.getSource() == jLabelName) {
                JDialogAddSingers.this.jLabelNameMouseEntered(evt);
            }
            else if (evt.getSource() == jLabelSurname) {
                JDialogAddSingers.this.jLabelSurnameMouseEntered(evt);
            }
            else if (evt.getSource() == jLabelDate) {
                JDialogAddSingers.this.jLabelDateMouseEntered(evt);
            }
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jButtonCancel) {
                JDialogAddSingers.this.jButtonCancelMouseExited(evt);
            }
            else if (evt.getSource() == jButtonAdd) {
                JDialogAddSingers.this.jButtonAddMouseExited(evt);
            }
            else if (evt.getSource() == jLabelName) {
                JDialogAddSingers.this.jLabelNameMouseExited(evt);
            }
            else if (evt.getSource() == jLabelSurname) {
                JDialogAddSingers.this.jLabelSurnameMouseExited(evt);
            }
            else if (evt.getSource() == jLabelDate) {
                JDialogAddSingers.this.jLabelDateMouseExited(evt);
            }
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }

        public void windowActivated(java.awt.event.WindowEvent evt) {
        }

        public void windowClosed(java.awt.event.WindowEvent evt) {
        }

        public void windowClosing(java.awt.event.WindowEvent evt) {
        }

        public void windowDeactivated(java.awt.event.WindowEvent evt) {
        }

        public void windowDeiconified(java.awt.event.WindowEvent evt) {
        }

        public void windowIconified(java.awt.event.WindowEvent evt) {
        }

        public void windowOpened(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == JDialogAddSingers.this) {
                JDialogAddSingers.this.formWindowOpened(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextFieldNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldNameKeyReleased
        // TODO add your handling code here:
        if(this.jFormattedTextFieldName.getText().trim().equalsIgnoreCase("")==false)
        {
            this.workerAdd.setName(this.jFormattedTextFieldName.getText().trim());
            if(this.name==false)
            {
                this.jLabelName.setEnabled(false);
                this.jLabelName.setVisible(false);
                this.name=true;
                this.checkAllValues();
            }
        }
        else
        {
            if(this.name==true)
            {
                this.jLabelName.setEnabled(true);
                this.jLabelName.setVisible(true);
                this.name=false;
                this.checkAllValues();
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldNameKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        if(this.worker.equalsMusicIndustryWorker(new MusicIndustryWorker())==true)
        {
            this.workerAdd=new MusicIndustryWorker();
            this.name=false;
            this.surname=false;
            this.date=true;
            this.jButtonAdd.setEnabled(false);
            this.jLabelDate.setEnabled(false);
            this.jLabelDate.setVisible(false);
            this.jComboBoxVoice.setModel(this.womenVoice);
        }
        else
        {
            this.workerAdd=new MusicIndustryWorker(this.worker);
            this.name=true;
            this.surname=true;
            this.date=true;
            this.jLabelDate.setEnabled(false);
            this.jLabelDate.setVisible(false);
            this.jLabelName.setEnabled(false);
            this.jLabelName.setVisible(false);
            this.jLabelSurname.setEnabled(false);
            this.jLabelSurname.setVisible(false);
            this.jFormattedTextFieldName.setText(this.worker.getName());
            this.jFormattedTextFieldSurname.setText(this.worker.getSurname());
            if(new DateCompare().compare(this.worker.getDate(), "1.1.1")!=0)
                this.jFormattedTextFieldDate.setText(this.worker.getDate());
            if(this.worker.getSex()==Man.WOMAN)
            {
                 this.jComboBoxVoice.setModel(this.womenVoice);
                 if(this.worker.getVoice().equalsIgnoreCase(Voice.CONTRALTO)==true)
                     this.jComboBoxVoice.setSelectedIndex(1);
            }
            else
            {
                this.jComboBoxSex.setSelectedIndex(1);
                this.jComboBoxVoice.setModel(this.menVoice);
                if(this.worker.getVoice().equalsIgnoreCase(Voice.BARITONE)==true)
                     this.jComboBoxVoice.setSelectedIndex(1);
                else
                {
                    if(this.worker.getVoice().equalsIgnoreCase(Voice.TENOR)==true)
                        this.jComboBoxVoice.setSelectedIndex(2);
                }
            }
            this.jButtonAdd.setText("Изменить");
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButtonCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelMouseEntered
        // TODO add your handling code here:
        this.jButtonCancel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 255), new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_jButtonCancelMouseEntered

    private void jButtonCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelMouseExited
        // TODO add your handling code here:
        jButtonCancel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
    }//GEN-LAST:event_jButtonCancelMouseExited

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddMouseEntered
        // TODO add your handling code here:
        this.jButtonAdd.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 255), new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_jButtonAddMouseEntered

    private void jButtonAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddMouseExited
        // TODO add your handling code here:
        this.jButtonAdd.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51)));
    }//GEN-LAST:event_jButtonAddMouseExited

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
        if(this.worker.equalsMusicIndustryWorker(new MusicIndustryWorker())==true)
        {
            this.worker.setName(this.workerAdd.getName());
            this.worker.setSurname(this.workerAdd.getSurname());
            try {
                this.worker.setDate(this.workerAdd.getDate());
            } catch (ParseException ex) {
            } catch (IllegalArgumentException ex) {
            }
            this.worker.setSex(this.workerAdd.getSex());
            this.worker.setVoice(this.workerAdd.getVoice());
        }
        else
        {
            if(this.worker.equalsMusicIndustryWorker(this.workerAdd)==false)
            {
                this.worker.setName(this.workerAdd.getName());
                this.worker.setSurname(this.workerAdd.getSurname());
                try {
                    this.worker.setDate(this.workerAdd.getDate());
                } catch (ParseException ex) {
                } catch (IllegalArgumentException ex) {
                }
                this.worker.setSex(this.workerAdd.getSex());
                this.worker.setVoice(this.workerAdd.getVoice());
            }
        }
        this.setVisible(false);
        
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jLabelNameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNameMouseEntered
        // TODO add your handling code here:
        this.jPopupMenu1.removeAll();
        this.jPopupMenu1.add("Поле не может быть пустым.");
        this.jPopupMenu1.show(this.jFormattedTextFieldName,0, 12);
    }//GEN-LAST:event_jLabelNameMouseEntered

    private void jLabelNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNameMouseExited
        // TODO add your handling code here:
       this.jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_jLabelNameMouseExited

    private void jFormattedTextFieldSurnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldSurnameKeyReleased
        // TODO add your handling code here:
        if(this.jFormattedTextFieldSurname.getText().trim().equalsIgnoreCase("")==false)
        {
            this.workerAdd.setSurname(this.jFormattedTextFieldSurname.getText().trim());
            if(this.surname==false)
            {
                this.jLabelSurname.setEnabled(false);
                this.jLabelSurname.setVisible(false);
                this.surname=true;
                this.checkAllValues();
            }
        }
        else
        {
            if(this.surname==true)
            {
                this.jLabelSurname.setEnabled(true);
                this.jLabelSurname.setVisible(true);
                this.surname=false;
                this.checkAllValues();
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldSurnameKeyReleased

    private void jLabelSurnameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSurnameMouseEntered
        // TODO add your handling code here:
        this.jPopupMenu1.removeAll();
        this.jPopupMenu1.add("Поле не может быть пустым.");
        this.jPopupMenu1.show(this.jFormattedTextFieldSurname,0, 12);
    }//GEN-LAST:event_jLabelSurnameMouseEntered

    private void jLabelSurnameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSurnameMouseExited
        // TODO add your handling code here:
        this.jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_jLabelSurnameMouseExited

    private void jFormattedTextFieldDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDateKeyReleased

        boolean a=false;
        try
        {
            this.workerAdd.setDate(this.jFormattedTextFieldDate.getText().trim());
            if(this.date==false)
            {
                this.jLabelDate.setEnabled(false);
                this.jLabelDate.setVisible(false);
                this.date=true;
                this.checkAllValues();
            }
            if(new DateCompare().compare(this.workerAdd.getDate(), this.jFormattedTextFieldDate.getText().trim())!=0)
                this.jFormattedTextFieldDate.setText(this.workerAdd.getDate());
        }
          catch (ParseException ex) {
            a=true;
        } catch (IllegalArgumentException ex) {
            a=true;
        }
        if(a==true)
        {
            try
            {
                this.workerAdd.setDate("1.1.1");
                if(this.date==true&&this.jFormattedTextFieldDate.getText().trim().equalsIgnoreCase("")!=true)
                {
                    this.jLabelDate.setEnabled(true);
                    this.jLabelDate.setVisible(true);
                    this.date=false;
                    this.checkAllValues();
                }
                else
                {
                    if(this.jFormattedTextFieldDate.getText().trim().equalsIgnoreCase("")==true)
                    {
                       this.jLabelDate.setEnabled(false);
                       this.jLabelDate.setVisible(false);
                       this.date=true;
                       this.checkAllValues();
                    }
                }
            }
             catch (ParseException ex) {
            } catch (IllegalArgumentException ex) {
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldDateKeyReleased

    private void jLabelDateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDateMouseEntered
        // TODO add your handling code here:
        this.jPopupMenu1.removeAll();
        this.jPopupMenu1.add("Дата неправильно введена.");
        this.jPopupMenu1.show(this.jFormattedTextFieldDate,0, 12);
    }//GEN-LAST:event_jLabelDateMouseEntered

    private void jLabelDateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDateMouseExited
        // TODO add your handling code here:
         this.jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_jLabelDateMouseExited

    private void jComboBoxSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSexActionPerformed
        // TODO add your handling code here:
       String s=this.jComboBoxSex.getSelectedItem().toString().trim();
       char c;
       if(s.equalsIgnoreCase("Мужской")==true)
           c=Man.MAN;
       else
           c=Man.WOMAN;
       if(this.workerAdd.getSex()!=c)
       {
           if(c==Man.WOMAN)
           {
               this.jComboBoxVoice.setModel(this.womenVoice);
               this.jComboBoxVoice.setSelectedIndex(0);
               this.workerAdd.setSex(Man.WOMAN);
           }
           else
           {
               this.jComboBoxVoice.setModel(this.menVoice);
                this.jComboBoxVoice.setSelectedIndex(0);
               this.workerAdd.setSex(Man.MAN);
           }
       }
    }//GEN-LAST:event_jComboBoxSexActionPerformed

    private void jComboBoxVoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVoiceActionPerformed
        // TODO add your handling code here:
        String s=this.jComboBoxVoice.getSelectedItem().toString().trim();
        if(this.workerAdd.getSex()==Man.WOMAN)
        {
            if(s.equalsIgnoreCase("Сопрано")==true)
                this.workerAdd.setVoice(Voice.SOPRANO);
            else
            {
                    this.workerAdd.setVoice(Voice.CONTRALTO);
            }
        }
        else
        {
            if(s.equalsIgnoreCase("Бас")==true)
                this.workerAdd.setVoice(Voice.BASS);
            else
            {
                if(s.equalsIgnoreCase("Баритон")==true)
                    this.workerAdd.setVoice(Voice.BARITONE);
                else
                {
                    this.workerAdd.setVoice(Voice.TENOR);
                }
                    
            }
        }
    }//GEN-LAST:event_jComboBoxVoiceActionPerformed
protected void checkAllValues()
{
    boolean a=false;
    if(this.name==true&&this.date==true&&this.surname==true)
    {
        if(this.jButtonAdd.isEnabled()==false)
            this.jButtonAdd.setEnabled(true);
        a=true;
    }
    if(a==false)
    {
        if(this.jButtonAdd.isEnabled()==true)
            this.jButtonAdd.setEnabled(false);
    }
}
/**
 * @param args the command line arguments
 */
protected boolean name;//для проверки не пустое ли поле имя
protected boolean surname;//для проверки не пусто ли поля фамилия
protected boolean date;//для провекри правильно ли введена дата 
protected MusicIndustryWorker worker;//переменная хранящая информацию полученую из форму родителя
protected MusicIndustryWorker workerAdd;
protected JPopupMenu jPopupMenu1;  
protected DefaultComboBoxModel womenVoice;//спислк женских голосов
protected DefaultComboBoxModel menVoice;//список мужские голосов
protected ImageIcon pictureLabel;//картинка для надписи
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JComboBox jComboBoxSex;
    private javax.swing.JComboBox jComboBoxVoice;
    private javax.swing.JFormattedTextField jFormattedTextFieldDate;
    private javax.swing.JFormattedTextField jFormattedTextFieldName;
    private javax.swing.JFormattedTextField jFormattedTextFieldSurname;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelSurname;
    // End of variables declaration//GEN-END:variables
}
