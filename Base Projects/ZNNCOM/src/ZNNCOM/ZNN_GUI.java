/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZNNCOM;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import webservice.WSFuncs;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class ZNN_GUI extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public static File stopf;
    public static File news;
    public static File updatefile;
    
    public ZNN_GUI() {
        final ArrayList<String> aNews = new ArrayList<String>();
        initComponents();
        setTitle("ZNN APPLICATION");
        
        //Close Window using Close Button
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
        this.addWindowListener(new WindowAdapter() {   
        public void windowClosing(WindowEvent evt) {  
            closing();  
            dispose(); //pede para a janela fechar  
         }  
        });  
        
        //----Create News List----//
        //configurações para leitura xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        
        if(news.length()>0){
            ArrayList<Webservice> lsnews = (ArrayList<Webservice>) parser.fromXML(news);
            for(Webservice n:lsnews)
                aNews.add(n.getDescription());
        }
        
        jList1.setModel(new javax.swing.AbstractListModel() {
            public int getSize() { return aNews.size(); }
            public Object getElementAt(int i) { return aNews.get(i); }
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

        jToggleButton1 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Visualizar Notícia");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jList1ComponentAdded(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Resumo da Notícia");

        jLabel2.setText("Notícias");

        jButton4.setText("Atualizar lista de Notícias");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Fechar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //VariáveisControle
        String aux= "";
        
        //Funcionalidades (Serviços)
        Webservice service = new Webservice();
        
        //Weservices funções
        WSFuncs funcs = new WSFuncs();
        
        service = funcs.SearchServiceByIDXML(Integer.toString(jList1.getSelectedIndex()+1),news);

        if(service!=null){
            aux = funcs.fService(service, service.getarguments().get(0));
        }
        else
            System.out.printf("service not exist, choice other service.\n");

        if(aux != null)
            System.out.println(aux + "\n");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        final ArrayList<String> aNews = new ArrayList<String>();
        //Update Criteria of the Adapter
        try{       
            BufferedWriter outs = new BufferedWriter(new FileWriter(updatefile));
            outs.write("update");  
            outs.close();
        }catch (IOException e){}

        //Time to execute Adapter first time
        try {  
           Thread.sleep(2000);  //each getTime() seconds
        } catch (Exception e) {  
           e.printStackTrace();  
        }
        
        //----Update News List----//
        //configurações para leitura xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        
        if(news.length()>0){
            ArrayList<Webservice> lsnews = (ArrayList<Webservice>) parser.fromXML(news);
            for(Webservice n:lsnews)
                aNews.add(n.getDescription());
        }
        
        jList1.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() { return aNews.size(); }
            @Override
            public Object getElementAt(int i) { return aNews.get(i); }
        });
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        closing();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jList1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1ComponentAdded

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
                //VariáveisControle    
        String resumo = "";
        if(!(jList1.getSelectedValue()==null)){
            //configurações para leitura xml
            XStream parser = new XStream(new DomDriver());
            parser.setMode(XStream.NO_REFERENCES);

            if(news.length()>0){
                ArrayList<Webservice> lsnews = (ArrayList<Webservice>) parser.fromXML(news);
                for(Webservice n:lsnews){
                    if(n.getid()==jList1.getSelectedIndex()+1)
                        resumo = n.getarguments().get(1);
                }
            }
        }
        jTextField1.setText(resumo);        
    }//GEN-LAST:event_jList1ValueChanged

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        //VariáveisControle    
        String resumo = "";
        
        //configurações para leitura xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        
        if(news.length()>0){
            ArrayList<Webservice> lsnews = (ArrayList<Webservice>) parser.fromXML(news);
            for(Webservice n:lsnews){
                if(n.getid()==jList1.getSelectedIndex()+1)
                    resumo = n.getarguments().get(1);
            }
        }
        
        jTextField1.setText(resumo);  
    }//GEN-LAST:event_jList1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{ 
        
        //<editor-fold defaultstate="collapsed" desc=" head ">
        
        //ArquivosServiços
        File AllServices = new File("Services.xml");
        if (!AllServices.exists())
            AllServices.createNewFile();
        
        news = AllServices;

       //File used for Stop Criteria of the Adapter
        File sf = new File("StopFile.txt");
        sf.createNewFile();
        try{       
            BufferedWriter out = new BufferedWriter(new FileWriter(sf));
            out.write("");  
            out.flush();
            out.close();
        }catch (IOException e){}
        
        stopf = sf;
        
        //File used for Stop Criteria of the Adapter
        File uf = new File("UpdateFile.txt");
        uf.createNewFile();
        try{       
            BufferedWriter out = new BufferedWriter(new FileWriter(uf));
            out.write("");  
            out.flush();
            out.close();
        }catch (IOException e){}
        
        updatefile = uf;
        
        //Executing Adapter
        String[] commands = {"","",""};
        commands[0] = "java";
        commands[1] = "-jar";
        commands[2] = "AdapterZNNCOM_1.jar"; //Path od the GenericAdapterProgram.jar 
        Process p  = Runtime.getRuntime().exec(commands);
         
        //</editor-fold>
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ZNN_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ZNN_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ZNN_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ZNN_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                        //Time to execute Adapter first time
                try {  
                   Thread.sleep(4000);  //each getTime() seconds
                } catch (Exception e) {  
                   e.printStackTrace();  
                }
                new ZNN_GUI().setVisible(true);
            }
        });                      
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    //---Kill all process--//
    public void closing(){
        //Clean Services File
        try{
            BufferedWriter outm = new BufferedWriter(new FileWriter(news));
            outm.write("");  
            outm.flush();
            outm.close();
        }catch (IOException e){} 

        //Stop Criteria of the Adapter
        try{       
            BufferedWriter out = new BufferedWriter(new FileWriter(stopf));
            out.write("stop");  
            out.close();
        }catch (IOException e){} 

        System.exit(0);
    }
}
